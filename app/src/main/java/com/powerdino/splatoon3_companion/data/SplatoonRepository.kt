package com.powerdino.splatoon3_companion.data

import com.powerdino.splatoon3_companion.model.Data
import com.powerdino.splatoon3_companion.model.challenge.EventItem
import com.powerdino.splatoon3_companion.model.resources_versus.ResourcesVersus
import com.powerdino.splatoon3_companion.model.salmon_run.Salmon
import com.powerdino.splatoon3_companion.model.salmon_run.resources.SalmonResources
import com.powerdino.splatoon3_companion.service.RetroFitService


interface SplatoonRepository {
    suspend fun getSplatoonData(): Data
    suspend fun getSalmonResources(): SalmonResources
    suspend fun getSalmonSchedule(): Salmon
    suspend fun getVersusResources(): ResourcesVersus
    suspend fun getEvents(): List<EventItem>
}

class NetworkSplatoonRepository(
    private val splatoonApiService:RetroFitService
): SplatoonRepository{
    override suspend fun getSplatoonData(): Data = splatoonApiService.getData()
    override suspend fun getSalmonResources(): SalmonResources = splatoonApiService.getSalmonResources(getLocale())
    override suspend fun getSalmonSchedule(): Salmon = splatoonApiService.getSalmonSchedules()
    override suspend fun getVersusResources(): ResourcesVersus = splatoonApiService.getVersusResources(getLocale())
    override suspend fun getEvents(): List<EventItem> = splatoonApiService.getEvents()
}