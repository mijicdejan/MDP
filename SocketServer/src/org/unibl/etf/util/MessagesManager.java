package org.unibl.etf.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.unibl.etf.model.Message;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class MessagesManager {
	
	private static MessagesManager instance;
	
	private MessagesManager() {}
	
	public static MessagesManager getInstance() {
		if(instance == null) {
			instance = new MessagesManager();
		}
		return instance;
	}
	
	public void serializeWithGson(ArrayList<Message> messages, File file) {
		Gson gson = new Gson();
		try {
			FileWriter out = new FileWriter(file);
			out.write(gson.toJson(messages));
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Message> deserializeWithGson(File file) {
		ArrayList<Message> messages = new ArrayList<>();
		Gson gson = new Gson();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			List<Message> temp = Arrays.asList(gson.fromJson(br.readLine(), Message[].class));
			for(Message m : temp) {
				messages.add(m);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return messages;
	}

}
