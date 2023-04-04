package com.killer.drone.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.killer.drone.domain.Drone;
import com.killer.drone.domain.Medication;
import com.killer.drone.enums.DroneState;
import com.killer.drone.mappers.DroneInDTO;
import com.killer.drone.services.DroneService;

@RestController
@RequestMapping
public class DispatchController {
	
	@Autowired
	DroneService droneService;
 
	@PostMapping("/registerDrone")
	public ResponseEntity<?> registerDrone(@RequestBody @Validated DroneInDTO drone){
		if (drone.getWeightLimit() > 500) {
			return ResponseEntity.badRequest().body("The weight limit can`t be more than 500");
		}else if (drone.getBatteryCapacity() > 100) {
			return ResponseEntity.badRequest().body("The battery capacity can`t be more than 100%");
		}
		
		droneService.registerADrone(drone);
		return ResponseEntity.ok("Drone registered successfully"+ drone);
	}
	
	@PostMapping
	public ResponseEntity<Drone> loadingDroneWithMedication(Drone drone, List<Medication> medicationList){
		
	}
	
	@GetMapping
	public ResponseEntity<Drone> checkLoadedMedicationItemsForDrone(Drone drone){
		
	}
	
	@GetMapping
	public ResponseEntity<List<Drone>> checkAvaliableDroneForLoading(){
		
	}
	
	@GetMapping
	public ResponseEntity<Drone> checkBatteryForDrone(Drone drone){
		
	}
	
}
