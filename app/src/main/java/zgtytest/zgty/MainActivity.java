package zgtytest.zgty;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView webView;
    private Button btn;
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.e("webview",webView.getUrl());
        initView();
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.webview);
        webView.setWebChromeClient(new WebChromeClient());
//        Log.e("webview",webView.getUrl());
//        webView.loadUrl("http://192.168.18.74:8080/robot/index");
//        webView.loadUrl("file:///android_asset/b.html");
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.set
//        Log.e("webview", webView.getUrl());
//        Log.d()

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        webView.addJavascriptInterface(this, "test");
        webView.loadUrl("file:///android_asset/b.html");

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
//                webView.loadUrl("javascript:showInfo('hello js')");
//                webView.loadUrl("javascript:callJS()");
                //调用此语句，可以调用js里调用Android相关的方法,最终还是回到Android native
//                webView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        webView.loadUrl("javascript:callJS()");
//                    }
//                });
//                click(v);
                webView.evaluateJavascript("callJS()", new ValueCallback() {
                    @Override
                    public void onReceiveValue(Object value) {
                        Log.e("callJS()", "value="+value);
                    }


                });

                break;
        }
    }

    public void click(View view) {
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:callJS()");
            }
        });
    }

//    private class A {
//
//        /**
//         * js 调用该方法发起 rpc 调用，或者返回 rpc 响应
//         */
//        @JavascriptInterface
//        public void callAndroid(final String str) {
//            System.out.print("!!!!" + str);
//            btn.setText(str);
//        }
//    }

    @JavascriptInterface
    public void hello(String msg) {
        Log.e("webview", "hello");
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
