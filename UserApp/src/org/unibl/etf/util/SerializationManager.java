package org.unibl.etf.util;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class SerializationManager {
	
private static SerializationManager instance;
	
	private SerializationManager() {}
	
	public static SerializationManager getInstance() {
		if(instance == null) {
			instance = new SerializationManager();
		}
		return instance;
	}
	
	public void serializeWithJava(ArrayList<String> data) {
		String fileName = PropertyManager.getInstance().getJavaFile();
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> deserializeWithJava() {
		ArrayList<String> data = new ArrayList<>();
		String fileName = PropertyManager.getInstance().getJavaFile();
		try {
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			data = (ArrayList<String>) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public void serializeWithGson(ArrayList<String> data) {
		String fileName = PropertyManager.getInstance().getGsonFile();
		Gson gson = new Gson();
		try {
			FileWriter out = new FileWriter(new File(fileName));
			out.write(gson.toJson(data));
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> deserializeWithGson() {
		ArrayList<String> data = new ArrayList<>();
		String fileName = PropertyManager.getInstance().getGsonFile();
		Gson gson = new Gson();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
			List<String> temp = Arrays.asList(gson.fromJson(br.readLine(), String[].class));
			for(String s : temp) {
				data.add(s);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public void serializeWithXML(ArrayList<String> data) {
		String fileName = PropertyManager.getInstance().getXMLFile();
		try {
			XMLEncoder encoder = new XMLEncoder(new FileOutputStream(new File(fileName)));
			encoder.writeObject(data);
			encoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> deserializeWithXML() {
		ArrayList<String> data = new ArrayList<>();
		String fileName = PropertyManager.getInstance().getXMLFile();
		try {
			XMLDecoder decoder = new XMLDecoder(new FileInputStream(new File(fileName)));
			data = (ArrayList<String>) decoder.readObject();
			decoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public void serializeWithKryo(ArrayList<String> data) {
		String fileName = PropertyManager.getInstance().getKryoFile();
		Kryo kryo = new Kryo();
		kryo.register(String.class);
		try {
			Output out = new Output(new FileOutputStream(new File(fileName)));
			kryo.writeClassAndObject(out, data);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> deserializeWithKryo() {
		ArrayList<String> data = new ArrayList<>();
		String fileName = PropertyManager.getInstance().getKryoFile();
		Kryo kryo = new Kryo();
		kryo.register(String.class);
		try {
			Input in = new Input(new FileInputStream(new File(fileName)));
			data = (ArrayList<String>) kryo.readClassAndObject(in);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return data;
	}

}
