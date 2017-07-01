package com.csparadigm.tourguide.controller;


import java.util.List;

public interface Dao {
	public List<Destination> searchDestination(String query);
	public List<Scene> searchScene(String query);
	public List<Note> searchNote(String query);
	
	public List<Scene> getSceneList(Integer destinationID);
	 public List<Scene> getSceneList_sortbyreviewNum(Integer destinationID);
	 
	 
	public List<Note> getNoteList(Integer destinationID);
	 public List<Note> getNoteList_sortbyreviewNum(Integer destinationID);
	 public List<Note> getNoteList_sortbypublishTime(Integer destinationID);
	 
	public List<Scene> getSceneAndReview(Integer SceneID);
	public List<Note> getNoteAndReview(Integer NoteID);
	 
	public void insertSceneReview(Integer sceneID, String username, String review);
	 public void insertNoteReview(Integer noteID, String username, String review) ;
	 public void insertNoteReview(Integer noteID, String username, String review, String photo);
	 
	public List<Destination> getAllDestinations();
	public Destination getDestination(Integer destinationID);
	
	public void insertUser(User user);
	public User getUser(String username);
}
