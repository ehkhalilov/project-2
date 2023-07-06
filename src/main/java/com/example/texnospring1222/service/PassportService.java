package com.example.texnospring1222.service;

import com.example.texnospring1222.dao.PassportAzEntity;
import com.example.texnospring1222.dao.PassportAzRepository;
import com.example.texnospring1222.exception.NotFoundException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.stereotype.Service;

@Service
public class PassportService {
    private final PassportAzRepository passportAzRepository;


    public PassportService(PassportAzRepository passportAzRepository) {
        this.passportAzRepository = passportAzRepository;
    }

    public PassportAzEntity getPassport(String fin) {
        return passportAzRepository.findByFin(fin).orElseThrow(
                () -> {
                    throw new NotFoundException("Passport az not found with fin: " + fin);
                }
        );
    }
}
