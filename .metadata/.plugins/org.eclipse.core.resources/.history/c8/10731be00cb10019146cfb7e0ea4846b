package org.unibl.etf.api;

public class AccountsApiProxy implements org.unibl.etf.api.AccountsApi {
  private String _endpoint = null;
  private org.unibl.etf.api.AccountsApi accountsApi = null;
  
  public AccountsApiProxy() {
    _initAccountsApiProxy();
  }
  
  public AccountsApiProxy(String endpoint) {
    _endpoint = endpoint;
    _initAccountsApiProxy();
  }
  
  private void _initAccountsApiProxy() {
    try {
      accountsApi = (new org.unibl.etf.api.AccountsApiServiceLocator()).getAccountsApi();
      if (accountsApi != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)accountsApi)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)accountsApi)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (accountsApi != null)
      ((javax.xml.rpc.Stub)accountsApi)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.unibl.etf.api.AccountsApi getAccountsApi() {
    if (accountsApi == null)
      _initAccountsApiProxy();
    return accountsApi;
  }
  
  public org.unibl.etf.model.User createAccount(java.lang.String firstName, java.lang.String lastName) throws java.rmi.RemoteException{
    if (accountsApi == null)
      _initAccountsApiProxy();
    return accountsApi.createAccount(firstName, lastName);
  }
  
  public boolean blockUnblockAccount(org.unibl.etf.model.User user) throws java.rmi.RemoteException{
    if (accountsApi == null)
      _initAccountsApiProxy();
    return accountsApi.blockUnblockAccount(user);
  }
  
  
}