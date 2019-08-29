package org.unibl.etf.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.unibl.etf.model.FileTransferData;
import org.unibl.etf.model.User;

public interface FilesInterface extends Remote {
	
	boolean uploadFile(FileTransferData data) throws RemoteException;
	
	ArrayList<FileTransferData> getAllFiles(User user) throws RemoteException;
	
	FileTransferData downloadFile(FileTransferData data) throws RemoteException;

}
