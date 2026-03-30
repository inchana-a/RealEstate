package com.example.RealEstate.Model;
import com.example.RealEstate.Enum.PaymentMethod;
import com.example.RealEstate.Enum.TransactionStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod; // stripe, paypal, bank_transfer

    @Enumerated(EnumType.STRING)
    private TransactionStatus status; // pending, completed, failed, refunded

    private LocalDateTime transactionDate;
}
