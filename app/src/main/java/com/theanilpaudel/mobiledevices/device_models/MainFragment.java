package com.theanilpaudel.mobiledevices.device_models;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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
    @BindView(R.id.progress)
    ProgressBar progressBar;
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
                .make(getActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void setBrands(List<Brand> modelList) {
        recyclerView.setAdapter(new SimpleListingRecyclerAdapter(getActivity(), modelList));
    }
}
