package com.zeekands.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(
    var idDb: Int? = null,
    var adaptability: Int?,
    var affectionLevel: Int?,
    var altNames: String?,
    var bidability: Int?,
    var catFriendly: Int?,
    var cfaUrl: String?,
    var childFriendly: Int?,
    var countryCode: String?,
    var countryCodes: String?,
    var description: String?,
    var dogFriendly: Int?,
    var energyLevel: Int?,
    var experimental: Int?,
    var grooming: Int?,
    var hairless: Int?,
    var healthIssues: Int?,
    var hypoallergenic: Int?,
    var id: String?,
    var image: String?,
    var indoor: Int?,
    var intelligence: Int?,
    var lap: Int?,
    var lifeSpan: String?,
    var name: String?,
    var natural: Int?,
    var origin: String?,
    var rare: Int?,
    var referenceImageId: String?,
    var rex: Int?,
    var sheddingLevel: Int?,
    var shortLegs: Int?,
    var socialNeeds: Int?,
    var strangerFriendly: Int?,
    var suppressedTail: Int?,
    var temperament: String?,
    var vcahospitalsUrl: String?,
    var vetstreetUrl: String?,
    var vocalisation: Int?,
    var weight: String?,
    var wikipediaUrl: String?,
    var favourite: Boolean = false
) : Parcelable