package com.killer.drone.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.killer.drone.persistence.entities.MedicationEntity;

public interface MedicationRespository extends JpaRepository<MedicationEntity, String> {
	
}
