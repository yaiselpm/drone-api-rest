package com.killer.drone.mappers;

import org.springframework.stereotype.Component;

import com.killer.drone.domain.Drone;
import com.killer.drone.enums.DroneState;
import com.killer.drone.persistence.entities.DroneEntity;
@Component
public class DroneMapper implements IMapper<DroneInDTO, Drone>{

	public static Drone entityToDomain(DroneEntity droneEntity) {
		Drone drone = new Drone();
		drone.setId(droneEntity.getId());
		drone.setSerialNumber(droneEntity.getSerialNumber());
		drone.setModel(droneEntity.getModel());
		drone.setWeightLimit(droneEntity.getWeightLimit());
		drone.setBatteryCapacity(droneEntity.getBatteryCapacity());
		drone.setState(droneEntity.getState());
		return drone;
	}
	
	public static DroneEntity domainToEntity(Drone drone) {
		DroneEntity droneEntity = new DroneEntity();
		droneEntity.setId(drone.getId());
		droneEntity.setSerialNumber(drone.getSerialNumber());
		droneEntity.setModel(drone.getModel());
		droneEntity.setWeightLimit(drone.getWeightLimit());
		droneEntity.setBatteryCapacity(drone.getBatteryCapacity());
		droneEntity.setState(drone.getState());
		return droneEntity;
	}

	@Override
	public Drone map(DroneInDTO in) {
		Drone drone = new Drone();
		drone.setSerialNumber(in.getSerialNumber());
		drone.setModel(in.getModel());
		drone.setBatteryCapacity(in.getBatteryCapacity());
		drone.setWeightLimit(in.getWeightLimit());
		drone.setState(DroneState.IDLE);
		
		return drone;
	}
	
}
