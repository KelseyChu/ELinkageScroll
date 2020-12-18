package com.baidu.elinkagescroll.sample;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.baidu.elinkagescroll.ELinkageScrollLayout;
import com.baidu.elinkagescroll.R;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private WebView mWebView;

    private ELinkageScrollLayout mElink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mElink = findViewById(R.id.elink);

        mWebView = findViewById(R.id.webview);
        initWebView();

        mRecyclerView = findViewById(R.id.recycler1);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinkageRecyclerAdapter rvAdapter = new LinkageRecyclerAdapter(getRVData(200), 0x6600ffff);
        mRecyclerView.setAdapter(rvAdapter);

        mRecyclerView = findViewById(R.id.recycler2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinkageRecyclerAdapter rvAdapter2 = new LinkageRecyclerAdapter(getRVData(5), 0x66ff00ff);
        mRecyclerView.setAdapter(rvAdapter2);

        mRecyclerView = findViewById(R.id.recycler_in_framelayout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinkageRecyclerAdapter rvAdapter3 =
                new LinkageRecyclerAdapter(getRVInFrameData(200), 0x66ff0000);
        mRecyclerView.setAdapter(rvAdapter3);
    }

    private void initWebView() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setSupportZoom(false);
        mWebView.getSettings().setDisplayZoomControls(false);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onRequestFocus(WebView view) {
                //super.onRequestFocus(view);
            }
        });

        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        if (Build.VERSION.SDK_INT >= 21) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());
        //mWebView.loadUrl("https://shop.timberland.com.tw/");

        loadHTML(
                "<p style=\"text-align: center\"><img alt=\"活動\" src=\"https://tpimage.91app.com/TBL/TBL_cam.jpg\"><br><img alt=\" Timberland \" src=\"https://tpimage.91app.com/TBL/20201021-25p/A2CM7A58_OK/001_01.jpg\"><br><img alt=\" Timberland \" src=\"https://tpimage.91app.com/TBL/20201021-25p/A2CM7A58_OK/001_02.jpg\"><br><img alt=\" Timberland \" src=\"https://tpimage.91app.com/TBL/20201021-25p/A2CM7A58_OK/001_03.jpg\"><br><img alt=\" Timberland \" src=\"https://tpimage.91app.com/TBL/20201021-25p/A2CM7A58_OK/002_01.jpg\"><br><img alt=\" Timberland \" src=\"https://tpimage.91app.com/TBL/20201021-25p/A2CM7A58_OK/002_02.jpg\"><br><img alt=\" Timberland \" src=\"https://tpimage.91app.com/TBL/20201021-25p/A2CM7A58_OK/002_03.jpg\"><br><img alt=\" Timberland \" src=\"https://tpimage.91app.com/TBL/20201021-25p/A2CM7A58_OK/002_04.jpg\"><br><img alt=\" Timberland \" src=\"https://tpimage.91app.com/TBL/20201021-25p/A2CM7A58_OK/003_01.jpg\"><br><img alt=\"Timberland\" src=\"https://tpimage.91app.com/TBL/Brandstory_750.jpg\"></p>");

        //loadHTML(
        //        "<p style=\"text-align: center\"><img alt=\"Timberland\" src=\"https://tpimage.91app.com/TBL/Shoes_size.jpg\"></p>");
    }

    public void loadHTML(String htmlContent) {
        if (htmlContent != null) {
            mWebView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null);
        }
    }

    private ArrayList<String> getRVData(int count) {
        ArrayList<String> data = new ArrayList<>();
        String temp = "RecyclerView - ";
        for (int i = 0; i < count; i++) {
            data.add(temp + i + "\n" + random());
        }
        return data;
    }

    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(8);
        for (int i = 0; i < randomLength; i++){
            randomStringBuilder.append("*\n");
        }
        randomStringBuilder.append("*");
        return randomStringBuilder.toString();
    }

    private ArrayList<String> getRVInFrameData(int count) {
        ArrayList<String> data = new ArrayList<>();
        String temp = "RecyclerInFrameLayout - ";
        for (int i = 0; i < count; i++) {
            data.add(temp + i);
        }
        return data;
    }

    public void onLLButtonClick(View view) {
        Toast.makeText(this, "Button Click", Toast.LENGTH_SHORT).show();
    }

    public void gotoTarget(View view) {
        mElink.gotoChild(3);
    }
}
