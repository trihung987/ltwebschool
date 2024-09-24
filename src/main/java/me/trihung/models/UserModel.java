package me.trihung.models;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserModel implements Serializable {
	private int id;
	private String username;
	private String email;
	private String password;
	private String fullname;
	private String images;
	private String role;
	private String phone;

	public UserModel() {
	}

	public UserModel(int id, String username, String email, String password, String fullname, String images, String role) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.images = images;
		this.role = role;
	}
	
	public UserModel(int id, String username, String email, String password, String fullname, String images, String role, String phone) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.images = images;
		this.role = role;
		this.phone = phone; 
	}
	
	public UserModel(String username, String email, String password, String fullname, String images, String role) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.images = images;
		this.role = role;
	}

//	public UserModel(String username, String email, String password, String fullname) {
//		this.username = username;
//		this.email = email;
//		this.password = password;
//		this.fullname = fullname;
//		this.role = "MEMBER";
//	}
	
	public UserModel(String username, String email, String password, String fullname, String phone) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.role = "MEMBER";
		this.phone = phone;
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

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}