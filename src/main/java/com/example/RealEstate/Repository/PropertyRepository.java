package com.example.RealEstate.Repository;

import com.example.RealEstate.Model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByOwnerUserId(Long ownerId);
}
