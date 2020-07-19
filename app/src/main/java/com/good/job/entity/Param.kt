package com.good.job.entity

import java.io.Serializable

class Param {
    var key : String? = null;
    var intVlaue : Int? = null;
    var strValue : String? = null;
    var doubleValue : Double? = null;
    var serValue : Serializable? = null;
    constructor(key: String?, value: Int?) {
        this.key = key;
        intVlaue = value;
    }
    constructor(key: String?, value: String?) {
        this.key = key;
        this.strValue = value;
    }
    constructor(key: String?, value: Double?) {
        this.key = key;
        this.doubleValue = value
    }
    constructor(key: String?, value: Serializable?) {
        this.key = key;
        this.serValue = value;
    }
}