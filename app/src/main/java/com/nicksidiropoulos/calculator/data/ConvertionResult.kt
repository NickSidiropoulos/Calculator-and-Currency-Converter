package com.nicksidiropoulos.calculator.data

data class ConvertionResult(
    val amount: Double,
    val base: String,
    val date: String,
    val rates: Rates
)