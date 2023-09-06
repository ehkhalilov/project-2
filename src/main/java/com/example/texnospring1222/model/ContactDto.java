package com.example.texnospring1222.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactDto {
    private String number;
    private String type;
}
