package com.example.rickandmortyxml.core.exception

interface ErrorWrapper {
    fun wrap(throwable: Throwable): Throwable
}