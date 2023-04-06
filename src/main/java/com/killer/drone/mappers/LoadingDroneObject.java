package com.killer.drone.mappers;

import java.util.List;

import com.killer.drone.domain.Drone;
import com.killer.drone.domain.Medication;

public class LoadingDroneObject {
	private Drone drone;
	private List<Medication> medicationList;
	
	public LoadingDroneObject(Drone drone, List<Medication> medicationList) {
		this.drone = drone;
		this.medicationList = medicationList;
	}

	public Drone getDrone() {
		return drone;
	}

	public void setDrone(Drone drone) {
		this.drone = drone;
	}

	public List<Medication> getMedicationList() {
		return medicationList;
	}

	public void setMedicationList(List<Medication> medicationList) {
		this.medicationList = medicationList;
	}
	
}
