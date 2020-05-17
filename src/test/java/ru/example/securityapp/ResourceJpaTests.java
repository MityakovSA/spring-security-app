package ru.example.securityapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.example.securityapp.entity.Resource;
import ru.example.securityapp.enums.ResType;
import ru.example.securityapp.repository.ResRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ResourceJpaTests {

    @Autowired
    private ResRepository resRepository;

    @Test
    public void whenGetResourceById_thenReturnResource() {
        // given
        String name = "ms wireless mouse";
        int id = 4;

        // when
        Resource found = resRepository.findById(id).orElse(new Resource());

        // then
        assertThat(found.getName())
                .isEqualTo(name);
    }

    @Test
    public void whenGetResourceBySn_thenReturnResource() {
        // given
        String sn = "xdgfh4fxgn5gfbd5";
        int id = 1;

        // when
        Resource found = resRepository.findBySn(sn).orElse(new Resource());

        // then
        assertThat(found.getId())
                .isEqualTo(id);
    }

    @Test
    public void whenCreateNewResource_thenSaved() {
        // given
        Resource res = new Resource();
        res.setSn("newSn");
        res.setSpecs("newSpecs");
        res.setType(ResType.monitor);
        res.setName("newName");

        // when
        resRepository.save(res);

        // then
        int id = res.getId();
        Resource found = resRepository.findById(id).orElse(new Resource());
        assertThat(found.getName())
                .isEqualTo(res.getName());
    }

    @Test
    public void whenUpdateResource_thenUpdated() {
        // given
        int id = 4;
        String sn = "updatedSn";
        String specs = "updatedSpecs";
        ResType type = ResType.monitor;
        String name = "updatedName";

        // when
        Resource updated = resRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no resource with such id!"));
        updated.setSn(sn);
        updated.setSpecs(specs);
        updated.setType(type);
        updated.setName(name);
        resRepository.save(updated);

        // then
        Resource found = resRepository.findById(id).orElse(new Resource());
        assertThat(found.getName())
                .isEqualTo(name);
    }

    @Test
    public void whenDeleteResource_thenDeleted() {
        // given
        int id = 4;

        // when
        resRepository.deleteById(id);

        // then
        Resource found = resRepository.findById(id).orElse(null);
        assertThat(found)
                .isEqualTo(null);
    }

    @Test
    public void whenFindAllResources_thenReturnList() {
        // given


        // when
        List<Resource> list = StreamSupport.stream(resRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        // then
        assertThat(list.size())
                .isEqualTo(6);
    }

}
