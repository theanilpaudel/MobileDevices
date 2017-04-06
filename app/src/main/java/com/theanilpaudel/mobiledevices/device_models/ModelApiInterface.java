package com.theanilpaudel.mobiledevices.device_models;

import com.theanilpaudel.mobiledevices.utils.Brand;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by nitv on 4/5/17.
 */

public interface ModelApiInterface {
    @GET("/listDeviceInfo/{ID}")
    Observable<Response<List<Brand>>> getBrandsList(@Path("ID")int id);

    interface ModelsView {
        void showProgress();
        void hideProgress();
        void errorMessage(String message);
        void setBrands(List<Brand> modelList);
    }

    interface ModelsPresenter {
        void getBrands(int id);
        void errorMessage(String message);

    }

    interface ModelsInteractor {
        void getBrands(int id);


    }

    interface ModelsListener {
        void takeBrandsList(List<Brand> modelList);
        void errorMessage(String message);
    }
}
