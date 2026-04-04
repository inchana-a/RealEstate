package com.example.RealEstate.Dto;

import com.example.RealEstate.Enum.ListingType;
import com.example.RealEstate.Enum.PropertyStatus;
import com.example.RealEstate.Enum.PropertyType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PropertyResponseDTO {
    private Long propertyId;
    private Long ownerId;
    private String title;
    private String description;
    private PropertyType type;
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
    private ListingType listingType;
    private List<PropertyImageDTO> images;
}
