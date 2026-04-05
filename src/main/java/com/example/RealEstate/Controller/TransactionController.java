package com.example.RealEstate.Controller;

import com.example.RealEstate.Model.Transaction;
import com.example.RealEstate.Service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@AllArgsConstructor
public class TransactionController {

    private TransactionService transactionService;

    // 💰 PAY (SIMULATED PAYPAL)
    @PostMapping("/pay")
    public Transaction makePayment(
            @RequestParam Long userId,
            @RequestParam Long propertyId,
            @RequestParam Double amount
    ) {
        return transactionService.makePayment(userId, propertyId, amount);
    }

    // 📄 GET BY ID
    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable Long id) {
        return transactionService.getTransaction(id);
    }

    // 📄 GET USER TRANSACTIONS
    @GetMapping("/user/{userId}")
    public List<Transaction> getUserTransactions(@PathVariable Long userId) {
        return transactionService.getUserTransactions(userId);
    }

    // 🔁 REFUND
    @PostMapping("/{id}/refund")
    public Transaction refund(@PathVariable Long id) {
        return transactionService.refundTransaction(id);
    }
}
