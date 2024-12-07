package com.qp.groceryapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    private Item item;
    private Integer quantity;
    private Double itemPrice;

    public OrderItem(Order order,Item item,Integer quantity,Double itemPrice){
        this.order = order;
        this.item = item;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }


}
