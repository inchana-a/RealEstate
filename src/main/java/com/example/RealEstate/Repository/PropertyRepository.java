package com.example.RealEstate.Repository;

import com.example.RealEstate.Model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PropertyRepository extends JpaRepository<Property, Long> , JpaSpecificationExecutor<Property> {
}
