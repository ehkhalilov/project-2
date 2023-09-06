package com.example.texnospring1222.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFilterDto {
    private String name;
    private Integer age;
    private String fin;
}
