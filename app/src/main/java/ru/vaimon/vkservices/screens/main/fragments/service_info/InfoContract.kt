package ru.vaimon.vkservices.screens.main.fragments.service_info

interface InfoContract {
    interface View{

    }

    interface Presenter{
        fun attachView(view: View)
        fun detachView()
    }

    interface Repository{

    }
}