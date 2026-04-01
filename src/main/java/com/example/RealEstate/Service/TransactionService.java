package com.example.RealEstate.Service;

import com.example.RealEstate.Enum.PaymentMethod;
import com.example.RealEstate.Model.Property;
import com.example.RealEstate.Model.Transaction;
import com.example.RealEstate.Model.User;
import com.example.RealEstate.Repository.PropertyRepository;
import com.example.RealEstate.Repository.TransactionRepository;
import com.example.RealEstate.Repository.UserRepository;
import lombok.AllArgsConstructor;
import com.example.RealEstate.Enum.TransactionStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;

    //  CREATE PAYMENT (SIMULATED)
    public Transaction makePayment(Long userId, Long propertyId,Double amount){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found for id" +userId));

        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found for id " +propertyId));

        Transaction transaction = new Transaction();
        transaction.setBuyer(user);
        transaction.setProperty(property);
        transaction.setAmount(java.math.BigDecimal.valueOf(amount));
        transaction.setTransactionDate(LocalDateTime.now());

        // SIMULATION
        transaction.setStatus(TransactionStatus.COMPLETED); // completed
        transaction.setPaymentMethod(PaymentMethod.PAYPAL);
        return transactionRepository.save(transaction);
    }
    // 📄 GET BY ID
    public Transaction getTransaction(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    // 📄 GET USER TRANSACTIONS
    public List<Transaction> getUserTransactions(Long userId) {
        return transactionRepository.findByBuyer_UserId(userId);
    }

    // 🔁 REFUND
    public Transaction refundTransaction(Long id) {
        Transaction transaction = getTransaction(id);

        transaction.setStatus(TransactionStatus.REFUNDED); // refunded

        return transactionRepository.save(transaction);
    }
}
