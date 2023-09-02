package com.hazem.corelayer.dashboard

import com.hazem.corelayer.model.DashboardItem
import com.hazem.corelayer.model.ResultData

interface DashboardUseCase {
    suspend operator fun invoke(): ResultData<List<DashboardItem>>
}