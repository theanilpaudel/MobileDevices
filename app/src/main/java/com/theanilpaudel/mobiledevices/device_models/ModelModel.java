package com.theanilpaudel.mobiledevices.device_models;

import com.theanilpaudel.mobiledevices.main.MainApiInterface;
import com.theanilpaudel.mobiledevices.utils.ApiManager;
import com.theanilpaudel.mobiledevices.utils.Brand;

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

public class ModelModel implements ModelApiInterface.ModelsInteractor {
    ModelApiInterface.ModelsListener modelsListener;

    public ModelModel(ModelApiInterface.ModelsListener modelsListener) {
        this.modelsListener = modelsListener;
    }

    @Override
    public void getBrands(int id) {
        Retrofit retrofit = ApiManager.getAdapter();
        final ModelApiInterface modelApiInterface = retrofit.create(ModelApiInterface.class);


        Observable<Response<List<Brand>>> observable = modelApiInterface.getBrandsList(id);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<List<Brand>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<List<Brand>> value) {
                        int responseCode = value.code();
                        if(responseCode==200){
                            modelsListener.takeBrandsList(value.body());
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
