//package com.example.gymappkotlin.fragment
//
//import android.R
//import android.content.Context
//import android.os.Bundle
//import android.view.View
//import android.widget.AdapterView
//import android.widget.ArrayAdapter
//import android.widget.ListView
//import androidx.appcompat.app.AlertDialog
//import com.example.gymappkotlin.utils.CM
//import com.example.gymappkotlin.utils.KcsProgressDialog
//
//
//open abstract class BaseFragment : androidx.fragment.app.Fragment() {
//
//    private var kcsDialog: KcsProgressDialog? = null
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//    }
//    fun showKcsDialog() {
//
//        /*activity!!.runOnUiThread(Runnable {
//            //if dialog not dismiss then first dialog=null.so not to show anytimewhen activtiy finish...
//
//        })*/
//       /* if (kcsDialog != null) {
//            kcsDialog = null
//        }*/
//        if (kcsDialog == null)
//            kcsDialog = KcsProgressDialog(activity!!, false)
//        if (kcsDialog != null && !kcsDialog!!.isShowing)
//            kcsDialog!!.show()
//    }
//    fun dismissKcsDialog() {
//
//        if (kcsDialog != null && kcsDialog!!.isShowing) {
//            kcsDialog!!.dismiss()
//            kcsDialog = null
//        }
//    }
//   open fun openDialog(context: Context, ondialog: onClickSupportType) {
//       val alertDialog =
//           AlertDialog.Builder(context)
//       var dialog :AlertDialog?=null
//       var list = CM.getSupportType(context)
//       if(list!=null) {
//           val dialogSimpleBinding = ActivityFilterDialogBinding.inflate(layoutInflater)
//           var adapter = ArrayAdapter<Any?>(context, R.layout.simple_list_item_1, list)
//           dialogSimpleBinding.list.adapter  = adapter
//           adapter.notifyDataSetChanged()
//
//           alertDialog.setView(dialogSimpleBinding.root)
//           dialogSimpleBinding.list.setOnItemClickListener { adapterView, view, i, l ->
//               ondialog.onClickPosition(list.get(i))
//               dialog!!.dismiss()
//           }
//           dialog = alertDialog.create()
//           dialog.show()
//       }
//   }
//}