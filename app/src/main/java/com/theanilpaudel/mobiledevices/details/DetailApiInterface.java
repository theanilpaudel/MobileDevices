package com.theanilpaudel.mobiledevices.details;

import com.theanilpaudel.mobiledevices.utils.Brand;
import com.theanilpaudel.mobiledevices.utils.BrandsDetail;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by nitv on 4/5/17.
 */

public interface DetailApiInterface {
    @GET("/listDeviceDetail/{name}")
    Observable<Response<List<BrandsDetail>>> getBrandsList(@Path("name")String name);

    interface DetailView {
        void showProgress();
        void hideProgress();
        void errorMessage(String message);
        void setBrands(List<BrandsDetail> brandsDetailList);
    }

    interface DetailPresenter {
        void getBrands(String name);

    }

    interface DetailInteractor {
        void getBrands(String name);

    }

    interface DetailListener {
        void takeBrandsList(List<BrandsDetail> brandsDetailList);
    }
}
