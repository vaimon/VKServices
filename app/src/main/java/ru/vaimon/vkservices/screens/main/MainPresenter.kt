package ru.vaimon.vkservices.screens.main

import retrofit2.HttpException
import ru.vaimon.vkservices.R
import ru.vaimon.vkservices.models.VKService
import ru.vaimon.vkservices.screens.main.adapters.VkServicesRecyclerViewAdapter
import ru.vaimon.vkservices.screens.main.fragments.service_info.InfoFragment
import java.io.IOException
import java.lang.Exception

class MainPresenter : MainContract.Presenter {
    private var mView: MainContract.View? = null
    private val mRepository: MainContract.Repository by lazy { MainRepository(this) }
    override val vkServicesRecyclerViewAdapter by lazy{
        VkServicesRecyclerViewAdapter(callbackSet = object: VkServicesRecyclerViewAdapter.VkServicesCallback{
            override fun onItemInteraction(item: VKService) = onServiceItemClicked(item)
            override fun onError(e: Exception?) {
                mView?.displayError(R.string.message_img_exception)
            }
        })
    }

    override fun attachView(view: MainContract.View) {
        mView = view
    }

    override fun detachView(){
        mView = null
    }

    override fun onServicesRequestError(e: Throwable) {
        mView?.toggleProgressBar(isProcessRunning = false)
        mView?.displayError(when(e){
            is HttpException -> R.string.message_http_exception
            is IOException -> R.string.message_io_exception
            else -> R.string.message_unknown_exception
        })
        mView?.toggleRetryButton(true)
    }

    override fun onVkServicesFetched(services: List<VKService>?){
        mView?.toggleProgressBar(isProcessRunning = false)
        vkServicesRecyclerViewAdapter.replaceData(services ?: listOf())
    }

    override fun fetchVkServices(){
        mView?.toggleProgressBar(isProcessRunning = true)
        mRepository.fetchVkServicesInfo()
    }

    fun onServiceItemClicked(service: VKService){
        mView?.showBottomDialog(InfoFragment(service))
    }

    override fun onRetryButtonClicked(){
        mView?.toggleRetryButton(false)
        fetchVkServices()
    }

}