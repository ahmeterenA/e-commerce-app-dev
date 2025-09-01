package com.erenarslanoglu.ecommerce.customer;


import com.erenarslanoglu.ecommerce.exceptions.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));

        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id())
                .orElseThrow(()->new CustomerNotFoundException("No customer found with id " + request.id()));

        mergeCustomer(customer,request);
        repository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if(!request.firstName().isBlank()){
            customer.setFirstName(request.firstName());
        }
        if(!request.firstName().isBlank()){
            customer.setLastName(request.lastName());
        }
        if(!request.email().isBlank()) {
            customer.setEmail(request.email());
        }
        if(request.address()!=null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        List<Customer> customers = repository.findAll();

        return customers.stream()
                .map(mapper::toCustomerResponse)
                .collect(Collectors.toList());
    }
}
