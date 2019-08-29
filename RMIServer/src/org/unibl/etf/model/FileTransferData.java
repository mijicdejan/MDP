package org.unibl.etf.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FileTransferData implements Serializable {
	
	private User sender;
	private User receiver;
	private String fileName;
	private byte[] fileData;
	private long seconds;
	
	public FileTransferData() {
		super();
	}

	public FileTransferData(User sender, User receiver, String fileName, byte[] fileData, long seconds) {
		super();
		this.sender = sender;
		this.receiver = receiver;
		this.fileName = fileName;
		this.fileData = fileData;
		this.seconds = seconds;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public long getSeconds() {
		return seconds;
	}

	public void setSeconds(long seconds) {
		this.seconds = seconds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((receiver == null) ? 0 : receiver.hashCode());
		result = prime * result + (int) (seconds ^ (seconds >>> 32));
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileTransferData other = (FileTransferData) obj;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		if (seconds != other.seconds)
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		return true;
	}

}
