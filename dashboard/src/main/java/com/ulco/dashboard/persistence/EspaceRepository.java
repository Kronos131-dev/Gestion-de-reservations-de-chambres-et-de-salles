package com.ulco.dashboard.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EspaceRepository extends JpaRepository<Espace, Long> {

    @Query("SELECT e.status FROM Espace e ")
    List<Espace> getAllStatus();
}
