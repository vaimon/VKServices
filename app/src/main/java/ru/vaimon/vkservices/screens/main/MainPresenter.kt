package ru.vaimon.vkservices.screens.main

import ru.vaimon.vkservices.models.VKService
import ru.vaimon.vkservices.screens.main.adapters.VkServicesRecyclerViewAdapter

class MainPresenter : MainContract.Presenter {
    private var mView: MainContract.View? = null
    private val mRepository: MainContract.Repository by lazy { MainRepository(this) }
    override val vkServicesRecyclerViewAdapter by lazy{
        VkServicesRecyclerViewAdapter(listOf(VKService("SkillFactory","Обучение цифровым профессиям: работа с данными, машинное обучение","https://mobile-olympiad-trajectory.hb.bizmrg.com/logo-skillfactory.png", "https://skillfactory.ru/")))
    }

    override fun attachView(view: MainContract.View) {
        mView = view
    }

    override fun detachView(){
        mView = null
    }

}