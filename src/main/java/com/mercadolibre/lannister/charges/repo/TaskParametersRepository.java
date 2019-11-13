package com.mercadolibre.lannister.charges.repo;

import com.mercadolibre.lannister.charges.model.NotificationState;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.control.Option;
import lombok.Value;
import lombok.val;
import org.bson.Document;

@Value
public class TaskParametersRepository implements ParametersRepository {
    public Option<Integer> limitParam() {
        return Option.of(300);
    }
    public Option<Integer> offsetParam() {
        return Option.none();
    }
    public Map<String, Object> toMapForRepo() {
        val query = new Document("$in", List.of(NotificationState.values()).filter(s -> !s.equals(NotificationState.PROCESSED)).map(Enum::toString).toJavaList());
        return  io.vavr.collection.HashMap.of("state", query);
    }
}
