package com.email.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.email.Service.EmailService;
import com.email.model.EmailRequest;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
public class EmailController {

	
	
	
	///object of email_service to send the collected data in request
	@Autowired
     EmailService emailService;
	
	
	
	
	
	
	///by_default method is get................
	@RequestMapping("/getemail")
	public String Welcome() {
		
		return " welcome  to email api";
	}
	
	
	
	
	//POST API to send the mail............................
	
	@PostMapping("/sendemail")
	public ResponseEntity<Map<String, String>> sendEmail(@RequestBody EmailRequest emailRequest) {
	    System.out.println(emailRequest);

	    boolean result = this.emailService.sendEmail(emailRequest.getSubject(), emailRequest.getMessage(), emailRequest.getTO());

	    Map<String, String> response = new HashMap<>();
	    if (result) {
	        response.put("message", "Email sent successfully!");
	        return ResponseEntity.ok().body(response);
	    } else {
	        response.put("message", "Email not sent...");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

}
		