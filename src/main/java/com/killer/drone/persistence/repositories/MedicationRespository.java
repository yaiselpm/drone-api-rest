package com.killer.drone.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.killer.drone.persistence.entities.MedicationEntity;

public interface MedicationRespository extends JpaRepository<MedicationEntity, String> {
	
	@Modifying
	@Query(value= "INSERT INTO drone_entity_medication_items (drone_entity_id,medication_items_code) values (:id , :code)", nativeQuery = true)
	public Integer saveMedicationToDroneRelationship(Long id, String code);
	
	@Query(value= "SELECT DISTINCT medication_entity.* FROM medication_entity, drone_entity_medication_items where drone_entity_medication_items.drone_entity_id = :id", nativeQuery= true)
	public List<MedicationEntity> getLoadedMedicationItems(Long id);
}
