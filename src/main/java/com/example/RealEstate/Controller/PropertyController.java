package com.example.RealEstate.Controller;

import com.example.RealEstate.Dto.PropertyDTO;
import com.example.RealEstate.Dto.PropertyImageDTO;
import com.example.RealEstate.Dto.PropertyResponseDTO;
import com.example.RealEstate.Service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping
    public ResponseEntity<PropertyResponseDTO> createProperty(@RequestBody PropertyDTO propertyDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.createProperty(propertyDTO));
    }

    @GetMapping
    public ResponseEntity<List<PropertyResponseDTO>> getAllProperties() {
        return ResponseEntity.ok(propertyService.getAllProperties());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponseDTO> getPropertyById(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.getPropertyById(id));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<PropertyResponseDTO>> getPropertiesByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(propertyService.getPropertiesByOwner(ownerId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyResponseDTO> updateProperty(@PathVariable Long id, @RequestBody PropertyDTO propertyDTO) {
        return ResponseEntity.ok(propertyService.updateProperty(id, propertyDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/images")
    public ResponseEntity<List<PropertyImageDTO>> uploadPropertyImages(
            @PathVariable Long id,
            @RequestParam("files") MultipartFile[] files
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.uploadPropertyImages(id, files));
    }

    @DeleteMapping("/{propertyId}/images/{imageId}")
    public ResponseEntity<Void> deletePropertyImage(@PathVariable Long propertyId, @PathVariable Long imageId) {
        propertyService.deletePropertyImage(propertyId, imageId);
        return ResponseEntity.noContent().build();
    }
}
