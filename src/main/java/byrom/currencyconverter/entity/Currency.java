package byrom.currencyconverter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor

public enum Currency {
    EUR("EUR"),
    USD("USD"),
    AMD("AMD"),
    AUD("AUD"),
    AZN("AZN"),
    BRL("BRL"),
    BYN("BYN"),
    CAD("CAD"),
    CHF("CHF"),
    CNY("CNY"),
    CZK("CZK"),
    DKK("DKK"),
    GBP("GBP"),
    GEL("GEL"),
    HKD("HKD"),
    HUF("HUF"),
    IDR("IDR"),
    ILS("ILS"),
    JPY("JPY"),
    KRW("KRW"),
    MXN("MXN"),
    MYR("MYR"),
    NOK("NOK"),
    NZD("NZD"),
    PLN("PLN"),
    RUB("RUB"),
    SEK("SEK"),
    SGD("SGD"),
    THB("THB"),
    TRY("TRY"),
    UAH("UAh"),
    ZAR("ZAR");

    public final String name;
}
