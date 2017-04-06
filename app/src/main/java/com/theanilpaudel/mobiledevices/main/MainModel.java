package com.theanilpaudel.mobiledevices.main;

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

public class MainModel implements MainApiInterface.MainInteractor {
    MainApiInterface.MainListener mainListener;

    public MainModel(MainApiInterface.MainListener mainListener) {
        this.mainListener = mainListener;
    }

    @Override
    public void getBrands() {
        Retrofit retrofit = ApiManager.getAdapter();
        final MainApiInterface mainApiInterface = retrofit.create(MainApiInterface.class);


        Observable<Response<List<Brand>>> observable = mainApiInterface.getBrands();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<List<Brand>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<List<Brand>> value) {
                        int responseCode = value.code();
                        if(responseCode==200){
                            mainListener.takeBrandsList(value.body());
                        }else{
                            mainListener.errorMessage("Error Occured");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mainListener.errorMessage("Error Occured");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
