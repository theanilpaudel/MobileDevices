package com.theanilpaudel.mobiledevices.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.theanilpaudel.mobiledevices.R;
import com.theanilpaudel.mobiledevices.utils.Brand;
import com.theanilpaudel.mobiledevices.utils.BrandsDetail;

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
    DetailPresImpl detailPres;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_activity);
        ButterKnife.bind(this);
        Brand brand = getIntent().getParcelableExtra("brand");

        detailPres = new DetailPresImpl(this);
        detailPres.getBrands(brand.getName());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void errorMessage(String message) {

    }

    @Override
    public void setBrands(List<BrandsDetail> brandsDetailList) {
        textView.setText(brandsDetailList.get(0).toString());

    }
}
