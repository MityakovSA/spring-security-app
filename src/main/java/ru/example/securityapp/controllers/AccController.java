package ru.example.securityapp.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.example.securityapp.dto.AccDto;
import ru.example.securityapp.entity.Accounting;
import ru.example.securityapp.entity.Employee;
import ru.example.securityapp.entity.Resource;
import ru.example.securityapp.service.AccServiceImpl;
import ru.example.securityapp.service.EmpServiceImpl;
import ru.example.securityapp.service.ResServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/accountings")
public class AccController {

    @Autowired
    private ResServiceImpl resService;

    @Autowired
    private EmpServiceImpl empService;

    @Autowired
    private AccServiceImpl accService;

    @GetMapping
    public List<Accounting> findAll() {
        return accService.findAll();
    }

    @GetMapping("/employee/{name}")
    public List<Accounting> findAllByEmployee(@PathVariable String name) {
        empService.getEmployeeByName(name);
        return  accService.findAllByEmployee(name);
    }

    @GetMapping("/{id}")
    public Accounting findOne(@PathVariable int id) {
        return accService.getAccountingById(id);
    }

    @GetMapping("/resource/{sn}")
    public Accounting findOneByResource(@PathVariable String sn) {
        resService.getResourceBySn(sn);
        return accService.getAccountingByRes(sn);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody AccDto accDto) {
        Accounting acc = new Accounting();
        BeanUtils.copyProperties(accDto, acc, "res", "emp");
        Resource res = resService.getResourceById(accDto.getRes());
        Employee emp = empService.getEmployeeById(accDto.getEmp());
        acc.setRes(res);
        acc.setEmp(emp);
        accService.saveAccounting(acc);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        accService.getAccountingById(id);
        accService.deleteAccounting(id);
    }

    @PutMapping("/{id}")
    public void updateAccounting(@RequestBody AccDto accDto, @PathVariable int id) {
        Resource res = resService.getResourceById(accDto.getRes());
        Employee emp = empService.getEmployeeById(accDto.getEmp());
        accService.updateAccounting(id, res, emp, accDto.getStDate(), accDto.getExpDate());
    }

}
