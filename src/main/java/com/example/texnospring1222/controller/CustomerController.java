package com.example.texnospring1222.controller;

import com.example.texnospring1222.dao.CustomerEntity;
import com.example.texnospring1222.model.CustomerDto;
import com.example.texnospring1222.service.CustomerService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

//    @GetMapping
//    public List<CustomerEntity> getAllCustomers(
//            Integer age
//    ) {
//        return customerService.getAllCustomers(age);
//    }

//    @GetMapping("/{customerId}")
//    public CustomerEntity getCustomer(@PathVariable Integer customerId) {
//        return customerService.getCustomer(customerId);
//    }

    @PostMapping
    public void saveCustomer(@RequestBody CustomerDto customer) {
        customerService.saveCustomer(customer);
    }

    @PutMapping("/{customerId}")
    public void editCustomer(@RequestBody CustomerEntity customer, @PathVariable Integer customerId) {
        customerService.editCustomer(customer, customerId);
    }

    @DeleteMapping("/{customerId}")
    public boolean deleteCustomer(@PathVariable Integer customerId) {
        return customerService.deleteCustomer(customerId);
    }

    @GetMapping
    public List<CustomerEntity> getCustomers(
            @RequestParam List<Integer> age
    ) {
        return customerService.getCustomer(age);
    }

//    @GetMapping("/{firstName}")
//    public CustomerEntity getCustomerByFirstName(@PathVariable String firstName) {
//        return customerService.getCustomerByFirstName(firstName);
//    }

    @GetMapping("/{customerId}")
    public CustomerDto getCustomer(@PathVariable Integer customerId) {
        return customerService.getCustomer(customerId);
    }
}
