package com.example.jack.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public String testUrl = "https://www.baidu.com";
    Button testButton;

    public String getTestUrl() {
        return testUrl;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testButton = (Button) findViewById(R.id.test_button);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runTest(testUrl);
            }
        });

    }

    /**
     * 在当前机器的apk上执行H5测试
     *
     * @param testUrl
     */
    public void runTest(String testUrl) {
        Intent intent = new Intent(MainActivity.this, H5TestActivity.class);
        intent.putExtra("url", testUrl);
        startActivity(intent);
    }
}
