package com.theanilpaudel.mobiledevices.details;

import com.theanilpaudel.mobiledevices.utils.BrandsDetail;

import java.util.List;

/**
 * Created by nitv on 4/5/17.
 */

public class DetailPresImpl implements DetailApiInterface.DetailPresenter,DetailApiInterface.DetailListener {
    DetailApiInterface.DetailView detailView;
    DetailApiInterface.DetailInteractor detailInteractor;

    public DetailPresImpl(DetailApiInterface.DetailView detailView) {
        this.detailView = detailView;
        detailInteractor = new DetailModel(this);
    }

    @Override
    public void getBrands( String name) {
        detailInteractor.getBrands( name);
    }

    @Override
    public void takeBrandsList(List<BrandsDetail> brandsDetailList) {
        detailView.setBrands(brandsDetailList);
    }
}
