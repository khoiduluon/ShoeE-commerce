package com.ps13251_tranhieutrung_GD2.DAO;

import com.ps13251_tranhieutrung_GD2.Models.Orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrdersDAO extends JpaRepository<Orders, Integer>{
    
}
