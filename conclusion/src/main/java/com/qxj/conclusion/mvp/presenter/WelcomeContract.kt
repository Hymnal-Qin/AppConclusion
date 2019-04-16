package com.qxj.conclusion.mvp.presenter

import android.app.Activity
import android.content.Context
import androidx.viewpager.widget.PagerAdapter
import com.qxj.commonbase.mvpbase.IView

interface WelcomeContract {

    interface IWelcomeView : IView {
        fun <T: PagerAdapter>showWelcomePage(adapter: T)
        fun showEndWelcomeBtn()
        fun showStartPage(images: IntArray)
        fun showPermissionMsgDialog(msg: String)
        fun showCheckPermissionResult(boolean: Boolean)
        fun <T: Activity>toTargetActivity(cls:Class<T> )
    }

    interface IWelcomePresenter{
        fun checkWelcomeStatus()
        fun checkWelcomeStatus(position: Int)
        fun changeWelcomeStatus(boolean: Boolean)

        fun toCheckPermission(context: Context)
        fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)

        fun checkTargetActivity()
    }
}