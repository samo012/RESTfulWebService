package com.store.rest;

import com.store.dao.ItemDAO;
import com.store.model.Item;
import java.util.Collection;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private ItemDAO itemDAO = new ItemDAO();

    public ItemService() {
    }

    public Item getItem(int itemId) {
        Item retString = this.itemDAO.getItem(itemId);
        return retString;
    }

    public Collection<Item> getAllItems() {
        Collection<Item> items = this.itemDAO.getAllItems();
        return items;
    }

    public Collection<Item> getItemByKeyword(String keyword) {
        Collection<Item> items = this.itemDAO.getItemByKeyword(keyword);
        return items;
    }
}