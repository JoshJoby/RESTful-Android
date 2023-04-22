package com.example.joshazureapi;
import static android.content.ContentValues.TAG;

import static com.example.joshazureapi.NetworkTask.getResponse;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    private Button buttonStart;
    private Button buttonAdd;

    private Button buttonUpdate;
    private Button buttonDelete;

    private TextView textViewGET;
    private TextView textViewPOST;
    private TextView textViewDELETE;
    private TextView textViewPUT;
    private CountDownTimer countDownTimer;
    Button submitBtn;

    EditText editTextName;
    EditText editTextAge;
    TableLayout tableLayout;
    GifImageView gifImageView;
    LinearLayout linearLayout;
    String jsonResponse1;
    Animation fadeInAnimation;
    Context context;
    LinearLayout layout1;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Josh's RESTful app");
        NetworkTask networkTask = new NetworkTask(context);
        networkTask.execute("https://joshfunctionapp2.azurewebsites.net/api/HttpTrigger1?code=h8XH9bEonJxj7oUPffSk14yE22Pd9GmU-rqC_1viUsR1AzFuYiJ67A==");
        context = getApplicationContext();
        fadeInAnimation = AnimationUtils.loadAnimation(context, R.xml.animations);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(context, "Fetching Data...", Toast.LENGTH_SHORT).show();
        gifImageView = findViewById(R.id.loadingif);
        buttonStart = findViewById(R.id.button);
        buttonStart.setVisibility(View.GONE);
        buttonAdd = findViewById(R.id.button2);
        buttonAdd.setVisibility(View.GONE);
        buttonUpdate = findViewById(R.id.button3);
        buttonUpdate.setVisibility(View.GONE);
        buttonDelete = findViewById(R.id.button4);
        buttonDelete.setVisibility(View.GONE);
        ViewGroup parentView = (ViewGroup) gifImageView.getParent();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Remove the view from its parent after the delay
                parentView.removeView(gifImageView);
                buttonStart.setAnimation(fadeInAnimation);
                buttonStart.setVisibility(View.VISIBLE);
                buttonAdd.setAnimation(fadeInAnimation);

                buttonAdd.setVisibility(View.VISIBLE);
                buttonUpdate.setAnimation(fadeInAnimation);

                buttonUpdate.setVisibility(View.VISIBLE);
                buttonDelete.setAnimation(fadeInAnimation);

                buttonDelete.setVisibility(View.VISIBLE);
            }
        }, 5000);

        layout1 = findViewById(R.id.layout1);
        tableLayout = new TableLayout(context);
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkTask networkTaskNew = new NetworkTask(context);
                networkTaskNew.execute("https://joshfunctionapp2.azurewebsites.net/api/HttpTrigger1?code=h8XH9bEonJxj7oUPffSk14yE22Pd9GmU-rqC_1viUsR1AzFuYiJ67A==");
                jsonResponse1 = getResponse();
                if (tableLayout.getChildCount() > 0) {
                    ViewGroup parentView = (ViewGroup) tableLayout.getParent();
                    parentView.removeView(tableLayout);
                    tableLayout = new TableLayout(context);

                    tableLayout.setGravity(Gravity.CENTER);
                }
                if(linearLayout.getChildCount() > 0){
                    ViewGroup parentView2 = (ViewGroup) linearLayout.getParent();
                    parentView2.removeView(linearLayout);
                    linearLayout = new LinearLayout(context);
                    linearLayout.setOrientation(LinearLayout.VERTICAL);

                }

                textViewGET = new TextView(context);
                textViewGET.setText("Presenting all Data as follows :-");
                textViewGET.setPadding(325,80,0,0);

// Create a new TableRow
                TableRow tableRow1 = new TableRow(context);
                tableRow1.setPadding(350, 75, 10, 10);
                TableRow tableRow2;
// Create three TextViews for the first row
                TextView textView1 = new TextView(context);
                textView1.setText("Name");
                Typeface typeface = Typeface.create("sans-serif-light", Typeface.BOLD);
                textView1.setTypeface(typeface);
                textView1.setTextSize(25);
                tableRow1.startAnimation(fadeInAnimation);

                tableRow1.addView(textView1);

                TextView textView2 = new TextView(context);
                textView2.setText("Age");
                Typeface typeface2 = Typeface.create("sans-serif-light", Typeface.BOLD);
                textView2.setTypeface(typeface);
                textView2.setTextSize(25);
                textView2.setPadding(80,0,0,0);

                tableRow1.addView(textView2);
// Add the first row to the table
                tableLayout.addView(textViewGET);
                tableLayout.addView(tableRow1);
                try {
                    String jsonResponse = jsonResponse1.substring(15);
                    Log.d(TAG, "helloo " + jsonResponse);
                    JSONArray jsonArray = new JSONArray(jsonResponse);
                    for(int i=0; i<jsonArray.length(); i++) {
                        Log.d(TAG, "inside loop");
// Create a second TableRow
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        tableRow2 = new TableRow(getApplicationContext());
// Create three TextViews for the second row
                        TextView textView3 = new TextView(getApplicationContext());
                        textView3.setText(jsonObject1.getString("NAME"));
                        Typeface typeface3 = Typeface.create("sans-serif-light", Typeface.NORMAL);
                        textView3.setTypeface(typeface);
                        textView3.setTextSize(20);
                        tableRow2.addView(textView3);

                        TextView textView4 = new TextView(getApplicationContext());
                        textView4.setText(String.valueOf(jsonObject1.getInt("AGE")));
                        textView4.setPadding(80,0,0,0);
                        Typeface typeface4 = Typeface.create("sans-serif-light", Typeface.NORMAL);
                        textView4.setTypeface(typeface);
                        textView4.setTextSize(20);
                        tableRow2.addView(textView4);
                        tableRow2.startAnimation(fadeInAnimation);

                        tableRow2.setPadding(375, 10, 10, 10);

// Add the second row to the tabl
                        tableLayout.addView(tableRow2);
                    }
// Add the table to the layout
                }catch (JSONException e) {
                    e.printStackTrace();
                }
                layout1.addView(tableLayout);
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tableLayout.getChildCount() > 0) {
                    ViewGroup parentView = (ViewGroup) tableLayout.getParent();
                    parentView.removeView(tableLayout);
                    tableLayout = new TableLayout(context);
                }
                if(linearLayout.getChildCount() > 0){
                    ViewGroup parentView2 = (ViewGroup) linearLayout.getParent();
                    parentView2.removeView(linearLayout);
                    linearLayout = new LinearLayout(context);
                    linearLayout.setOrientation(LinearLayout.VERTICAL);

                }

                textViewPOST= new TextView(context);
                textViewPOST.setText("Please fill the following form to add the field :-");
                textViewPOST.setPadding(0,0,0,100);
                    linearLayout.setPadding(200,200,200,0);
                    editTextName = new EditText(context);
                    editTextName.setTextColor(Color.WHITE);
                    editTextName.setWidth(350);
                    editTextName.setHint("Enter Name");
                    editTextName.setHintTextColor(Color.DKGRAY);


                    editTextAge = new EditText(context);
                    editTextAge.setTextColor(Color.WHITE);
                    editTextAge.setWidth(350);
                    editTextAge.setHint("Enter Age");
                    editTextAge.setHintTextColor(Color.DKGRAY);

                    LinearLayout submitlayout = new LinearLayout(context);
                    submitBtn = new Button(context);
                    submitBtn.setText("Submit");
                    submitBtn.setTextColor(Color.BLACK);
                    submitBtn.setBackgroundColor(Color.WHITE);

                    submitBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String name = String.valueOf(editTextName.getText());
                            String age = String.valueOf(editTextAge.getText());
                            Log.d(TAG, name + ", " + age);
                            NetworkTaskPOST networkTaskPOST = new NetworkTaskPOST(context);
                            NetworkTask networkTask = new NetworkTask(context);
                            networkTaskPOST.execute("https://joshfunctionapp2.azurewebsites.net/api/HttpTrigger1?code=h8XH9bEonJxj7oUPffSk14yE22Pd9GmU-rqC_1viUsR1AzFuYiJ67A==", name, age);
                            Toast.makeText(context, "Adding Data...", Toast.LENGTH_SHORT).show();
                            linearLayout.removeView(textViewPOST);
                            linearLayout.removeView(editTextName);
                            linearLayout.removeView(editTextAge);
                            linearLayout.removeView(submitlayout);
                            layout1.removeView(linearLayout);

                        }
                    });
                    submitlayout.addView(submitBtn);
                    submitlayout.setPadding(250,50,0,0);

                linearLayout.addView(textViewPOST);

                linearLayout.addView(editTextName);
                    linearLayout.addView(editTextAge);
                    linearLayout.addView(submitlayout);
                    layout1.addView(linearLayout);
                linearLayout.startAnimation(fadeInAnimation);

            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tableLayout.getChildCount() > 0) {
                    ViewGroup parentView = (ViewGroup) tableLayout.getParent();
                    parentView.removeView(tableLayout);
                    tableLayout = new TableLayout(context);
                }
                if(linearLayout.getChildCount() > 0){
                    ViewGroup parentView2 = (ViewGroup) linearLayout.getParent();
                    parentView2.removeView(linearLayout);
                    linearLayout = new LinearLayout(context);
                    linearLayout.setOrientation(LinearLayout.VERTICAL);

                }
                textViewPUT= new TextView(context);
                textViewPUT.setText("Please fill the following form to update the field:-");
                textViewPUT.setPadding(0,0,0,100);
                linearLayout.setPadding(200,200,200,0);
                editTextName = new EditText(context);
                editTextName.setTextColor(Color.WHITE);
                editTextName.setWidth(350);
                editTextName.setHint("Enter Name");
                editTextName.setHintTextColor(Color.DKGRAY);


                editTextAge = new EditText(context);
                editTextAge.setTextColor(Color.WHITE);
                editTextAge.setWidth(350);
                editTextAge.setHint("Enter Age");
                editTextAge.setHintTextColor(Color.DKGRAY);

                LinearLayout submitlayout = new LinearLayout(context);
                submitBtn = new Button(context);
                submitBtn.setText("Submit");
                submitBtn.setTextColor(Color.BLACK);
                submitBtn.setBackgroundColor(Color.WHITE);

                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = String.valueOf(editTextName.getText());
                        String age = String.valueOf(editTextAge.getText());
                        Log.d(TAG, name + ", " + age);
                        NetworkTaskPUT networkTaskPUT = new NetworkTaskPUT(context);
                        networkTaskPUT.execute("https://joshfunctionapp2.azurewebsites.net/api/HttpTrigger1?code=h8XH9bEonJxj7oUPffSk14yE22Pd9GmU-rqC_1viUsR1AzFuYiJ67A==", name, age);
                        Toast.makeText(context, "Updating Data...", Toast.LENGTH_SHORT).show();
                        linearLayout.removeView(textViewPUT);
                        linearLayout.removeView(editTextName);
                        linearLayout.removeView(editTextAge);
                        linearLayout.removeView(submitlayout);
                        layout1.removeView(linearLayout);

                    }

                });

                submitlayout.addView(submitBtn);
                submitlayout.setPadding(250,50,0,0);
                linearLayout.addView(textViewPUT);

                linearLayout.addView(editTextName);
                linearLayout.addView(editTextAge);
                linearLayout.addView(submitlayout);
                layout1.addView(linearLayout);
                linearLayout.startAnimation(fadeInAnimation);

            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tableLayout.getChildCount() > 0) {
                    ViewGroup parentView = (ViewGroup) tableLayout.getParent();
                    parentView.removeView(tableLayout);
                    tableLayout = new TableLayout(context);
                }
                if(linearLayout.getChildCount() > 0){
                    ViewGroup parentView2 = (ViewGroup) linearLayout.getParent();
                    parentView2.removeView(linearLayout);
                    linearLayout = new LinearLayout(context);
                    linearLayout.setOrientation(LinearLayout.VERTICAL);

                }
                textViewDELETE= new TextView(context);
                textViewDELETE.setText("Please fill the following form to delete the field:-");
                textViewDELETE.setPadding(0,0,0,100);
                linearLayout.setPadding(200,200,200,0);
                editTextName = new EditText(context);
                editTextName.setTextColor(Color.WHITE);
                editTextName.setWidth(350);
                editTextName.setHint("Enter Name");
                editTextName.setHintTextColor(Color.DKGRAY);

                LinearLayout submitlayout = new LinearLayout(context);
                submitBtn = new Button(context);
                submitBtn.setText("Submit");
                submitBtn.setTextColor(Color.BLACK);
                submitBtn.setBackgroundColor(Color.WHITE);

                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = String.valueOf(editTextName.getText());
                        Log.d(TAG, name);
                        NetworkTaskDELETE networkTaskDELETE = new NetworkTaskDELETE(context);
                        networkTaskDELETE.execute("https://joshfunctionapp2.azurewebsites.net/api/HttpTrigger1?code=h8XH9bEonJxj7oUPffSk14yE22Pd9GmU-rqC_1viUsR1AzFuYiJ67A==", name);
                        Toast.makeText(context, "Deleting Data...", Toast.LENGTH_SHORT).show();
                        linearLayout.removeView(textViewDELETE);
                        linearLayout.removeView(editTextName);
                        linearLayout.removeView(submitlayout);
                        layout1.removeView(linearLayout);

                    }
                });
                submitlayout.addView(submitBtn);
                submitlayout.setPadding(250,50,0,0);
                linearLayout.addView(textViewDELETE);
                linearLayout.addView(editTextName);
                linearLayout.addView(submitlayout);
                layout1.addView(linearLayout);
                linearLayout.startAnimation(fadeInAnimation);

            }
        });


    }
}

class NetworkTask extends AsyncTask<String, Void, String> {
    public static String response;
    String responsetext;
    Context context;

    NetworkTask(Context context){
        this.context = context;
    }
    public static String getResponse(){
        return response;
    }
    @Override
    protected String doInBackground(String... urls) {
        OkHttpClient client = new OkHttpClient();

        String url = urls[0];

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            System.out.print(responseBody + " hii");
            return responseBody;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        response = result;
        Log.d(TAG, "Hi from onPostExecute of get");
        if (result != null) {
            Log.d(TAG, result);
        }
        else Log.d(TAG, "No output");
    }
}

class NetworkTaskPOST extends AsyncTask<String, Void, String> {
    public static String response;

    Context context;
    String responsetext;

    NetworkTaskPOST(Context context){
        this.context = context;
    }
    public String name;
    public String age;

    public static String getResponse(){
        return response;
    }
    @Override
    protected String doInBackground(String... urls) {
        try {
            OkHttpClient client = new OkHttpClient();
            name = urls[1];
            age = urls[2];
            String requestText = "{\"name\" : \"" + name + "\", \"age\" : " + age + "}";
            Log.d(TAG, requestText);
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody requestBody = RequestBody.create(requestText, mediaType);
            Request request = new Request.Builder()
                    .url(urls[0])
                    .post(requestBody)
                    .build();

            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            String requestBodyString = buffer.readUtf8();
            Log.d(TAG, requestBodyString);

            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            responsetext = responseBody.string();
            Log.d(TAG, requestText);
// Read and handle the response from the server here

            responseBody.close();
            client.dispatcher().executorService().shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responsetext;
    }

    @Override
    protected void onPostExecute(String result) {
        response = result;
        Log.d(TAG, "Hi from onPostExecute");
        if (result != null) {
            Log.d(TAG, result);
            if (result.equals("Row added successfully")) {
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            }
        }
        else Log.d(TAG, "No output");
    }
}

class NetworkTaskPUT extends AsyncTask<String, Void, String> {
    public static String response;
    Context context;
    String responsetext;

    NetworkTaskPUT(Context context){
        this.context = context;
    }
    public String name;
    public String age;

    public static String getResponse(){
        return response;
    }
    @Override
    protected String doInBackground(String... urls) {
        try {
            OkHttpClient client = new OkHttpClient();
            name = urls[1];
            age = urls[2];
            String requestText = "{\"name\" : \"" + name + "\", \"age\" : " + age + "}";
            Log.d(TAG, requestText);
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody requestBody = RequestBody.create(requestText, mediaType);
            Request request = new Request.Builder()
                    .url(urls[0])
                    .put(requestBody)
                    .build();

            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            String requestBodyString = buffer.readUtf8();
            Log.d(TAG, requestBodyString);

            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            responsetext = responseBody.string();
            Log.d(TAG, responsetext);
// Read and handle the response from the server here

            responseBody.close();
            client.dispatcher().executorService().shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responsetext;
    }

    @Override
    protected void onPostExecute(String result) {
        response = result;
        Log.d(TAG, "Hi from onPostExecute");
        if (result != null) {
            Log.d(TAG, result);
            if (result.equals("Row updated successfully")) {
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            }
        }
        else Log.d(TAG, "No output");
    }
}

class NetworkTaskDELETE extends AsyncTask<String, Void, String> {
    public static String response;

    String responsetext;
    Context context;

    NetworkTaskDELETE(Context context){
        this.context=context;
    }
    public String name;
    public static String getResponse(){
        return response;
    }
    @Override
    protected String doInBackground(String... urls) {
        ResponseBody responseBody = null;
        try {
            OkHttpClient client = new OkHttpClient();
            name = urls[1];
            String requestText = "{\"name\" : \"" + name + "\"}";
            Log.d(TAG, requestText);
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody requestBody = RequestBody.create(requestText, mediaType);
            Request request = new Request.Builder()
                    .url(urls[0])
                    .delete(requestBody)
                    .build();

            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            String requestBodyString = buffer.readUtf8();
            Log.d(TAG, requestBodyString);

            Response response = client.newCall(request).execute();
            responseBody = response.body();
            responsetext = responseBody.string();
            Log.d(TAG, responsetext + "of delete, not post exe");

// Read and handle the response from the server here
            responseBody.close();
            client.dispatcher().executorService().shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responsetext;
    }

    @Override
    protected void onPostExecute(String result) {

        String response1 = result + "of delete";
        Log.d(TAG, "Hi from onPostExecute of delete");
        if (result != null) {
            Log.d(TAG, result);
            if (result.equals("Row deleted successfully")) {
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            }
        }
        else Log.d(TAG, "No output of delete");
    }
}