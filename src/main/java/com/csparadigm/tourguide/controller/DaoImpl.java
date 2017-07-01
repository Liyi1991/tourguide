package com.csparadigm.tourguide.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;





public class DaoImpl implements Dao{
	 @Autowired
	 DataSource dataSource;

	 
	 
	 public void insertUser(User user) {

		  String sql = "INSERT INTO users "
		    + "(username,password) VALUES (?, ?)";

		  JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		  jdbcTemplate.update(sql, new Object[] { user.getUsername(), user.getPassword()});

		 }
		 
		 public User getUser(String username) {
			
			  String sql = "select * from users where username = ?";
			  try{
				  
				  JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				  List<User> userList = jdbcTemplate.query(sql, new Object[]{username}, new UserMapper());
				  return userList.get(0);
				  
			  }catch (IndexOutOfBoundsException e) {
					return null;
				}	
 
			 }
		 public class UserMapper implements RowMapper<User> {
		   	   public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		   		User user = new User();
		  	  
		  	  user.setUsername(rs.getString(1));
		  	  user.setPassword(rs.getString(2));	  
		  	  return user;
		   	   }
		   	}
		 
	 //
	 //search for destination/scenery spot/travel note by keywords 
	 //
	 public List<Destination> searchDestination(String query) {

			String sql = "SELECT * from Destinations "
					+ "where destination like ? or state Like ? or city like ? or district like ? or profile like ? ";
			try{
				
			
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			List<Destination> destinationList = jdbcTemplate.query(sql, new Object[] { "%"+query+"%", "%"+query+"%", "%"+query+"%", "%"+query+"%", "%"+query+"%"}, new DestinationMapper());
			
			System.out.println(destinationList.get(0).getDestination() + destinationList.get(0).getDistrict());
		
		     return destinationList;
			  
			  }catch (IndexOutOfBoundsException e) {
				return null;
			}	

	 }
	 public class DestinationMapper implements RowMapper<Destination> {
	   	   public Destination mapRow(ResultSet rs, int rowNum) throws SQLException {
	   	    
	   		Destination destination = new Destination();
	   	    
	   	    destination.setDestinationID(rs.getInt("destinationID"));
	   	    destination.setDestination(rs.getString("destination"));
	   	    
		   	destination.setState(rs.getString("state"));
		   	destination.setCity(rs.getString("city"));
		   	destination.setDistrict(rs.getString("district"));
		   	destination.setProfile(rs.getString("profile"));
		   	destination.setBestVisitTime(rs.getString("bestVisitTime"));
		   	destination.setImage(rs.getString("image"));
		   	destination.setWearingGuide(rs.getString("WearingGuide"));
		   	destination.setTelecommunication(rs.getString("telecommunication"));
	   
	   	    return destination;
	   	   }
	   	}
	
	 
	 
	 public List<Scene> searchScene(String query) {
		
			String sql = "SELECT DestiScenes.*,count(review) as reviewnum "
					+ "from DestiScenes Left Join SceneReviews On DestiScenes.SceneID = SceneReviews.SceneID "
					+ "where scene like ? or profile Like ? "
					+ "group by DestiScenes.SceneID";
			try{
				
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				List<Scene> sceneList = jdbcTemplate.query(sql, new Object[] { "%"+query+"%", "%"+query+"%"}, new SceneMapper());
				
				 return sceneList;
			 
			}catch (IndexOutOfBoundsException e) {
				return null;
			}	

	 }
	 
	 public class SceneMapper implements RowMapper<Scene> {
	   	   public Scene mapRow(ResultSet rs, int rowNum) throws SQLException {
	   	    
	   		Scene scene = new Scene();
	   	    
	   	    scene.setSceneID(rs.getInt("DestiScenes.sceneID"));
	   	    
	   	    scene.setDestinationID(rs.getInt("destinationID"));
	   	    
		   	scene.setScene(rs.getString("scene"));
		   	scene.setProfile(rs.getString("profile"));
		   	scene.setPhone(rs.getString("phone"));
		   	scene.setWebsite(rs.getString("website"));
		   	scene.setTicketFee(rs.getString("ticketFee"));
		   	scene.setOpenTime(rs.getString("OpenTime"));
		   	scene.setTransportation(rs.getString("Transportation"));
		   	scene.setEstimatedStayPeriod(rs.getString("estimatedStayPeriod"));
		   	scene.setImage(rs.getString("image"));
		   	scene.setSceneReviewNum(rs.getInt("reviewnum"));
		   	
		   	scene.setReviewID(null);
		   	scene.setReview(null);
			scene.setUsername(null);
			scene.setPhoto(null);
			scene.setPublishTime(null);
		   
	   	    return scene;
	   	   }
	   	}
	 
	 
	 
	 public List<Note> searchNote(String query) {

			
			String sql = "SELECT DestiNotes.*,count(review) as reviewnum "
					+ "from DestiNotes Left Join NoteReviews On DestiNotes.NoteID = NoteReviews.NoteID "
					+ "where noteTitle like ? or noteContent Like ? "
					+ "group by DestiNotes.NoteID";
			try{
				
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				List<Note> noteList = jdbcTemplate.query(sql, new Object[] { "%"+query+"%", "%"+query+"%"}, new NoteMapper());
				
				 return noteList;
			 
			}catch (IndexOutOfBoundsException e) {
				return null;
			}	

	 }
	 public class NoteMapper implements RowMapper<Note> {
	   	   public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
	   	    
	   		Note note = new Note();
	   	    
	   		note.setNoteID(rs.getInt("DestiNotes.NoteID"));
	   	    
	   		note.setDestinationID(rs.getInt("destinationID"));
	   	    
	   		note.setNoteTitle(rs.getString("noteTitle"));
	   		note.setNoteContent(rs.getString("noteContent"));
		   	note.setUsername(rs.getString("username"));
		   	note.setImage(rs.getString("image"));
		   	note.setPublishTime(rs.getDate("PublishTime").toString());
		   	note.setNoteReviewNum(rs.getInt("reviewnum"));
		   	
		   	note.setReviewID(null);
		   	note.setReview(null);
	   		note.setReviewUsername(null);
	   		note.setReviewPhoto(null);
	   		note.setReviewPublishTime(null);
		   
	   	    return note;
	   	   }
	   	}
	 
	 
	 
	 //
	 //get basic information about a certain destination
	 //
	 public Destination getDestination(Integer destinationID) {

			String sql = "SELECT * from Destinations "
					+ "where destinationID = ? ";
			try{
				
			
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			List<Destination> destinationList = jdbcTemplate.query(sql, new Object[] { destinationID }, new DestinationMapper());
			
			System.out.println(destinationList.get(0).getDestination() + destinationList.get(0).getDistrict());
		
		     return destinationList.get(0);
			  
			  }catch (IndexOutOfBoundsException e) {
				return null;
			}	

	 }
	 
	 //
	 //get all destinations
	 //
	 public List<Destination> getAllDestinations() {

			String sql = "SELECT * from Destinations ";
					
			try{
				
			
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			List<Destination> destinationList = jdbcTemplate.query(sql,  new DestinationMapper());
			
			System.out.println(destinationList.get(0).getDestination() + destinationList.get(0).getDistrict());
		
		     return destinationList;
			  
			  }catch (IndexOutOfBoundsException e) {
				return null;
			}	

	 }
	 
	 //
	 //get information and reviews about a single scenery spot
	 //and submit reviews
	 public List<Scene> getSceneAndReview(Integer SceneID) {

		  
			
			String sql = "SELECT * "
					+ "from DestiScenes Left Join SceneReviews On DestiScenes.SceneID = SceneReviews.SceneID"
					+ "where DestiScenes.SceneID = ? ";
			try{
				
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				List<Scene> sceneList = jdbcTemplate.query(sql, new Object[] {SceneID }, new SceneAndReviewMapper());
				
				System.out.println(sceneList.get(0).getSceneID() + sceneList.get(0).getScene());
				
				 return sceneList;
				 
			} catch (IndexOutOfBoundsException e) {
				return null;
			}	

	 }
	 
	 public class SceneAndReviewMapper implements RowMapper<Scene> {
	   	   public Scene mapRow(ResultSet rs, int rowNum) throws SQLException {
	   	    
	   		Scene scene = new Scene();
	   	    
	   	    scene.setSceneID(rs.getInt("DestiScenes.sceneID"));
	   	    
	   	    scene.setDestinationID(rs.getInt("destinationID"));
	   	    
		   	scene.setScene(rs.getString("scene"));
		   	scene.setProfile(rs.getString("profile"));
		   	scene.setPhone(rs.getString("phone"));
		   	scene.setWebsite(rs.getString("website"));
		   	scene.setTicketFee(rs.getString("ticketFee"));
		   	scene.setOpenTime(rs.getString("OpenTime"));
		   	scene.setTransportation(rs.getString("Transportation"));
		   	scene.setEstimatedStayPeriod(rs.getString("estimatedStayPeriod"));
		   	scene.setImage(rs.getString("image"));
		   	scene.setSceneReviewNum(null);
		   	
		   	scene.setReviewID(rs.getInt("reviewID"));
		   	scene.setReview(rs.getString("review"));
			scene.setUsername(rs.getString("username"));
			scene.setPhoto(rs.getString("photo"));
			scene.setPublishTime(rs.getDate("publishTime").toString());
		   
	   	    return scene;
	   	   }
	   	}
	 
	 public void insertSceneReview(Integer sceneID, String username, String review) {

		   JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
		   String sql = "INSERT INTO sceneReviews "
				    + "(sceneID,review,username,publishTime) VALUES (?, ?, ?, ? )";
		   Date dNow = new Date( );
		      SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		   String currenttime = ft.format(dNow);

		     jdbcTemplate.update(sql, new Object[] { sceneID, review, username, currenttime});
		

	 }
	 
	 
	 //get a scenelist about a certain destination
	 //order by most reviewed
	 //
	 public List<Scene> getSceneList(Integer destinationID) {

			
			String sql = "SELECT DestiScenes.*,count(review) as reviewnum "
					+ "from DestiScenes Left Join SceneReviews On DestiScenes.SceneID = SceneReviews.SceneID "
					+ "where destinationID = ? "
					+ "group by DestiScenes.SceneID";
			try{
				
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				List<Scene> sceneList = jdbcTemplate.query(sql, new Object[] { destinationID }, new SceneMapper());
				
				 return sceneList;
			 
			}catch (IndexOutOfBoundsException e) {
				return null;
			}	

	 }
	 public List<Scene> getSceneList_sortbyreviewNum(Integer destinationID) {

			
			String sql = "SELECT DestiScenes.*,count(review) as reviewnum "
					+ "from DestiScenes Left Join SceneReviews On DestiScenes.SceneID = SceneReviews.SceneID "
					+ "where destinationID = ? "
					+ "group by DestiScenes.SceneID"
					+ "Order by reviewnum DESC";
			try{
				
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				List<Scene> sceneList = jdbcTemplate.query(sql, new Object[] { destinationID }, new SceneMapper());
				
				 return sceneList;
			 
			}catch (IndexOutOfBoundsException e) {
				return null;
			}	

	 }
	
	 //
	 //Get information and reviews about a single travel note 
	 //
	 
	 public List<Note> getNoteAndReview(Integer NoteID) {

		  
			
			String sql = "SELECT * "
					+ "from DestiNotes Left Join NoteReviews On DestiNotes.NoteID = NoteReviews.NoteID"
					+ " where DestiNotes.NoteID = ? ";
			try{
				
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				List<Note> noteList = jdbcTemplate.query(sql, new Object[] { NoteID }, new NoteAndReviewMapper());
				
				 return noteList;
				 
			} catch (IndexOutOfBoundsException e) {
				return null;
			}	

	 }
	 public class NoteAndReviewMapper implements RowMapper<Note> {
	   	   public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
	   	    
	   		Note note = new Note();
	   	    
	   		note.setNoteID(rs.getInt("DestiNotes.NoteID"));
	   	    
	   		note.setDestinationID(rs.getInt("destinationID"));
	   	    
	   		note.setNoteTitle(rs.getString("noteTitle"));
	   		note.setNoteContent(rs.getString("noteContent"));
		   	note.setUsername(rs.getString("DestiNotes.username"));
		   	note.setImage(rs.getString("DestiNotes.image"));
		   	note.setPublishTime(rs.getDate("DestiNotes.publishTime").toString());
		   	note.setNoteReviewNum(null);
		   	
		   	note.setReviewID(rs.getInt("ReviewID"));
		   	note.setReview(rs.getString("Review"));
	   		note.setReviewUsername(rs.getString("NoteReviews.username"));
	   		note.setReviewPhoto(rs.getString("NoteReviews.Photo"));
	   		note.setReviewPublishTime(rs.getDate("NoteReviews.PublishTime").toString());
		   
	   	    return note;
	   	   }
	   	}
	 
	 
	 
	 //get a travelnote list about a certain destination
	 //order by most reviewed
	 //order by latest published
	 
	   public List<Note> getNoteList(Integer destinationID) {
	
		   String sql = "SELECT DestiNotes.*,count(review) as reviewnum "
		   		+ "from DestiNotes Left Join NoteReviews On DestiNotes.NoteID = NoteReviews.NoteID "
		   		+ "where destinationID = ?"
		   		+ " group by DestiNotes.NoteID";
		   
			try{
				
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				List<Note> noteList = jdbcTemplate.query(sql, new Object[] { destinationID }, new NoteMapper());
				
				 return noteList;
			 
			}catch (IndexOutOfBoundsException e) {
				return null;
			}	

	 }
	   public List<Note> getNoteList_sortbyreviewNum(Integer destinationID) {

		   String sql = "SELECT DestiNotes.*,count(review) as reviewnum "
		   		+ "from DestiNotes Left Join NoteReviews On DestiNotes.NoteID = NoteReviews.NoteID "
		   		+ "where destinationID = ? "
		   		+ "group by DestiNotes.NoteID "
		   		+ "ORDER BY reviewnum DESC";
		   
			try{
				
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				List<Note> noteList = jdbcTemplate.query(sql, new Object[] { destinationID }, new NoteMapper());
				
				 return noteList;
			 
			}catch (IndexOutOfBoundsException e) {
				return null;
			}	

	 }
	   public List<Note> getNoteList_sortbypublishTime(Integer destinationID) {
	
		   String sql = "SELECT DestiNotes.*,count(review) as reviewnum "
		   		+ "from DestiNotes Left Join NoteReviews On DestiNotes.NoteID = NoteReviews.NoteID "
		   		+ "where destinationID = ? "
		   		+ "group by DestiNotes.NoteID "
		   		+ "ORDER BY DestiNotes.PublishTime DESC";
			try{
				
				JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
				List<Note> noteList = jdbcTemplate.query(sql, new Object[] { destinationID }, new NoteMapper());
				
				 return noteList;
			 
			}catch (IndexOutOfBoundsException e) {
				return null;
			}	

	 }
	   public void insertNoteReview(Integer noteID, String username, String review, String photo) {

		   JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
		   String sql = "INSERT INTO noteReviews "
				    + "(noteID,review,username,publishTime,photo) VALUES (?, ?, ?, ?,? )";
		   Date dNow = new Date( );
		      SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		   String currenttime = ft.format(dNow);

		     jdbcTemplate.update(sql, new Object[] { noteID, review, username, currenttime, photo});
		

	 }
	   public void insertNoteReview(Integer noteID, String username, String review) {

		   JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
		   String sql = "INSERT INTO noteReviews "
				    + "(noteID,review,username,publishTime) VALUES (?, ?, ?, ? )";
		   Date dNow = new Date( );
		      SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		   String currenttime = ft.format(dNow);

		     jdbcTemplate.update(sql, new Object[] { noteID, review, username, currenttime});
		

	 }
	 
	 
	 
	
}
