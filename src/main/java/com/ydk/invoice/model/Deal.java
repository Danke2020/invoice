package com.ydk.invoice.model;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "deal_info", schema = "invoice")
public class Deal {
    private int id;
    private String name;
    private String size;
    private String unit;
    private Integer num;
    private Float price;
    private Float total;
    private Bill billByNo;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "size")
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Basic
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "num")
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Basic
    @Column(name = "price")
    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Basic
    @Column(name = "total")
    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deal deal = (Deal) o;
        return id == deal.id &&
                Objects.equals(name, deal.name) &&
                Objects.equals(size, deal.size) &&
                Objects.equals(unit, deal.unit) &&
                Objects.equals(num, deal.num) &&
                Objects.equals(price, deal.price) &&
                Objects.equals(total, deal.total);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, size, unit, num, price, total);
    }

    @ManyToOne
    @JoinColumn(name = "billno", referencedColumnName = "billno")
    public Bill getBillByNo() {
        return billByNo;
    }

    public void setBillByNo(Bill billInfoByBillno) {
        this.billByNo = billInfoByBillno;
    }
}
