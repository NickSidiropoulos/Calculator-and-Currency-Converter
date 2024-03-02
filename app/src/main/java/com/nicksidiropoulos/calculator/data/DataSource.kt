package com.nicksidiropoulos.calculator.data


class DataSource {
    val currencyList = listOf(
        Currency(code = "EUR", name = "Euro", symbol = "€"),
        Currency(code = "USD", name = "United States Dollar", symbol = "$"),
        Currency(code = "JPY", name = "Japanese Yen", symbol = "¥"),
        Currency(code = "GBP", name = "British Pound", symbol = "£"),
        Currency(code = "AUD", name = "Australian Dollar", symbol = "A$"),
        Currency(code = "BGN", name = "Bulgarian Lev", symbol = "лв"),
        Currency(code = "BRL", name = "Brazilian Real", symbol = "R$"),
        Currency(code = "CAD", name = "Canadian Dollar", symbol = "C$"),
        Currency(code = "CHF", name = "Swiss Franc", symbol = "₣"),
        Currency(code = "CNY", name = "Chinese Renminbi Yuan", symbol = "¥"),
        Currency(code = "CZK", name = "Czech Koruna", symbol = "Kč"),
        Currency(code = "DKK", name = "Danish Krone", symbol = "kr."),
        Currency(code = "HKD", name = "Hong Kong Dollar", symbol = "HK$"),
        Currency(code = "HUF", name = "Hungarian Forint", symbol = "Ft"),
        Currency(code = "IDR", name = "Indonesian Rupiah", symbol = "Rp"),
        Currency(code = "ILS", name = "Israeli New Sheqel", symbol = "₪"),
        Currency(code = "INR", name = "Indian Rupee", symbol = "₹"),
        Currency(code = "ISK", name = "Icelandic Króna", symbol = "kr"),
        Currency(code = "KRW", name = "South Korean Won", symbol = "₩"),
        Currency(code = "MXN", name = "Mexican Peso", symbol = "₱"),
        Currency(code = "MYR", name = "Malaysian Ringgit", symbol = "RM"),
        Currency(code = "NOK", name = "Norwegian Krone", symbol = "kr."),
        Currency(code = "NZD", name = "New Zealand Dollar", symbol = "NZ$"),
        Currency(code = "PHP", name = "Philippine Peso", symbol = "₱"),
        Currency(code = "PLN", name = "Polish Złoty", symbol = "zł"),
        Currency(code = "RON", name = "Romanian Leu", symbol = "L"),
        Currency(code = "SEK", name = "Swedish Krona", symbol = "kr."),
        Currency(code = "SGD", name = "Singapore Dollar", symbol = "S$"),
        Currency(code = "THB", name = "Thai Baht", symbol = "฿"),
        Currency(code = "TRY", name = "Turkish Lira", symbol = "₺"),
        Currency(code = "ZAR", name = "South African Rand", symbol = "R")
    )
}

data class Currency(
    val code: String,
    val name: String,
    val symbol: String
)