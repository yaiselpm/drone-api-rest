package com.killer.drone.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.killer.drone.persistence.entities.DroneEntity;

public interface DroneRepository extends JpaRepository<DroneEntity, Long>{
	
	@Query(value = "SELECT * from drone WHERE serialNumber= :serial", nativeQuery = true)
	public DroneEntity checkBatteryLevel(Long serial);
	
	@Query(value = "SELECT * from drone_entity WHERE state= 'IDLE'", nativeQuery = true)
	public List<DroneEntity> checkAvailablesDroneForLoading();
	
	@Query(value = "SELECT * from drone_entity WHERE id= :id", nativeQuery = true)
	public DroneEntity checkLoadedMedicationItems(Long id);
	
	@Modifying
	@Query(value = "UPDATE drone_entity SET state = :state WHERE id =:id", nativeQuery = true)
	public DroneEntity updateStateDrone(Long id, String state);
}
