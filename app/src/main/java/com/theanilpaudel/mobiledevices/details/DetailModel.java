package com.theanilpaudel.mobiledevices.details;

import com.theanilpaudel.mobiledevices.device_models.ModelApiInterface;
import com.theanilpaudel.mobiledevices.utils.ApiManager;
import com.theanilpaudel.mobiledevices.utils.Brand;
import com.theanilpaudel.mobiledevices.utils.BrandsDetail;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by nitv on 4/5/17.
 */

public class DetailModel implements DetailApiInterface.DetailInteractor {
    DetailApiInterface.DetailListener detailListener;

    public DetailModel(DetailApiInterface.DetailListener detailListener) {
        this.detailListener = detailListener;
    }

    @Override
    public void getBrands(String name) {
        Retrofit retrofit = ApiManager.getAdapter();
        final DetailApiInterface detailApiInterface = retrofit.create(DetailApiInterface.class);


        Observable<Response<List<BrandsDetail>>> observable = detailApiInterface.getBrandsList(name);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<List<BrandsDetail>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<List<BrandsDetail>> value) {
                        int responseCode = value.code();
                        if(responseCode==200){
                            detailListener.takeBrandsList(value.body());
                        }else{
//                            genreListener.onErrorOccured(value.headers().get("message")); //value.message()
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
//                        genreListener.onErrorOccured(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
