package NhanVien.DTO;

import java.sql.Date;

public class nhanVien {
    private String ID;
    private String name;
    private String phone;
    private String birth;
    private String address;
    private String email;
    private String password;
    private int shift;
    private int position;
    private int gender;
    private int work;
    private int daywork;
    private int Salary;

	private String CCCD;
    
	public nhanVien() {
	}
	public nhanVien(String iD, String name, String phone, String birth, String address, String email, String password,
					int shift, int position, int gender, int work, int daywork, int salary,String CCCD) {
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
		this.CCCD=CCCD;
	}

	public String getCCCD() {
		return CCCD;
	}

	public void setCCCD(String CCCD) {
		this.CCCD = CCCD;
	}

	public int getWork() {
		return work;
	}
	public void setWork(int work) {
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
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
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
	public int getShift() {
		return shift;
	}
	public void setShift(int shift) {
		this.shift = shift;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
    
}
