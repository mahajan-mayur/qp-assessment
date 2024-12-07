package com.qp.groceryapi.repository;


import com.qp.groceryapi.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, String > {

    @Modifying
    @Query("update Item i set i.stock = :stock where i.id = :id")
    int setStock(@Param("stock") Integer stock, @Param("id") String id);


    @NonNull
    Optional<Item> findById(@NonNull String itemId);


}
