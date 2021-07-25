package com.ps13251_tranhieutrung_GD2.ontap.Models;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "products")
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsOnTap implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int productId;
    private String name;
    private int price;
    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date date = new Date();
    private String image;
    private boolean available;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoriesOnTap category;
}
