package ru.example.securityapp.entity;

import ru.example.securityapp.enums.Positions;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "f_name", nullable = false, unique = true)
    private String name;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private Positions position;

    @Column(name = "emp_date", nullable = false)
    private Date empDate;

    @OneToMany(mappedBy = "emp", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Accounting> resources;

    public Employee() {
    }

//    public Employee(String f_name, Positions position) {
//        this.f_name = f_name;
//        this.position = position;
//        this.emp_date = new Date(System.currentTimeMillis());
//    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Positions getPosition() {
        return position;
    }

    public void setPosition(Positions position) {
        this.position = position;
    }

    public Date getEmpDate() {
        return empDate;
    }

    public void setEmpDate(Date empDate) {
        this.empDate = empDate;
    }

    //    @Override
//    public String toString() {
//        return "Employee{" +
//                "id=" + id +
//                ", full_name=" + f_name +
//                ", position=" + position +
//                ", employment_date=" + emp_date +
//                '}';
//    }
}
