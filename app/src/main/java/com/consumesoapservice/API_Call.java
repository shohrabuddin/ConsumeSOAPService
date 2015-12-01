package com.consumesoapservice;

import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

/**
 * Created by shohrab.uddin on 01.12.2015.
 * This class performs the API call using KSOAP2 library
 */
public class API_Call {
    public boolean serverReachable;
    public JSONObject jsonObject;

    public void apiCall(SoapObject request, String soapAction){
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        envelope.dotNet = false;

        HttpTransportSE androidHttpTransport = new HttpTransportSE(StaticValue.BASE_URL);

        try {
                androidHttpTransport.call(soapAction, envelope);
        } catch (IOException e) {

            e.printStackTrace();
        } catch (XmlPullParserException e) {

            e.printStackTrace();
        }

        if (envelope.bodyIn instanceof SoapObject) { // SoapObject = SUCCESS
            SoapObject result = (SoapObject) envelope.bodyIn;

            if(result!=null){
                this.setServerReachable(true);
                String jsonSting= result.getProperty(0).toString();
                try {
                    JSONObject jsonObj = new JSONObject(jsonSting);
                    this.setJsonObject(jsonObj);
                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }else{

                this.setServerReachable(false);
            }

            // ... do whatever you want with this object now
        } else {
            this.setServerReachable(false);
            if (envelope.bodyIn instanceof SoapFault) { // SoapFault = FAILURE
                SoapFault soapFault = (SoapFault) envelope.bodyIn;
                try {
                    throw new Exception(soapFault.getMessage());
                } catch (Exception e) {
                    this.setServerReachable(false);
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isServerReachable() {
        return serverReachable;
    }

    public void setServerReachable(boolean serverReachable) {
        this.serverReachable = serverReachable;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
