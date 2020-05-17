package ru.example.securityapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.example.securityapp.entity.Employee;

import java.util.Optional;

@Repository
public interface EmpRepository extends CrudRepository<Employee, Integer> {

    Optional<Employee> findByName(String name);

}
