package com.killer.drone.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MedicationEntity {

	@Id
	String code;
	String name;
	Double weight;
	String image;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "MedicationEntity [code=" + code + ", name=" + name + ", weight=" + weight + ", image=" + image + "]";
	}
	

}
