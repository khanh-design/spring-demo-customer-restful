package com.example.springdemocustomerrestful.controller.rest;

import com.example.springdemocustomerrestful.exception.DuplicateEmailException;
import com.example.springdemocustomerrestful.model.Customer;
import com.example.springdemocustomerrestful.service.impl.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
    private static final Logger log = LoggerFactory.getLogger(CustomerRestController.class);
    @Autowired
    private CustomerService iCustomerService;

    @GetMapping
    public ResponseEntity<Iterable<Customer>> findAllCustomers() {
        List<Customer> customers = (List<Customer>) iCustomerService.findAll();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = iCustomerService.findById(id);
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customer.get(), HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer)  {
//        return new ResponseEntity<>(iCustomerService.save(customer), HttpStatus.CREATED);
//    }
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        iCustomerService.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Optional<Customer> customer1 = iCustomerService.findById(id);
        if (!customer1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customer.setId(customer1.get().getId());
        iCustomerService.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        Optional<Customer> customer1 = iCustomerService.findById(id);
        if (!customer1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iCustomerService.remove(id);
        return new ResponseEntity<>("successful",HttpStatus.OK);
    }
}
