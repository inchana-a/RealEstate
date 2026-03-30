package com.example.RealEstate.Repository;

import com.example.RealEstate.Model.SavedSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedSearchRepository extends JpaRepository<SavedSearch, Long> {
}
