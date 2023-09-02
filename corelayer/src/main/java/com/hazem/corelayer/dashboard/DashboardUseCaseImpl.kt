package com.hazem.corelayer.dashboard

import com.hazem.corelayer.model.DashboardItem
import com.hazem.corelayer.model.ResultData
import com.hazem.corelayer.model.doIfFailure
import com.hazem.corelayer.model.doIfSuccess
import com.hazem.corelayer.repo.DashboardRepo
import kotlinx.coroutines.delay
import javax.inject.Inject

class DashboardUseCaseImpl @Inject constructor(val repo: DashboardRepo) : DashboardUseCase {
    override suspend fun invoke(): ResultData<List<DashboardItem>> {
        delay(2000)//simulate api call
        return repo.getRemoteItemsList().doIfSuccess {
            repo.saveListToCache(it)
            var result = repo.getCacheItemsList()
            when (result) {
                is ResultData.Success -> {
                    result.data
                }
                else -> emptyList()
            }
        } //test do if failure
            .doIfFailure {
                var result = repo.getCacheItemsList()
                when (result) {
                    is ResultData.Success -> {
                        result.data
                    }

                    else -> {
                        emptyList()
                    }
                }

            }
    }
}