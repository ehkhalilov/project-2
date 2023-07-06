package com.example.texnospring1222.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {
    List<CustomerEntity> findAllByAge(Integer age);
    Optional<CustomerEntity> findByFirstNameIgnoreCase(String firstName);
    List<CustomerEntity> findAllByAgeInOrderByFirstNameDesc(List<Integer> ages);

    void deleteAllByAge(Integer age);

//    @Query(value = "select c from CustomerEntity c " +
//            "where c.firstName = :customerName " +
//            "and c.age = (" +
//            "select min(c2.age) from CustomerEntity c2 " +
//            "where c2.firstName = :customerName" +
//            ")")
//    Optional<CustomerEntity> getCustomerWithLessAge(String customerName);

    @Query(value = "select * from customers c " +
            "where c.first_name = :customerName " +
            "and c.age = (" +
            "select min(c2.age) from customers c2 " +
            "where c2.first_name = :customerName" +
            ")", nativeQuery = true)
    Optional<CustomerEntity> getCustomerWithLessAge(String customerName);
}
