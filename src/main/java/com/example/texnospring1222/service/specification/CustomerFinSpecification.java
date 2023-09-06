package com.example.texnospring1222.service.specification;

import com.example.texnospring1222.dao.CustomerEntity;
import com.example.texnospring1222.dao.PassportAzEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class CustomerFinSpecification implements Specification<CustomerEntity> {
    private final String fin;

    public CustomerFinSpecification(String fin) {
        this.fin = fin;
    }

    @Override
    public Predicate toPredicate(Root<CustomerEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (fin == null) {
            return null;
        }

        return criteriaBuilder.equal(root.join("passportAzEntity").<PassportAzEntity>get("fin"), fin);
    }
}
