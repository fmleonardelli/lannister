package com.mercadolibre.lannister.charges.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum NotificationState {
    PENDING, PROCESSED, NOT_PROCESSED;
}
