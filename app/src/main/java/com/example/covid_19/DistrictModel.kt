package com.example.covid_19

class DistrictModel {
    var state:String?=null
    var district:String?=null
    var active:String?=null
    var confirmed:String?=null
    var deaths:String?=null
    var recoverd:String?=null


    constructor() {}
    constructor(
        state: String?,
         district: String?,
        active:String?,
        confirmed: String?,
        deaths: String?,
        recoverd: String?

    ) {
        this.state = state
        this.district=district
        this.active=active
        this.confirmed = confirmed
        this.deaths=deaths
        this.recoverd=recoverd

    }

}

