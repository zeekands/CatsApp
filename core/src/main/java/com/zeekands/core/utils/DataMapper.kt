package com.zeekands.core.utils

import com.zeekands.core.data.source.local.entity.CatEntity
import com.zeekands.core.data.source.remote.response.ListCatsResponse
import com.zeekands.core.domain.model.Cat

object DataMapper {
    fun mapGameResponsesToEntities(input: ListCatsResponse): List<CatEntity> {
        val catsList = ArrayList<CatEntity>()
        input.map {
            val cats = CatEntity(
                null,
                it.adaptability,
                it.affectionLevel,
                it.altNames,
                it.bidability,
                it.catFriendly,
                it.cfaUrl,
                it.childFriendly,
                it.countryCode,
                it.countryCodes,
                it.description,
                it.dogFriendly,
                it.energyLevel,
                it.experimental,
                it.grooming,
                it.hairless,
                it.healthIssues,
                it.hypoallergenic,
                it.id,
                it.image?.url,
                it.indoor,
                it.intelligence,
                it.lap,
                it.lifeSpan,
                it.name,
                it.natural,
                it.origin,
                it.rare,
                it.referenceImageId,
                it.rex,
                it.sheddingLevel,
                it.shortLegs,
                it.socialNeeds,
                it.strangerFriendly,
                it.suppressedTail,
                it.temperament,
                it.vcahospitalsUrl,
                it.vetstreetUrl,
                it.vocalisation,
                it.weight?.imperial,
                it.wikipediaUrl,
                favourite = false
            )
            catsList.add(cats)
        }
        return catsList
    }

    fun mapDomainToEntity(it: Cat): CatEntity {
        return CatEntity(
            it.idDb,
            it.adaptability,
            it.affectionLevel,
            it.altNames,
            it.bidability,
            it.catFriendly,
            it.cfaUrl,
            it.childFriendly,
            it.countryCode,
            it.countryCodes,
            it.description,
            it.dogFriendly,
            it.energyLevel,
            it.experimental,
            it.grooming,
            it.hairless,
            it.healthIssues,
            it.hypoallergenic,
            it.id,
            it.image,
            it.indoor,
            it.intelligence,
            it.lap,
            it.lifeSpan,
            it.name,
            it.natural,
            it.origin,
            it.rare,
            it.referenceImageId,
            it.rex,
            it.sheddingLevel,
            it.shortLegs,
            it.socialNeeds,
            it.strangerFriendly,
            it.suppressedTail,
            it.temperament,
            it.vcahospitalsUrl,
            it.vetstreetUrl,
            it.vocalisation,
            it.weight,
            it.wikipediaUrl,
            favourite = it.favourite
        )
    }

    fun mapEntitiesToDomain(input: List<CatEntity>): List<Cat> {
        return input.map {
            Cat(
                it.idDb,
                it.adaptability,
                it.affectionLevel,
                it.altNames,
                it.bidability,
                it.catFriendly,
                it.cfaUrl,
                it.childFriendly,
                it.countryCode,
                it.countryCodes,
                it.description,
                it.dogFriendly,
                it.energyLevel,
                it.experimental,
                it.grooming,
                it.hairless,
                it.healthIssues,
                it.hypoallergenic,
                it.id,
                it.image,
                it.indoor,
                it.intelligence,
                it.lap,
                it.lifeSpan,
                it.name,
                it.natural,
                it.origin,
                it.rare,
                it.referenceImageId,
                it.rex,
                it.sheddingLevel,
                it.shortLegs,
                it.socialNeeds,
                it.strangerFriendly,
                it.suppressedTail,
                it.temperament,
                it.vcahospitalsUrl,
                it.vetstreetUrl,
                it.vocalisation,
                it.weight,
                it.wikipediaUrl
            )
        }
    }
}