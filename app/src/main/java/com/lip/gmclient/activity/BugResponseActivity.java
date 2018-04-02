package com.lip.gmclient.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.lip.gmclient.R;

public class BugResponseActivity extends AppCompatActivity {

    public EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_bugresponse);
    }

    public void back(View view){
        finish();
    }

    public void commitBugResponse(View view){

    }
}
