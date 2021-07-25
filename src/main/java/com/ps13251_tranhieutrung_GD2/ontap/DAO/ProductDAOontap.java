package com.ps13251_tranhieutrung_GD2.ontap.DAO;

import com.ps13251_tranhieutrung_GD2.ontap.Models.ProductsOnTap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAOontap extends JpaRepository<ProductsOnTap, Integer>{
    
}
