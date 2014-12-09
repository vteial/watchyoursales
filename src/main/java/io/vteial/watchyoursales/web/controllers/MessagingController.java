package io.vteial.watchyoursales.web.controllers;

import io.vteial.watchyoursales.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;

import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class MessagingController {

	@MessageMapping("/announcement")
	@SendTo("/topic/announcement")
	public MessageDto anounce(@Payload MessageDto iMsg,
			MessageHeaders messageHeaders) throws Exception {
		MessageDto oMsg = new MessageDto();

		log.info("MessageHeader : {}", messageHeaders.toString());

		log.info("Arrived : {}", iMsg);
		oMsg.setMessage("Announcing : " + iMsg.getMessage() + "!");
		log.info("Sent    : {}", oMsg);

		return oMsg;
	}
}