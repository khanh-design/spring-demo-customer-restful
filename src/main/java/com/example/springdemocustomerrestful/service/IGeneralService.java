package com.example.springdemocustomerrestful.service;

import com.example.springdemocustomerrestful.exception.DuplicateEmailException;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);
    void remove(Long id);
}
