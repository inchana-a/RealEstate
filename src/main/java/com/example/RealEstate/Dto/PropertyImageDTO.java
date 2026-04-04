package com.example.RealEstate.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyImageDTO {
    private Long imageId;
    private String imageUrl;
    private Boolean isPrimary;
}
