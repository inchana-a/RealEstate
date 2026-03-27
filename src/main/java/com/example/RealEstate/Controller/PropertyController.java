package com.example.RealEstate.Controller;

import com.example.RealEstate.Dto.PropertyDTO;
import com.example.RealEstate.Model.Property;
import com.example.RealEstate.Service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody PropertyDTO propertyDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.createProperty(propertyDTO));
    }

    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        return ResponseEntity.ok(propertyService.getAllProperties());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.getPropertyById(id));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Property>> getPropertiesByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(propertyService.getPropertiesByOwner(ownerId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody PropertyDTO propertyDTO) {
        return ResponseEntity.ok(propertyService.updateProperty(id, propertyDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }
}
