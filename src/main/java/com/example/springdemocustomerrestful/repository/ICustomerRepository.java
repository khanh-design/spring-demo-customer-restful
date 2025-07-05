package com.example.springdemocustomerrestful.repository;

import com.example.springdemocustomerrestful.model.Customer;
import com.example.springdemocustomerrestful.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {
    Iterable<Customer> findAllByProvince(Province province);

    Page<Customer> findAll(Pageable pageable);
}
