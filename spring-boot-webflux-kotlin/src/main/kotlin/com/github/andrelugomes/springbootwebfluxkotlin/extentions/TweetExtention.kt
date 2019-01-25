package com.github.andrelugomes.springbootwebfluxkotlin.extentions

import com.github.andrelugomes.springbootwebfluxkotlin.model.kotlin.Tweet
import com.google.gson.Gson
import com.google.gson.JsonObject


inline fun Tweet.selection(fields: Array<String>): Tweet {
    val jsonObject = JsonObject()
    val gson = Gson()

    fields.forEach {

        try {
            val method = this.javaClass.getMethod("get${it.capitalize()}")
            jsonObject.addProperty(it, method.invoke(this).toString())
        }catch (e: NoSuchMethodException){
            //nothing
        }
    }

    return gson.fromJson(jsonObject, Tweet::class.java)
}
