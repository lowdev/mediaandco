package eu.entropy.mediapedia;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import eu.entropy.mediapedia.company.StakeholderRepository;
import eu.entropy.mediapedia.networkgraph.visjs.VisjsModelConverter;

public class NetworkGraphFragment extends Fragment {

    private VisjsModelConverter visjsModelConverter;

    public static NetworkGraphFragment newInstance() {
        NetworkGraphFragment fragment = new NetworkGraphFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        visjsModelConverter = new VisjsModelConverter(getActivity().getAssets(),
                getResources(), getActivity().getPackageName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sigma, container, false);
        WebView webView = (WebView) view.findViewById(R.id.sigma_webview);

        webView.loadUrl("file:///android_asset/www/index.html");
        webView.getSettings().setJavaScriptEnabled(true);
        /*webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                webView.loadUrl("javascript:init('" + "" + "')");
            }
        });*/

        return view;
    }
}
