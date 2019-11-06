package com.mercadolibre.lannister.charges.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.lannister.charges.model.ChargeNotification;
import com.mongodb.MongoClient;
import com.mongodb.client.model.IndexOptions;
import io.vavr.Function1;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.bson.Document;
import org.mongojack.JacksonMongoCollection;

import java.util.HashMap;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class NotificationRepository {

    JacksonMongoCollection<ChargeNotification> collection;
    ObjectMapper objectMapper;

     public NotificationRepository(MongoClient client, String databaseName, String collectionName, ObjectMapper mapper) {
         val mongoCollection = client.getDatabase(databaseName).getCollection(collectionName);
         JacksonMongoCollection.JacksonMongoCollectionBuilder<ChargeNotification> builder = JacksonMongoCollection.builder();
         this.collection = builder.withObjectMapper(mapper).build(mongoCollection, ChargeNotification.class);
         this.objectMapper = mapper;

         this.collection.createIndex(new Document("type", 1));
         this.collection.createIndex(new Document("eventId", 1), new IndexOptions().unique(true));
         this.collection.createIndex(new Document("state", 1));
         this.collection.createIndex(new Document("date", 1));
         this.collection.createIndex(new Document("processedDate", 1));
     }

     private Either<Throwable, ChargeNotification> insert(ChargeNotification notification) {
         try {
             collection.insert(notification);
             return Either.right(notification);
         } catch (Exception ex) {
             return Either.left(ex);
         }
     }

     public Either<Throwable, ChargeNotification> save(ChargeNotification notification) {
         return insert(notification);
     }

     public Either<Throwable, ChargeNotification> update(ChargeNotification notification) {
         val document = JacksonMongoCollection.convertToDocument(notification, this.objectMapper, ChargeNotification.class);
         document.remove("version");
         val update = new Document("$set", document);
         update.put("$inc", new Document("version", 1));

         val query = new HashMap<String, Object>();
         query.put("_id", notification.getEventId());
         query.put("version", notification.getVersion());

         RepositoryUpdate repo = () -> collection.findAndModify(new Document(query), new Document(), new Document(), collection.serializeFields(update), true, false);
         return Try.of(repo :: findAndModify).toEither();
     }

     public Either<Throwable, List<ChargeNotification>> findAll() {
         RepositoryFind<ChargeNotification> repo = collection::find;
         return Try.of(repo :: find).toEither().map(List::ofAll);
     }
}
