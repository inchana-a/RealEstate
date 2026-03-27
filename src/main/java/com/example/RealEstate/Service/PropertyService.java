package com.example.RealEstate.Service;

import com.example.RealEstate.Dto.PropertyDTO;
import com.example.RealEstate.Exceptions.ResourceNotFoundException;
import com.example.RealEstate.Model.Property;
import com.example.RealEstate.Model.User;
import com.example.RealEstate.Repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserService userService;

    public Property createProperty(PropertyDTO propertyDTO) {
        User owner = userService.getUserById(propertyDTO.getOwnerId());

        Property property = new Property();
        mapToEntity(propertyDTO, property, owner);
        return propertyRepository.save(property);
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

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
}
