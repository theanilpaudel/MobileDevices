package com.theanilpaudel.mobiledevices.main;


import com.theanilpaudel.mobiledevices.utils.Brand;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by nitv on 07/06/16.
 */
public interface MainApiInterface {

    @GET("/listBrand")
    Observable<Response<List<Brand>>> getBrands();



    interface MainView {
        void showProgress();
        void hideProgress();
        void errorMessage(String message);
        void setBrands(List<Brand> brandList);
    }

    interface MainPresenter {
        void getBrands();

    }

    interface MainInteractor {
        void getBrands();

    }

    interface MainListener {
        void takeBrandsList(List<Brand> brandList);
    }

}
