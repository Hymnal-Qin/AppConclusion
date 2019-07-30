package com.qxj.socket

import org.json.JSONException

interface Received  {

    @Throws(JSONException::class)
    fun parseData(result: Result<String>)
}
