package com.book.pojo;

public class Customer {
	
	private Integer cId;
	private String cname;
	private String email;
	private String password;
	private Long contactNo;
	private Integer age;
	private String gender;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Customer(Integer cId, String cname, String email, String password, Long contactNo, Integer age,
			String gender) {
		super();
		this.cId = cId;
		this.cname = cname;
		this.email = email;
		this.password = password;
		this.contactNo = contactNo;
		this.age = age;
		this.gender = gender;
	}
	
	public Customer(String cname, String email, String password, Long contactNo, Integer age, String gender) {
		super();
		this.cname = cname;
		this.email = email;
		this.password = password;
		this.contactNo = contactNo;
		this.age = age;
		this.gender = gender;
	}

	public Integer getcId() {
		return cId;
	}
	public void setcId(Integer cId) {
		this.cId = cId;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
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
	public Long getContactNo() {
		return contactNo;
	}
	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		return "Customer :- \ncId=" + cId + "\ncname=" + cname + "\nemail=" + email + "\npassword=" + password
				+ "\ncontactNo=" + contactNo + "\nage=" + age + "\ngender=" + gender;
	}
	
	
}
