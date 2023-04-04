package com.killer.drone.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.killer.drone.enums.DroneModels;
import com.killer.drone.enums.DroneState;

@Entity
public class DroneEntity {
	
	@Id
	Long serialNumber;
	DroneModels model;
	Double weightLimit;
	Double batteryCapacity;
	DroneState state;
	
	public DroneEntity() {}
	
	public DroneEntity(Long serialNumber, DroneModels model, Double weightLimit, Double batteryCapacity, DroneState state) {
		this.serialNumber = serialNumber;
		this.model = model;
		this.weightLimit = weightLimit;
		this.batteryCapacity = batteryCapacity;
		this.state = state;
	}
	
	public Long getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Long serialNumber) {
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
	
	
}
