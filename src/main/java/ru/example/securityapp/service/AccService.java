package ru.example.securityapp.service;

import ru.example.securityapp.entity.Accounting;
import ru.example.securityapp.entity.Employee;
import ru.example.securityapp.entity.Resource;

import java.sql.Date;
import java.util.List;

public interface AccService {
    Accounting getAccountingById(Integer id);
    Accounting getAccountingByRes(String sn);
    void saveAccounting(Accounting acc);
    void updateAccounting(Integer id, Resource res, Employee emp, Date stDate, Date expDate);
    void deleteAccounting(Integer id);
    List<Accounting> findAll();
    List<Accounting> findAllByEmployee(String name);
}
