package com.csparadigm.tourguide.controller;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;





@Controller
@SessionAttributes("userObj")

public class MainController {
	
	@Autowired
	Dao dao;
	
	@RequestMapping(value={"/login"})
	public ModelAndView login() {
		System.out.println("login...");
		
		return new ModelAndView("loginPage","user-entity", new User());
	}
	
	@RequestMapping(value = { "/process-login" }, method = RequestMethod.POST)
	   public String submit( @ModelAttribute User user, Model model) {
		System.out.println("process-login...");
		
		String username = user.getUsername();
		 if(username == null || username =="") return "redirect:/login?error=true";
	       
	      User user1 = dao.getUser(username);
	      System.out.println(username + user.getPassword() + user1.getUsername() + user1.getPassword());
	      
	      if(user1 == null || !user1.getPassword().equals(user.getPassword())) return "redirect:/login?error=true";
	      
	        model.addAttribute("userObj", user1);
	         
	        return "redirect:/home" ; 
	}
	
	@RequestMapping(value={"/register"}, method = RequestMethod.GET)
	public String register(@ModelAttribute User user) {
		System.out.println("register...");
		return "registrationPage";

	}
	
	@RequestMapping("/insert")
	 public String insertData(@ModelAttribute("user") User user) {
		//System.out.println("insert...");
		if (user != null & user.getUsername() != "")
	   dao.insertUser(user);
		else return "redirect:/register?error=true";
		
	  return "redirect:/home"; 
	 }
	
	
	@RequestMapping(value={"/home"})
	public String home(Model model, HttpServletRequest request ) {
		//System.out.println("...");
		
		List<Destination> destinationList= dao.getAllDestinations();
		
       Integer length=destinationList.size();
		
		
		for (int i = 0; i < length; i++) {
			String originalImage= destinationList.get(i).getImage();
			String dir = request.getSession().getServletContext().getRealPath("/") + "resources/image/"+ originalImage ;
			File folder = new File(dir);
			File[] listOfFiles = folder.listFiles();
	       
		      if (listOfFiles[0].isFile()) {
		    	  destinationList.get(i).setImage(originalImage+"/"+listOfFiles[0].getName());
		    	  System.out.println(destinationList.get(i).getImage());
		      }
		    }
		model.addAttribute("destinationList", destinationList);   
		model.addAttribute("length", length); 
		
		
		return "homePage";
	}
	
	@RequestMapping(value={"/destination"})
	public String destination(@RequestParam Integer destinationID,HttpServletRequest request, Model model) {
		//System.out.println("...");
		
		Destination destination= dao.getDestination(destinationID);
		String dir = request.getSession().getServletContext().getRealPath("/") + "resources/image/"+ destination.getImage();
		
		System.out.println(dir);
		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();
        List<String> imageList = new ArrayList<String>();
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        imageList.add(listOfFiles[i].getName());
		      }
		    }
		    
		 String imageName = imageList.get(0);
		 System.out.println(imageName); 
	    model.addAttribute("destination", destination);
	    model.addAttribute("imageName", imageName);
		return "destinationPage";
	}
	
	@RequestMapping(value={"/destinationScene"})
	public String destinationScene(@RequestParam Integer destinationID, HttpServletRequest request, Model model) {
		//System.out.println("...");
		List<Scene> sceneList = dao.getSceneList(destinationID);
		
		
		Integer length=sceneList.size();
		
		
		for (int i = 0; i < length; i++) {
			String originalImage= sceneList.get(i).getImage();
			String dir = request.getSession().getServletContext().getRealPath("/") + "resources/image/"+ originalImage ;
			File folder = new File(dir);
			File[] listOfFiles = folder.listFiles();
	       
		      if (listOfFiles[0].isFile()) {
		    	  sceneList.get(i).setImage(originalImage+"/"+listOfFiles[0].getName());
		    	  System.out.println(sceneList.get(i).getImage());
		      }
		    }
		model.addAttribute("sceneList", sceneList);   
		model.addAttribute("length", length); 
		
		Destination destination= dao.getDestination(destinationID);
		model.addAttribute("destination", destination);
		return "destinationScenePage";
	}
	
	@RequestMapping(value={"/destinationNote"})
	public String destinationNote(@RequestParam Integer destinationID, HttpServletRequest request, Model model) {
		//System.out.println("...");
		List<Note> noteList = dao.getNoteList_sortbyreviewNum(destinationID);
		
		
		Integer length=noteList.size();
		
		
		
		for (int i = 0; i < length; i++) {
			String originalImage=noteList.get(i).getImage();
			String dir = request.getSession().getServletContext().getRealPath("/") + "resources/image/"+ originalImage ;
			File folder = new File(dir);
			File[] listOfFiles = folder.listFiles();
	       
		      if (listOfFiles[0].isFile()) {
		    	  noteList.get(i).setImage(originalImage+"/"+listOfFiles[0].getName());
		    	  System.out.println(noteList.get(i).getImage());
		      }
		    }
		model.addAttribute("noteList", noteList);   
		model.addAttribute("length", length); 
		
		Destination destination= dao.getDestination(destinationID);
		model.addAttribute("destination", destination);
		return "destinationNotePage";
	}
	
	@RequestMapping(value={"/destinationNote_latest"})
	public String destinationNote_latest(@RequestParam Integer destinationID,HttpServletRequest request, Model model) {
		//System.out.println("...");
		List<Note> noteList = dao.getNoteList_sortbypublishTime(destinationID);
		
		
		Integer length=noteList.size();

		for (int i = 0; i < length; i++) {
			String originalImage=noteList.get(i).getImage();
			String dir = request.getSession().getServletContext().getRealPath("/") + "resources/image/"+ originalImage ;
			File folder = new File(dir);
			File[] listOfFiles = folder.listFiles();
	       
		      if (listOfFiles[0].isFile()) {
		    	  noteList.get(i).setImage(originalImage+"/"+listOfFiles[0].getName());
		      }
		    }
		model.addAttribute("noteList", noteList);   
		model.addAttribute("length", length); 
		
		Destination destination= dao.getDestination(destinationID);
		model.addAttribute("destination", destination);
		return "destinationNotePage";
	}
	
	@RequestMapping(value={"/search"} , method = RequestMethod.POST)
	public String search(HttpServletRequest request, Model model) throws Exception{
		
		String query = request.getParameter("query");
		List<Destination> destinationList = dao.searchDestination(query);
		List<Scene> sceneList = dao.searchScene(query);
		List<Note> noteList = dao.searchNote(query);
		
		/*Integer delength = destinationList.size();
        Integer scenelength = sceneList.size();
        Integer notelength = noteList.size();
		
       
	
		for (int i = 0; i < notelength; i++) {
			String originalImage=noteList.get(i).getImage();
			String dir = request.getSession().getServletContext().getRealPath("/") + "resources/image/"+ originalImage ;
			File folder = new File(dir);
			File[] listOfFiles = folder.listFiles();
	       
		      if (listOfFiles[0].isFile()) {
		    	  noteList.get(i).setImage(originalImage+"/"+listOfFiles[0].getName());
		    	  //System.out.println(noteList.get(i).getImage());
		      }
		    }
		for (int i = 0; i < scenelength; i++) {
			String originalImage= sceneList.get(i).getImage();
			String dir = request.getSession().getServletContext().getRealPath("/") + "resources/image/"+ originalImage ;
			File folder = new File(dir);
			File[] listOfFiles = folder.listFiles();
	       
		      if (listOfFiles[0].isFile()) {
		    	  sceneList.get(i).setImage(originalImage+"/"+listOfFiles[0].getName());
		    	 // System.out.println(sceneList.get(i).getImage());
		      }
		    }
		for (int i = 0; i < delength; i++) {
			String originalImage= sceneList.get(i).getImage();
			String dir = request.getSession().getServletContext().getRealPath("/") + "resources/image/"+ originalImage ;
			File folder = new File(dir);
			File[] listOfFiles = folder.listFiles();
	       
		      if (listOfFiles[0].isFile()) {
		    	  destinationList.get(i).setImage(originalImage+"/"+listOfFiles[0].getName());
		    	  //System.out.println(sceneList.get(i).getImage());
		      }
		    }*/
		
        
		model.addAttribute("destinationList", destinationList);
		model.addAttribute("noteList", noteList);  
		model.addAttribute("sceneList", sceneList);
		model.addAttribute("query", query);
		//model.addAttribute("deLength", delength);
		//model.addAttribute("sceneLength", scenelength);
	   // model.addAttribute("noteLength", notelength);
		
		return "searchResultPage";
	}
	
	
	
	@RequestMapping(value={"/note"})
	public String note(@RequestParam Integer noteID,HttpServletRequest request, Model model) {
		System.out.println(noteID);
		
		List<Note> noteList = dao.getNoteAndReview(noteID);
		Note note = noteList.get(0);
		
        String dir = request.getSession().getServletContext().getRealPath("/") + "resources/image/"+ note.getImage();
		
		System.out.println(dir);
		
		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();
        List<String> imageList = new ArrayList<String>();
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        imageList.add(listOfFiles[i].getName());
		      }
		    }
		    
	    model.addAttribute("imageList", imageList);
		model.addAttribute("noteList", noteList);
		model.addAttribute("note", note); 
		return "notePage";
	}
	
	@RequestMapping(value={"/scene"})
	public String scene(@RequestParam Integer sceneID, Model model) {
		
		List<Scene> sceneList = dao.getSceneAndReview(sceneID);
		Scene scene = sceneList.get(0);
		model.addAttribute("sceneList", sceneList);
		model.addAttribute("scene", scene); 
		
		
		return "scenePage";
	}
	
	@RequestMapping(value={"/submitreview"})
	public String submitreview(HttpServletRequest request, Model model) {
		
		String username = request.getParameter("username");
		Integer sceneID = Integer.parseInt(request.getParameter("sceneID").trim());
		String review = request.getParameter("review");
		dao.insertSceneReview(sceneID, username, review);
		
		return "redirect:/scene?sceneID="+sceneID;
	}	
	
	 
	  @RequestMapping(value = "/multipartFileUpload", method = RequestMethod.POST)  
	    public String uploadNoteReviewAndImage(MultipartHttpServletRequest request,HttpServletRequest request1 // 页面上的控件值  
	    ) {  
	        
	    	String username = request1.getParameter("username");
			Integer noteID = Integer.parseInt(request1.getParameter("noteID").trim());
			String review = request1.getParameter("review");
			
	    	List<UploadFile> uploadFiles = new ArrayList<UploadFile>();  
	        List<MultipartFile> files = request.getFiles("file");
	        if(files == null) dao.insertNoteReview(noteID, username, review);
	        else{
	        String dir = request.getSession().getServletContext().getRealPath("/") + "resources/image/NoteReviews";  
	   
	        Random randomGenerator = new Random();
	        int randomInt = randomGenerator.nextInt();
	        String finaldir = dir + "/" + randomInt;
	        
	        File file = new File(finaldir);
		    while(file.exists()) {
		            	randomInt = randomGenerator.nextInt();
		            	finaldir = dir + "/" + randomInt;
		            	file = new File(finaldir);
		               }
		    file.mkdirs();  
	        for (int i = 0; i < files.size(); i++) {  
	            if (!files.get(i).isEmpty()) {  
	                try {  
	                    String realName = this.copyFile(files.get(i).getInputStream(), finaldir, files.get(i).getOriginalFilename());  
	                    UploadFile u = new UploadFile();  
	                    u.setRealName(realName);  
	                   
	                    
	                    uploadFiles.add(u);  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	        } 
	        System.out.println(noteID + username + review + finaldir);
	        dao.insertNoteReview(noteID, username, review, "NoteReviews/"+ randomInt );
			
	        request.setAttribute("uploadFiles", uploadFiles);
	        } 
	        
	        return "redirect:/note/?noteID=" +noteID;  
	    }  
	  
	   
	   
	  
	    
	    private String copyFile(InputStream in, String dir, String fileName) throws IOException {  
	        
	    	System.out.println(fileName);
	    	
	    	fileName = fileName.substring(fileName.lastIndexOf("."));  
	        String realName = UUID.randomUUID().toString() + fileName; 
	        
	        System.out.println(fileName + " "+ realName);
	       
	        File file = new File(dir, realName);
	        
	           
	            	
	           
	        file.createNewFile();  
	     
	        FileUtils.copyInputStreamToFile(in, file); 
	        
	        return realName; 
	    }  
	  
	

}
