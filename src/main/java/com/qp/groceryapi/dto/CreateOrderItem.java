package com.qp.groceryapi.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderItem {

    @NotNull(message = "Item id must not be null")
    @NotBlank(message = "item id must not be blank")
    private String itemId;

    private Integer quantity;
}
