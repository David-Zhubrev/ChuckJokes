package com.appdav.chuckjokes.ui.web;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.appdav.chuckjokes.OnBackPressedHandler;
import com.appdav.chuckjokes.R;
import com.appdav.chuckjokes.ui.activities.MainActivity;

public class WebFragment extends Fragment implements OnBackPressedHandler.OnBackPressedListener {

    private WebView webView;
    private ProgressBar progressBar;
    private static final String API_MAIN_PAGE_URL = "http://www.icndb.com/api/";
    private OnBackPressedHandler backPressedHandler;

    @Override
    public void onPause() {
        super.onPause();
        backPressedHandler.detachBackPressedListener(this);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_web, container, false);

        webView = v.findViewById(R.id.webView);
        progressBar = v.findViewById(R.id.progressBarWeb);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState);
        } else {
            webView.loadUrl(API_MAIN_PAGE_URL);
        }
        WebSettings settings = webView.getSettings();
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        backPressedHandler = (MainActivity) getActivity();
        assert backPressedHandler != null;
        backPressedHandler.attachBackPressedListener(this);
    }

    @Override
    public boolean onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            return true;
        } else return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        backPressedHandler = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }
}