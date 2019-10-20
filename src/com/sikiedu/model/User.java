package com.sikiedu.model;

public class User {

	private int id;
	private String username;
	private String email;
	private String password;
	private String name;
	private String phone;
	private String address;
	private boolean isadmin = false;
	private boolean isisvalidate = false;

	public User() {
		super();
	}

	public User(int id, String username, String email, String password, String name, String phone, String address,
			boolean isadmin, boolean isisvalidate) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.isadmin = isadmin;
		this.isisvalidate = isisvalidate;
	}

	public User(String username, String email, String password, String name, String phone, String address) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.isadmin = false;
		this.isisvalidate = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isIsadmin() {
		return isadmin;
	}

	public void setIsadmin(boolean isadmin) {
		this.isadmin = isadmin;
	}

	public boolean isIsisvalidate() {
		return isisvalidate;
	}

	public void setIsisvalidate(boolean isisvalidate) {
		this.isisvalidate = isisvalidate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", name="
				+ name + ", phone=" + phone + ", address=" + address + ", isadmin=" + isadmin + ", isisvalidate="
				+ isisvalidate + "]";
	}

}
