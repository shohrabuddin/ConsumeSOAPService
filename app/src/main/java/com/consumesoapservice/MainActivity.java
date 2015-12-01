package com.consumesoapservice;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import org.ksoap2.serialization.SoapObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Shohrab Uddin
 *This activity class is used to
 */

public class MainActivity extends AppCompatActivity {
    //@InjectView is ButterKnife annotation to inject Views and to get rid of findviewById.
    @InjectView(R.id.edtName) EditText edtName;
    @InjectView(R.id.edtAge) EditText edtAge;
    @InjectView(R.id.edtAddress) EditText edtAddress;
    @InjectView(R.id.coordinatorLayout) CoordinatorLayout coordinatorL;

    private  ProgressDialog prog;
    private String mName, mAge, mAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        prog = new ProgressDialog(this);

    }

    // The API call is a background task. You can not call API in the main thread.
    //Note If you do not want the user to wait for the response from API or if you have nested API calls then use RxJava and Retrofit for better performance
    private class GetAsyncClass extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... params) {
            SoapObject request = new SoapObject(StaticValue.WS_NAMESPACE,
                    StaticValue.GET_METHOD_NAME);

            StaticValue.API_CALL.apiCall(request, StaticValue.SOAP_ACTION_GET);

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            prog.dismiss();
            if(StaticValue.API_CALL.isServerReachable()) {
                String response = StaticValue.API_CALL.getJsonObject().toString();
                StaticValue.UTILITY.showAlertDlg(MainActivity.this, "API Response", "Response from server\n\n" + response + "\n\nThis is a JSON response. If you" +
                        " are not familiar with JSON then do some self study :)");

            }else{
                Snackbar snackbar = Snackbar.make(coordinatorL, "Server is not reachable!!", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
            super.onPostExecute(s);
        }
    }

    // The API call is a background task. You can not call API in the main thread.
    //Note If you do not want the user to wait for the response from API or if you have nested API calls then use RxJava and Retrofit for better performance
    private class PostAsyncClass extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute() {
            mName = edtName.getText().toString();
            mAge = edtAge.getText().toString();
            mAddress = edtAddress.getText().toString();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            SoapObject request = new SoapObject(StaticValue.WS_NAMESPACE, StaticValue.POST_METHOD_NAME);

            // Add parameters to the corresponding method of Web service
            request.addProperty("name", mName);
            request.addProperty("age", mAge);
            request.addProperty("address", mAddress);

            StaticValue.API_CALL.apiCall(request, StaticValue.SOAP_ACTION_POST);

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            prog.dismiss();
            if(StaticValue.API_CALL.isServerReachable()) {
                String response = StaticValue.API_CALL.getJsonObject().toString();
                StaticValue.UTILITY.showAlertDlg(MainActivity.this, "API Response", "Response from server\n\n" + response + "\n\nThis is a JSON response. If you" +
                        " are not familiar with JSON then do some self study :)");
            }else{
                Snackbar snackbar = Snackbar.make(coordinatorL, "Server is not reachable!!", Snackbar.LENGTH_LONG);
                snackbar.show();
            }



            super.onPostExecute(s);
        }
    }

    //@OnClick is a Butterknife annotation. It performs the click event of a View and reduce code complexity
    @OnClick(R.id.btnPost)
    public void postButtonClick(){
        //Validation
        if(!StaticValue.UTILITY.isNetworkAvailable(getApplicationContext())){
            StaticValue.UTILITY.showSnackBar(coordinatorL, "You do not have internet connection.");
            return;
        }

        if(edtAddress.getText().toString().isEmpty() || edtAge.getText().toString().isEmpty() || edtAddress.getText().toString().isEmpty()){
            Snackbar snackbar = Snackbar.make(coordinatorL, "You have to provide name, age and address data.", Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }


        prog.setMessage("Please wait");
        prog.show();

        PostAsyncClass asyncTask = new PostAsyncClass();
        asyncTask.execute();

    }

    //@OnClick is a Butterknife annotation. It performs the click event of a View and reduce code complexity
    @OnClick(R.id.btnGet)
    public void gettButtonClick(){
        prog.setMessage("Please wait");
        prog.show();

        GetAsyncClass asyncTask = new GetAsyncClass();
        asyncTask.execute();
    }

}
