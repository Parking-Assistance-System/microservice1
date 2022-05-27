package com.vishal.microservice1.controllers;

import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.vishal.microservice1.entity.messageinfo;
import com.vishal.microservice1.services.messageinfoService;

@RestController
public class TestController {

	@Autowired
	public messageinfoService messageinfoservice;
	
    @GetMapping("/test")
    public String test() {
        return "Test";
    }
    
    @PostMapping("/message")
    public void addmessage(@RequestBody messageinfo message) {
    	//System.out.println("fired");
    	messageinfoservice.addmessage(message);
    	//System.out.println(message.getUsername2());
    	String recieverPhoneNumber = messageinfoservice.getRecieverPhoneNumber(message.getUsername2());
    	
    	//System.out.println(recieverPhoneNumber+" this is reciever phone number");
    	messageinfoservice.sendSms(recieverPhoneNumber,message.getMessage());
    }
    
    @GetMapping("/getmessage")
    public List<messageinfo> getMessage(@RequestParam String username) {
    	
    	return messageinfoservice.getMessageOfUser(username);
    	
    }
    
    
   
    
    
}
