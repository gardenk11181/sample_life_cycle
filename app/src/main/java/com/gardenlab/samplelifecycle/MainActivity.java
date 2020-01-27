package com.gardenlab.samplelifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Main", "onCreate called");
        editText = findViewById(R.id.editText);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Main", "onStart called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Main", "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Main", "onDestroy called");
        clearState();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Main", "onPause called");

        saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Main", "onResume called");

        restoreState();
    }

    protected void saveState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", editText.getText().toString());
        editor.commit();
    }

    protected  void restoreState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if((pref != null) && pref.contains("name")) {
            String name = pref.getString("name","");
            editText.setText(name);
        }
    }

    protected  void clearState() {
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}
