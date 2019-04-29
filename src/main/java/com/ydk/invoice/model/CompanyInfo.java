package com.ydk.invoice.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "company_info")
public class CompanyInfo {

    @Id
    @Column(name = "credit_code")
    private String id;
    private String name;
    private String address;
    private String bank;
    private String tel;
    @Column(name = "bank_account")
    private String account;

}
