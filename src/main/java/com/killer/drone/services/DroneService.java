package com.killer.drone.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.killer.drone.domain.Drone;
import com.killer.drone.mappers.DroneInDTO;
import com.killer.drone.mappers.DroneMapper;
import com.killer.drone.persistence.repositories.DroneRepository;

@Service
public class DroneService {

	private DroneRepository droneRepository;
	private DroneMapper mapperDrone;
	
	@Transactional
	public void registerADrone(DroneInDTO droneinDTO) {
		Drone drone = mapperDrone.map(droneinDTO);
		droneRepository.save(DroneMapper.domainToEntity(drone));
	}
}
