package main.rest.beans;

public class Car
{
	private Integer id;
	private String model;
	
	public Car() {
		
	}
	
	public Car(Integer id, String name) {
		this.id  = id;
		this.model = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", model=" + model + "]";
	}
}
