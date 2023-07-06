package com.example.texnospring1222.controller;

import com.example.texnospring1222.dao.PassportAzEntity;
import com.example.texnospring1222.service.PassportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passports")
public class PassportController {

    private final PassportService passportService;

    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    @GetMapping
    public PassportAzEntity getPassport(String fin) {
        return passportService.getPassport(fin);
    }
}

//git init
//git add .
//git commit -m "my commit"
//git remote set-url origin git@github.com:username/repo.git
//git push origin master