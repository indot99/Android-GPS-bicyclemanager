package org.richcode.bicyclesuperultra;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FragmentTip extends Fragment {

    public FragmentTip() {
    }

    WebView wb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_tip, container, false);

        wb = (WebView)layout.findViewById(R.id.wb_tip);

        wb.getSettings().setJavaScriptEnabled(true);
        wb.setWebViewClient(new WebViewClient());
        wb.loadUrl("https://woaks143.tistory.com");

        return layout;
    }

    public void refresh(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.detach(this).attach(this).commit();
    }

}
