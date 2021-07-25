package com.ps13251_tranhieutrung_GD2.Services;

import com.ps13251_tranhieutrung_GD2.DAO.AccountDAO;
import com.ps13251_tranhieutrung_GD2.Models.Accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AccountsServiceImp implements AccountsService{
    
    @Autowired
    AccountDAO accountDAO;

    @Override
    public Page<Accounts> findPaginated(int pageNo, int pageSize) {
        Pageable pageAble = PageRequest.of(pageNo - 1, pageSize);
        return this.accountDAO.findAll(pageAble);
    }

    @Override
    public Page<Accounts> sortPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageAble = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.accountDAO.findAll(pageAble);
    }
    
}
