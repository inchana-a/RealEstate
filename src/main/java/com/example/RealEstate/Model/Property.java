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
    @JoinColumn(name = "owner_id")
    private User owner;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private PropertyType type; // apartment, house, villa, commercial, land

    @Enumerated(EnumType.STRING)
    private PropertyStatus status; // available, sold, rented

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

    @Enumerated(EnumType.STRING)
    private ListingType listingType;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
