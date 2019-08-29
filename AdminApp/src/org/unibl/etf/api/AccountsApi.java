/**
 * AccountsApi.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.unibl.etf.api;

public interface AccountsApi extends java.rmi.Remote {
    public org.unibl.etf.model.User createAccount(java.lang.String firstName, java.lang.String lastName) throws java.rmi.RemoteException;
    public boolean blockUnblockAccount(org.unibl.etf.model.User user) throws java.rmi.RemoteException;
}
