package com.hazem.datalayer.cache.dao

import com.hazem.corelayer.model.DashboardItem
import com.hazem.corelayer.model.ResultData

interface DashboardDao {
    suspend fun getList(): ResultData<List<DashboardItem>>
    suspend fun saveList(items: List<DashboardItem>): ResultData<Unit>
}