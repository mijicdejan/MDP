/**
 * Session.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.unibl.etf.model;

public class Session  implements java.io.Serializable {
    private java.lang.String loginTime;

    private java.lang.String logoutTime;

    public Session() {
    }

    public Session(
           java.lang.String loginTime,
           java.lang.String logoutTime) {
           this.loginTime = loginTime;
           this.logoutTime = logoutTime;
    }


    /**
     * Gets the loginTime value for this Session.
     * 
     * @return loginTime
     */
    public java.lang.String getLoginTime() {
        return loginTime;
    }


    /**
     * Sets the loginTime value for this Session.
     * 
     * @param loginTime
     */
    public void setLoginTime(java.lang.String loginTime) {
        this.loginTime = loginTime;
    }


    /**
     * Gets the logoutTime value for this Session.
     * 
     * @return logoutTime
     */
    public java.lang.String getLogoutTime() {
        return logoutTime;
    }


    /**
     * Sets the logoutTime value for this Session.
     * 
     * @param logoutTime
     */
    public void setLogoutTime(java.lang.String logoutTime) {
        this.logoutTime = logoutTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Session)) return false;
        Session other = (Session) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.loginTime==null && other.getLoginTime()==null) || 
             (this.loginTime!=null &&
              this.loginTime.equals(other.getLoginTime()))) &&
            ((this.logoutTime==null && other.getLogoutTime()==null) || 
             (this.logoutTime!=null &&
              this.logoutTime.equals(other.getLogoutTime())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getLoginTime() != null) {
            _hashCode += getLoginTime().hashCode();
        }
        if (getLogoutTime() != null) {
            _hashCode += getLogoutTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Session.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model.etf.unibl.org", "Session"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.etf.unibl.org", "loginTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logoutTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model.etf.unibl.org", "logoutTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
