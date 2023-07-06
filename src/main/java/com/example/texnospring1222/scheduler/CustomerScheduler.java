package com.example.texnospring1222.scheduler;

import com.example.texnospring1222.service.CustomerService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class CustomerScheduler {
    private final CustomerService customerService;

    public CustomerScheduler(CustomerService customerService) {
        this.customerService = customerService;
    }

//    @Scheduled(cron = "0/2 * * * * 1-4,5-7")
    public void scheduleFixedDelayTask() {
        var customer = customerService.getCustomer(1);
        System.out.println(customer);
    }
}
