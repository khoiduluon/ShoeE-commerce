package com.ps13251_tranhieutrung_GD2.Services;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ps13251_tranhieutrung_GD2.DAO.ProductsDAO;
import com.ps13251_tranhieutrung_GD2.Models.Item;
import com.ps13251_tranhieutrung_GD2.Models.Products;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Service
public class ShoppingCardServiceImp implements ShoppingCardService {

    @Autowired
    ProductsDAO productsDAO;

    Map<Integer, Item> map = new HashMap<>();

    @Override
    public Item add(int id) {

        Item item = map.get(id);

        if (item == null) {
            item = new Item();
            Products p = new Products();
            List<Products> listProducts = productsDAO.findAll();
            //p = listProducts.get(id);
            p = listProducts.stream()
                .filter(it -> it.getProductId() == id)
                .collect(Collectors.toList())
                .get(0);
            item.setId(p.getProductId());
            item.setName(p.getName());
            item.setPrice(p.getPrice());
            item.setQty(1);
            item.setImage(p.getImage());
            map.put(id, item);
        } else {
            item.setQty(item.getQty() + 1);
        }
        return item;
    }

    @Override
    public void remove(int id) {
        map.remove(id);
    }

    @Override
    public Item update(int id, int qty) {
        Item item = map.get(id);
        item.setQty(qty);
        return item;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Collection<Item> getItems() {
        return map.values();
    }

    @Override
    public int getCount() {
        return map.values().stream().mapToInt(item -> item.getQty()).sum();
    }

    @Override
    public double getAmount() {
        return map.values().stream().mapToDouble(item -> item.getPrice() * item.getQty()).sum();
    }
}
