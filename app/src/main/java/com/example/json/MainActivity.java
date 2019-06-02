package com.example.json;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.json.Model.JsonModel;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtType, txtArtistName, txtCollectionName, txtTrackName, txtKind;
    ImageView image;
    Button button;
    String imageUrl;
    Bitmap bitmap;
    EditText number;
    int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtType = findViewById(R.id.txtType);
        txtArtistName = findViewById(R.id.txtArtistName);
        txtCollectionName = findViewById(R.id.txtCollectionName);
        txtTrackName = findViewById(R.id.txtTrackName);
        txtKind = findViewById(R.id.txtKind);
        image = findViewById(R.id.image);
        button = findViewById(R.id.button);
        number = findViewById(R.id.number);

        button.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        if(number.getText().toString().matches("")){
            number.setError("you should pick the song number");
            number.requestFocus();
        }else if(Integer.parseInt(number.getText().toString()) < 1 ||
                Integer.parseInt(number.getText().toString()) >50){
            number.setError("you should pick from 1 to 50 .. no less - no more");
            number.requestFocus();
        }else {
            num = Integer.parseInt(number.getText().toString()) -1;
            new JSONTask(MainActivity.this).execute();
        }
    }

    public class JSONTask extends AsyncTask<String, Void, JsonModel>{

        Context context;
        ProgressDialog progressDialog;

        public JSONTask(Context context){

            this.context = context;
            progressDialog = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Downloading ... please wait");
            progressDialog.show();
        }

        @Override
        protected JsonModel doInBackground(String... strings) {

            JsonModel jsonModel = new JsonModel();

            JsonClient JsonClient = new JsonClient();
            String data = JsonClient.getJsonData();

            try {
                jsonModel = JsonParser.getJsonModel(data, num);
                imageUrl = jsonModel.getArtistViewURL();
                bitmap = JsonClient.downloadBitmap(imageUrl);

            }catch (Throwable t){
                t.printStackTrace();
            }

            return jsonModel;
        }

        @Override
        protected void onPostExecute(JsonModel jsonModel) {
            super.onPostExecute(jsonModel);

            txtType.setText(jsonModel.getType());
            txtArtistName.setText(jsonModel.getArtistName());
            txtCollectionName.setText(jsonModel.getCollectionName());
            txtTrackName.setText(jsonModel.getTrackName());
            txtKind.setText(jsonModel.getKind());
            image.setImageBitmap(bitmap);

            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }
    }
}
