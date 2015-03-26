package com.stephensparker.bgtrends;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import android.os.AsyncTask;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity {
    ImageView BGChartImg;
    Bitmap bitmap;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BGChartImg = (ImageView)findViewById(R.id.BGChartImg);

        new LoadChart().execute("http://stephensparker.com/BGTrends/images/bgchart.jpg");
        new LoadTipofDay().execute("http://stephensparker.com/BGTrends/images/bgchart.jpg");
    }


    private class LoadChart extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Loading BG Chart....");
            pDialog.show();
        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap image) {
            if(image != null){
                BGChartImg.setImageBitmap(image);
                pDialog.dismiss();
            }else{
                pDialog.dismiss();
                Toast.makeText(MainActivity.this, "Chart Does Not exist or Network Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class LoadTipOfDay extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Loading BG Chart....");
            pDialog.show();
        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap image) {
            if(image != null){
                BGChartImg.setImageBitmap(image);
                pDialog.dismiss();
            }else{
                pDialog.dismiss();
                Toast.makeText(MainActivity.this, "Chart Does Not exist or Network Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

            @Override
            private void readStream(InputStream in) {
                BufferedReader reader = null;
                try {

                    URL url = new URL(eURL);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    readStream(con.getInputStream());
                    reader = new BufferedReader(new InputStreamReader(in));

                    JSONInputStream objectIn = new JSONInputStream(in);
                    String line = "";
                    strParsedValue= "";
                    while ((line = reader.readLine()) != null) {
                        objectIn.getClass();
                        strParsedValue += line;

                    }
                    txtViewParsedValue.setText(strParsedValue);
                } catch (IOException e) {
                    e.printStackTrace();
                    strParsedValue = "Something Wrong " + e.toString();
                    txtViewParsedValue.setText(strParsedValue);
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                            strParsedValue = "Something Wrong " + e.toString();
                            txtViewParsedValue.setText(strParsedValue);
                        }
                    }
                }
            }

        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
