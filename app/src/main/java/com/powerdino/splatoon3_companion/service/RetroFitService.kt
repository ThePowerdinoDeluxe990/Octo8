package com.powerdino.splatoon3_companion.service
import com.powerdino.splatoon3_companion.model.Data
import com.powerdino.splatoon3_companion.model.challenge.EventItem
import com.powerdino.splatoon3_companion.model.resources_versus.ResourcesVersus
import com.powerdino.splatoon3_companion.model.salmon_run.Salmon
import com.powerdino.splatoon3_companion.model.salmon_run.resources.SalmonResources
import retrofit2.http.GET
import retrofit2.http.Query


interface RetroFitService{


        @GET("api/v1/three/versus/phases?count=3")
        //Use this get for testing splatfest screens
        //@GET("api/v1/three/versus/phases?startsAfter=2024-09-13T10%3A29%3A48.000Z&count=3")
        suspend fun getData(): Data

        @GET("api/v1/three/resources/coop")
        suspend fun getSalmonResources(
            @Query("language") language:String
        ): SalmonResources

        @GET("api/v1/three/resources/versus")
        suspend fun getVersusResources(
            @Query("language") language: String,

        ): ResourcesVersus


        //Use this get for testing big run screens
        //@GET("api/v1/three/coop/phases?startsAfter=2022-03-04T10%3A29%3A48.000Z&count=3")
        //Use this get for testing eggstra work
        //@GET("api/v1/three/coop/phases?startsAfter=2026-05-30T16%3A45%3A18.000Z&count=3")
        @GET("api/v1/three/coop/phases?count=3")
        suspend fun getSalmonSchedules(): Salmon

        @GET("api/v1/three/versus/league/events?count=2")
        suspend fun getEvents(): List<EventItem>
}