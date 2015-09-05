package eu.entropy.mediapedia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.google.gson.GsonBuilder;

import eu.entropy.mediapedia.company.Company;
import eu.entropy.mediapedia.company.CompanyRepository;
import eu.entropy.mediapedia.company.apigee.ApigeeCompanyResult;
import eu.entropy.mediapedia.networkgraph.visjs.VisjsModel;
import eu.entropy.mediapedia.networkgraph.visjs.VisjsModelConverter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class NetworkGraphFragment extends Fragment {
    public static final String ARG_PAGE = "company";

    private Company company;
    private VisjsModelConverter visjsModelConverter;

    public static NetworkGraphFragment newInstance(Company company) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_PAGE, company);
        NetworkGraphFragment fragment = new NetworkGraphFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        visjsModelConverter = new VisjsModelConverter();
        company = getArguments().getParcelable(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.network_graph, container, false);

        final WebView webView = (WebView) view.findViewById(R.id.network_graph_webview);
        webView.setVisibility(View.INVISIBLE);
        webView.loadUrl("file:///android_asset/www/index.html");
        webView.getSettings().setJavaScriptEnabled(true);

        VisjsModel visjsModel = visjsModelConverter.toVisjsModel(company);
        final String nodes = new GsonBuilder().create()
                .toJson(visjsModel.getNodes());
        final String edges = new GsonBuilder().create()
                .toJson(visjsModel.getEdges());

        webView.addJavascriptInterface(new VisjsDto(edges, nodes), "visjsDto");

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (null == getView()) {
                    return;
                }

                final ProgressBar progress = (ProgressBar) getView().findViewById(R.id.progressBar);
                progress.setProgress(newProgress);
                progress.setVisibility(View.VISIBLE);
                if (newProgress == 100) {
                    progress.setVisibility(View.GONE);
                    webView.setVisibility(View.VISIBLE);
                }
            }
        });

        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        return view;
    }

    class VisjsDto {
        private CompanyRepository companyRepository;

        private String edges;
        private String nodes;

        public VisjsDto(String edges, String nodes) {
            companyRepository = new CompanyRepository();
            this.edges = edges;
            this.nodes = nodes;
        }

        @JavascriptInterface
        public void init(String companyId, final String[] nodeIds) {
            Callback<ApigeeCompanyResult> callback = new Callback<ApigeeCompanyResult>() {
                @Override
                public void success(ApigeeCompanyResult apigeeCompanyResult, Response response) {
                    VisjsModel visjsModel = visjsModelConverter.toVisjsModel(company);
                    visjsModel.removeNodes(nodeIds);

                    setNodes(new GsonBuilder().create().toJson(visjsModel.getNodes()));
                    setEdges(new GsonBuilder().create().toJson(visjsModel.getEdges()));
                }

                @Override
                public void failure(RetrofitError error) {

                }
            };
            companyRepository.findById(companyId, callback);
        }

        @JavascriptInterface
        public String getEdges() {
            return edges;
        }

        @JavascriptInterface
        public String getNodes() {
            return nodes;
        }

        private void setEdges(String edges) {
            this.edges = edges;
        }

        private void setNodes(String nodes) {
            this.nodes = nodes;
        }
    }
}
