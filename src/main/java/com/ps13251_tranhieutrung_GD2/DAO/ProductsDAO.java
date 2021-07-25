package com.ps13251_tranhieutrung_GD2.DAO;

import java.util.List;

import com.ps13251_tranhieutrung_GD2.Models.Products;
import com.ps13251_tranhieutrung_GD2.Models.Report;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsDAO extends JpaRepository<Products, Integer>{

    @Query("select o from Products o where o.price between ?1 and ?2")
    List<Products> findByPriceBetween(int minPrice, int maxPrice);

    @Query("select o from Products o where o.name like ?1")
    Page<Products> findByKeyWord(String keyWord, Pageable pageable);

    @Query("select o from Products o where o.name like ?1")
    List<Products> searchProducts(String keyWord);

    @Query("select new Report(o.category, sum(o.price), count(o))"
                +" from Products o "
                +" group by o.category "
                +" order by sum(o.price) desc ")
    List<Report> getIventoryByCategory();


}
