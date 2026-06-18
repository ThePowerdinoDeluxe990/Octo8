package com.powerdino.splatoon3_companion.data

import java.util.Locale

fun getLocale(): String {
    val language = Locale.getDefault().language
    val country = Locale.getDefault().country
    var currentLang = "USen"

    val listOfLangs = listOf(
        "JPja",
        "USen",
        "USpt",
        "EUfr",
        "EUde",
        "EUit",
        "EUnl",
        "EUru",
        "KRko",
        "CNzh",
        "TWzh"
    )

    if( language == "es"){
        currentLang = if(country == "ES"){
            "EUes"
        }else{
            "USes"
        }
    }else{
        listOfLangs.forEach { item ->
            if(item.takeLast(2) == language){
                currentLang = item
            }
        }
    }
    return currentLang
}