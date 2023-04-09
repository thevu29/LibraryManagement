package NhanVien.arraylistNV;

import java.sql.Date;

public class nhanVien {
    private String ID;
    private String name;
    private  String phone;
    private Date birth;
    private String address;
    private String email;
    private String password;
    private Shift shift;
    private Position position;
    private Gender gender;
    private Workplace work;
    private int daywork;
    private int Salary;
    
	public nhanVien() {
	}
	public nhanVien(String iD, String name, String phone, Date birth, String address, String email, String password,
					Shift shift, Position position, Gender gender, Workplace work, int daywork, int salary) {
		super();
		ID = iD;
		this.name = name;
		this.phone = phone;
		this.birth = birth;
		this.address = address;
		this.email = email;
		this.password = password;
		this.shift = shift;
		this.position = position;
		this.gender = gender;
		this.work = work;
		this.daywork = daywork;
		Salary = salary;
	}
	public Workplace getWork() {
		return work;
	}
	public void setWork(Workplace work) {
		this.work = work;
	}
	public int getDaywork() {
		return daywork;
	}
	public void setDaywork(int daywork) {
		this.daywork = daywork;
	}
	public int getSalary() {
		return Salary;
	}
	public void setSalary(int salary) {
		Salary = salary;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Shift getShift() {
		return shift;
	}
	public void setShift(Shift shift) {
		this.shift = shift;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
    
}
