package ru.example.securityapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.deltasolutions.newsapi.constants.Statuses;
import ru.deltasolutions.newsapi.models.Article;
import ru.deltasolutions.newsapi.models.ArticlesResult;
import ru.example.securityapp.entity.Employee;
import ru.example.securityapp.enums.Positions;
import ru.example.securityapp.repository.EmpRepository;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmpServiceImpl  implements EmpService {

    private static Logger logger = LoggerFactory.getLogger(EmpServiceImpl.class);

    @Autowired
    private EmpRepository repository;

    @Override
    public Employee getEmployeeById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no employee with such id!"));
    }

    @Override
    public Employee getEmployeeByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("There is no employee with such name!"));
    }

    @Override
    public void saveEmployee(Employee emp) {
        repository.save(emp);
    }

    @Override
    public void saveAllFromArticles(ArticlesResult result) {
        if ((result.getStatus() == Statuses.error) || (result.getTotalResults() == 0))
            return;
        for (Article article : result.getArticles()) {
            if (article.getAuthor() == null)
                continue;
            Employee emp = new Employee();
            emp.setName(article.getAuthor());
            emp.setEmpDate( new Date( article.getPublishedAt().toEpochMilli() ) );
            try {
                repository.save(emp);
            } catch (Exception e) {
                logger.warn("Exception while saving employee: " + e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void updateEmployee(Integer id, String name, Positions position, Date empDate) {
        Employee updated = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no employee with such id!"));
        updated.setName(name);
        updated.setPosition(position);
        updated.setEmpDate(empDate);
        repository.save(updated);
    }

    @Override
    public void deleteEmployee(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Employee> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
