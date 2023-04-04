package com.killer.drone.services;

import org.springframework.stereotype.Service;

import com.killer.drone.domain.Drone;
import com.killer.drone.mappers.DroneMapper;
import com.killer.drone.persistence.repositories.DroneRepository;

@Service
public class DroneService {

	DroneRepository droneRepository;
	
	public void registerADrone(Drone drone) {
		droneRepository.save(DroneMapper.domainToEntity(drone));
	}
}
