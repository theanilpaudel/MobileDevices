package com.theanilpaudel.mobiledevices.device_models;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theanilpaudel.mobiledevices.R;
import com.theanilpaudel.mobiledevices.adapters.SimpleListingRecyclerAdapter;
import com.theanilpaudel.mobiledevices.utils.Brand;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nitv on 4/5/17.
 */

public class MainFragment extends Fragment implements ModelApiInterface.ModelsView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    ModelPresImpl modelPres;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, rootView);
        modelPres = new ModelPresImpl(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        Brand brand = getArguments().getParcelable("brand");

        modelPres.getBrands(brand.getID());
        return rootView;
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
    public void setBrands(List<Brand> modelList) {
        recyclerView.setAdapter(new SimpleListingRecyclerAdapter(getActivity(), modelList));
    }
}
