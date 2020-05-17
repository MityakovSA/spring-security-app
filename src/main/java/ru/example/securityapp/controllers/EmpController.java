package ru.example.securityapp.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.deltasolutions.newsapi.NewsApiClient;
import ru.deltasolutions.newsapi.models.Article;
import ru.deltasolutions.newsapi.models.ArticlesResult;
import ru.deltasolutions.newsapi.models.EverythingRequest;
import ru.deltasolutions.newsapi.models.TopHeadlinesRequest;
import ru.example.securityapp.dto.EverythingRequestDto;
import ru.example.securityapp.entity.Employee;
import ru.example.securityapp.service.EmpServiceImpl;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmpController {

    @Autowired
    private EmpServiceImpl service;

    @GetMapping
    public List<Employee> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Employee findOne(@PathVariable int id) {
        return service.getEmployeeById(id);
    }

    @GetMapping("/name/{name}")
    public Employee findOneByName(@PathVariable String name) {
        return service.getEmployeeByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Employee emp) {
        service.saveEmployee(emp);
    }

    @PostMapping("/fromNewsApi/topHeadlines/{apiKey}")
    @ResponseStatus(HttpStatus.CREATED)
    public ArticlesResult saveAllFromTopHeadlines(@RequestBody TopHeadlinesRequest request, @PathVariable String apiKey) {
        NewsApiClient client = new NewsApiClient(apiKey);
        ArticlesResult result = client.getTopHeadlines(request);
        service.saveAllFromArticles(result);
        return result;
    }

    @PostMapping("/fromNewsApi/everything/{apiKey}")
    @ResponseStatus(HttpStatus.CREATED)
    public ArticlesResult saveAllFromEverything(@RequestBody EverythingRequestDto dto, @PathVariable String apiKey) {
        NewsApiClient client = new NewsApiClient(apiKey);
        EverythingRequest request = new EverythingRequest();
        BeanUtils.copyProperties(dto, request, "from", "to");
        if (dto.getFrom() != null && !dto.getFrom().isEmpty()) {
            request.setFrom(Instant.parse(dto.getFrom()));
        }
        if (dto.getTo() != null && !dto.getTo().isEmpty()) {
            request.setTo(Instant.parse(dto.getTo()));
        }
        ArticlesResult result = client.getEverything(request);
        service.saveAllFromArticles(result);
        return result;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.getEmployeeById(id);
        service.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@RequestBody Employee emp, @PathVariable int id) {
        service.updateEmployee(id, emp.getName(), emp.getPosition(), emp.getEmpDate());
    }

}
