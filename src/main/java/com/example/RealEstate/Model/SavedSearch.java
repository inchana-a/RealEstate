package com.example.RealEstate.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "saved_searches")
public class SavedSearch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long searchId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String city;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String propertyType;
    private LocalDateTime createdAt;
}
