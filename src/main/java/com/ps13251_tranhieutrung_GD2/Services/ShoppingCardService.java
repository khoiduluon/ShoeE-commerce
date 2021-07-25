package com.ps13251_tranhieutrung_GD2.Services;

import java.util.Collection;

import com.ps13251_tranhieutrung_GD2.Models.Item;

public interface ShoppingCardService {
    Item add(int id);
    void remove(int id);
    Item update(int id, int qty);
    void clear();
    Collection<Item> getItems();
    int getCount();
    double getAmount();
}
