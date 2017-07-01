package com.csparadigm.tourguide.controller;



public class Note {
	private Integer noteID;
	private Integer destinationID;
	private String noteTitle;
	private String noteContent;
	private String username;
	private String image;
	private String publishTime;
	private Integer noteReviewNum;	
	
	private Integer reviewID;
	private String review;
	private String reviewUsername;
	private String reviewPhoto;
	private String reviewPublishTime;
	
	public Integer getNoteID() {
		return noteID;
	}
	public void setNoteID(Integer noteID) {
		this.noteID = noteID;
	}
	
	public Integer getDestinationID() {
		return destinationID;
	}
	public void setDestinationID(Integer destinationID) {
		this.destinationID = destinationID;
	}
	public String getNoteTitle() {
		return noteTitle;
	}
	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}
	
	public String getNoteContent() {
		return noteContent;
	}
	public void setNoteContent(String noteContent) {
		this.noteContent =noteContent;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public Integer getNoteReviewNum() {
		return noteReviewNum;
	}
	public void setNoteReviewNum(Integer noteReviewNum) {
		this.noteReviewNum = noteReviewNum;
	}
	
	
	public Integer getReviewID() {
		return reviewID;
	}
	public void setReviewID(Integer reviewID) {
		this.reviewID = reviewID;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	public String getReviewUsername() {
		return reviewUsername;
	}
	public void setReviewUsername(String reviewUsername) {
		this.reviewUsername = reviewUsername;
	}
	public String getReviewPhotoe() {
		return reviewPhoto;
	}
	public void setReviewPhoto(String reviewPhoto) {
		this.reviewPhoto = reviewPhoto;
	}
	public String getReviewPublishTime() {
		return reviewPublishTime;
	}
	public void setReviewPublishTime(String reviewPublishTime) {
		this.reviewPublishTime = reviewPublishTime;
	}
	
	
	
}
