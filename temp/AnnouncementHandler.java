package io.ahaitech.harmoney.helloworld;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class AnnouncementHandler extends TextWebSocketHandler {

	@Override
	protected void handleTextMessage(WebSocketSession session,
			TextMessage iMessage) throws Exception {
		super.handleTextMessage(session, iMessage);

		String value = "Hello " + iMessage.getPayload();
		TextMessage oMessage = new TextMessage(value);
		session.sendMessage(oMessage);
	}

}
