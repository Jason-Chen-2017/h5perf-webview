package com.example.jack.myapplication2;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * PC端服务程序，调用如下命令，触发手机上的Webview壳执行：
 * adb shell am start --es url "https://jason-chen-2017.github.io/Jason-Chen-2017/" com.example.jack.myapplication2/com.example.jack.myapplication2.H5TestActivity
 * <p>
 * adb shell am start --es url "https://jason-chen-2017.github.io/Jason-Chen-2017/" --el tid 1 com.example.jack.myapplication2/com.example.jack.myapplication2.H5TestActivity
 */


public class H5TestActivity extends AppCompatActivity {

    final String domStr = "javascript:window.addEventListener('DOMContentLoaded', function(){prompt('domc:' + new Date().getTime());})";
    final String loadStr = "javascript:window.addEventListener('load', function(){prompt('load:' + new Date().getTime());})";
    final List<HashMap<String, String>> requestResources = new ArrayList<>();
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5test);

        String testUrl = getIntent().getStringExtra("url");
//        String tid = getIntent().getStringExtra("tid");
        Long tid = getIntent().getLongExtra("tid", 1);
        Log.i("TAGH5-testUrl", testUrl);
        Log.i("TAGH5-tid", tid + "");

        doH5Test(testUrl, tid);
    }


    /**
     * @param testUrl
     * @param tid
     */
    public void doH5Test(final String testUrl, final Long tid) {
        webView = (WebView) findViewById(R.id.h5WebView);
        //启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);

        final Long startTime = System.currentTimeMillis();
        Log.i("TAGH5-StartTime:", startTime + "");

        webView.setWebViewClient(new WebViewClient() {

            volatile Integer countOnLoadResource = 0;
            volatile Integer countInterceptRequest = 0;

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.i("TAGH5", "onPageStarted 时间: " + (System.currentTimeMillis() - startTime) + "");
                Log.i("TAGH5", "onPageStarted url: " + url);
            }


            public void onPageFinished(WebView view, String url) {
                Log.i("TAGH5", "onPageFinished 时间: " + (System.currentTimeMillis() - startTime) + "");
                Log.i("TAGH5", "onPageFinished url: " + url);

                //“NetworkOnMainThreadException”出错提示的原因及解决办法
                new Thread() {
                    @Override
                    public void run() {
                        //把网络访问的代码放在这里
                        RecordPerfDataTask recordPerfDataTask = new RecordPerfDataTask(requestResources);
                        recordPerfDataTask.doInBackground(null);
                    }
                }.start();


            }

            /**
             * 页面加载完成可见
             * @param view
             * @param url
             */
            public void onPageCommitVisible(WebView view, String url) {
                Log.i("TAGH5", "onPageCommitVisible 时间: " + (System.currentTimeMillis() - startTime) + "");
                Log.i("TAGH5", "onPageCommitVisible url: " + url);
            }

            public void onLoadResource(WebView view, String url) {
//                Log.i("TAGH5", "第" + (++countOnLoadResource) + "个onLoadResource 时间: " + (System.currentTimeMillis() - startTime) + "");
//                Log.i("TAGH5", "onLoadResource url: " + url);
//                Log.i("TAGH5", "onLoadResource getResources: " + JSON.toJSONString(view.getResources()));

            }

            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                Log.i("TAGH5", "第" + (++countInterceptRequest) + "个shouldInterceptRequest Request时间: " + ((System.currentTimeMillis() - startTime)) + " " + JSON.toJSONString(request));
//                Log.i("TAGH5", "shouldInterceptRequest uri: " + JSON.toJSONString(request.getUrl()));
//                Log.i("TAGH5", "shouldInterceptRequest Method: " + request.getMethod());
//                Log.i("TAGH5", "shouldInterceptRequest RequestHeaders: " + JSON.toJSONString(request.getRequestHeaders()));

                String requestUrl = request.getUrl().getScheme() + ":" + request.getUrl().getSchemeSpecificPart();
                String method = request.getMethod();
                Long timestamp = System.currentTimeMillis();
                Integer timeCost = Integer.valueOf(timestamp - startTime + "");
                String headers = JSON.toJSONString(request.getRequestHeaders());

                HashMap<String, String> map = new HashMap<>();
                map.put("tid", tid == null ? "" : tid + "");
                map.put("index", countInterceptRequest == null ? "" : countInterceptRequest + "");
                map.put("testUrl", testUrl);
                map.put("startTime", startTime == null ? "" : startTime + "");
                map.put("timestamp", timestamp == null ? "" : timestamp + "");
                map.put("timeCost", timeCost == null ? "" : timeCost + "");
                map.put("requestUrl", requestUrl);
                map.put("method", method);
                map.put("headers", headers);

                Log.i("TAGH5-addMap", JSON.toJSONString(map));
                requestResources.add(map);


                return null;
            }

            public void onReceivedLoginRequest(WebView view, String realm,
                                               String account, String args) {
            }
        });


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
                                           if (progress == 100) {
                                               Log.i("TAGH5", "onProgressChanged:" + progress);
                                           }
                                           super.onProgressChanged(view, progress);
                                       }
                                   }
        );


        webView.loadUrl(testUrl);

    }


}


