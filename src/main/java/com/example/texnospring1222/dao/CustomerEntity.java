package com.example.texnospring1222.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customers")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "passport_id")
    @JsonManagedReference
    private PassportAzEntity passportAzEntity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ContactEntity> contactEntityList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "customer_work",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "work_id"))
    private Set<WorkEntity> workEntitySet;

    public CustomerEntity() {
    }

    public CustomerEntity(Integer id, String firstName, String lastName, Integer age, PassportAzEntity passportAzEntity, List<ContactEntity> contactEntityList, Set<WorkEntity> workEntitySet) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.passportAzEntity = passportAzEntity;
        this.contactEntityList = contactEntityList;
        this.workEntitySet = workEntitySet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public PassportAzEntity getPassportAzEntity() {
        return passportAzEntity;
    }

    public void setPassportAzEntity(PassportAzEntity passportAzEntity) {
        this.passportAzEntity = passportAzEntity;
    }

    public List<ContactEntity> getContactEntityList() {
        return contactEntityList;
    }

    public void setContactEntityList(List<ContactEntity> contactEntityList) {
        this.contactEntityList = contactEntityList;
    }

    public Set<WorkEntity> getWorkEntitySet() {
        return workEntitySet;
    }

    public void setWorkEntitySet(Set<WorkEntity> workEntitySet) {
        this.workEntitySet = workEntitySet;
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
