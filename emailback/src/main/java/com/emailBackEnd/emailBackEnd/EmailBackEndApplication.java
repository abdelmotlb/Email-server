package com.emailBackEnd.emailBackEnd;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.emailBackEnd.emailBackEnd.Controller.Show;

@SpringBootApplication
public class EmailBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailBackEndApplication.class, args);
		// Show s = new Show();
		// s.deleteMessages("1672575376531$hesham$inbox");
		// LocalDateTime myDateObj = LocalDateTime.now();
		// DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy
		// HH:mm:ss");
		// String formattedDate = myDateObj.format(myFormatObj);
		// System.out.println("After formatting: " + formattedDate);
	}

}
