package com.mercadolibre.lannister.charges.repo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.lannister.charges.api.Paginated;
import com.mercadolibre.lannister.charges.model.ChargeNotification;
import com.mercadolibre.lannister.charges.repo.functions.RepositoryFind;
import com.mercadolibre.lannister.charges.repo.functions.RepositoryUpdate;
import com.mercadolibre.lannister.config.Repository;
import com.mongodb.MongoClient;
import com.mongodb.client.model.IndexOptions;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.bson.Document;
import org.mongojack.JacksonMongoCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.vavr.API.Left;
import static io.vavr.API.Right;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class NotificationRepository extends Repository<ChargeNotification> {

    Logger logger = LoggerFactory.getLogger(NotificationRepository.class);
    ObjectMapper objectMapper;

     public NotificationRepository(MongoClient client, String databaseName, String collectionName, ObjectMapper mapper) {
         val mongoCollection = client.getDatabase(databaseName).getCollection(collectionName);
         JacksonMongoCollection.JacksonMongoCollectionBuilder<ChargeNotification> builder = JacksonMongoCollection.builder();
         this.collection = builder.withObjectMapper(mapper).build(mongoCollection, ChargeNotification.class);
         this.objectMapper = mapper;

         this.collection.createIndex(new Document("type", 1));
         Map<String, Object> uniques = io.vavr.collection.HashMap.of("type", 1, "eventId", 1);
         this.collection.createIndex(new Document(uniques.toJavaMap()), new IndexOptions().unique(true));
         this.collection.createIndex(new Document("state", 1));
         this.collection.createIndex(new Document("date", 1));
         this.collection.createIndex(new Document("processedDate", 1));
     }

    public Either<Throwable, List<ChargeNotification>> findBy(ParametersRepository parameters) {
        RepositoryFind<ChargeNotification> repo = () -> collection.find(new Document(parameters.toMapForRepo().toJavaMap())).skip(parameters.offset() * parameters.limit()).limit(parameters.limit());
        return Try.of(repo :: find).toEither().map(List::ofAll);
    }

    public Either<Throwable, Paginated<ChargeNotification>> findByPaginated(ParametersRepository parameters) {
        return findBy(parameters).flatMap(l -> count(parameters).map(c -> new Paginated<>(l, parameters.offset(), parameters.limit(), (c % parameters.limit() == 0) ? c / parameters.limit() : (c / parameters.limit()) + 1 )));
    }

    public Either<Throwable, ChargeNotification> save(ChargeNotification notification) {
        logger.info("Save with params: " + notification.toString());
        return insert(notification);
    }

    public Either<Throwable, ChargeNotification> update(ChargeNotification notification) {
        val document = JacksonMongoCollection.convertToDocument(notification, this.objectMapper, ChargeNotification.class);
        Map<String, Object> query = io.vavr.collection.HashMap.of("type", notification.getType(), "eventId", notification.getEventId(), "version", notification.getVersion());

        RepositoryUpdate repo = () -> collection.findAndModify(new Document(query.toJavaMap()),
                new Document(),
                new Document(),
                collection.serializeFields(incVersion(document)),
                true,
                false);
        return Try.of(repo :: findAndModify).toEither();
     }

    Either<Throwable, Long> count(ParametersRepository parameters) {
        try {
            return Right(collection.getCount(new Document(parameters.toMapForRepo().toJavaMap())));
        } catch (Exception ex) {
            return Left(ex);
        }
    }
}
