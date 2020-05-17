package ru.example.securityapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.example.securityapp.entity.Resource;
import ru.example.securityapp.service.ResServiceImpl;

import java.util.InputMismatchException;
import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResController {

    @Autowired
    private ResServiceImpl service;

    @GetMapping
    public List<Resource> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Resource findOne(@PathVariable int id) {
        return service.getResourceById(id);
    }

    @GetMapping("/sn/{sn}")
    public Resource findOneBySn(@PathVariable String sn) {
        return service.getResourceBySn(sn);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Resource res) {
        service.saveResource(res);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.getResourceById(id);
        service.deleteResource(id);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@RequestBody Resource res, @PathVariable int id) {
        service.updateResource(id, res.getSn(), res.getSpecs(), res.getType(), res.getName());
    }

}
