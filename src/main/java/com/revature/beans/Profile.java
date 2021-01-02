package com.revature.beans;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name= "profile", schema="public")
public class Profile {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="profile_id")
	private int profileId; //serial
	
	@OneToOne()
    @JoinColumn(name="user_id", referencedColumnName="user_id", updatable = false, nullable = false)
    private Users user;
	
	@Column(name="about_me")
	private String aboutMe;
	
	@Column(name="age")
	private int age;
	
	@Column(name="city")
	private String city;
	
	@Column(name="profession")
	private String profession;
	
	@Column(name="favorite_planet")
	private String favoritePlanet;
	
	@Column(name="profile_picture")
	private String profilePicture;
	
	public Profile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Profile(int profileId, Users user, String aboutMe, int age, String city, String profession, LocalDate birthDate,
			String favoritePlanet, String profilePicture) {
		super();
		this.profileId = profileId;
		this.user = user;
		this.aboutMe = aboutMe;
		this.age = age;
		this.city = city;
		this.profession = profession;
		
		this.favoritePlanet = favoritePlanet;
		this.profilePicture = profilePicture;
	}
	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	public Users getUser() {
		return user;
	}

	public void setUserId(Users user) {
		this.user = user;
	}
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getFavoritePlanet() {
		return favoritePlanet;
	}
	public void setFavoritePlanet(String favoritePlanet) {
		this.favoritePlanet = favoritePlanet;
	}
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	@Override
	public String toString() {
		return "Profile [profileId=" + profileId + ", userId=" + user + ", aboutMe=" + aboutMe + ", age=" + age + ", city=" + city
				+ ", profession=" + profession +  ", favoritePlanet=" + favoritePlanet
				+ ", profilePicture=" + profilePicture + "]";
	}
	
	
	
	
}
