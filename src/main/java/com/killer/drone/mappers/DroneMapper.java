package com.killer.drone.mappers;

import com.killer.drone.domain.Drone;
import com.killer.drone.persistence.entities.DroneEntity;

public class DroneMapper {

	public static Drone entityToDomain(DroneEntity droneEntity) {
		Drone drone = new Drone();
		drone.setSerialNumber(droneEntity.getSerialNumber());
		drone.setModel(droneEntity.getModel());
		drone.setWeightLimit(droneEntity.getWeightLimit());
		drone.setBatteryCapacity(droneEntity.getBatteryCapacity());
		drone.setState(droneEntity.getState());
		return drone;
	}
	
	public static DroneEntity domainToEntity(Drone drone) {
		DroneEntity droneEntity = new DroneEntity();
		droneEntity.setSerialNumber(drone.getSerialNumber());
		droneEntity.setModel(drone.getModel());
		droneEntity.setWeightLimit(drone.getWeightLimit());
		droneEntity.setBatteryCapacity(drone.getBatteryCapacity());
		droneEntity.setState(drone.getState());
		return droneEntity;
	}
}
