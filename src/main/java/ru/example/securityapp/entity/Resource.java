package ru.example.securityapp.entity;

import ru.example.securityapp.enums.ResType;

import javax.persistence.*;

@Entity
@Table(name = "resources")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sn", nullable = false, unique = true)
    private String sn;

    @Column(name = "specs")
    private String specs;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ResType type;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "res", orphanRemoval = true)
    private Accounting employee;

    public Resource() {
    }

    public int getId() {
        return id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public ResType getType() {
        return type;
    }

    public void setType(ResType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
