package com.example.springdemocustomerrestful.service;

import com.example.springdemocustomerrestful.model.Customer;
import com.example.springdemocustomerrestful.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService extends IGeneralService<Customer> {
    Iterable<Customer> findAllByProvince(Province province);

    Page<Customer> findAll(Pageable pageable);
}
