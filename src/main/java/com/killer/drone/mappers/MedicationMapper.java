package com.killer.drone.mappers;

import org.springframework.stereotype.Component;

import com.killer.drone.domain.Medication;
import com.killer.drone.persistence.entities.MedicationEntity;

@Component
public class MedicationMapper {
	
	public static Medication entityToDomain(MedicationEntity medicationEntity) {
		Medication medication = new Medication();
		medication.setCode(medicationEntity.getCode());
		medication.setName(medicationEntity.getName());
		medication.setWeight(medicationEntity.getWeight());
		medication.setImage(medicationEntity.getImage());
		return medication;
	}
	
	public static MedicationEntity domainToEntity(Medication medication) {
		MedicationEntity medicationEntity = new MedicationEntity();
		medicationEntity.setCode(medication.getCode());
		medicationEntity.setName(medication.getName());
		medicationEntity.setWeight(medication.getWeight());
		medicationEntity.setImage(medication.getImage());
		
		return medicationEntity;
	}
}
