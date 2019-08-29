/**
 * AccountsApiServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.unibl.etf.api;

import org.unibl.etf.util.PropertyManager;

public class AccountsApiServiceLocator extends org.apache.axis.client.Service implements org.unibl.etf.api.AccountsApiService {

    public AccountsApiServiceLocator() {
    	AccountsApi_address = PropertyManager.getInstance().getServiceAddress();
    }


    public AccountsApiServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AccountsApiServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AccountsApi
    private java.lang.String AccountsApi_address;

    public java.lang.String getAccountsApiAddress() {
        return AccountsApi_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AccountsApiWSDDServiceName = "AccountsApi";

    public java.lang.String getAccountsApiWSDDServiceName() {
        return AccountsApiWSDDServiceName;
    }

    public void setAccountsApiWSDDServiceName(java.lang.String name) {
        AccountsApiWSDDServiceName = name;
    }

    public org.unibl.etf.api.AccountsApi getAccountsApi() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AccountsApi_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAccountsApi(endpoint);
    }

    public org.unibl.etf.api.AccountsApi getAccountsApi(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.unibl.etf.api.AccountsApiSoapBindingStub _stub = new org.unibl.etf.api.AccountsApiSoapBindingStub(portAddress, this);
            _stub.setPortName(getAccountsApiWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAccountsApiEndpointAddress(java.lang.String address) {
        AccountsApi_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.unibl.etf.api.AccountsApi.class.isAssignableFrom(serviceEndpointInterface)) {
                org.unibl.etf.api.AccountsApiSoapBindingStub _stub = new org.unibl.etf.api.AccountsApiSoapBindingStub(new java.net.URL(AccountsApi_address), this);
                _stub.setPortName(getAccountsApiWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("AccountsApi".equals(inputPortName)) {
            return getAccountsApi();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://api.etf.unibl.org", "AccountsApiService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://api.etf.unibl.org", "AccountsApi"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AccountsApi".equals(portName)) {
            setAccountsApiEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
