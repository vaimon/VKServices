package ru.vaimon.vkservices.screens.main

import ru.vaimon.vkservices.screens.main.adapters.VkServicesRecyclerViewAdapter

interface MainContract {
    interface View{

    }

    interface Presenter{
        val vkServicesRecyclerViewAdapter: VkServicesRecyclerViewAdapter
        fun attachView(view: View)
        fun detachView()
    }

    interface Repository{

    }
}