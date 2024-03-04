package com.example.convert.repository;

import com.example.convert.entity.ConvertEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvertRepository extends CrudRepository<ConvertEntity,Long> {
    // ResponseEntity<Object> convertCurrency(String from, String to, double amount);

    ConvertEntity findCourse(String from,String to);
}
