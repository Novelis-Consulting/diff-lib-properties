package org.novelis.diff.lib.properties;

public class ColumnExcel {

	private String name;
	private String value;
	private String color;
	private short bgColor;
	private String styleClass="";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public short getBgColor() {
		return bgColor;
	}

	public void setBgColor(short bgColor) {
		this.bgColor = bgColor;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}




}
