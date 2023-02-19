package ru.vaimon.vkservices.screens.main

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.vaimon.vkservices.models.VKService
import ru.vaimon.vkservices.services.RetrofitService
import java.lang.Exception

class MainRepository(private val mPresenter: MainPresenter): MainContract.Repository {
    private val retrofitService = RetrofitService.create()

    override fun fetchVkServicesInfo(){
        retrofitService.getVkServiceInfo().enqueue(object: Callback<Array<VKService>> {
            override fun onResponse(
                call: Call<Array<VKService>>,
                response: Response<Array<VKService>>
            ) {
                if (response.isSuccessful) {
                    mPresenter.onVkServicesFetched(response.body()?.toList())
                }
            }

            override fun onFailure(call: Call<Array<VKService>>, t: Throwable) {
                mPresenter.onServicesRequestError(t)
            }

        })
    }
}