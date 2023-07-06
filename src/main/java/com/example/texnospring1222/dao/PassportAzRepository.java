package com.example.texnospring1222.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PassportAzRepository extends JpaRepository<PassportAzEntity, Long> {

    Optional<PassportAzEntity> findByFin(String fin);
}
