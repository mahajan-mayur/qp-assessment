package com.qp.groceryapi.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ItemOutOfStockException extends RuntimeException {
    private final  String itemId;
    private final int stock;
    private final int requestedQuantity;
}
