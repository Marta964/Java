package com.example.convert.repository;

import com.example.convert.model.Convert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConvertRepository extends CrudRepository<Convert, Long> {
    List<Convert> findAll();
}
