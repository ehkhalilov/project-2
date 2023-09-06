package com.example.texnospring1222.mapper;

import com.example.texnospring1222.dao.CustomerEntity;
import com.example.texnospring1222.model.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Mapper(componentModel = "spring", imports = {ChronoUnit.class, LocalDate.class})
public interface CustomerMapper {


    @Mapping(target = "name", source = "firstName")
    @Mapping(target = "serialNumber", source = "passportAzEntity.serialNumber")
    @Mapping(target = "fin", source = "passportAzEntity.fin")
//    @Mapping(target = "expireDay", expression = "java(ChronoUnit.DAYS.between(LocalDate.now(), customer.getPassportAzEntity().getExpiredAt()))")
//    @Mapping(target = "expireDay", qualifiedByName = "getExpireDay")
    CustomerDto mapEntityToDto(CustomerEntity customer);

    @Mapping(target = "firstName", source = "name")
    @Mapping(target = "passportAzEntity.serialNumber", source = "serialNumber")
    @Mapping(target = "passportAzEntity.fin", source = "fin")
    CustomerEntity mapDtoToEntity(CustomerDto customerDto);

    @Named("getExpireDay")
    default Long test(CustomerEntity customer) {
        return ChronoUnit.DAYS.between(LocalDate.now(), customer.getPassportAzEntity().getExpiredAt());
    }

//    public CustomerDto mapEntityToDto(CustomerEntity customerEntity) {
//
//        Long p2 = ChronoUnit.DAYS.between(LocalDate.now(), customerEntity.getPassportAzEntity().getExpiredAt());
//
//        return new CustomerDto(
//                customerEntity.getFirstName(),
//                customerEntity.getAge(),
//                customerEntity.getPassportAzEntity().getSerialNumber(),
//                p2.intValue()
//        );
//    }
}
