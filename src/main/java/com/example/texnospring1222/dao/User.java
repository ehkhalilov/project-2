package com.example.texnospring1222.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String username;
    private String password;
    private Boolean enabled;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<AuthorityEntity> authorityEntityList;
}
