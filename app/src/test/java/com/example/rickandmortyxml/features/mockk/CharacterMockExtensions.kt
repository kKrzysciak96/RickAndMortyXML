package com.example.rickandmortyxml.features.mockk

import com.example.rickandmortyxml.core.api.model.CharacterRemoteInfo
import com.example.rickandmortyxml.core.api.model.CharactersResponse
import com.example.rickandmortyxml.features.characters.data.local.CharacterCached
import com.example.rickandmortyxml.features.characters.data.local.CharacterLocationCached
import com.example.rickandmortyxml.features.characters.data.local.OriginCached
import com.example.rickandmortyxml.features.characters.data.model.CharacterLocationRemote
import com.example.rickandmortyxml.features.characters.data.model.CharacterRemote
import com.example.rickandmortyxml.features.characters.data.model.OriginRemote
import com.example.rickandmortyxml.features.characters.domain.model.CharacterDomain
import com.example.rickandmortyxml.features.characters.domain.model.CharacterLocationDomain
import com.example.rickandmortyxml.features.characters.domain.model.OriginDomain
import com.example.rickandmortyxml.features.characters.presentation.model.CharacterDisplayable
import com.example.rickandmortyxml.features.characters.presentation.model.CharacterLocationDisplayable
import com.example.rickandmortyxml.features.characters.presentation.model.OriginDisplayable
import org.jetbrains.annotations.TestOnly


@TestOnly
fun CharacterRemoteInfo.Companion.mock() =
    CharacterRemoteInfo(count = 10, pages = 3, next = "next page url", prev = "previous page url")

@TestOnly
fun CharacterLocationRemote.Companion.mock() = CharacterLocationRemote(name = "name", url = "url")

@TestOnly
fun OriginRemote.Companion.mock() = OriginRemote(name = "name", url = "url")

@TestOnly
fun CharacterRemote.Companion.mock() = CharacterRemote(
    created = "created",
    episode = emptyList(),
    gender = "gender",
    id = 1,
    image = "image",
    location = CharacterLocationRemote.mock(),
    name = "name",
    origin = OriginRemote.mock(),
    species = "species",
    status = "status",
    type = "type",
    url = "url"
)

@TestOnly
fun CharactersResponse.Companion.mock() = CharactersResponse(
    info = CharacterRemoteInfo.mock(),
    results = listOf(CharacterRemote.mock(), CharacterRemote.mock(), CharacterRemote.mock())
)

@TestOnly
fun CharacterLocationCached.Companion.mock() = CharacterLocationCached(name = "name", url = "url")

@TestOnly
fun OriginCached.Companion.mock() = OriginCached(name = "name", url = "url")

@TestOnly
fun CharacterCached.Companion.mock() = CharacterCached(
    created = "created",
    episode = emptyList(),
    gender = "gender",
    id = 1,
    image = "image",
    location = CharacterLocationCached.mock(),
    name = "name",
    origin = OriginCached.mock(),
    species = "species",
    status = "status",
    type = "type",
    url = "url"
)

@TestOnly
fun CharacterLocationDomain.Companion.mock() = CharacterLocationDomain(name = "name", url = "url")

@TestOnly
fun OriginDomain.Companion.mock() = OriginDomain(name = "name", url = "url")

@TestOnly
fun CharacterDomain.Companion.mock() = CharacterDomain(
    created = "created",
    episode = emptyList(),
    gender = "gender",
    id = 1,
    image = "image",
    location = CharacterLocationDomain.mock(),
    name = "name",
    origin = OriginDomain.mock(),
    species = "species",
    status = "status",
    type = "type",
    url = "url"
)

@TestOnly
fun CharacterLocationDisplayable.Companion.mock() =
    CharacterLocationDisplayable(name = "name", url = "url")

@TestOnly
fun OriginDisplayable.Companion.mock() = OriginDisplayable(name = "name", url = "url")

@TestOnly
fun CharacterDisplayable.Companion.mock() = CharacterDisplayable(
    created = "created",
    episode = emptyList(),
    gender = "gender",
    id = 1,
    image = "image",
    location = CharacterLocationDisplayable.mock(),
    name = "name",
    origin = OriginDisplayable.mock(),
    species = "species",
    status = "status",
    type = "type",
    url = "url"
)


