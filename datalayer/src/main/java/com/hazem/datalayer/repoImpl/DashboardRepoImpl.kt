package com.hazem.datalayer.repoImpl

import com.hazem.corelayer.model.DashboardItem
import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.repo.DashboardRepo
import com.hazem.datalayer.cache.dao.DashboardDao
import kotlinx.coroutines.delay
import javax.inject.Inject

class DashboardRepoImpl @Inject constructor(private val dao: DashboardDao) : DashboardRepo {
    override suspend fun getRemoteItemsList(): ResultData<List<DashboardItem>> {
        delay(2000)
        return ResultData.Success(
            listOf(
                DashboardItem(
                    name = "Amal Amjad",
                    image = "https://images.unsplash.com/photo-1544005313-94ddf0286df2?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=688&q=80",
                    jobTitle = "Project Manager",
                    nameHighlight = "",
                    timestamp = "1m"
                ),
                DashboardItem(
                    name = "Hazem Afaneh",
                    image = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=774&q=80",
                    jobTitle = "Android Developer",
                    nameHighlight = "",
                    timestamp = "12m"
                ),
                DashboardItem(
                    name = "Mohammad Afaneh",
                    image = "https://images.unsplash.com/photo-1552374196-c4e7ffc6e126?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=774&q=80",
                    jobTitle = "Logistics Consultant",
                    nameHighlight = "",
                    timestamp = "Fri"
                ),
                DashboardItem(
                    name = "Bayan Zaghloul",
                    image = "https://images.unsplash.com/photo-1535468850893-d6e543fbd7f5?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1740&q=80",
                    jobTitle = "Android Developer",
                    nameHighlight = "",
                    timestamp = "Sat"
                ),
            )
        )
    }

    override suspend fun getCacheItemsList(): ResultData<List<DashboardItem>> {
        return dao.getList()
    }

    override suspend fun saveListToCache(list: List<DashboardItem>): ResultData<Unit> {
        return dao.saveList(list)
    }
}