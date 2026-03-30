package com.example.RealEstate.Specification;

import com.example.RealEstate.Enum.PropertyType;
import com.example.RealEstate.Model.Property;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class PropertySpecification {

    public static Specification<Property> filterProperties(
            String city,
            PropertyType type,
            Integer bhk,
            Integer bathrooms,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Double minArea,
            Double maxArea
    ) {
        return (root, query, cb) -> {

            var predicates = cb.conjunction();

            if (city != null) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("city"), city));
            }

            if (type != null) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("type"), type));
            }

            // BHK logic (IMPORTANT)
            if (type != PropertyType.LAND && bhk != null) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("bedrooms"), bhk));
            }
            if (bathrooms != null) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("bathrooms"), bathrooms));
            }
            if (minPrice != null) {
                predicates = cb.and(predicates,
                        cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            }

            if (maxPrice != null) {
                predicates = cb.and(predicates,
                        cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            if (minArea != null) {
                predicates = cb.and(predicates,
                        cb.greaterThanOrEqualTo(root.get("areaSqft"), minArea));
            }

            if (maxArea != null) {
                predicates = cb.and(predicates,
                        cb.lessThanOrEqualTo(root.get("areaSqft"), maxArea));
            }

            return predicates;
        };
    }
}
