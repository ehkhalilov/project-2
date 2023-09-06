package com.example.texnospring1222.service

import com.example.texnospring1222.client.TestClient
import com.example.texnospring1222.dao.CustomerEntity
import com.example.texnospring1222.dao.CustomerRepository
import com.example.texnospring1222.exception.NotFoundException
import com.example.texnospring1222.mapper.CustomerMapper
import com.example.texnospring1222.model.CustomerDto
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.transaction.UnexpectedRollbackException
import spock.lang.Specification
import spock.lang.Unroll

class CustomerServiceTest extends Specification {
    private CustomerRepository customerRepository
    private CustomerMapper customerMapper
    private TestClient testClient
    private CustomerService customerService

    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    void setup() {
        customerRepository = Mock()
        customerMapper = Mock()
        testClient = Mock()
        customerService = new CustomerService(customerRepository, customerMapper, testClient)
    }

    def "SaveCustomer success"() {
       given:
       def customerDto = random.nextObject(CustomerDto)
       def customerEntity = random.nextObject(CustomerEntity)

       when:
       customerService.saveCustomer(customerDto)

       then:
       1 * customerMapper.mapDtoToEntity(customerDto) >> {
           throw new RuntimeException("map is failed")
       }
       0 * customerRepository.save(_)

       def exception = thrown(RuntimeException)
        exception.message == "map is failed"
    }

    def "GetCustomer success"() {
        given:
        def customerId = random.nextInt()
        def customerEntity = random.nextObject(CustomerEntity)
        def message = "This is message"
        def customerDto = random.nextObject(CustomerDto)

        when:
        def result = customerService.getCustomer(customerId)

        then:
        1 * customerRepository.findById(customerId) >> Optional.of(customerEntity)
        1 * testClient.sayHello(customerEntity.firstName) >> message
        1 * customerMapper.mapEntityToDto(customerEntity) >> customerDto

        result == customerDto
    }

    def "GetCustomer CustomerNotFound exception"() {
        given:
        def customerId = random.nextInt()

        when:
        def result = customerService.getCustomer(customerId)

        then:
        1 * customerRepository.findById(customerId) >> Optional.empty()
        0 * testClient.sayHello(_)
        0 * customerMapper.mapEntityToDto(_)

        def exception = thrown(NotFoundException)
        exception.message == "CUSTOMER_NOT_FOUND"

        result == null
    }

}
