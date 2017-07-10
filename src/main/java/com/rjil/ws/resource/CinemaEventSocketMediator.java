package com.rjil.ws.resource;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@ServerEndpoint("/testWsSocket/{client-id}")
public class CinemaEventSocketMediator {

	private static Set<Session> peers = Collections.synchronizedSet(new HashSet());
	
	@OnMessage
	public String onMessage(String message, Session session, @PathParam("client-id") String clientId) {
		try {
			Gson gson = new Gson();
			//BackupReq backupReq = gson.fromJson(message, BackupReq.class);
			//WSMessageUploadRequest wsBackupReq = new WSMessageUploadRequest(backupReq, session);
			System.out.println(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "message was received by socket mediator and processed: " + message;
	}

	@OnOpen
	public void onOpen(Session session, @PathParam("client-id") String clientId) {
		System.out.println("mediator: opened websocket channel for client " + clientId);
		peers.add(session);

		try {
			session.getBasicRemote().sendText("good to be in touch");
		} catch (IOException e) {
		}
	}

	@OnClose
	public void onClose(Session session, @PathParam("client-id") String clientId) {
		System.out.println("mediator: closed websocket channel for client " + clientId);
		peers.remove(session);
	}

}
