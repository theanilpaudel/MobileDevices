package com.theanilpaudel.mobiledevices.main;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.theanilpaudel.mobiledevices.R;
import com.theanilpaudel.mobiledevices.device_models.MainFragment;
import com.theanilpaudel.mobiledevices.utils.Brand;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainApiInterface.MainView{
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.tabanim_viewpager)
    ViewPager viewPager;
    @BindView(R.id.adView2)
    AdView adView;
    @BindView(R.id.progree)
    ProgressBar progressBar;
    MainPresImpl mainPres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPres = new MainPresImpl(this);
        mainPres.getBrands();

        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice(getResources().getString(R.string.test_device))
                .build();
        adView.loadAd(adRequest);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void errorMessage(String message) {
        hideProgress();
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void setBrands(List<Brand> brandList) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), brandList);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {
        List<Brand> brandList;

        public ViewPagerAdapter(FragmentManager fm, List<Brand> brandList) {
            super(fm);
            ViewPagerAdapter.this.brandList = brandList;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment mainFragment = new MainFragment();
            Bundle basket = new Bundle();
            basket.putParcelable("brand",brandList.get(position));
            mainFragment.setArguments(basket);
            return mainFragment;

        }

        @Override
        public int getCount() {
            return brandList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return brandList.get(position).getName();

        }
    }
}
