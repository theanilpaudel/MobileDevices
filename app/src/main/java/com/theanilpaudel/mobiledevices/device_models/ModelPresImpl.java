package com.theanilpaudel.mobiledevices.device_models;

import com.theanilpaudel.mobiledevices.utils.Brand;

import java.util.List;

/**
 * Created by nitv on 4/5/17.
 */

public class ModelPresImpl implements ModelApiInterface.ModelsPresenter,ModelApiInterface.ModelsListener {
    ModelApiInterface.ModelsView modelsView;
    ModelApiInterface.ModelsInteractor modelsInteractor;

    public ModelPresImpl(ModelApiInterface.ModelsView modelsView) {
        this.modelsView = modelsView;
        modelsInteractor = new ModelModel(this);
    }

    @Override
    public void getBrands(int id) {
        modelsInteractor.getBrands(id);
    }

    @Override
    public void takeBrandsList(List<Brand> modelList) {
        modelsView.setBrands(modelList);
    }
}
