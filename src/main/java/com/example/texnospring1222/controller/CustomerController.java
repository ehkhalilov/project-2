package com.example.texnospring1222.controller;

import com.example.texnospring1222.dao.CustomerEntity;
import com.example.texnospring1222.model.CustomerDto;
import com.example.texnospring1222.model.CustomerFilterDto;
import com.example.texnospring1222.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;


    @PostMapping
    public void saveCustomer(@Valid @RequestBody CustomerDto customer) {
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
    public Page<CustomerDto> getCustomers(
            Pageable pageable,
            CustomerFilterDto customerFilterDto
    ) {
        return customerService.getCustomers(pageable, customerFilterDto);
    }

//    @GetMapping("/{firstName}")
//    public CustomerEntity getCustomerByFirstName(@PathVariable String firstName) {
//        return customerService.getCustomerByFirstName(firstName);
//    }

    @GetMapping("/{customerId}")
    public CustomerDto getCustomer(@PathVariable Integer customerId) {
        return customerService.getCustomer(customerId);
    }


    @GetMapping("/crypt/{text}")
    public String cryptText(@PathVariable String text) {
        return passwordEncoder.encode(text);
    }
}
