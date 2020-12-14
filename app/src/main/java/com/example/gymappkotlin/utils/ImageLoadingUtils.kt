package com.example.gymappkotlin.utils

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.media.ExifInterface
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.TypedValue
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class ImageLoadingUtils(private val context: Context) {

    val filename: File
        get() {
            val APP_FOLDER_NAME = "ImageUploadDemo"
            val DOCUMENT_SAVE_PATH = Environment.getExternalStorageDirectory().toString() + File.separator + APP_FOLDER_NAME
            val file = File(DOCUMENT_SAVE_PATH)
            if (!file.exists()) {
                file.mkdirs()
            }
            val uriSting = (file.absolutePath + "/" + CM.getCaptureImagePath())
            return File(uriSting)

        }

    fun convertDipToPixels(dips: Float): Int {
        val r = context.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dips, r.displayMetrics
        ).toInt()
    }

    fun calculateInSampleSize(
        options: BitmapFactory.Options,
        reqWidth: Int, reqHeight: Int
    ): Int {
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val heightRatio = Math.round(height.toFloat() / reqHeight.toFloat())
            val widthRatio = Math.round(width.toFloat() / reqWidth.toFloat())
            inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
        }
        val totalPixels = (width * height).toFloat()
        val totalReqPixelsCap = (reqWidth * reqHeight * 2).toFloat()

        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++
        }

        return inSampleSize
    }

    fun decodeBitmapFromPath(filePath: String): Bitmap? {
        var scaledBitmap: Bitmap? = null

        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        scaledBitmap = BitmapFactory.decodeFile(filePath, options)

        options.inSampleSize = calculateInSampleSize(
            options,
            convertDipToPixels(150f), convertDipToPixels(200f)
        )
        options.inDither = false
        options.inPurgeable = true
        options.inInputShareable = true
        options.inJustDecodeBounds = false

        scaledBitmap = BitmapFactory.decodeFile(filePath, options)
        return scaledBitmap
    }

    /**
     * Compressing image used encode and decode time retur compress images
     *
     *
     * @param imageUri
     * @param utils
     * @return
     */
    fun compressImage(
        activity: Activity, imageUri: String,
        utils: ImageLoadingUtils
    ): String {
        try {
            val filePath = getRealPathFromURI(activity, imageUri)
            if (!File(filePath!!).exists()) {
                return ""
            }
            var scaledBitmap: Bitmap? = null

            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            var bmp = BitmapFactory.decodeFile(filePath, options)

            var bitampHeight = options.outHeight
            var bitmapWidth = options.outWidth
            val maxBitmapHeight = 816.0f
            val maxBitmapWidth = 612.0f
            var bitmapRatio = (bitmapWidth / bitampHeight).toFloat()
            val maxRatio = maxBitmapWidth / maxBitmapHeight

            if (bitampHeight > maxBitmapHeight || bitmapWidth > maxBitmapWidth) {
                if (bitmapRatio < maxRatio) {
                    bitmapRatio = maxBitmapHeight / bitampHeight
                    bitmapWidth = (bitmapRatio * bitmapWidth).toInt()
                    bitampHeight = maxBitmapHeight.toInt()
                } else if (bitmapRatio > maxRatio) {
                    bitmapRatio = maxBitmapWidth / bitmapWidth
                    bitampHeight = (bitmapRatio * bitampHeight).toInt()
                    bitmapWidth = maxBitmapWidth.toInt()
                } else {
                    bitampHeight = maxBitmapHeight.toInt()
                    bitmapWidth = maxBitmapWidth.toInt()

                }
            }

            options.inSampleSize = utils.calculateInSampleSize(
                options,
                bitmapWidth, bitampHeight
            )
            options.inJustDecodeBounds = false
            options.inDither = false
            options.inPurgeable = true
            options.inInputShareable = true
            options.inTempStorage = ByteArray(16 * 1024)

            try {
                bmp = BitmapFactory.decodeFile(filePath, options)
            } catch (exception: OutOfMemoryError) {
                exception.printStackTrace()

            }

            try {
                scaledBitmap = Bitmap.createBitmap(
                    bitmapWidth, bitampHeight,
                    Bitmap.Config.ARGB_8888
                )
            } catch (exception: OutOfMemoryError) {
                exception.printStackTrace()
            }

            val ratioX = bitmapWidth / options.outWidth.toFloat()
            val ratioY = bitampHeight / options.outHeight.toFloat()
            val middleX = bitmapWidth / 2.0f
            val middleY = bitampHeight / 2.0f

            val scaleMatrix = Matrix()
            scaleMatrix.setScale(ratioX, ratioY, middleX, middleY)

            var canvas = Canvas(scaledBitmap!!)
            canvas.setMatrix(scaleMatrix)
            canvas.drawBitmap(
                bmp, middleX - bmp.width / 2,
                middleY - bmp.height / 2, Paint(
                    Paint.FILTER_BITMAP_FLAG
                )
            )

            val exif: ExifInterface
            try {
                exif = ExifInterface(filePath)

                val orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0
                )

                val matrix = Matrix()
                if (orientation == 6) {
                    matrix.postRotate(90f)
                } else if (orientation == 3) {
                    matrix.postRotate(180f)
                } else if (orientation == 8) {
                    matrix.postRotate(270f)
                }
                scaledBitmap = Bitmap.createBitmap(
                    scaledBitmap, 0, 0,
                    scaledBitmap.width, scaledBitmap.height,
                    matrix, true
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }

            var out: FileOutputStream? = null
            val convertImageFileName = CM.getCaptureImagePath()
            try {
                out = FileOutputStream(convertImageFileName)
                scaledBitmap!!.compress(Bitmap.CompressFormat.JPEG, 80, out)

            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

            return convertImageFileName
        } catch (e: IllegalArgumentException) {
            return ""
        }

    }

    /**
     * Passing uri get SDCARD path
     *
     * @param activity
     * @param contentURI
     * @return
     */
    private fun getRealPathFromURI(
        activity: Activity,
        contentURI: String
    ): String? {
        val contentUri = Uri.parse(contentURI)
        val cursor = activity.contentResolver.query(contentUri, null, null, null, null)
        if (cursor == null) {
            return contentUri.path
        } else {
            cursor.moveToFirst()
            val idx = cursor
                .getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            return cursor.getString(idx)
        }
    }
}
