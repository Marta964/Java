package com.example.convert.repository;

import com.example.convert.model.ExchangeRate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRateRepository extends CrudRepository<ExchangeRate,Long> {
    @Query(value = "SELECT a FROM ExchangeRate a WHERE a.from = :from AND a.to =:to")
    List<ExchangeRate> getExchangeRateByPair(@Param("from")String from,@Param("to")String to);
    List<ExchangeRate> findAll();
    boolean existsByRate(Float rate);
    boolean existsByFrom(String from);
    boolean existsByTo(String to);
}
