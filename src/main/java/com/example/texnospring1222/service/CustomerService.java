package com.example.texnospring1222.service;

import com.example.texnospring1222.dao.CustomerEntity;
import com.example.texnospring1222.dao.CustomerRepository;
import com.example.texnospring1222.exception.NotFoundException;
import com.example.texnospring1222.mapper.CustomerMapper;
import com.example.texnospring1222.model.CustomerDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private static final Log logger = LogFactory.getLog(CustomerService.class);

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<CustomerEntity> getAllCustomers(Integer age) {
        return customerRepository.findAllByAge(age);
    }

    public CustomerDto getCustomer(Integer customerId) {
//        System.out.println("Get customer by customerId started ...");
        logger.info("Get customer by customerId started: customerId " + customerId);
        CustomerEntity customerEntity = customerRepository.findById(customerId)
                .orElseThrow(() -> {
                    logger.error("Customer not found with id " + customerId);
                    throw new NotFoundException("CUSTOMER_NOT_FOUND");
                });
        logger.info("Get customer by customerId ended: customerId " + customerId);
        return customerMapper.mapEntityToDto(customerEntity);
    }

    public void saveCustomer(CustomerDto customer) {
        System.out.println("Save customer started ...");

        customerRepository.save(customerMapper.mapDtoToEntity(customer));
    }

    public void editCustomer(CustomerEntity customer, Integer customerId) {
        System.out.println("Edit customer by customerId started ...");
        customer.setId(customerId);
        customerRepository.save(customer);
    }

    public boolean deleteCustomer(Integer customerId) {
        System.out.println("Delete customer by customerId started ...");
        customerRepository.deleteById(customerId);
        return true;
    }

    public List<CustomerEntity> getCustomer(List<Integer> ages) {
        return customerRepository.findAllByAgeInOrderByFirstNameDesc(ages);
    }

    public CustomerEntity getCustomerByFirstName(String firstName) {
        return customerRepository.getCustomerWithLessAge(firstName).orElseThrow(
                () -> {
                    throw new RuntimeException("Customer not found with name: " + firstName);
                }
        );
    }

}
