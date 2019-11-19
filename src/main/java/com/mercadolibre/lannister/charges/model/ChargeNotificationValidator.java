package com.mercadolibre.lannister.charges.model;

import com.mercadolibre.lannister.charges.api.ValidationError;
import io.vavr.collection.List;
import io.vavr.control.Either;

import java.math.BigDecimal;

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
    default Either<Throwable, Boolean> checkAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            return Either.right(true);
        }
        return Either.left(new ValidationError("Invalid amount: " + amount));
    }
}
