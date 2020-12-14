package com.example.gymappkotlin

import android.app.Application
import android.content.Context
import com.example.gymappkotlin.network.RestClient
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump

class ApplicatonActivity : Application() {
    private var context: ApplicatonActivity? = null

    override fun onCreate() {
        super.onCreate()
        context = this

        RestClient.init(this)

        ViewPump.init(
                ViewPump.builder()
                        .addInterceptor(
                                CalligraphyInterceptor(
                                        CalligraphyConfig.Builder()
                                                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                                                .setFontAttrId(R.attr.fontPath)
                                                .build()
                                )
                        )
                        .build()
        )


    }
    companion object {
        var mInstance: ApplicatonActivity? = null

        fun getInstance(): ApplicatonActivity? {
            return mInstance
        }
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }


}
