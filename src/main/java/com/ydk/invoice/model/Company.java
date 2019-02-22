package com.ydk.invoice.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "company_info", schema = "invoice")
public class Company {
    @Id
    private String cno; //公司税号
    private String cname; //公司名
    private String address; //地址
    private String bank; //开户行
    private String telephone; //电话号码
    private String cardnum; //银行卡号
    private Collection<Bill> billByPid;
    private Collection<Bill> billByBid;

    @Id
    @Column(name = "cno")
    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    @Basic
    @Column(name = "cname")
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "bank")
    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "cardnum")
    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(cno, company.cno) &&
                Objects.equals(cname, company.cname) &&
                Objects.equals(address, company.address) &&
                Objects.equals(bank, company.bank) &&
                Objects.equals(telephone, company.telephone) &&
                Objects.equals(cardnum, company.cardnum);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cno, cname, address, bank, telephone, cardnum);
    }

    @OneToMany(mappedBy = "pc")
    public Collection<Bill> getBillByPid() {
        return billByPid;
    }

    public void setBillByPid(Collection<Bill> billInfosByCno) {
        this.billByPid = billInfosByCno;
    }

    @OneToMany(mappedBy = "bc")
    public Collection<Bill> getBillByBid() {
        return billByBid;
    }

    public void setBillByBid(Collection<Bill> billInfosByCno_0) {
        this.billByBid = billInfosByCno_0;
    }
}
