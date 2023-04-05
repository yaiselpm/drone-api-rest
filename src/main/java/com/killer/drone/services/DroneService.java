package com.killer.drone.services;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.killer.drone.domain.Drone;
import com.killer.drone.mappers.DroneInDTO;
import com.killer.drone.mappers.DroneMapper;
import com.killer.drone.persistence.entities.DroneEntity;
import com.killer.drone.persistence.repositories.DroneRepository;

@Service
public class DroneService {

	private DroneRepository droneRepository;
	private DroneMapper mapperDrone;
	
	public DroneService(DroneRepository droneRepository, DroneMapper mapperDrone) {
		this.droneRepository = droneRepository;
		this.mapperDrone = mapperDrone;
	}

	@Transactional
	public ResponseEntity<?> registerADrone(DroneInDTO droneinDTO) {
		Drone drone = mapperDrone.map(droneinDTO);
		DroneEntity dE= droneRepository.save(DroneMapper.domainToEntity(drone));
		return ResponseEntity.ok(DroneMapper.entityToDomain(dE));
	}
	
	public Drone getDroneBatteryLevel(DroneEntity drone) {
		return droneRepository.findById(drone.getId())
				.map(DroneMapper::entityToDomain)
				.orElseThrow();
	}
}
