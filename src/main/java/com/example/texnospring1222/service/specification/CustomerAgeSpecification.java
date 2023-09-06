package com.example.texnospring1222.service.specification;

import com.example.texnospring1222.dao.CustomerEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class CustomerAgeSpecification implements Specification<CustomerEntity> {

    private final Integer age;

    public CustomerAgeSpecification(Integer age) {
        this.age = age;
    }

    @Override
    public Predicate toPredicate(Root<CustomerEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (age == null) {
            return null;
        }

        return criteriaBuilder.equal(root.<Integer>get("age"), age);
    }
}
