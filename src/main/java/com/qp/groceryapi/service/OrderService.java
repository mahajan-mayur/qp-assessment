package com.qp.groceryapi.service;

import com.qp.groceryapi.dto.CreateOrderItem;
import com.qp.groceryapi.dto.CreateOrderRequest;
import com.qp.groceryapi.exception.BadRequestException;
import com.qp.groceryapi.exception.ItemOutOfStockException;
import com.qp.groceryapi.model.Item;
import com.qp.groceryapi.model.Order;
import com.qp.groceryapi.model.OrderItem;
import com.qp.groceryapi.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.qp.groceryapi.repository.ItemRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;


    @Transactional
    public Order placeOrder(CreateOrderRequest createOrderRequest) {
        double totalPrice = 0d;
        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>(createOrderRequest.getItems().size());
        for (CreateOrderItem createOrderItem : createOrderRequest.getItems()) {
            Optional<Item> optionalItem = itemRepository.findById(createOrderItem.getItemId());

            if (optionalItem.isEmpty()) {
                log.info("invalid itemId: {}", createOrderItem.getItemId());
                throw new BadRequestException(String.format("Item with id : %s is invalid", createOrderItem.getItemId()));
            }
            Item item = optionalItem.get();
            if (item.getStock() < createOrderItem.getQuantity()) {
                log.info("requested item is out of stock itemId: {} stock: {} requestedQuantity: {}",
                        createOrderItem.getItemId(), item.getStock(), createOrderItem.getQuantity());
                throw new ItemOutOfStockException(createOrderItem.getItemId(), item.getStock(), createOrderItem.getQuantity());
            }

            item.setStock( item.getStock() - createOrderItem.getQuantity());
            OrderItem orderItem = new OrderItem(order, item, createOrderItem.getQuantity(), item.getPrice());
            orderItems.add(orderItem);
            totalPrice += orderItem.getItemPrice() * orderItem.getQuantity();
        }
        order.setOrderItemList(orderItems);
        order.setTotalPrice(totalPrice);
        return orderRepository.save(order);

    }
}
