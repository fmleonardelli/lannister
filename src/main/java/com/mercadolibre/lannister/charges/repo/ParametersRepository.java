package com.mercadolibre.lannister.charges.repo;

import io.vavr.collection.Map;
import io.vavr.control.Option;

public interface ParametersRepository {
    Option<Integer> limitParam();
    Option<Integer> offsetParam();
    Map<String, Object> toMapForRepo();
    default Integer limit() {
        return limitParam().getOrElse(100);
    }
    default Integer offset() {
        return offsetParam().getOrElse(0);
    }
}
