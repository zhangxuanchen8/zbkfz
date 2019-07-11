package com.ehinfo.hr.entity.system.dict;

public class Colum implements Comparable<Colum> {

	private String property;
	private String propertyvalue;

	public String getPropertyvalue() {
		return propertyvalue;
	}
	public void setPropertyvalue(String propertyvalue) {
		this.propertyvalue = propertyvalue;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	
	public Colum() {
		super();
	}
	public Colum(String property, String propertyvalue) {
		super();
		this.property = property;
		this.propertyvalue = propertyvalue;
	}
	@Override
	public int compareTo(Colum o) {
		return this.getProperty().compareTo(o.getProperty());
	}
}
