package com.qp.groceryapi.service;

import com.qp.groceryapi.config.Constants;
import com.qp.groceryapi.exception.ItemNotFoundException;
import com.qp.groceryapi.model.Item;
import com.qp.groceryapi.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {
    private final ItemRepository itemRepository;

    public Page<Item> getItems(Integer pageNumber, Integer pageSize){
        log.info("getting items for pageNo: {} pageSize: {}", pageNumber, pageSize);
        PageRequest pageRequest;
        if(pageNumber != null && pageSize != null){
            pageRequest = PageRequest.of(pageNumber, pageSize);
        } else if(pageNumber != null) {
            pageRequest = PageRequest.of(pageNumber, Constants.DEFAULT_PAGE_SIZE);
        } else if (pageSize != null) {
            pageRequest = PageRequest.ofSize(pageSize);
        } else {
            log.info("defaulting to page size: {}", Constants.DEFAULT_PAGE_SIZE);
            pageRequest = PageRequest.ofSize(Constants.DEFAULT_PAGE_SIZE);
        }
        return  itemRepository.findAll(pageRequest);
    }


    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    public Item getItem(String itemId) {
        try {
            Item item = itemRepository.getReferenceById(itemId);
            log.info("fetched item name: {} ", item.getName());
            return item;
        } catch (EntityNotFoundException e){
            log.info("item not found with id : {} ", itemId);
               throw new ItemNotFoundException(itemId);
        }
    }

    public void deleteItem(String itemId) {

        itemRepository.deleteById(itemId);
        log.info("item with id {} is deleted", itemId);
    }

    public Item updateItem(String itemId, Item item) {
        item.setId(itemId);
        return itemRepository.save(item);

    }

    @Transactional
    public void updateItemStock(String itemId, Integer stock) {
        log.info("updating itemId: {}  stock: {}", itemId, stock);
        if(!itemRepository.existsById(itemId)){
            throw new ItemNotFoundException(itemId);
        }
         itemRepository.setStock(stock, itemId)  ;
    }
}
