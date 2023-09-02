package com.hazem.datalayer.cache.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class DashboardItemEntity(
    @Id
    var id: Long = 0,
    val name: String,
    val jobTitle: String,
    val nameHighlight: String,
    val timestamp: String,
    val image: String,
)