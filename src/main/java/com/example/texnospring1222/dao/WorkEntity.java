package com.example.texnospring1222.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Table(name = "works")
@Entity
public class WorkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    @ManyToMany
    private Set<CustomerEntity> customerEntitySet;

    public WorkEntity() {
    }

    public WorkEntity(Long id, String companyName, Set<CustomerEntity> customerEntitySet) {
        this.id = id;
        this.companyName = companyName;
        this.customerEntitySet = customerEntitySet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Set<CustomerEntity> getCustomerEntitySet() {
        return customerEntitySet;
    }

    public void setCustomerEntitySet(Set<CustomerEntity> customerEntitySet) {
        this.customerEntitySet = customerEntitySet;
    }
}
