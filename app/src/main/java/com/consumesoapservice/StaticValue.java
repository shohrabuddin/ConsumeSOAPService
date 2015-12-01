package com.consumesoapservice;

/**
 * Created by shohrab.uddin on 01.12.2015.
 */
public class StaticValue {

   /* ATTENTION!! I have used my soap_webservice project from github. Its a java EE application.  You can clone it from https://github.com/shohrabuddin/soap_webservice.git.
   after downloading the project import it in your Java EE version of Eclipse. Then run it on Apache tomcat server.
    */

    // TO access SOAP based web service you always have to add ?wsdl at the end.
    public static String BASE_URL = "http://10.30.22.53:8080/soap_webservice/services/SOAPWebService?wsdl"; // Change the IP address (10.30.22.26) according to your local machine

    public static String WS_NAMESPACE = "http://webservice.soap.com"; //In WSDL file you can find the targetNamespace under the <wsdl:definitions> tag. This is the package name of your project

    public static String SOAP_ACTION_POST="urn:postMethod"; //In the WSDL file you can find "urn:posttMethod" under the <soap:operation ...> tag

    public static String SOAP_ACTION_GET="urn:getMethod"; //In the WSDL file you can find "urn:getMethod" under the <soap:operation ...> tag

    public static String GET_METHOD_NAME ="getMethod"; //getMethod is a method in SOAPWebService class.

    public static String POST_METHOD_NAME ="setMethod";//postMethod is a method in SOAPWebService class.

    public static API_Call API_CALL = new API_Call();
    public static Utility UTILITY = new Utility();


}
