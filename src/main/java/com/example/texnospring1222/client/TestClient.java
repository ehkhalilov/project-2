package com.example.texnospring1222.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "test-client", url = "http://localhost:8081/main")
public interface TestClient {

    @GetMapping("/hello")
    String sayHello(@RequestParam String name);
}
