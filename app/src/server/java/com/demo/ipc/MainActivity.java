package com.demo.ipc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView dataView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataView = findViewById(R.id.dataView);
        dataView.setText(getReceivedData(getIntent()));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        dataView.setText(getReceivedData(intent));
    }

    private String getReceivedData(Intent intent) {
        String data = intent.getStringExtra(C.EXTRA_IPC_DATA);
        if (data == null) return "";
        try {
            JSONObject jsonObject = new JSONObject(data);
            data = jsonObject.getString("field1") + "\n" + jsonObject.getString("field2");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

}