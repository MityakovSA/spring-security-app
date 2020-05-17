package ru.example.securityapp.dto;

import java.sql.Date;

public class AccDto {

    private int res;

    private int emp;

    private Date stDate;

    private Date expDate;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public int getEmp() {
        return emp;
    }

    public void setEmp(int emp) {
        this.emp = emp;
    }

    public Date getStDate() {
        return stDate;
    }

    public void setStDate(Date stDate) {
        this.stDate = stDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }
}
