package com.example.convert.repository;

import com.example.convert.model.ExchangeRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRateRepository extends CrudRepository<ExchangeRate,Long> {
    List<ExchangeRate> findAll();

}
