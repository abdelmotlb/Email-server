package com.emailBackEnd.emailBackEnd;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.emailBackEnd.emailBackEnd.Controller.Show;

@SpringBootApplication
public class EmailBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailBackEndApplication.class, args);
		long myTime = System.currentTimeMillis();
		while (true) {
			if (System.currentTimeMillis() - myTime > 1000 * 60) {
				deleteAfterTime();
				myTime = System.currentTimeMillis();
			}

		}

	}

	public static void deleteAfterTime() {
		Service.all = Service.getJasonList("allUsers.json");
		Show s = new Show();
		for (User u : Service.all) {
			List<Message> allMessages = s.getMessages(u.getEmail() + "$trash");
			if (allMessages != null) {
				for (Message m : allMessages) {
					if (System.currentTimeMillis() - Long.parseLong(m.getId()) >= 1000 * 60 * 60 * 5) {
						s.deleteMessages(u.getEmail() + "$trash" + "$" + m.getId());
						System.out.println(u.getEmail() + "$trash" + "$" + m.getId());
					}
				}
			}

		}
	}

}
