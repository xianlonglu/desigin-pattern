package com.lxl.serialize2019;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket 通讯 Server
 */
public class Server {
    
    public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8080);
			Socket socket = serverSocket.accept();
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			User user = (User) objectInputStream.readObject();
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null) {
				serverSocket.close();
			}
		}
	}
}
