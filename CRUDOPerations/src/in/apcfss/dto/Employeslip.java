package in.apcfss.dto;

public class Employeslip {
	private int id=0;
	private String name=null;
	private String pancard=null;
	private float salary=0;
	private int dayspresent=0;
	public String getPancard() {
		return pancard;
	}
	public void setPancard(String pancard) {
		this.pancard = pancard;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public int getDayspresent() {
		return dayspresent;
	}
	public void setDayspresent(int dayspresent) {
		this.dayspresent = dayspresent;
	}


	
}
