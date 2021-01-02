package com.revature.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Integer> {

}
