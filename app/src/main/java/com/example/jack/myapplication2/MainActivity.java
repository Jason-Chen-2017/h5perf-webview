package com.example.jack.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public String testUrl = "https://www.baidu.com";
    Button testButton;
    Button startDingButton;

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

        final PackageManager pm = this.getPackageManager();

        startDingButton = (Button) findViewById(R.id.start_ding_button);
        startDingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                String packageName = "com.alibaba.android.rimet";
//                String className = "com.alibaba.lightapp.runtime.activity.CommonWebViewActivity";
//                intent.setClassName(packageName, className);
//                intent.setData(Uri.parse("dingtalk://dingtalkclient/page/link?url=http%3A%2F%2Fwww.baidu.com"));//merge
//                startActivity(intent);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri1 = Uri.parse("dingtalk://dingtalkclient/page/link?url=http%3A%2F%2Fwww.baidu.com");
                intent.setData(uri1);

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                Context context = getApplicationContext();
                if (null != intent.resolveActivity(pm)) {
                    context.startActivity(intent);
                }

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
