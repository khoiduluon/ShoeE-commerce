package com.ps13251_tranhieutrung_GD2.Models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "orderdetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orderdetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "id")
    private Orders orderid;
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Products productid;

    
}
