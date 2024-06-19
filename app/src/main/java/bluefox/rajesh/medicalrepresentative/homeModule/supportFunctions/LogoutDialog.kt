package bluefox.rajesh.medicalrepresentative.homeModule.supportFunctions

import android.content.Context
import android.view.LayoutInflater


class LogoutDialog(
    layoutInflater: LayoutInflater,
    context: Context,
    private val listener: () -> Unit
) {

    private val mLayoutInflater: LayoutInflater
    private val mContext: Context

    init {
        mLayoutInflater = layoutInflater
        mContext = context
    }



}