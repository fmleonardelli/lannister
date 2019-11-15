package com.mercadolibre.lannister.charges.repo;

import com.mercadolibre.lannister.charges.api.ChargesParametersApi;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.Value;

import static io.vavr.API.Tuple;

@Value
@AllArgsConstructor
public class ChargesParametersRepository implements ParametersRepository {
    ChargesParametersApi parametersApi;

    public Option<Integer> limitParam() {
        return parametersApi.getLimit();
    }

    public Option<Integer> offsetParam() {
        return parametersApi.getOffset();
    }

    public Map<String, Object> toMapForRepo() {
        return List.of(
                parametersApi.getEventId().map(e -> Tuple("eventId", e)),
                parametersApi.getEventType().map(t -> Tuple("type", t)),
                parametersApi.getUserId().map(u -> Tuple("userId", u)),
                filterByDates("date", parametersApi.fromDate(), parametersApi.toDate())
        ).flatMap(x -> x.map(r -> r)).toMap(x -> x);
    }
}
