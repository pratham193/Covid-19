package com.example.covid_19

class CountryModel {
    var flag: String? = null
    var country: String? = null


    constructor() {}
    constructor(
        flag: String?,
        country: String?

    ) {
        this.flag = flag
        this.country = country

    }

}