package com.ps13251_tranhieutrung_GD2.Services;



import com.ps13251_tranhieutrung_GD2.Models.Accounts;

import org.springframework.data.domain.Page;

public interface AccountsService {
    Page<Accounts> findPaginated(int pageNo, int pageSize);
    Page<Accounts> sortPaginated(int pageNo, int pageSize, String sortFiled, String sortDirection);
}
