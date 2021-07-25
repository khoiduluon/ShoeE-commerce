package com.ps13251_tranhieutrung_GD2.DAO;

import com.ps13251_tranhieutrung_GD2.Models.Categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesDAO  extends JpaRepository<Categories, Integer>{
    
}
