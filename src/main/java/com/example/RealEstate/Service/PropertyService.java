package com.example.RealEstate.Service;

import com.example.RealEstate.Enum.PropertyType;
import com.example.RealEstate.Model.Property;
import com.example.RealEstate.Repository.PropertyRepository;
import com.example.RealEstate.Specification.PropertySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

   public Page<Property> searchProperties(
            String city,
            PropertyType type,
            Integer bhk,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Double minArea,
            Double maxArea,
            String sortBy,
            String sortDir,
            int page,
            int size
    ){
       Sort sort = sortDir.equalsIgnoreCase("desc")
               ? Sort.by(sortBy).descending()
               : Sort.by(sortBy).ascending();

       Pageable pageable = PageRequest.of(page, size, sort);

       var spec = PropertySpecification.filterProperties(
               city, type, bhk, minPrice, maxPrice, minArea, maxArea
       );

       return propertyRepository.findAll(spec, pageable);
   }

    // 📦 GET ALL PROPERTIES
    public Page<Property> getAllProperties(int page, int size) {
        return propertyRepository.findAll(PageRequest.of(page, size));
    }

    // 📄 GET PROPERTY BY ID
    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));
    }
}
