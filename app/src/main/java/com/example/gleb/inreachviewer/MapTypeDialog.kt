package com.example.gleb.inreachviewer

import android.app.DialogFragment
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * Created by Gleb on 17.11.2017.
 */

class MapTypeDialog: DialogFragment(),View.OnClickListener {

    val TAG = "myLogs"
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.setTitle("Title")
        val view = inflater?.inflate(R.layout.map_type_dialog,null)
        //btnNormal.setOnClickListener {view -> Snackbar.make(view,"Hello", Snackbar.LENGTH_LONG).show() }
        return view
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onClick(p0: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}