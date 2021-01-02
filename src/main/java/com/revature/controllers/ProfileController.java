package com.revature.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Profile;
import com.revature.beans.Users;
import com.revature.exception.ResourceNotFoundException;
import com.revature.repos.ProfileRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class ProfileController {
	@Autowired
	private ProfileRepository profileRepo;
	
	@GetMapping("/profile")
	public List<Profile> getAllProfiles(){

		 return profileRepo.findAll();
		
	}
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping("/profile/{id}")
	public ResponseEntity<Profile> getProfileByUserId(@PathVariable(value = "id") int userId)
			throws ResourceNotFoundException {
		List<Profile> profileList = getAllProfiles();
		Profile profile = new Profile();
		Iterator<Profile> iterator = profileList.iterator();
		while(iterator.hasNext()) {
			profile = iterator.next();
			if(profile.getUser().getUserId() == userId) {
			return ResponseEntity.ok().body(profile);
			}
		}
		throw new ResourceNotFoundException("Profile Not Found For User with this Id :: " + userId);
		
	}
	
	@PostMapping("/addprofile")
	public Profile createProfile (@RequestBody Profile profile) {
		profile.getUser().getUserId();
		return profileRepo.save(profile);
		//ToDO - log new profile created
		
}
	@PostMapping("/updateprofile")
	public Profile updateProfile(@Valid @RequestBody Profile updateProfile) throws ResourceNotFoundException {
		System.out.println(updateProfile.getProfileId());
		int updateProfileId = updateProfile.getProfileId();
;		List<Profile> profileList = getAllProfiles();
		Profile profile = new Profile();
		Iterator<Profile> iterator = profileList.iterator();
		while(iterator.hasNext()) {
			profile = iterator.next();
			if(profile.getProfileId() == updateProfileId) {
				profile.setAboutMe(updateProfile.getAboutMe());
				profile.setAge(updateProfile.getAge());
				profile.setCity(updateProfile.getCity());
				profile.setFavoritePlanet(updateProfile.getFavoritePlanet());
				profile.setProfession(updateProfile.getProfession());
				profile.setProfilePicture(updateProfile.getProfilePicture());
				final Profile updatedProfile = profileRepo.save(profile);
				return updatedProfile;
			}
		}
		throw new ResourceNotFoundException("Update Profile not possible");
	}

}