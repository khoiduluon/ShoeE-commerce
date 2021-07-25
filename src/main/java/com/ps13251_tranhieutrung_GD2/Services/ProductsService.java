package com.ps13251_tranhieutrung_GD2.Services;

import com.ps13251_tranhieutrung_GD2.Models.Products;

import org.springframework.data.domain.Page;

public interface ProductsService {
    Page<Products> findPaginated(int pageNo, int pageSize);
    Page<Products> sortPaginated(int pageNo, int pageSize, String sortFiled, String sortDirection);
}
