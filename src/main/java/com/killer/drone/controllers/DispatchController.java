package com.killer.drone.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.killer.drone.domain.Drone;
import com.killer.drone.domain.Medication;
import com.killer.drone.enums.DroneState;
import com.killer.drone.mappers.DroneInDTO;
import com.killer.drone.mappers.DroneMapper;
import com.killer.drone.mappers.LoadingDroneObject;
import com.killer.drone.mappers.MedicationMapper;
import com.killer.drone.persistence.entities.DroneEntity;
import com.killer.drone.persistence.entities.MedicationEntity;
import com.killer.drone.services.DroneService;
import com.killer.drone.services.MedicationService;

/**
 * @author yaisel
 *
 */
@RestController
@RequestMapping
public class DispatchController {
	
	@Autowired
	DroneService droneService;
	@Autowired
	MedicationService medicationService;
 
	private String picture;
	
	@PostMapping("/registerDrone")
	public ResponseEntity<?> registerDrone(@RequestBody @Validated DroneInDTO drone){
		if (drone.getWeightLimit() > 500) {
			return ResponseEntity.badRequest().body("The weight limit can`t be more than 500");
		}else if (drone.getBatteryCapacity() > 100) {
			return ResponseEntity.badRequest().body("The battery capacity can`t be more than 100%");
		}
		
		ResponseEntity<?> dE = droneService.registerADrone(drone);
		return ResponseEntity.ok("Drone registered successfully "+ dE.getBody());
	}
	
	@PostMapping("/loadingDrone")
	public ResponseEntity<?> loadingDroneWithMedication( @RequestBody @Validated LoadingDroneObject loadingDroneObject){
		Drone drone = loadingDroneObject.getDrone();
		List<Medication> medicationList = loadingDroneObject.getMedicationList();
		
		if (!droneService.checkIfExist(DroneMapper.domainToEntity(drone))) {
			return (ResponseEntity<?>) ResponseEntity.badRequest().body("Drone not exist");
		}
		Drone droneAux = droneService.getDroneBatteryLevel(DroneMapper.domainToEntity(drone));
		Double batteryLevel = droneAux.getBatteryCapacity();
		DroneState state = droneAux.getState();
		if (batteryLevel<25 || state!=DroneState.IDLE) {
			return (ResponseEntity<?>) ResponseEntity.badRequest().body("Drone can't be load because is in use or the battery level is lower 25");
		}
		double sum=0;
		
		for (Medication medication : medicationList) {
			if (!medicationService.checkIfExist(MedicationMapper.domainToEntity(medication))) {
				return (ResponseEntity<?>) ResponseEntity.badRequest().body("The medication item can't be load because it don't exist. Check the list again "+ medication);
			}
			sum+=medication.getWeight();
		}
		
		if (droneAux.getWeightLimit()<sum) {
			return (ResponseEntity<?>) ResponseEntity.badRequest().body("Drone can't be load because the weight of charge is more that it can carry");
		}
		droneAux.setState(DroneState.LOADING);		
		droneService.updateDroneState(DroneMapper.domainToEntity(droneAux));
		
		medicationService.saveMedicationToDroneRelationship(droneAux.getId(), medicationList);
		droneAux.setState(DroneState.LOADED);
		droneService.updateDroneState(DroneMapper.domainToEntity(droneAux));		
		return ResponseEntity.ok("The Drone was loaded successfully");
	}
	
	@PostMapping("/registerMedication")
	public ResponseEntity<?> registerMedication(@RequestBody @Validated Medication medication){
		if (!medication.validateCode(medication.getCode())) {
			return ResponseEntity.badRequest().body("The code only allowed only upper case letters, underscore and numbers");
		}else if (!medication.validateName(medication.getName()) ) {
			return ResponseEntity.badRequest().body("The name only allowed only letters, score, underscore and numbers");
		}
		
		ResponseEntity<?> medic = medicationService.registerMedication(medication);
		return ResponseEntity.ok("Medication registered successfully "+ medic.getBody());
	}
	@GetMapping("/checkLoadedMedication")
	public List<MedicationEntity> checkLoadedMedicationItemsForDrone(@RequestBody Drone drone){
	 if (!droneService.checkIfExist(DroneMapper.domainToEntity(drone))) {
		 return (List<MedicationEntity>) ResponseEntity.badRequest().body("The drone don't exist");
	}
	 	return medicationService.getloadedMedicationItemsToADrone(drone);
	}
	
	@GetMapping("/checkAvailableDrone")
	public List<DroneEntity> checkAvailableDroneForLoading(){
		return droneService.getAllAvailableDrone();
		
	}
	
	@GetMapping("/checkBatteryLevel")
	public ResponseEntity<?> checkBatteryForDrone(@RequestBody Drone drone){
		Drone droneaux = droneService.getDroneBatteryLevel(DroneMapper.domainToEntity(drone));
		return ResponseEntity.ok(droneaux.getBatteryCapacity());
	}
	/**
	 * @author yaisel
	 *@param file
	 *@apiNote This function is used for to load the image of the medication item,
	 */	 	
	@PostMapping("/imageToMedication")
	public ResponseEntity<?> saveImage(@RequestParam("file") MultipartFile file) throws IOException {
		
		try {
			if (!file.isEmpty()) {
				
				Path directoryImage = Paths.get("src//main//resources//static//images");
				String ruta = directoryImage.toFile().getAbsolutePath();
				try {
					byte[] bytesImg = file.getBytes();
					Path routeComplete = Paths.get(ruta+"//"+ file.getOriginalFilename());
					Files.write(routeComplete, bytesImg);
				 	picture= file.getOriginalFilename();
				} catch (IOException e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				
			}
		
		} catch (Exception e) {
			System.out.println(e);
		}
		
	 	return ResponseEntity.ok(picture);
	}
	
	// Return the image from the classpath location using ResponseEntity
    @GetMapping("/img/{id}")
    public ResponseEntity<byte[]> fromClasspathAsResEntity(@PathVariable("id") String id) throws IOException {
    	
    	String pictureName = medicationService.getById(id).getImage();

        ClassPathResource imageFile = new ClassPathResource("static//images//"+ pictureName);

        byte[] imageBytes = StreamUtils.copyToByteArray(imageFile.getInputStream());

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }
//	Insert into drone_entity_medication_items (drone_entity_id,medication_items_code) values (1 , 'b')
//	SELECT DISTINCT medication_entity.* FROM medication_entity, drone_entity_medication_items where drone_entity_medication_items.drone_entity_id = 1

}
