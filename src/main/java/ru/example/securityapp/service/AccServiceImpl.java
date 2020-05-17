package ru.example.securityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.securityapp.entity.Accounting;
import ru.example.securityapp.entity.Employee;
import ru.example.securityapp.entity.Resource;
import ru.example.securityapp.repository.AccRepository;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AccServiceImpl implements AccService {

    @Autowired
    private AccRepository repository;

    @Override
    public Accounting getAccountingById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no record with such id!"));
    }

    @Override
    public Accounting getAccountingByRes(String sn) {
        return repository.getAccountingByRes_Sn(sn)
                .orElse(new Accounting());
    }

    @Override
    public void saveAccounting(Accounting acc) {
        repository.save(acc);
    }

    @Override
    public void updateAccounting(Integer id, Resource res, Employee emp, Date stDate, Date expDate) {
        Accounting updated = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no record with such id!"));
        updated.setRes(res);
        updated.setEmp(emp);
        updated.setStDate(stDate);
        updated.setExpDate(expDate);
        repository.save(updated);
    }

    @Override
    public void deleteAccounting(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Accounting> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Accounting> findAllByEmployee(String name) {
        return StreamSupport.stream(repository.findAllByEmp_Name(name).spliterator(), false)
                .collect(Collectors.toList());
    }
}
