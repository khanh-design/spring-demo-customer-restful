package com.example.springdemocustomerrestful.repository;

import com.example.springdemocustomerrestful.model.Province;
import org.springframework.data.repository.CrudRepository;

public interface IProvinceRepository extends CrudRepository<Province, Long> {
}
