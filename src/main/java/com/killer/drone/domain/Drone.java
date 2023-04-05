package com.killer.drone.domain;

import com.killer.drone.enums.DroneModels;
import com.killer.drone.enums.DroneState;

public class Drone {
	Long id;
	String serialNumber;
	DroneModels model;
	Double weightLimit;
	Double batteryCapacity;
	DroneState state;
	
	//All Arguments Constructor
	public Drone(String serialNumber, DroneModels model, Double weightLimit, Double batteryCapacity, DroneState state) {
		this.serialNumber = serialNumber;
		this.model = model;
		this.weightLimit = weightLimit;
		this.batteryCapacity = batteryCapacity;
		this.state = state;
	}
	//No Arguments Constructor
	public Drone() {}
	
	
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
		return "Drone [id=" + id + ", serialNumber=" + serialNumber + ", model=" + model + ", weightLimit="
				+ weightLimit + ", batteryCapacity=" + batteryCapacity + ", state=" + state + "]";
	}
	
	
}
