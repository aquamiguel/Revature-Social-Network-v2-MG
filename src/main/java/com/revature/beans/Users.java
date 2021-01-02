package com.revature.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users", schema="public")
public class Users implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 904056302678698572L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="user_id")
	private int userId; //serial
	
	@Column (name="email", nullable = false)
	private String email;
	
	@Column (name="password", nullable = false)
	private String password;
	
	@Column (name="first_name")
	private String firstName;
	
	@Column (name="last_name")
	private String lastName;
	
	@Column (name="number_posts")
	private int numberPosts;

	@Column(name="birth_date")
	private LocalDate birthDate;
	
	@OneToMany( targetEntity = Posts.class, mappedBy = "user", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private List<Posts> posts = new ArrayList<>();
	
	public Users() {
		super();
			}
	public Users(int userId, String email, String password, String firstName, String lastName, int numberPosts,Boolean isAdmin, LocalDate birthDate) {
		super();
		
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.numberPosts = numberPosts;
		this.birthDate = birthDate;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getNumberPosts() {
		return numberPosts;
	}
	public void setNumberPosts(int numberPosts) {
		this.numberPosts = numberPosts;
	}
	
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	@Override
	public String toString() {
		return "user [userId=" + userId + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", numberPosts=" + numberPosts + "]";
	}
	
	

}
