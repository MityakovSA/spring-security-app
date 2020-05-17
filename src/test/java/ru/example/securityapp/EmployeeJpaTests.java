package ru.example.securityapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.example.securityapp.entity.Employee;
import ru.example.securityapp.enums.Positions;
import ru.example.securityapp.repository.EmpRepository;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TestTaskApplicationTests {
//
//    @Test
//    public void contextLoads() {
//    }
//
//}

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeJpaTests {

    @Autowired
    private EmpRepository empRepository;

    @Test
    public void whenGetEmployeeById_thenReturnEmployee() {
        // given
        String name = "emp5";
        int id = 5;

        // when
        Employee found = empRepository.findById(id).orElse(new Employee());

        // then
        assertThat(found.getName())
                .isEqualTo(name);
    }

    @Test
    public void whenGetEmployeeByName_thenReturnEmployee() {
        // given
        String name = "emp5";
        int id = 5;

        // when
        Employee found = empRepository.findByName(name).orElse(new Employee());

        // then
        assertThat(found.getId())
                .isEqualTo(id);
    }

    @Test
    public void whenCreateNewEmployee_thenSaved() {
        // given
        Employee emp = new Employee();
        emp.setName("emp8");
        emp.setPosition(Positions.programmer);
        emp.setEmpDate( new Date( System.currentTimeMillis() ) );

        // when
        empRepository.save(emp);

        // then
        int id = emp.getId();
        Employee found = empRepository.findById(id).orElse(new Employee());
        assertThat(found.getName())
                .isEqualTo(emp.getName());
    }

    @Test
    public void whenUpdateEmployee_thenUpdated() {
        // given
        int id = 5;
        String name = "updatedEmp";
        Positions position = Positions.manager;
        Date empDate = new Date(System.currentTimeMillis());

        // when
        Employee updated = empRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no employee with such id!"));
        updated.setName(name);
        updated.setPosition(position);
        updated.setEmpDate(empDate);
        empRepository.save(updated);

        // then
        Employee found = empRepository.findById(id).orElse(new Employee());
        assertThat(found.getName())
                .isEqualTo(name);
    }

    @Test
    public void whenDeleteEmployee_thenDeleted() {
        // given
        int id = 5;

        // when
        empRepository.deleteById(id);

        // then
        Employee found = empRepository.findById(id).orElse(null);
        assertThat(found)
                .isEqualTo(null);
    }

    @Test
    public void whenFindAllEmployees_thenReturnList() {
        // given


        // when
        List<Employee> list = StreamSupport.stream(empRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        // then
        assertThat(list.size())
                .isEqualTo(7);
    }

}
