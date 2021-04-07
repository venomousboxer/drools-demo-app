package com.yadavmo.demo.model

class Dummy {

    public var instrumentType: Instrument.InstrumentType? = null
        get() = field
        set(value) {
            field = value
        }
    public var foType:Int? = null
        get() = field
        set(value) {
            field = value
        }
    public var subType: List<Int>? = null
        get() = field
        set(value) {
            field = value
        }
    public var gboType: List<Int>? = null
        get() = field
        set(value) {
            field = value
        }
    public var uuFOType: Int? = null
        get() = field
        set(value) {
            field = value
        }
    public var uuSubType: Int? = null
        get() = field
        set(value) {
            field = value
        }
    public var underlyingGBOType: Int? = null
        get() = field
        set(value) {
            field = value
        }
    public var isTRS: Boolean? = null
        get() = field
        set(value) {
            field = value
        }
    public var isListed: Boolean? = null
        get() = field
        set(value) {
            field = value
        }

    public var price: Int? = null
        get() = field
        set(value) {
            field = value
        }

    public var pnlSpnQuoteRule: List<Int>? = null
        get() = field
        set(value) {
            field = value
        }

    public var priceType: Instrument.PriceType? = null

    constructor(obj: Dummy){
        this.instrumentType = obj.instrumentType
        this.foType = obj.foType
        this.subType = obj.subType
        this.gboType = obj.gboType
        this.uuFOType = obj.uuFOType
        this.uuSubType = obj.uuSubType
        this.underlyingGBOType = obj.underlyingGBOType
        this.isTRS = obj.isTRS
        this.isListed = obj.isListed
        this.price = obj.price
        this.pnlSpnQuoteRule = obj.pnlSpnQuoteRule
        this.priceType = obj.priceType
    }

    constructor(
        instrumentType: Instrument.InstrumentType?,
        foType: Int?,
        subType: List<Int>?,
        gboType: List<Int>?,
        uuFOType: Int?,
        uuSubType: Int?,
        underlyingGBOType: Int?,
        isTRS: Boolean?,
        isListed: Boolean?,
        price: Int?,
        pnlSpnQuoteRule: List<Int>?,
        priceType: Instrument.PriceType?
    ) {
        this.instrumentType = instrumentType
        this.foType = foType
        this.subType = subType
        this.gboType = gboType
        this.uuFOType = uuFOType
        this.uuSubType = uuSubType
        this.underlyingGBOType = underlyingGBOType
        this.isTRS = isTRS
        this.isListed = isListed
        this.price = price
        this.pnlSpnQuoteRule = pnlSpnQuoteRule
        this.priceType = priceType
    }
}