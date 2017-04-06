package com.theanilpaudel.mobiledevices.utils;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.theanilpaudel.mobiledevices.R;

/**
 * Created by nitv on 23/06/15.
 */
public class FullScreenAd {
    private Context context;
    private InterstitialAd mInterstitialAd;

    public FullScreenAd(Context context) {
        this.context = context;
        mInterstitialAd = new InterstitialAd(context);
    }

    public InterstitialAd requestNewInterstitial(String interestitialAdKey) {

        mInterstitialAd.setAdUnitId(interestitialAdKey);
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice("7B3B13D2DF7DF371CDBA50C7003D9851").build();
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(context.getResources().getString(R.string.test_device)).build();

        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();

                if (mInterstitialAd != null) {
                    mInterstitialAd.show();
                }
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });

        return mInterstitialAd;

    }

    public void LoadAd(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    public void CloseAd(){
        if(mInterstitialAd.isLoaded()){
            mInterstitialAd.loadAd(null);
        }
    }
}
