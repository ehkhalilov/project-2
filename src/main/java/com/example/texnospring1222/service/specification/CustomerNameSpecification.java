package com.example.texnospring1222.service.specification;

import com.example.texnospring1222.dao.CustomerEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class CustomerNameSpecification implements Specification<CustomerEntity> {
    private final String name;

    public CustomerNameSpecification(String name) {
        this.name = name;
    }

    @Override
    public Predicate toPredicate(Root<CustomerEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (name == null) {
            return null;
        }

        return criteriaBuilder.equal(root.<String>get("firstName"), name);
    }
}
