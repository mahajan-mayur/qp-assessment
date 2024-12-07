package com.qp.groceryapi.controller;

import com.qp.groceryapi.dto.CreateOrderRequest;
import com.qp.groceryapi.model.Item;
import com.qp.groceryapi.model.Order;
import com.qp.groceryapi.service.ItemService;
import com.qp.groceryapi.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final OrderService orderService;
    private final ItemService itemService;

    @GetMapping("/item")
    ResponseEntity<Page<Item>> getAllItems(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        Page<Item> items = itemService.getItems(pageNumber, pageSize);
        return ResponseEntity.ok().body(items);
    }

    @PostMapping("/order")
    ResponseEntity <Order> placeOrder( @Valid @RequestBody CreateOrderRequest createOrderRequest){

        Order savedOrder = orderService.placeOrder(createOrderRequest);
       return ResponseEntity.ok().body(savedOrder);
    }
}
