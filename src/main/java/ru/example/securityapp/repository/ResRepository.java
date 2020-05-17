package ru.example.securityapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.example.securityapp.entity.Resource;

import java.util.Optional;

@Repository
public interface ResRepository extends CrudRepository<Resource, Integer> {

    Optional<Resource> findBySn(String sn);

}
