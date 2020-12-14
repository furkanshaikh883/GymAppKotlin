package com.example.gymappkotlin.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.text.TextUtils
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.gymappkotlin.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.*
import java.nio.channels.FileChannel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object CM {
    fun startActivity(activity: Activity, cls: Class<*>) {
        val intent = Intent(activity, cls)

        activity.startActivity(intent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            activity.overridePendingTransition(
                R.anim.push_in_from_left,
                R.anim.push_out_to_right
            )
        }
    }

    fun startActivityWithIntent(activity: Activity, intent: Intent) {

        activity.startActivity(intent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            activity.overridePendingTransition(
                R.anim.push_in_from_left,
                R.anim.push_out_to_right
            )
        }
    }

    fun startActivityWithResult(activity: Activity, intent: Intent, reqCode: Int) {

        activity.startActivityForResult(intent, reqCode)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            activity.overridePendingTransition(
                R.anim.push_in_from_left,
                R.anim.push_out_to_right
            )
        }
    }

    fun finishActivity(activity: Activity) {
        activity.finish()
        activity.overridePendingTransition(0, R.anim.push_out_to_left)
    }

    fun finishActivityWithResultOk(activity: Activity) {
        val data = Intent()
        activity.setResult(Activity.RESULT_OK, data)
        activity.finish()
        activity.overridePendingTransition(0, R.anim.push_out_to_left)
    }

    fun clearSp(activity: Context?) {
        val settings = activity!!.getSharedPreferences(activity.packageName, Context.MODE_PRIVATE)
        settings.edit().clear().apply()

    }

    fun showMessageOK(
        context: Activity?,
        title: String,
        message: String,
        okListener: DialogInterface.OnClickListener?
    ): AlertDialog {
        val builder = AlertDialog.Builder(context!!, R.style.AlertDialogTheme)
            .setMessage(message).setCancelable(false)
            .setPositiveButton(context.resources.getString(R.string.common_ok), okListener)
        if (TextUtils.isEmpty(title)) {
            builder.setTitle(title)
        }
        builder.create()
        return builder.show()
    }

    fun showMessageYesNo(
        context: Activity?,
        title: String,
        message: String,
        yesListener: DialogInterface.OnClickListener?, noListener: DialogInterface.OnClickListener?
    ) {
        val builder = AlertDialog.Builder(context!!, R.style.AlertDialogTheme)
            .setMessage(message)
            .setPositiveButton(context.resources.getString(R.string.common_yes), yesListener)
            .setNegativeButton(context.resources.getString(R.string.common_no), noListener)
        if (TextUtils.isEmpty(title)) {
            builder.setTitle(title)
        }
        builder.create()
        builder.show()
    }

    fun isInternetAvailable(context: Context?): Boolean {
        val cm = context
            ?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    fun setSp(activity: Context?, key: String, value: Any) {
        var prefs: SharedPreferences? = activity!!.getSharedPreferences(
            activity!!.packageName, Activity.MODE_PRIVATE
        )
        var editor: SharedPreferences.Editor? = prefs!!.edit()

        if (value is String) {
            editor!!.putString(key, value)
        } else if (value is Boolean) {
            editor!!.putBoolean(key, value)
        } else if (value is Int) {
            editor!!.putInt(key, value)
        } else if (value is Long) {
            editor!!.putLong(key, value)
        } else if (value is Float) {
            editor!!.putFloat(key, value)
        }
        editor!!.commit()

    }


    fun getSp(activity: Context?, key: String, defaultValue: Any): Any {
        val prefs = activity?.getSharedPreferences(
            activity.packageName, Activity.MODE_PRIVATE
        )
        return (if (defaultValue is String) {
            prefs!!.getString(key, defaultValue)
        } else if (defaultValue is Boolean) {
            prefs!!.getBoolean(key, defaultValue)
        } else if (defaultValue is Int) {
            prefs!!.getInt(key, defaultValue)
        } else if (defaultValue is Long) {
            prefs!!.getLong(key, defaultValue)
        } else {
            prefs!!.getFloat(key, defaultValue as Float)
        })!!

    }

    fun removeStatusBar(activity: AppCompatActivity) {
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    fun getCaptureImagePath(): String {
        //it will return /sdcard/image.tmp
        val path = File(Environment.getExternalStorageDirectory(), CV.APP_FOLDER_NAME)
        if (!path.exists()) {
            path.mkdir()
        }
        return (Environment
            .getExternalStorageDirectory().toString() + File.separator + CV.APP_FOLDER_NAME + File.separator + System.currentTimeMillis() + ".jpg")
    }

    fun getPathOfSelectedImage(activity: Activity, contentUri: Uri): String {
        var picturePath: String? = ""

        val ImagePath: String

        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = activity.contentResolver.query(
            contentUri,
            filePathColumn, null, null, null
        )
        if (cursor != null && cursor.count > 0) {
            cursor.moveToFirst()
            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
            picturePath = cursor.getString(columnIndex)
            cursor.close()
        } else {
            picturePath = contentUri.path
        }
        /////////////////////////////////////////////////
        // picturePath = data.getData().getPath();


        val fileAttached = File(picturePath!!)
        val path = File(Environment.getExternalStorageDirectory(), CV.APP_FOLDER_NAME)
        if (!path.exists()) {
            path.mkdir()
        }
        ImagePath = Environment.getExternalStorageDirectory().toString() + File.separator + CV.APP_FOLDER_NAME + File.separator + System.currentTimeMillis() + fileAttached.name
        val DestFile = File(ImagePath)
        try {
            copyFile(fileAttached, DestFile)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return ImagePath
    }

    @Throws(IOException::class)
    fun copyFile(sourceFile: File, destFile: File) {
        if (!sourceFile.exists()) {
            return
        }
        var source: FileChannel? = null
        var destination: FileChannel? = null
        source = FileInputStream(sourceFile).channel
        destination = FileOutputStream(destFile).channel
        if (destination != null && source != null) {
            destination.transferFrom(source, 0, source.size())
        }
        source?.close()
        destination?.close()
    }

    fun getImageFileName(strFilePrefix: String): String {

        val path = File(Environment.getExternalStorageDirectory(), CV.APP_FOLDER_NAME)
        if (!path.exists()) {
            path.mkdir()
        }
        var fileName = "A_"
        if (!strFilePrefix.isEmpty()) {
            fileName = strFilePrefix + "_"
        }
        fileName += SimpleDateFormat(
            CV.IMAGE_SAVE_FORMAT,
            Locale.getDefault()
        ).format(Date()) + ".jpg"
        return path.absolutePath + File.separator + fileName
    }

    fun dumpImageMetaData(activity: Activity, uri: Uri): String? {
        var size: String? = null
        // The query, since it only applies to a single document, will only return
        // one row. There's no need to filter, sort, or select fields, since we want
        // all fields for one document.
        val cursor = activity.contentResolver
            .query(uri, null, null, null, null, null)

        try {
            // moveToFirst() returns false if the cursor has 0 rows.  Very handy for
            // "if there's anything to look at, look at it" conditionals.
            if (cursor != null && cursor.moveToFirst()) {

                // Note it's called "Display Name".  This is
                // provider-specific, and might not necessarily be the file name.
                val displayName = cursor.getString(
                    cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                )
                //Log.i("TAG", "Display Name: " + displayName);

                val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                // If the size is unknown, the value stored is null.  But since an
                // int can't be null in Java, the behavior is implementation-specific,
                // which is just a fancy term for "unpredictable".  So as
                // a rule, check if it's null before assigning to an int.  This will
                // happen often:  The storage API allows for remote files, whose
                // size might not be locally known.

                if (!cursor.isNull(sizeIndex)) {
                    // Technically the column stores an int, but cursor.getString()
                    // will do the conversion automatically.
                    size = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                } else {
                    size = "Unknown"
                }
                val inputStream = activity.contentResolver.openInputStream(uri)
                val folder = File(CV.DOWNLOADFILEPATH)

                if (!folder.exists()) {
                    folder.mkdirs()
                }

                val ImagePath = Environment
                    .getExternalStorageDirectory().toString() + File.separator + CV.APP_FOLDER_NAME + File.separator + displayName

                val DestFile = File(ImagePath)
                DestFile.parentFile.mkdirs()
                DestFile.createNewFile()

                val outputStream = FileOutputStream(DestFile)

                copyFileUsingStream(inputStream!!, outputStream)
                //Log.i("TAG", "Size: " + size);
                return ImagePath
            }
        } catch (e: IOException) {
            val msg = activity.resources.getString(R.string.msg_error_attachemnt)
            showMessageOK(activity, "", msg, null)//            e.printStackTrace();
        } finally {
            cursor!!.close()
        }
        return null
    }

    @Throws(IOException::class)
    private fun copyFileUsingStream(inputStream: InputStream, os: OutputStream) {
//        try {
//            val buffer = ByteArray(1024)
//            var length: Int
//            while ((length = inputStream.read(buffer)) > 0) {
//                os.write(buffer, 0, length)
//            }
//        } finally {
//            inputStream.close()
//            os.close()
//        }
        inputStream.use { input ->
            os.use { output ->
                input.copyTo(output)
            }
        }
    }

    fun convertDateFormate(
        oldpattern: String,
        newPattern: String, dateString: String
    ): String {
        // SimpleDateFormat sdf = new SimpleDateFormat(oldpattern);
        if (isValidDate(dateString, newPattern)) {
            return dateString
        } else {
            val sdf = SimpleDateFormat(
                oldpattern,
                CV.LOCALE_USE_DATEFORMAT
            )
            var testDate: Date? = null
            try {
                testDate = sdf.parse(dateString)
                val formatter = SimpleDateFormat(
                    newPattern,
                    CV.LOCALE_USE_DATEFORMAT
                )
                val newFormat = formatter.format(testDate)
                println("" + newFormat)
                return newFormat
            } catch (e: Exception) {
                // TODO Auto-generated catch block
                e.printStackTrace()
                return ""
            }

        }
    }


    fun isValidDate(inDate: String, format: String): Boolean {
        val dateFormat = SimpleDateFormat(format)
        dateFormat.isLenient = false
        try {
            dateFormat.parse(inDate.trim { it <= ' ' })
        } catch (pe: ParseException) {
            return false
        }

        return true
    }

    fun updateResources(context: Activity, language: String, langId: String) {
     setSp(context, CV.SP_SELECTED_LANGUAGE, language);
//        CM.setSp(context, CV.SP_SELECTED_LANGUAGE_ID, langId);
        val locale =Locale(language);
        Locale.setDefault(locale);
        val res = context.getResources ()
        val config =  Configuration(res.getConfiguration())
        config.locale = locale;
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLayoutDirection(locale);
        }
        res.updateConfiguration(config, res.getDisplayMetrics())
        context.onConfigurationChanged(config);
    }



    fun supportTypes(
        activity: Context,
        usertypeData: List<String>?) {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {
        }.type
        val strObject = gson.toJson(usertypeData, type)
        var prefs: SharedPreferences? = activity!!.getSharedPreferences(
            activity!!.packageName, Activity.MODE_PRIVATE
        )
        prefs!!.edit().putString(CV.supportTypeId, strObject).apply()

    }
    fun getSupportType(activity: Context): List<String> {
        val gson = Gson()
        var prefs: SharedPreferences? = activity!!.getSharedPreferences(
            activity!!.packageName, Activity.MODE_PRIVATE
        )
        val json = prefs!!.getString(CV.supportTypeId, null)
        val type = object : TypeToken<List<String>>() {
        }.type
        return gson.fromJson(json, type)
    }

    fun supportTypesId(
        activity: Context,
        usertypeData: List<String>?) {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {
        }.type
        val strObject = gson.toJson(usertypeData, type)
        var prefs: SharedPreferences? = activity!!.getSharedPreferences(
            activity!!.packageName, Activity.MODE_PRIVATE
        )
        prefs!!.edit().putString(CV.SupportTypes, strObject).apply()

    }
    fun getSupportTypeId(activity: Context): List<String> {
        val gson = Gson()
        var prefs: SharedPreferences? = activity!!.getSharedPreferences(
            activity!!.packageName, Activity.MODE_PRIVATE
        )
        val json = prefs!!.getString(CV.SupportTypes, null)
        val type = object : TypeToken<List<String>>() {
        }.type
        return gson.fromJson(json, type)
    }
    fun status(
        activity: Context,
        usertypeData: List<String>?) {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {
        }.type
        val strObject = gson.toJson(usertypeData, type)
        var prefs: SharedPreferences? = activity!!.getSharedPreferences(
            activity!!.packageName, Activity.MODE_PRIVATE
        )
        prefs!!.edit().putString(CV.Statustype, strObject).apply()

    }

    fun getStatus(activity: Context): List<String> {
        val gson = Gson()
        var prefs: SharedPreferences? = activity!!.getSharedPreferences(
            activity!!.packageName, Activity.MODE_PRIVATE
        )
        val json = prefs!!.getString(CV.Statustype, null)
        val type = object : TypeToken<List<String>>() {
        }.type
        return gson.fromJson(json, type)
    }


    fun statusId(
        activity: Context,
        usertypeData: List<String>?) {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {
        }.type
        val strObject = gson.toJson(usertypeData, type)
        var prefs: SharedPreferences? = activity!!.getSharedPreferences(
            activity!!.packageName, Activity.MODE_PRIVATE
        )
        prefs!!.edit().putString(CV.statusTypeId, strObject).apply()

    }

    fun getStatusId(activity: Context): List<String> {
        val gson = Gson()
        var prefs: SharedPreferences? = activity!!.getSharedPreferences(
            activity!!.packageName, Activity.MODE_PRIVATE
        )
        val json = prefs!!.getString(CV.statusTypeId, null)
        val type = object : TypeToken<List<String>>() {
        }.type
        return gson.fromJson(json, type)
    }
}
