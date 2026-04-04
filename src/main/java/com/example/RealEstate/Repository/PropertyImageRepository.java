package com.example.RealEstate.Repository;

import com.example.RealEstate.Model.Property;
import com.example.RealEstate.Model.PropertyImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyImageRepository extends JpaRepository<PropertyImage, Long> {
    List<PropertyImage> findByPropertyPropertyId(Long propertyId);

    void deleteByProperty(Property property);
}
