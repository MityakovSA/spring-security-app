package ru.example.securityapp.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "accounting")
public class Accounting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(optional = false)
    @JoinColumn(name = "res", nullable = false, unique = true)
    private Resource res;

    @ManyToOne
    @JoinColumn(name = "emp", nullable = false)
    private Employee emp;

    @Column(name = "st_date", nullable = false)
    private Date stDate;

    @Column(name = "exp_date")
    private Date expDate;

    public Accounting() {
    }

    public Resource getRes() {
        return res;
    }

    public void setRes(Resource res) {
        this.res = res;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public Date getStDate() {
        return stDate;
    }

    public void setStDate(Date date) {
        this.stDate = date;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date date) {
        this.expDate = date;
    }

    public int getId() {
        return id;
    }
}
