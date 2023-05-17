package com.example.rickandmortyxml.core.exception

interface ErrorMapper {
    fun map(throwable: Throwable): String
}