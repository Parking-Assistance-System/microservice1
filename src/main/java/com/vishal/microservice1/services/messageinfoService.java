package com.vishal.microservice1.services;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.vishal.microservice1.entity.messageinfo;
import com.vishal.microservice1.repository.UserRepository;
import com.vishal.microservice1.repository.messageRepository;
@Service
public class messageinfoService {

	@Autowired
	public messageRepository messagerepository;
	
	@Autowired
	public UserRepository userrepository;
	public void addmessage(messageinfo message) {
		messagerepository.save(message);
	}

	public  List<messageinfo> getMessageOfUser(String username) {
		// TODO Auto-generated method stub
		return messagerepository.getMessageOfUser(username);
	}
	
	public String getRecieverPhoneNumber(String username) {
		
		return userrepository.getRecieverPhonenumber(username);
	}
	
	
	public void sendSms(String recieverPhoneNumber,String messageToSend) {
		
		RestTemplate resttemplate = new RestTemplate();
		
//		String ACCOUNT_SID="AC5bb03f49570d9e27cba39105f511547b";
//		String AUTH_TOKEN="4dbc09cf4ba1e2b69d8d81d4b299981e";
//		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//		Message message = Message.creator(
//		    new PhoneNumber("+916397759033"),
//		    new PhoneNumber("+19206773227"),
//		    "Sample Twilio SMS using Java")
//		.create();
		
		
		
		
		
		
		
		 String url="https://www.fast2sms.com/dev/bulkV2";
		 
		 
		 String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
			        .queryParam("authorization", "{authorization}")
			        .queryParam("message", "{message}")
			        .queryParam("language", "{language}")
			        .queryParam("route", "{route}")
			        .queryParam("numbers", "{numbers}")
			        .queryParam("flash", "{flash}")
			        .encode()
			        .toUriString();

			String authorization="jEnHeLBM7QJmNh8R0pTaYW6ZrcOfxI2iUslw9PA5S1XGzgqDvCvSlsRIY9qoxM1C6UX3D5LVBuicObJg";
			String message=messageToSend;
			String language="english";
			String route="q";
//			String numbers="9205261029";
			String numbers=recieverPhoneNumber;
			String flash ="0";
			Map<String, String> params = new HashMap<>();
			
			params.put("authorization", authorization);
			
			params.put("message",message);
			params.put("language", language);
			params.put("route", route);
			params.put("numbers", numbers);
			params.put("flash", flash);
			HttpHeaders headers = new HttpHeaders();
			headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<?> entity = new HttpEntity<>(headers);
			HttpEntity<String> response = resttemplate.exchange(
			        urlTemplate,
			        HttpMethod.GET,
			        entity,
			        String.class,
			        params);
//			return response.getBody();
		
		
		
		
		
	}
}
