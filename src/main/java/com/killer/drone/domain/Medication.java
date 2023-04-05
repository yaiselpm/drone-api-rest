package com.killer.drone.domain;

public class Medication {
	
	Long id;
	String code;
	String name;
	Double weight;
	String image;
	
	public Medication(String code, String name, Double weight, String image) {
		this.code = code;
		this.name = name;
		this.weight = weight;
		this.image = image;
	}
	
	public Medication() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
	
	public boolean validateName(String name) {
		if (name.contains("-")|| name.contains("_")|| name.matches("[A-Z]*")|| name.matches("[0-9]*")) {
			return true;
		}
		return false;
	}
	public boolean validateCode(String code) {
		if (name.contains("_")|| name.matches("[A-Z]*")|| name.matches("[0-9]*")) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Medication [id=" + id + ", code=" + code + ", name=" + name + ", weight=" + weight + ", image=" + image
				+ "]";
	}
	
}
