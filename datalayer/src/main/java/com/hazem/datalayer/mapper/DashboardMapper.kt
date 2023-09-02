package com.hazem.datalayer.mapper

import com.hazem.corelayer.model.DashboardItem
import com.hazem.datalayer.cache.entity.DashboardItemEntity


fun DashboardItem.toEntity(): DashboardItemEntity {
    return DashboardItemEntity(
        name = name,
        jobTitle = jobTitle,
        nameHighlight = nameHighlight,
        timestamp = timestamp,
        image = image,
    )
}

fun DashboardItemEntity.toDashboardItem(): DashboardItem {
    return DashboardItem(
        name = name,
        jobTitle = jobTitle,
        nameHighlight = nameHighlight,
        timestamp = timestamp,
        image = image,
    )
}