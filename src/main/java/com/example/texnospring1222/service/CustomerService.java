package com.example.texnospring1222.service;

import com.example.texnospring1222.client.TestClient;
import com.example.texnospring1222.dao.CustomerEntity;
import com.example.texnospring1222.dao.CustomerRepository;
import com.example.texnospring1222.exception.NotFoundException;
import com.example.texnospring1222.mapper.CustomerMapper;
import com.example.texnospring1222.model.CustomerDto;
import com.example.texnospring1222.model.CustomerFilterDto;
import com.example.texnospring1222.service.specification.CustomerAgeSpecification;
import com.example.texnospring1222.service.specification.CustomerFinSpecification;
import com.example.texnospring1222.service.specification.CustomerNameSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.named.NamedContextFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final TestClient testClient;

    public List<CustomerEntity> getAllCustomers(Integer age) {
        return customerRepository.findAllByAge(age);
    }

    @Transactional
    public CustomerDto getCustomer(Integer customerId) {
        log.info("Get customer by customerId started: customerId " + customerId);
        CustomerEntity customerEntity = customerRepository.findById(customerId)
                .orElseThrow(() -> {
                    log.error("Customer not found with id " + customerId);
                    throw new NotFoundException("CUSTOMER_NOT_FOUND");
                });

        customerEntity.setAge(28);

//        var message = testClient.sayHello(customerEntity.getFirstName());
//        System.out.println(message);
        log.info("Get customer by customerId ended: customerId " + customerId);

        return customerMapper.mapEntityToDto(customerEntity);
    }

    public void saveCustomer(CustomerDto customer) {
        System.out.println("Save customer started ...");
        var customerEntity = customerMapper.mapDtoToEntity(customer);
        customerRepository.save(customerEntity);
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

    public Page<CustomerDto> getCustomers(Pageable pageable, CustomerFilterDto customerFilterDto) {
        var specification = Specification
                .where(new CustomerAgeSpecification(customerFilterDto.getAge()))
                .and(new CustomerNameSpecification(customerFilterDto.getName()))
                .and(new CustomerFinSpecification(customerFilterDto.getFin()));

        var customers = customerRepository.findAll(specification, pageable)
                .stream().map(customerMapper::mapEntityToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(customers);
    }

    public CustomerEntity getCustomerByFirstName(String firstName) {
        return customerRepository.getCustomerWithLessAge(firstName).orElseThrow(
                () -> {
                    throw new RuntimeException("Customer not found with name: " + firstName);
                }
        );
    }

}
