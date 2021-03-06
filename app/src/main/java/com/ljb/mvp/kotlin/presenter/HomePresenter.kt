package com.ljb.mvp.kotlin.presenter

import com.ljb.mvp.kotlin.contract.HomeContract
import com.ljb.mvp.kotlin.model.HomeModel
import mvp.ljb.kt.presenter.BaseMvpPresenter

/**
 * @Author:Kotlin MVP Plugin
 * @Date:2019/04/20
 * @Description input description
 **/
class HomePresenter : BaseMvpPresenter<HomeContract.IView, HomeContract.IModel>(), HomeContract.IPresenter {

    override fun registerModel() = HomeModel::class.java

}

