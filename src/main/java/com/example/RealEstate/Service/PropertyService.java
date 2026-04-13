package com.example.RealEstate.Service;

import com.example.RealEstate.Enum.ListingType;
import com.example.RealEstate.Enum.PropertyType;
import com.example.RealEstate.Enum.Role;
import com.example.RealEstate.Model.Property;
import com.example.RealEstate.Model.PropertyImage;
import com.example.RealEstate.Repository.PropertyImageRepository;
import com.example.RealEstate.Repository.PropertyRepository;
import com.example.RealEstate.Specification.PropertySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.example.RealEstate.Dto.PropertyDTO;
import com.example.RealEstate.Dto.PropertyImageDTO;
import com.example.RealEstate.Dto.PropertyResponseDTO;
import com.example.RealEstate.Exceptions.ResourceNotFoundException;
import com.example.RealEstate.Model.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyImageRepository propertyImageRepository;
    private final FileStorageService fileStorageService;
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
    public PropertyResponseDTO createProperty(PropertyDTO propertyDTO) {
        User authenticatedUser = getAuthenticatedUser();
        validateOwnerAccess(propertyDTO.getOwnerId(), authenticatedUser, false);
        User owner = userService.getUserById(propertyDTO.getOwnerId());

        Property property = new Property();
        mapToEntity(propertyDTO, property, owner);
        Property savedProperty = propertyRepository.save(property);
        syncPropertyImages(savedProperty, propertyDTO.getImageUrls());
        return toResponseDto(savedProperty);
    }
    //  GET ALL PROPERTIES
    public List<PropertyResponseDTO> getAllProperties() {
        return propertyRepository.findAll().stream()
                .map(this::toResponseDto)
                .toList();
    }
    //  GET PROPERTY BY ID
    public PropertyResponseDTO getPropertyById(Long id) {
        return toResponseDto(getPropertyEntityById(id));
    }

    public Property getPropertyEntityById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + id));
    }

    public List<PropertyResponseDTO> getPropertiesByOwner(Long ownerId) {
        userService.getUserById(ownerId);
        return propertyRepository.findByOwnerUserId(ownerId).stream()
                .map(this::toResponseDto)
                .toList();
    }

    public PropertyResponseDTO updateProperty(Long id, PropertyDTO propertyDTO) {
        Property existingProperty = getPropertyEntityById(id);
        User authenticatedUser = getAuthenticatedUser();
        validateOwnerAccess(existingProperty.getOwner().getUserId(), authenticatedUser, true);
        User owner = userService.getUserById(propertyDTO.getOwnerId());
        validateOwnerAssignment(existingProperty.getOwner().getUserId(), owner.getUserId(), authenticatedUser);
        mapToEntity(propertyDTO, existingProperty, owner);
        Property savedProperty = propertyRepository.save(existingProperty);
        syncPropertyImages(savedProperty, propertyDTO.getImageUrls());
        return toResponseDto(savedProperty);
    }

    public void deleteProperty(Long id) {
        Property property = getPropertyEntityById(id);
        User authenticatedUser = getAuthenticatedUser();
        validateOwnerAccess(property.getOwner().getUserId(), authenticatedUser, true);
        deletePropertyImages(property);
        propertyImageRepository.deleteByProperty(property);
        propertyRepository.delete(property);
    }

    public List<PropertyImageDTO> uploadPropertyImages(Long propertyId, MultipartFile[] files) {
        if (files == null || files.length == 0) {
            throw new IllegalArgumentException("At least one image file is required");
        }

        Property property = getPropertyEntityById(propertyId);
        User authenticatedUser = getAuthenticatedUser();
        validateOwnerAccess(property.getOwner().getUserId(), authenticatedUser, true);

        boolean hasPrimaryImage = propertyImageRepository.findByPropertyPropertyId(propertyId).stream()
                .anyMatch(image -> Boolean.TRUE.equals(image.getIsPrimary()));

        List<PropertyImage> savedImages = new ArrayList<>();
        for (MultipartFile file : files) {
            String imageUrl = fileStorageService.storePropertyImage(file);

            PropertyImage image = new PropertyImage();
            image.setProperty(property);
            image.setImageUrl(imageUrl);
            image.setIsPrimary(!hasPrimaryImage && savedImages.isEmpty());
            savedImages.add(propertyImageRepository.save(image));
        }

        return savedImages.stream()
                .map(image -> new PropertyImageDTO(image.getImageId(), image.getImageUrl(), image.getIsPrimary()))
                .toList();
    }

    public void deletePropertyImage(Long propertyId, Long imageId) {
        Property property = getPropertyEntityById(propertyId);
        User authenticatedUser = getAuthenticatedUser();
        validateOwnerAccess(property.getOwner().getUserId(), authenticatedUser, true);

        PropertyImage image = propertyImageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("Property image not found with id: " + imageId));

        if (!image.getProperty().getPropertyId().equals(propertyId)) {
            throw new IllegalArgumentException("Image does not belong to the specified property");
        }

        fileStorageService.deleteFile(image.getImageUrl());
        propertyImageRepository.delete(image);

        List<PropertyImage> remainingImages = propertyImageRepository.findByPropertyPropertyId(propertyId);
        if (!remainingImages.isEmpty() && remainingImages.stream().noneMatch(img -> Boolean.TRUE.equals(img.getIsPrimary()))) {
            PropertyImage firstImage = remainingImages.get(0);
            firstImage.setIsPrimary(true);
            propertyImageRepository.save(firstImage);
        }
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
        property.setListingType(propertyDTO.getListingType());
    }

    private void syncPropertyImages(Property property, List<String> imageUrls) {
        deletePropertyImages(property);
        propertyImageRepository.deleteByProperty(property);

        if (imageUrls == null || imageUrls.isEmpty()) {
            return;
        }

        List<PropertyImage> images = new ArrayList<>();
        for (int index = 0; index < imageUrls.size(); index++) {
            String imageUrl = imageUrls.get(index);
            if (imageUrl == null || imageUrl.isBlank()) {
                continue;
            }

            PropertyImage image = new PropertyImage();
            image.setProperty(property);
            image.setImageUrl(imageUrl);
            image.setIsPrimary(index == 0);
            images.add(image);
        }

        if (!images.isEmpty()) {
            propertyImageRepository.saveAll(images);
        }
    }

    private void deletePropertyImages(Property property) {
        List<PropertyImage> existingImages = propertyImageRepository.findByPropertyPropertyId(property.getPropertyId());
        for (PropertyImage image : existingImages) {
            fileStorageService.deleteFile(image.getImageUrl());
        }
    }

    private PropertyResponseDTO toResponseDto(Property property) {
        PropertyResponseDTO response = new PropertyResponseDTO();
        response.setPropertyId(property.getPropertyId());
        response.setOwnerId(property.getOwner().getUserId());
        response.setOwnerName(property.getOwner().getFullName());
        response.setOwnerEmail(property.getOwner().getEmail());
        response.setOwnerPhone(property.getOwner().getPhone());
        response.setTitle(property.getTitle());
        response.setDescription(property.getDescription());
        response.setType(property.getType());
        response.setStatus(property.getStatus());
        response.setPrice(property.getPrice());
        response.setAreaSqft(property.getAreaSqft());
        response.setBedrooms(property.getBedrooms());
        response.setBathrooms(property.getBathrooms());
        response.setAddress(property.getAddress());
        response.setCity(property.getCity());
        response.setState(property.getState());
        response.setCountry(property.getCountry());
        response.setLatitude(property.getLatitude());
        response.setLongitude(property.getLongitude());
        response.setCreatedAt(property.getCreatedAt());
        response.setListingType(property.getListingType());
        response.setImages(propertyImageRepository.findByPropertyPropertyId(property.getPropertyId()).stream()
                .map(image -> new PropertyImageDTO(image.getImageId(), image.getImageUrl(), image.getIsPrimary()))
                .toList());
        return response;
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("Authentication is required");
        }

        String email = authentication.getName();
        return userService.getUserByEmail(email);
    }

    private void validateOwnerAccess(Long ownerId, User authenticatedUser, boolean existingListing) {
        if (authenticatedUser.getRole() == Role.ADMIN) {
            return;
        }

        if (!authenticatedUser.getUserId().equals(ownerId)) {
            String action = existingListing ? "modify" : "create";
            throw new AccessDeniedException("You can only " + action + " listings for your own account");
        }
    }

    private void validateOwnerAssignment(Long currentOwnerId, Long requestedOwnerId, User authenticatedUser) {
        if (authenticatedUser.getRole() == Role.ADMIN) {
            return;
        }

        if (!currentOwnerId.equals(requestedOwnerId)) {
            throw new AccessDeniedException("You cannot transfer a property listing to another owner");
        }
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

