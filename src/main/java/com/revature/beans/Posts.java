package com.revature.beans;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="posts", schema="public")
public class Posts implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7022284477521226987L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="post_id")
	private int postId; //serial
	
	@ManyToOne()
    @JoinColumn(name="user_id", referencedColumnName="user_id", updatable = false, nullable = false)
    private Users user;
	
	@Column(name="count_likes")
	private int countLikes;
	
	@Column(name="post_text")
	private String postText;
	
	@Column(name="post_date")
	private LocalDate postDate;
	
	@Column(name="post_media_url")
	private String postMediaUrl;
	
	public Posts() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Posts(int postId, Users user, int countLikes, String postText, LocalDate postDate, String postMediaUrl) {
		super();
		this.postId = postId;
		this.user = user;
		this.countLikes = countLikes;
		this.postText = postText;
		this.postDate = postDate;
		this.postMediaUrl = postMediaUrl;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	public int getCountLikes() {
		return countLikes;
	}
	public void setCountLikes(int countLikes) {
		this.countLikes = countLikes;
	}
	public String getPostText() {
		return postText;
	}
	public void setPostText(String postText) {
		this.postText = postText;
	}
	public LocalDate getPostDate() {
		return postDate;
	}
	public void setPostDate(LocalDate postDate) {
		this.postDate = postDate;
	}
	public String getPostMediaUrl() {
		return postMediaUrl;
	}
	public void setPostMediaUrl(String postMediaUrl) {
		this.postMediaUrl = postMediaUrl;
	}
	@Override
	public String toString() {
		return "Posts [postId=" + postId + ", userId=" + user + ", countLikes=" + countLikes + ", postText="
				+ postText + ", postDate=" + postDate + ", postMediaUrl=" + postMediaUrl + "]";
	}
	
	
	

}
