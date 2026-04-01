package com.example.RealEstate.Repository;

import com.example.RealEstate.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>  {
    List<Transaction> findByBuyer_UserId(Long userId);

}
