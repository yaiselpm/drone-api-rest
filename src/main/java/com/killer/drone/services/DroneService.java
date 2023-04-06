package com.killer.drone.services;

import java.util.List;

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
	
	public Drone getDroneBatteryLevel(DroneEntity droneEntity) {
		System.out.println("asdsa");
		Drone dron=droneRepository.findById(droneEntity.getId())
				.map(DroneMapper::entityToDomain)
				.orElseThrow();
		return dron;
	}
	
	public boolean checkIfExist(DroneEntity drone) {
		return droneRepository.existsById(drone.getId());
	}
	
	@Transactional
	public ResponseEntity<?> updateDroneState(DroneEntity droneEntity){
		droneRepository.saveAndFlush(droneEntity);
		return ResponseEntity.ok("Updated successfully");		
	}
	
	public List<DroneEntity> getAllAvailableDrone(){
		return droneRepository.checkAvailablesDroneForLoading();
	}
	
	public List<DroneEntity> getAllDrone(){
		return droneRepository.findAll();
	}	
}
