package com.sabinetek.vesta

import android.content.Context
import android.net.TrafficStats
import com.tendcloud.tenddata.TalkingDataSDK

import com.tendcloud.tenddata.TalkingDataSDKConfig




object Vesta {

    fun init(context: Context,config: VestaConfig) {
        initTakingData(context, config)
    }
    private fun initTakingData(context: Context,config: VestaConfig) {
        TrafficStats.setThreadStatsTag(Thread.currentThread().id.toInt())
        val talkConfig = TalkingDataSDKConfig()
        talkConfig.setIMEIAndMEIDEnabled(true) //不允许收集IMEI和MEID
            .setMACEnabled(true) //不允许收集MAC
            .setAppListEnabled(false) //不允许收集应用列表
            .setLocationEnabled(true) //不允许收集位置信息

        TalkingDataSDK.setConfig(talkConfig)
        TalkingDataSDK.setVerboseLogDisable()
        TalkingDataSDK.initSDK(context, config.talkingDataId, config.channel, "")
        TalkingDataSDK.startA(context)
    }

}