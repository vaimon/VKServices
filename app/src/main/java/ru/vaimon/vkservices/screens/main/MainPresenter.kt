package ru.vaimon.vkservices.screens.main

import ru.vaimon.vkservices.models.VKService
import ru.vaimon.vkservices.screens.main.adapters.VkServicesRecyclerViewAdapter

class MainPresenter : MainContract.Presenter {
    private var mView: MainContract.View? = null
    private val mRepository: MainContract.Repository by lazy { MainRepository(this) }
    override val vkServicesRecyclerViewAdapter by lazy{
        VkServicesRecyclerViewAdapter()
    }

    override fun attachView(view: MainContract.View) {
        mView = view
    }

    override fun detachView(){
        mView = null
    }

    override fun onServicesRequestError(message: String) {
        mView?.toggleProgressBar(isProcessRunning = false)
        mView?.displayError(message)
    }

    override fun onVkServicesFetched(services: List<VKService>?){
        mView?.toggleProgressBar(isProcessRunning = false)
        vkServicesRecyclerViewAdapter.replaceData(services ?: listOf())
    }

    override fun fetchVkServices(){
        mView?.toggleProgressBar(isProcessRunning = true)
        mRepository.fetchVkServicesInfo()
    }

}