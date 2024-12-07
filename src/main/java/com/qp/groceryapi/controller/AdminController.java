package com.qp.groceryapi.controller;

import com.qp.groceryapi.dto.UpdateStockRequest;
import com.qp.groceryapi.exception.BadRequestException;
import com.qp.groceryapi.model.Item;
import com.qp.groceryapi.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final ItemService itemService;

    @PostMapping("/item")
    ResponseEntity<Item> addItem(@RequestBody(required = true) Item item){
        log.info("adding new item item name: {}", item.getName());
        Item savedItem = itemService.addItem(item);

        return ResponseEntity.ok(savedItem);
    }



    @GetMapping("/item/{itemId}")
    ResponseEntity<Item> getItem(@PathVariable String itemId) {
        log.info("get item request itemId: {}", itemId);
        return ResponseEntity.ok().body(itemService.getItem(itemId));
    }

    @DeleteMapping("/item/{itemId}")
    ResponseEntity<?> deleteItem(@PathVariable String itemId) {
        log.info("delete item request itemId: {}", itemId);
        itemService.deleteItem(itemId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/item/{itemId}")
    ResponseEntity<Item> updateItem(
            @PathVariable String itemId,
            @RequestBody(required = true) Item item) {
        log.info("updating  item itemId: {}", itemId);
        if(item.getId() !=null && !Objects.equals(itemId, item.getId())){
            log.info("item id mismatch in request");
            throw new BadRequestException("item id mismatch");
        }
        Item savedItem = itemService.updateItem(itemId, item);

        return ResponseEntity.ok(savedItem);
    }

    @PatchMapping("/item/{itemId}/update-stock")
    ResponseEntity<Item> updateItemStock(
            @PathVariable String itemId,
            @RequestBody UpdateStockRequest updateStockRequest) {
        itemService.updateItemStock(itemId, updateStockRequest.getStock());
        return ResponseEntity.ok().build();
    }




}
