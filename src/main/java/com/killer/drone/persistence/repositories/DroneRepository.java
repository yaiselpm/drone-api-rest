package com.killer.drone.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.killer.drone.persistence.entities.DroneEntity;

public interface DroneRepository extends JpaRepository<DroneEntity, Long>{

}
