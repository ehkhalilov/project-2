package com.example.texnospring1222.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerDto {
    @NotNull
    @Size(min = 3, max = 20)
    private String name;
    @Min(value = 18, message = "Age must be more than 18")
    @Max(value = 65)
    private Integer age;
    private String serialNumber;
    private String fin;
    private Long expireDay;
    @Email
    private String email;
}
