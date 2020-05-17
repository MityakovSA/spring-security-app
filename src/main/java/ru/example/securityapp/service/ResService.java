package ru.example.securityapp.service;

import ru.example.securityapp.entity.Resource;
import ru.example.securityapp.enums.ResType;

import java.util.List;

public interface ResService {
    Resource getResourceById(Integer id);
    Resource getResourceBySn(String sn);
    void saveResource(Resource res);
    void updateResource(Integer id, String sn, String specs, ResType type, String name);
    void deleteResource(Integer id);
    List<Resource> findAll();
}
