package com.example.rickandmortyxml.core.exception

import android.content.Context
import androidx.annotation.StringRes
import com.example.rickandmortyxml.R

class ErrorMapperImpl(private val context: Context) : ErrorMapper {
    override fun map(throwable: Throwable): String {
        return when (throwable) {
            is ServerException -> mapServerException(throwable)
            else -> getMessage(R.string.unknown)
        }
    }

    private fun mapServerException(serverException: ServerException): String {
        return when (serverException) {
            is ServerException.Internal -> getMessage(R.string.internal)
            is ServerException.BadRequest -> getMessage(R.string.bad_request)
            else -> getMessage(R.string.unknown)

        }
    }

    private fun getMessage(@StringRes stringRes: Int) = context.getString(stringRes)

}