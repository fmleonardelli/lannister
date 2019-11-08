package com.mercadolibre.lannister.charges.model;

import io.vavr.collection.List;
import io.vavr.control.Either;

public interface ChargeNotificationValidator {
    default Either<Throwable, Boolean> checkType(String type) {
        if (!List.of(ChargeTypes.values()).flatMap(ChargeTypes::getTypes).exists(t -> t.equalsIgnoreCase(type))){
            return Either.left(new RuntimeException("Charge type not supported: " + type));
        }
        return Either.right(true);
    }
    default Either<Throwable, Boolean> checkCurrency(String currency) {
        if (!List.of(CurrencyTypes.values()).map(CurrencyTypes::getIdentifier).exists(c -> c.equalsIgnoreCase(currency))) {
            return Either.left(new RuntimeException("Currency type not supported: " + currency));
        }
        return Either.right(true);
    }
    default Either<Throwable, Boolean> checkAmount(Double amount) {
        if (Double.compare(amount, 0d) != 1) {
            return Either.left(new RuntimeException("Invalid amount: " + amount));
        }
        return Either.right(true);
    }
}
