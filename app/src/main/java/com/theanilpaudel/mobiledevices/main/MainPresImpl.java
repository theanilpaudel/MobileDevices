package com.theanilpaudel.mobiledevices.main;

import com.theanilpaudel.mobiledevices.utils.Brand;

import java.util.List;

/**
 * Created by nitv on 4/5/17.
 */

public class MainPresImpl implements MainApiInterface.MainPresenter,MainApiInterface.MainListener {
    MainApiInterface.MainView mainView;
    MainApiInterface.MainInteractor mainInteractor;

    public MainPresImpl(MainApiInterface.MainView mainView) {
        this.mainView = mainView;
        mainInteractor = new MainModel(this);
    }

    @Override
    public void getBrands() {
        mainView.showProgress();
        mainInteractor.getBrands();
    }

    @Override
    public void errorMessage(String message) {
        mainView.errorMessage(message);
    }

    @Override
    public void takeBrandsList(List<Brand> brandList) {
        mainView.setBrands(brandList);
        mainView.hideProgress();
    }
}
