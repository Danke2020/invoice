package com.ydk.invoice.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "bill_info", schema = "invoice")
public class Bill {
    private int billno;
    private Date signdate;
    private Company pc;
    private Company bc;
    private Collection<Deal> dealInfos;

    @Id
    @Column(name = "billno")
    public int getBillno() {
        return billno;
    }

    public void setBillno(int billno) {
        this.billno = billno;
    }

    @Basic
    @Column(name = "signdate")
    public Date getSigndate() {
        return signdate;
    }

    public void setSigndate(Date signdate) {
        this.signdate = signdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return billno == bill.billno &&
                Objects.equals(signdate, bill.signdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billno, signdate);
    }

    @ManyToOne
    @JoinColumn(name = "pid", referencedColumnName = "cno", nullable = false)
    public Company getPc() {
        return pc;
    }

    public void setPc(Company companyInfoByPid) {
        this.pc = companyInfoByPid;
    }

    @ManyToOne
    @JoinColumn(name = "bid", referencedColumnName = "cno", nullable = false)
    public Company getBc() {
        return bc;
    }

    public void setBc(Company companyInfoByBid) {
        this.bc = companyInfoByBid;
    }

    @OneToMany(mappedBy = "billByNo")
    public Collection<Deal> getDealInfos() {
        return dealInfos;
    }

    public void setDealInfos(Collection<Deal> dealInfosByBillno) {
        this.dealInfos = dealInfosByBillno;
    }

}
