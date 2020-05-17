package ru.example.securityapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.example.securityapp.entity.Accounting;

import java.util.Optional;

@Repository
public interface AccRepository extends CrudRepository<Accounting, Integer> {

    Iterable<Accounting> findAllByEmp_Name(String name);
    Optional<Accounting> getAccountingByRes_Sn(String sn);

}
