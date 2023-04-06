package com.killer.drone.persistence.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.killer.drone.enums.DroneModels;
import com.killer.drone.enums.DroneState;

@Entity
public class DroneEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	Long id;
	@Column(unique = true, length = 100)
	String serialNumber;
	@Enumerated(EnumType.STRING)
	DroneModels model;
	Double weightLimit;
	Double batteryCapacity;
	@Enumerated(EnumType.STRING)
	DroneState state;
	@OneToMany
	List<MedicationEntity> medicationItems;
	
	public DroneEntity() {}
	
	public DroneEntity(String serialNumber, DroneModels model, Double weightLimit, Double batteryCapacity, DroneState state) {
		this.serialNumber = serialNumber;
		this.model = model;
		this.weightLimit = weightLimit;
		this.batteryCapacity = batteryCapacity;
		this.state = state;
	}
		
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public DroneModels getModel() {
		return model;
	}
	public void setModel(DroneModels model) {
		this.model = model;
	}
	public Double getWeightLimit() {
		return weightLimit;
	}
	public void setWeightLimit(Double weightLimit) {
		this.weightLimit = weightLimit;
	}
	public Double getBatteryCapacity() {
		return batteryCapacity;
	}
	public void setBatteryCapacity(Double batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}
	public DroneState getState() {
		return state;
	}
	public void setState(DroneState state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "DroneEntity [id=" + id + ", serialNumber=" + serialNumber + ", model=" + model + ", weightLimit="
				+ weightLimit + ", batteryCapacity=" + batteryCapacity + ", state=" + state + "]";
	}
	
	
}
