package com.theanilpaudel.mobiledevices.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.theanilpaudel.mobiledevices.R;
import com.theanilpaudel.mobiledevices.utils.Brand;
import com.theanilpaudel.mobiledevices.utils.BrandsDetail;
import com.theanilpaudel.mobiledevices.utils.FullScreenAd;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nitv on 4/5/17.
 */

public class DeviceDetailActivity extends AppCompatActivity implements DetailApiInterface.DetailView {
    @BindView(R.id.mainLinear)
    LinearLayout linearLayout;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.adView2)
    AdView adView;
    DetailPresImpl detailPres;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_activity);
        ButterKnife.bind(this);
        Brand brand = getIntent().getParcelableExtra("brand");

        detailPres = new DetailPresImpl(this);
        detailPres.getBrands(brand.getName());

        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice(getResources().getString(R.string.test_device))
                .build();
        adView.loadAd(adRequest);

//        mInterstitialAd = new InterstitialAd(this);
//        mInterstitialAd.setAdUnitId(getResources().getString(R.string.admob_full_ad));
        FullScreenAd fullScreenAd = new FullScreenAd(this);
        InterstitialAd mInterstitialAd = fullScreenAd.requestNewInterstitial(getResources().getString(R.string.admob_full_ad));
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void errorMessage(String message) {
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void setBrands(List<BrandsDetail> brandsDetailList) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(brandsDetailList.get(0).toString(),Html.FROM_HTML_MODE_LEGACY));
        } else {
            textView.setText(Html.fromHtml(brandsDetailList.get(0).toString()));
        }


    }
}
