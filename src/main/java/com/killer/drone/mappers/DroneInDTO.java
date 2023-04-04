package com.killer.drone.mappers;

import com.killer.drone.enums.DroneModels;

public class DroneInDTO {
	
	private Long serialNumber;
	private DroneModels model;
	private Double weightLimit;
	private Double batteryCapacity;
	
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
	

}
