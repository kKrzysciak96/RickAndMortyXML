package com.example.rickandmortyxml.features.mockk

import com.example.rickandmortyxml.core.api.model.LocationRemoteInfo
import com.example.rickandmortyxml.core.api.model.LocationsResponse
import com.example.rickandmortyxml.features.locations.data.local.LocationCached
import com.example.rickandmortyxml.features.locations.data.model.LocationRemote
import com.example.rickandmortyxml.features.locations.domain.model.LocationDomain
import com.example.rickandmortyxml.features.locations.presentation.model.LocationDisplayable
import org.jetbrains.annotations.TestOnly

@TestOnly
fun LocationRemote.Companion.mock() = LocationRemote(
    created = "created",
    dimension = "dimension",
    id = 1,
    name = "name",
    residents = emptyList(),
    type = "type",
    url = "url"
)

@TestOnly
fun LocationRemoteInfo.Companion.mock() =
    LocationRemoteInfo(count = 10, pages = 3, next = "next page url", prev = "previous page url")

@TestOnly
fun LocationsResponse.Companion.mock() = LocationsResponse(
    info = LocationRemoteInfo.mock(),
    results = listOf(
        LocationRemote.mock(),
        LocationRemote.mock(),
        LocationRemote.mock()
    )
)

@TestOnly
fun LocationCached.Companion.mock() = LocationCached(
    created = "created",
    dimension = "dimension",
    id = 1,
    name = "name",
    residents = emptyList(),
    type = "type",
    url = "url"
)

@TestOnly
fun LocationDomain.Companion.mock() = LocationDomain(
    created = "created",
    dimension = "dimension",
    id = 1,
    name = "name",
    residents = emptyList(),
    type = "type",
    url = "url"
)

@TestOnly
fun LocationDisplayable.Companion.mock() = LocationDisplayable(
    created = "created",
    dimension = "dimension",
    id = 1,
    name = "name",
    residents = emptyList(),
    type = "type",
    url = "url"
)



