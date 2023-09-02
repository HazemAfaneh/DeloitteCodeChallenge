package com.hazem.corelayer.repo

import com.hazem.corelayer.model.DashboardItem
import com.hazem.corelayer.model.ResultData

interface DashboardRepo {
    suspend fun getRemoteItemsList():ResultData<List<DashboardItem>>
    suspend fun getCacheItemsList():ResultData<List<DashboardItem>>
    suspend fun saveListToCache(list:List<DashboardItem>):ResultData<Unit>
}