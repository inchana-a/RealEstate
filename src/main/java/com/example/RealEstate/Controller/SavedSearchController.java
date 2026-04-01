package com.example.RealEstate.Controller;

import com.example.RealEstate.Model.SavedSearch;
import com.example.RealEstate.Repository.SavedSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SavedSearchController {

    private final SavedSearchRepository savedSearchrepository;

    @PostMapping("/save")
    public SavedSearch saveSearch(@RequestBody SavedSearch search) {

        return savedSearchrepository.save(search);
    }

    @GetMapping("/user/{userId}")
    public List<SavedSearch> getUserSearches(@PathVariable Long userId) {
        return savedSearchrepository.findAll(); // simplify for now
    }
}
