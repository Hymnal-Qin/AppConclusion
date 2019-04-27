package com.qxj.welcome.data

import com.qxj.commonbase.data.Listing
import com.qxj.commonbase.mvvm.Repository
import com.qxj.commonbase.coroutine.Callback
import com.qxj.welcome.api.ApiService
import com.qxj.welcome.proxy.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class BlankRepository : Repository {
    override fun <T> getDataList(pageSize: Int): Listing<T> {
        return Listing(null,
                null,
                null,
                {},
                {})
    }

    suspend fun getUserName() = suspendCoroutine<String> {
        getUser(object : Callback<String> {
            override fun onSuccess(value: String) {
                it.resume(value)
            }

            override fun onError(t: Throwable) {
                it.resumeWithException(t)
            }

        })
    }

    suspend fun getStudents() =
            withContext(Dispatchers.Default) {
                try {
                    val students = ApiService.getStudents<Student>().await().data
                    val studnet1 = ApiService.getStudents<Student>().await().data
                    val result = mutableListOf<Student>()
                            .apply {
                                addAll(students!!)
                                addAll(studnet1!!)
                            }

                    result
                } catch (e: Throwable) {
                    e.printStackTrace()
                    throw e
                }

            }

    var count = 0
    private fun getUser(callback: Callback<String>) {
        count++
        if (count < 4) {
            callback.onSuccess("�ɹ� $count")
        } else {
            callback.onError(Exception("������� $count"))
        }
    }
}