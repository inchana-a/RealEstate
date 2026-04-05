package com.example.RealEstate.Service;

import com.example.RealEstate.Enum.ListingType;
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

import com.example.RealEstate.Dto.PropertyDTO;
import com.example.RealEstate.Exceptions.ResourceNotFoundException;
import com.example.RealEstate.Model.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserService userService;

    public Page<Property> searchProperties(
            String city,
            PropertyType type,
            Integer bhk,
            Integer bathrooms,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Double minArea,
            Double maxArea,
            String sortBy,
            String sortDir,
            int page,
            int size
    ) {
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        var spec = PropertySpecification.filterProperties(
                city, type, bhk,bathrooms, minPrice, maxPrice, minArea, maxArea
        );

        return propertyRepository.findAll(spec, pageable);
    }

    //                                 GET ALL PROPERTIES
//    public Page<Property> getAllProperties(int page, int size) {
//        return propertyRepository.findAll(PageRequest.of(page, size));
//    }

    // GET PROPERTY BY ID
//    public Property getPropertyById(Long id) {
//        return propertyRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Property not found"));
//    }
    public Property createProperty(PropertyDTO propertyDTO) {
        User owner = userService.getUserById(propertyDTO.getOwnerId());

        Property property = new Property();
        mapToEntity(propertyDTO, property, owner);
        return propertyRepository.save(property);
    }
    //  GET ALL PROPERTIES
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }
    //  GET PROPERTY BY ID
    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + id));
    }

    public List<Property> getPropertiesByOwner(Long ownerId) {
        userService.getUserById(ownerId);
        return propertyRepository.findByOwnerUserId(ownerId);
    }

    public Property updateProperty(Long id, PropertyDTO propertyDTO) {
        Property existingProperty = getPropertyById(id);
        User owner = userService.getUserById(propertyDTO.getOwnerId());
        mapToEntity(propertyDTO, existingProperty, owner);
        return propertyRepository.save(existingProperty);
    }

    public void deleteProperty(Long id) {
        Property property = getPropertyById(id);
        propertyRepository.delete(property);
    }

    private void mapToEntity(PropertyDTO propertyDTO, Property property, User owner) {
        property.setOwner(owner);
        property.setTitle(propertyDTO.getTitle());
        property.setDescription(propertyDTO.getDescription());
        property.setType(propertyDTO.getType());
        property.setStatus(propertyDTO.getStatus());
        property.setPrice(propertyDTO.getPrice());
        property.setAreaSqft(propertyDTO.getAreaSqft());
        property.setBedrooms(propertyDTO.getBedrooms());
        property.setBathrooms(propertyDTO.getBathrooms());
        property.setAddress(propertyDTO.getAddress());
        property.setCity(propertyDTO.getCity());
        property.setState(propertyDTO.getState());
        property.setCountry(propertyDTO.getCountry());
        property.setLatitude(propertyDTO.getLatitude());
        property.setLongitude(propertyDTO.getLongitude());
    }

    public Page<Property> searchByListingType(
            String city,
            ListingType listingType,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return propertyRepository.findByCityAndListingType(city, listingType, pageable);
    }
}

