package com.demo.ipc;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

import static com.demo.ipc.C.ACTION_IPC;
import static com.demo.ipc.C.EXTRA_IPC_DATA;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText etField1 = findViewById(R.id.field1);
        final EditText etField2 = findViewById(R.id.field2);

        findViewById(R.id.sendBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(ACTION_IPC);
                JSONObject data = new JSONObject();
                Editable field1 = etField1.getText();
                Editable field2 = etField2.getText();
                try {
                    data.put("field1", field1 != null ? field1.toString() : "");
                    data.put("field2", field2 != null ? field2.toString() : "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                sendIntent.putExtra(EXTRA_IPC_DATA, data.toString());
                sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(sendIntent);
            }
        });
    }

}