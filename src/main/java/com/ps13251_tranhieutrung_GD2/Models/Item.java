package com.ps13251_tranhieutrung_GD2.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    int id;String name;double price;int qty = 1;String image;
}
