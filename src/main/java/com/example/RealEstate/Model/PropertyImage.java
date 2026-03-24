package com.example.RealEstate.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "property_images")
public class PropertyImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    private String imageUrl;
    private Boolean isPrimary;
}
