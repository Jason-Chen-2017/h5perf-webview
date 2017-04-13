package com.example.jack.myapplication2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class H5TestActivity extends AppCompatActivity {

    final String domStr = "javascript:window.addEventListener('DOMContentLoaded', function(){prompt('domc:' + new Date().getTime());})";
    final String loadStr = "javascript:window.addEventListener('load', function(){prompt('load:' + new Date().getTime());})";

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5test);

        doH5Test();
    }

    public void doH5Test() {
        tcpdump();

        webView = (WebView) findViewById(R.id.h5WebView);
        //启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);

        final long startTime = System.currentTimeMillis();
        Log.i("TAGH5-StartTime:", startTime + "");

        webView.setWebChromeClient(new WebChromeClient() {

                                       /**
                                        * DOMContentLoaded时间：指的是Dom树构建完成时间，此时加载完页面，解析完所有标签（不包括执行CSS和JS）。
                                        * 在WebChromeClient的onReceivedTitle事件被触发时注入我们的js代码，
                                        * 然后通过WebChromeClient的onJsPrompt事件来获取DOMContentLoaded时间。
                                        * @param view
                                        * @param title
                                        */
                                       @Override
                                       public void onReceivedTitle(WebView view, String title) {
                                           /**
                                            * 白屏时间：用户首次看到网页有内容的时间，即第一次渲染流程完成时间。
                                            * 但是在传统的采集方式里，是在HTML的head标签结尾里记录时间戳，来计算白屏时间。
                                            * 在这个时刻，浏览器开始解析body标签内的内容。而现代浏览器不会等待CSS树和DOM树（整个body标签解析完成）构建完成才开始绘制，而是马上开始显示中间结果。
                                            * 所以经常在低网速的环境中，观察到页面由上至下缓慢显示完，或者先显示文本内容后再重绘成带有格式的页面内容。
                                            * 在android中我们通过使用webview.WebChromeClient的onReceivedTitle事件来近似获得白屏时间。
                                            */
                                           long whiteScreenTime = System.currentTimeMillis();
                                           Log.i("TAGH5-whiteScreenTime", whiteScreenTime - startTime + "");

                                           //插入js
                                           view.loadUrl(domStr);
                                           view.loadUrl(loadStr);
                                       }

                                       /**
                                        * prompt消息被Java层的WebChromeClient.onJsPrompt拦截到
                                        *
                                        * @param view
                                        * @param url
                                        * @param message
                                        * @param defaultValue
                                        * @param result
                                        * @return
                                        */
                                       public boolean onJsPrompt(WebView view, String url, String message,
                                                                 String defaultValue, JsPromptResult result) {
                                           result.confirm();
                                           Log.i("TAGH5-RESULT", message + "|" + url + "|" + defaultValue);
                                           if (message == null || "".equals(message)) {
                                               return false;
                                           }

                                           int domcIndex = message.indexOf("domc:");
                                           if (domcIndex > -1) {
                                               String domc = message.substring(domcIndex + 5);
                                               long domcTime = Long.parseLong(domc);
                                               Log.i("TAGH5-DOMContentLoad", domcTime - startTime + "");
                                           }

                                           int loadIndex = message.indexOf("load:");
                                           if (loadIndex > -1) {
                                               String load = message.substring(loadIndex + 5);
                                               long loadTime = Long.parseLong(load);
                                               Log.i("TAGH5-load", loadTime - startTime + "");
                                           }

                                           return true;
                                       }


                                       @Override
                                       public void onProgressChanged(WebView view, int progress) {
                                       }


                                   }
        );

        String testUrl = getIntent().getStringExtra("testUrl");
        webView.loadUrl(testUrl);
    }


    /**
     * tcpdump抓取请求数据包
     *
     *
     */

    private void tcpdump() {

        Thread tcpdump = new Thread() {
            @Override
            public void run() {
                super.run();
                String[] cmds = {
                        "tcpdump -p -s 0 -w /sdcard/h5test.pcap"
                };

                Log.i("TAGH5", "执行Shell 。。。");
                ShellUtil.CommandResult result = ShellUtil.execCommand(cmds, true, true);
                Log.i("TAGH5", "CommandResult：" + result);
            }
        };

        tcpdump.start();

    }

    /**
     * 记录性能数据，写入web的数据库中
     */

    private void recordPerfDate(String url, PerfData data) {

    }

}
