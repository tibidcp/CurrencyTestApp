package com.balash.currencytestapp

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import org.junit.Assert.assertNotNull
import java.io.InputStream
import java.io.InputStreamReader


abstract class BaseParserTest {

    protected open fun <T> parse(actualFileName: String, clazz: Class<T>): T? {
        val inputStream: InputStream = getFile(actualFileName)
        val actualEntity: T?
        actualEntity = Gson().fromJson(JsonReader(InputStreamReader(inputStream)), clazz)
        return actualEntity
    }


    protected open fun getFile(file: String): InputStream {
        val fileStream = javaClass.classLoader!!.getResourceAsStream(file)
        assertNotNull("File not found: $file", fileStream)
        return fileStream
    }
}