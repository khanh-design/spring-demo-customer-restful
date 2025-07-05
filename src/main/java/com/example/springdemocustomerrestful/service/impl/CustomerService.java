package com.example.springdemocustomerrestful.service.impl;

import com.example.springdemocustomerrestful.exception.DuplicateEmailException;
import com.example.springdemocustomerrestful.model.Customer;
import com.example.springdemocustomerrestful.model.Province;
import com.example.springdemocustomerrestful.repository.ICustomerRepository;
import com.example.springdemocustomerrestful.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void save(Customer customer) {
//        try {
//            customerRepository.save(customer);
//        } catch (DataIntegrityViolationException ex) {
//            throw new DuplicateEmailException();
//        }
//        return customerRepository.save(customer);
        customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Iterable<Customer> findAllByProvince(Province province) {
        return customerRepository.findAllByProvince(province);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
}
