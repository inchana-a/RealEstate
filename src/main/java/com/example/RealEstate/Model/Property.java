package com.example.RealEstate.Model;

import com.example.RealEstate.Enum.ListingType;
import com.example.RealEstate.Enum.PropertyStatus;
import com.example.RealEstate.Enum.PropertyType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "properties")
@Data
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private PropertyType type;

    @Enumerated(EnumType.STRING)
    private PropertyStatus status;

    private BigDecimal price;
    private Double areaSqft;
    private Integer bedrooms;
    private Integer bathrooms;

    private String address;
    private String city;
    private String state;
    private String country;

    private Double latitude;
    private Double longitude;

    private LocalDateTime createdAt;

    private String nearbyPlaces;

    @Enumerated(EnumType.STRING)
    private ListingType listingType;

    // CONTACT FIELDS
    private String contactName;
    private String contactNumber;
    private String contactEmail;

    // SINGLE PREPERSIST ONLY (IMPORTANT)
    @PrePersist
    protected void onCreate() {

        createdAt = LocalDateTime.now();


            String[] names = {
                    "Amit Sharma",
                    "Ravi Patel",
                    "Neha Verma",
                    "Priya Singh",
                    "Karan Mehta",
                    "Sneha Joshi"
            };
        String[] emails = {
                "demo1@gmail.com",
                "demo2@gmail.com"
        };

        String[] prefixes = {
                "98", "91", "99", "88", "77", "96"
        };

        // fallback if ID not generated yet → use hash
        int index = (int) (System.currentTimeMillis() % names.length);

        this.contactName = names[index];

        this.contactEmail = emails[index % emails.length];

        this.contactNumber =
                prefixes[index % prefixes.length] +
                        (long)(Math.random() * 100000000L);
    }
    }
