package ru.vaimon.vkservices

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.junit.Test

import org.junit.Assert.*
import retrofit2.converter.gson.GsonConverterFactory
import ru.vaimon.vkservices.models.VKService

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DeserializerUnitTests {

    @Test
    fun outerObjectIsDroppedWhileParsing() {
        val json = """{
    "items": [
        {
            "name": "ВКонтакте",
            "description": "Самая популярная соцсеть и первое суперприложение в Роcсии",
            "icon_url": "https://mobile-olympiad-trajectory.hb.bizmrg.com/logo-vk.png",
            "service_url": "https://vk.com/"
        }
    ]
}"""
        assertArrayEquals("Simple json",
            arrayOf(VKService("ВКонтакте",
                "Самая популярная соцсеть и первое суперприложение в Роcсии",
                "https://mobile-olympiad-trajectory.hb.bizmrg.com/logo-vk.png",
                "https://vk.com/"
        )), GsonBuilder()
                .registerTypeAdapter(Array<VKService>::class.java, VKService.Companion.Deserializer())
                .create().fromJson(json, Array<VKService>::class.java))
    }

    @Test
    fun outerObjectIsDroppedWithEmptyArray() {
        val json = """{
    "items": [
    ]
}"""
        assertArrayEquals("Empty array",
            arrayOf(),
            GsonBuilder()
                .registerTypeAdapter(Array<VKService>::class.java, VKService.Companion.Deserializer())
                .create().fromJson(json, Array<VKService>::class.java))
    }

    @Test
    fun exceptionIsThrownIfThereIsNoOuterObject() {
        val json = """[
        {
            "name": "ВКонтакте",
            "description": "Самая популярная соцсеть и первое суперприложение в Роcсии",
            "icon_url": "https://mobile-olympiad-trajectory.hb.bizmrg.com/logo-vk.png",
            "service_url": "https://vk.com/"
        }
    ]"""
        assertThrows("No outer object",
            Exception::class.java){
            GsonBuilder()
                .registerTypeAdapter(Array<VKService>::class.java, VKService.Companion.Deserializer())
                .create().fromJson(json, Array<VKService>::class.java)
        }

    }
}