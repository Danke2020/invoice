package com.ydk.invoice.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "bill_info")
public class BillInfo {
    @Id
    @GeneratedValue
    private int id;
    private String signdate;
    private String material;
    private String size;
    private String unit;
    private BigDecimal num;
    private BigDecimal price;
    private BigDecimal total;
    private String pid;
    private String bid;
    @ManyToOne
    @JoinColumn(name = "pid", referencedColumnName = "credit_code", nullable = false, insertable = false, updatable = false)
    private CompanyInfo pc;
    @ManyToOne
    @JoinColumn(name = "bid", referencedColumnName = "credit_code", nullable = false, insertable = false, updatable = false)
    private CompanyInfo bc;

}
