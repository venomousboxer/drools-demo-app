package com.yadavmo.demo.model

class Instrument {

    public var instrumentType: InstrumentType? = null
        get() = field
        set(value) {
            field = value
        }
    public var foType:String? = null
        get() = field
        set(value) {
            field = value
        }
    public var subType: String? = null
        get() = field
        set(value) {
            field = value
        }
    public var gboType: String? = null
        get() = field
        set(value) {
            field = value
        }
    public var uuFOType: String? = null
        get() = field
        set(value) {
            field = value
        }
    public var uuSubType: String? = null
        get() = field
        set(value) {
            field = value
        }
    public var underlyingGBOType: String? = null
        get() = field
        set(value) {
            field = value
        }
    public var isTRS: String? = null
        get() = field
        set(value) {
            field = value
        }
    public var isListed: String? = null
        get() = field
        set(value) {
            field = value
        }

    public var price: String? = null
        get() = field
        set(value) {
            field = value
        }

    public var pnlSpnQuoteRule: String? = null
        get() = field
        set(value) {
            field = value
        }

    public var priceType: PriceType? = null

    constructor(
        instrumentType: InstrumentType?,
        foType: String?,
        subType: String?,
        gboType: String?,
        uuFOType: String?,
        uuSubType: String?,
        underlyingGBOType: String?,
        isTRS: String?,
        isListed: String?,
        price: String?,
        pnlSpnQuoteRule: String?,
        priceType: PriceType?
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

    constructor(obj: Instrument){
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


    enum class InstrumentType {
        IS_SWAP_TRADE, IS_CDS_TRADE, IS_CFD_TRADE, IS_GOVERNMENT_BOND_SWAP_TRADE, IS_GOVERNMENT_BOND_TRADE,
        ASSET_STRIP_TYPE, NON_CONVERTIBLE_DEBT_TYPE, CONVERT_TYPE
    }

    enum class PriceType {
        PNDG, PCTG, MNTRY_VAL_AMOUNT
    }
}