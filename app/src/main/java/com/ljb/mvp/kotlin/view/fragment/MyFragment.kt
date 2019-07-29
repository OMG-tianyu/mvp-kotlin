package com.ljb.mvp.kotlin.view.fragment

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.DialogInterface
import android.content.Intent
import com.ljb.mvp.kotlin.R
import com.ljb.mvp.kotlin.adapter.MyTabAdapter
import com.ljb.mvp.kotlin.contract.MyContract
import com.ljb.mvp.kotlin.domain.MyTabFragmentBean
import com.ljb.mvp.kotlin.domain.User
import com.ljb.mvp.kotlin.img.ImageLoader
import com.ljb.mvp.kotlin.presenter.MyPresenter
import com.ljb.mvp.kotlin.view.act.LoginActivity
import com.ljb.mvp.kotlin.widget.dialog.NormalMsgDialog
import kotlinx.android.synthetic.main.fragment_my.*
import mvp.ljb.kt.fragment.BaseMvpFragment


/**
 * Created by L on 2017/7/18.
 */
class MyFragment : BaseMvpFragment<MyContract.IPresenter>(), MyContract.IView {

    private val mTabArr by lazy {
        arrayOf(
                MyTabFragmentBean(getString(R.string.events), EventsFragment()),
                MyTabFragmentBean(getString(R.string.starred), StarredFragment()),
                MyTabFragmentBean(getString(R.string.followers), FollowersFragment())
        )
    }

    private val mLogoutDialog by lazy {
        NormalMsgDialog(activity!!)
                .setMessage(R.string.logout_user)
                .setLeftButtonInfo(R.string.cancel)
                .setRightButtonInfo(R.string.enter, DialogInterface.OnClickListener { _, _ ->
                    getPresenter().logout()
                })
    }

    override fun getLayoutId() = R.layout.fragment_my

    override fun registerPresenter() = MyPresenter::class.java

    override fun initView() {
        viewpager.apply {
            offscreenPageLimit = mTabArr.size
            adapter = MyTabAdapter(childFragmentManager, mTabArr)
        }
        tab_layout.setViewPager(viewpager)
        btn_logout.setOnClickListener { mLogoutDialog.show() }
    }

    override fun initData() {
        getPresenter().getUserInfo()
    }

    override fun logoutSuccess() {
        goLogin()
    }

    private fun goLogin() {
        startActivity(Intent(activity, LoginActivity::class.java))
    }

    override fun showUserInfo(user: User) {
        ImageLoader.load(activity!!, user.avatar_url, iv_header, ImageLoader.getCircleRequest())
        tv_name.text = user.login
        tv_location.text = user.location
        tv_company.text = user.company
    }

}