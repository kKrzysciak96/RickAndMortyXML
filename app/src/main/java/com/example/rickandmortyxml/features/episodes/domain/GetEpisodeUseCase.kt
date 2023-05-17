package com.example.rickandmortyxml.features.episodes.domain

import com.example.rickandmortyxml.core.base.BaseUseCase
import com.example.rickandmortyxml.features.episodes.domain.model.EpisodeDomain

class GetEpisodeUseCase(
    private val episodeRepository: EpisodeRepository
) :
    BaseUseCase<Unit, List<EpisodeDomain>>() {
    override suspend fun action(params: Unit): List<EpisodeDomain> = episodeRepository.getEpisodes()


}