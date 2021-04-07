package com.yadavmo.demo.model

class Customer {
    public var type: CustomerType? = null
        get() = field
        set(value) {
            field = value
        }

    public var years = 0
        get() = field
        set(value) {
            field = value
        }

    public var discount = 0
        get() = field
        set(value) {
            field = value
        }

    constructor(type: CustomerType?, years: Int) {
        this.type = type
        this.years = years
    }

    enum class CustomerType {
        INDIVIDUAL, BUSINESS
    }
}