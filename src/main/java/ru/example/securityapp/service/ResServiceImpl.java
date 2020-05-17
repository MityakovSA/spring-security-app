package ru.example.securityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.securityapp.entity.Resource;
import ru.example.securityapp.enums.ResType;
import ru.example.securityapp.repository.ResRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ResServiceImpl implements ResService {

    @Autowired
    private ResRepository repository;

    @Override
    public Resource getResourceById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no resource with such id!"));
    }

    @Override
    public Resource getResourceBySn(String sn) {
        return repository.findBySn(sn)
                .orElseThrow(() -> new NoSuchElementException("There is no resource with such sn!"));
    }

    @Override
    public void saveResource(Resource res) {
        repository.save(res);
    }

    @Override
    public void updateResource(Integer id, String sn, String specs, ResType type, String name) {
        Resource updated = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no resource with such id!"));
        updated.setSn(sn);
        updated.setSpecs(specs);
        updated.setType(type);
        updated.setName(name);
        repository.save(updated);
    }

    @Override
    public void deleteResource(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Resource> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
