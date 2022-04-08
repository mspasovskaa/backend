package com.backend.service;

import com.backend.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Optional<Country> findById(Long id);
    List<Country> findAll();
}
