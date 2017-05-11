package com.example.jack.myapplication2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public String testUrl = "https://www.baidu.com";
    Button testButton;
    Button startDingWebViewButton;
    Button startDingAppButton;

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

        final PackageManager packageManager = this.getPackageManager();

        startDingWebViewButton = (Button) findViewById(R.id.start_ding_webview_button);
        startDingWebViewButton.setOnClickListener(new View.OnClickListener() {
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
                if (null != intent.resolveActivity(packageManager)) {
                    context.startActivity(intent);
                }

            }
        });

        startDingAppButton = (Button) findViewById(R.id.start_ding_app_button);
        startDingAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageInfo pi = null;
                try {
                    pi = packageManager.getPackageInfo("com.alibaba.android.rimet", 0);
                } catch (PackageManager.NameNotFoundException e) {

                }
                Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
                resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                resolveIntent.setPackage(pi.packageName);

                List<ResolveInfo> apps = packageManager.queryIntentActivities(resolveIntent, 0);

                ResolveInfo ri = apps.iterator().next();
                if (ri != null ) {
                    String className = ri.activityInfo.name;

                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);

                    ComponentName cn = new ComponentName("com.alibaba.android.rimet", className);
                    intent.setComponent(cn);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    Context context = getApplicationContext();
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
