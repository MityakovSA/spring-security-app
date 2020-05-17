package ru.example.securityapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.example.securityapp.entity.Accounting;
import ru.example.securityapp.entity.Employee;
import ru.example.securityapp.entity.Resource;
import ru.example.securityapp.repository.AccRepository;
import ru.example.securityapp.repository.EmpRepository;
import ru.example.securityapp.repository.ResRepository;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountingJpaTests {

    @Autowired
    private ResRepository resRepository;

    @Autowired
    private EmpRepository empRepository;

    @Autowired
    private AccRepository accRepository;

    @Test
    public void whenGetAccountingById_thenReturnAccounting() {
        // given
        Date date = Date.valueOf("2019-02-03");
        int id = 3;

        // when
        Accounting found = accRepository.findById(id).orElse(new Accounting());

        // then
        assertThat(found.getStDate())
                .isEqualTo(date);
    }

    @Test
    public void whenGetAccountingByEmp_thenReturnAccounting() {
        // given
        String sn = "xdgfh4fxgn5gfbd5";

        // when
        Accounting found = accRepository.getAccountingByRes_Sn(sn).orElse(new Accounting());

        // then
        assertThat(found.getRes().getSn())
                .isEqualTo(sn);
    }

    @Test
    public void whenCreateNewAccounting_thenSaved() {
        // given
        Resource res = resRepository.findById(6)
                .orElseThrow(() -> new NoSuchElementException("There is no resource with such id!"));
        Employee emp = empRepository.findById(3)
                .orElseThrow(() -> new NoSuchElementException("There is no employee with such id!"));
        Accounting acc = new Accounting();
        acc.setRes(res);
        acc.setEmp(emp);
        acc.setStDate(Date.valueOf("2019-03-07"));
        acc.setExpDate(Date.valueOf("2019-06-07"));

        // when
        accRepository.save(acc);

        // then
        int id = acc.getId();
        Accounting found = accRepository.findById(id).orElse(new Accounting());
        assertThat(found.getStDate())
                .isEqualTo(acc.getStDate());
    }

    @Test
    public void whenUpdateAccounting_thenUpdated() {
        // given
        int id = 3;
        Date stDate = Date.valueOf("2019-03-07");
        Date expDate = Date.valueOf("2019-06-07");

        // when
        Accounting updated = accRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no record with such id!"));
        updated.setStDate(stDate);
        updated.setExpDate(expDate);
        accRepository.save(updated);

        // then
        Accounting found = accRepository.findById(id).orElse(new Accounting());
        assertThat(found.getStDate())
                .isEqualTo(stDate);
    }

    @Test
    public void whenDeleteAccounting_thenDeleted() {
        // given
        int id = 3;

        // when
        accRepository.deleteById(id);

        // then
        Accounting found = accRepository.findById(id).orElse(null);
        assertThat(found)
                .isEqualTo(null);
    }

    @Test
    public void whenFindAllAccountings_thenReturnList() {
        // given


        // when
        List<Accounting> list = StreamSupport.stream(accRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        // then
        assertThat(list.size())
                .isEqualTo(5);
    }

    @Test
    public void whenFindAllByEmployeeName_thenReturnList() {
        // given
        String name = "emp7";

        // when
        List<Accounting> list = StreamSupport.stream(accRepository.findAllByEmp_Name(name).spliterator(), false)
                .collect(Collectors.toList());

        // then
        assertThat(list.size())
                .isEqualTo(2);
    }

}
