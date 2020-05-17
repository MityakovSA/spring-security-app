package ru.example.securityapp.service;

import ru.deltasolutions.newsapi.models.ArticlesResult;
import ru.example.securityapp.entity.Employee;
import ru.example.securityapp.enums.Positions;

import java.sql.Date;
import java.util.List;

public interface EmpService {
    Employee getEmployeeById(Integer id);
    Employee getEmployeeByName(String name);
    void saveEmployee(Employee emp);
    void saveAllFromArticles(ArticlesResult result);
    void updateEmployee(Integer id, String name, Positions position, Date empDate);
    void deleteEmployee(Integer id);
    List<Employee> findAll();
}
