package com.hazem.datalayer.cache.dao

import com.hazem.corelayer.model.DashboardItem
import com.hazem.corelayer.model.ErrorEntity
import com.hazem.corelayer.model.ResultData
import com.hazem.datalayer.cache.entity.DashboardItemEntity
import com.hazem.datalayer.mapper.toDashboardItem
import com.hazem.datalayer.mapper.toEntity
import io.objectbox.Box
import javax.inject.Inject

class DashboardDaoImpl @Inject constructor(
    private val dashboardBox: Box<DashboardItemEntity>?
) : DashboardDao {
    override suspend fun getList(): ResultData<List<DashboardItem>> {
        return try {
            ResultData.Success(dashboardBox?.all?.map {
                it.toDashboardItem()
            } ?: emptyList())
        } catch (e: Exception) {
            ResultData.Error(ErrorEntity.InternalError(e.message ?: "Error in getting Dashboard Items"))
        }
    }

    override suspend fun saveList(items: List<DashboardItem>): ResultData<Unit> {
        return try {
            dashboardBox?.put(items.map {
                it.toEntity()
            })
            ResultData.Success(Unit)

        } catch (e: Exception) {
            ResultData.Error(ErrorEntity.InternalError(e.message ?: "Error in insertion"))
        }
    }
}