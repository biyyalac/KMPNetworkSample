package com.example.a6

import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

class RocketComponent {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {

            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })


        }
    }
    private suspend fun getDateOfLastSuccessfulLaunch(): List<String> {
       // val rockets:String = httpClient.get("https://api.spacexdata.com/v4/launches").body()

        val data=httpClient.get("https://api.spacexdata.com/v4/launches").body<String>()

      val  data1=  Json.parseToJsonElement(data).jsonArray



        //print("rockets  ${ data1.get(0).toString()}" )

       // val rockets: List<RocketLaunch> = httpClient.get("https://api.spacexdata.com/v4/launches").body()

        val datalist=ArrayList<String>()
        data1.forEach {
            val name=it.jsonObject.get("name").toString()
            val status=it.jsonObject.get("success").toString()
            println("valus status $status")
            datalist.add(name +" - "+ status)
            /*if( status!!.equals("true"))
            {
                val date=  Instant.parse(it.jsonObject.get("date_unix") as String)
                    .toLocalDateTime(TimeZone.currentSystemDefault())
                return "${date!!.month} ${date!!.dayOfMonth}, ${date!!.year}"
            }*/
        }

        return datalist
        /*val lastSuccessLaunch = rockets.last { it.success == true }
        val date = lastSuccessLaunch.dateUtc?.let {
            Instant.parse(it)
                .toLocalDateTime(TimeZone.currentSystemDefault())
        }

        println("123456789 ${date!!.month} ${date!!.dayOfMonth}, ${date!!.year}")
        return "${date!!.month} ${date!!.dayOfMonth}, ${date!!.year}"
*/

    }

    suspend fun launchPhrase(): List<String> =
        try {
            getDateOfLastSuccessfulLaunch()
           /* "The last successful launch was on ${getDateOfLastSuccessfulLaunch()} 🚀"*/
        } catch (e: Exception) {
            println("Exception during getting the date of the last successful launch $e")
            ArrayList<String>()
        }
}