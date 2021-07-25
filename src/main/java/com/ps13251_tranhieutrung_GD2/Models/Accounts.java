package com.ps13251_tranhieutrung_GD2.Models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Accounts{
    @Id
    private String username;
    private boolean activated;
    private boolean admin;
    private String email;
    private String fullname;
    private String password;
    private String photo;

    @OneToMany(mappedBy = "account")
    List<Orders> orders;
    
}
