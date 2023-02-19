package ru.vaimon.vkservices.screens.main.fragments.service_info

class InfoPresenter : InfoContract.Presenter {
    private var mView: InfoContract.View? = null
    private val mRepository: InfoContract.Repository by lazy { InfoRepository(this) }

    override fun attachView(view: InfoContract.View) {
        mView = view
    }

    override fun detachView(){
        mView = null
    }

}