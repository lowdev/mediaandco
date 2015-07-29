package eu.entropy.mediapedia.utils;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import eu.entropy.mediapedia.R;

public class SigmaFragment extends Fragment {

    public static SigmaFragment newInstance() {
        SigmaFragment fragment = new SigmaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sigma, container, false);
        WebView webView = (WebView) view.findViewById(R.id.sigma_webview);
        webView.loadUrl("file:///android_asset/www/index.html");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        return view;
    }
}
