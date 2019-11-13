package com.mercadolibre.lannister.charges.model;

import com.mercadolibre.lannister.charges.api.ValidationError;
import io.vavr.collection.List;
import io.vavr.control.Either;

public interface ChargeNotificationValidator {
    default Either<Throwable, Boolean> checkType(String type) {
        if (!List.of(ChargeType.values()).flatMap(ChargeType::getTypes).exists(t -> t.equalsIgnoreCase(type))){
            return Either.left(new ValidationError("Charge type not supported: " + type));
        }
        return Either.right(true);
    }
    default Either<Throwable, Boolean> checkCurrency(String currency) {
        if (!List.of(CurrencyType.values()).map(CurrencyType::getIdentifier).exists(c -> c.equalsIgnoreCase(currency))) {
            return Either.left(new ValidationError("Currency type not supported: " + currency));
        }
        return Either.right(true);
    }
    default Either<Throwable, Boolean> checkAmount(Double amount) {
        if (Double.compare(amount, 0d) != 1) {
            return Either.left(new ValidationError("Invalid amount: " + amount));
        }
        return Either.right(true);
    }
}
