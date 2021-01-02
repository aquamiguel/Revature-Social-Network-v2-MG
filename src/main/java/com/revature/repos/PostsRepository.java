package com.revature.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.beans.Posts;

@Repository
public interface PostsRepository extends JpaRepository<Posts,Integer> {

}
