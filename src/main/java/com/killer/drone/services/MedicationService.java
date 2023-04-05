package com.killer.drone.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.killer.drone.domain.Drone;
import com.killer.drone.domain.Medication;
import com.killer.drone.mappers.MedicationMapper;
import com.killer.drone.persistence.entities.MedicationEntity;
import com.killer.drone.persistence.repositories.MedicationRespository;

@Service
public class MedicationService {
		
	private MedicationRespository medicationRespository;
	private MedicationMapper mapper;
		
	public MedicationService(MedicationRespository medicationRespository, MedicationMapper mapper) {
		this.medicationRespository = medicationRespository;
		this.mapper = mapper;
	}

	@Transactional
	public ResponseEntity<?> registerMedication(Medication medication){
		MedicationEntity medicationEntity = medicationRespository.save(MedicationMapper.domainToEntity(medication));
		return ResponseEntity.ok("Medication successfully registered "+medicationEntity);
	}
	public boolean checkIfExist(MedicationEntity medicationEntity) {
		return medicationRespository.existsById(medicationEntity.getCode());
	}
	public Medication getById(String code) {
		MedicationEntity medicationEntity =  medicationRespository.getReferenceById(code);
		return MedicationMapper.entityToDomain(medicationEntity);
	}
	public ResponseEntity<?> saveMedicationToDroneRelationship(Long id, List<Medication> medicationList){
		for (Medication medicationEntity : medicationList) {
			medicationRespository.saveMedicationToDroneRelationship(id, medicationEntity.getCode());
		}
		return ResponseEntity.ok("The query was successfully run");
	}
	
	public List<MedicationEntity> getloadedMedicationItemsToADrone(Drone drone){
		
		return medicationRespository.getLoadedMedicationItems(drone.getId());
	}
}
