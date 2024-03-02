package com.nicksidiropoulos.calculator


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nicksidiropoulos.calculator.data.ApiClient
import com.nicksidiropoulos.calculator.data.ConvertionResult
import com.nicksidiropoulos.calculator.data.DataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConverterScreen(dataSource: DataSource, context: Context) {

    val currencyList = dataSource.currencyList

    var expandedFrom by remember { mutableStateOf(false) }
    var selectedFromOption by rememberSaveable { mutableStateOf(currencyList[0].code) }
    var symbolFrom by rememberSaveable { mutableStateOf(currencyList[0].symbol) }

    var expandedTo by remember { mutableStateOf(false) }
    var selectedToOption by rememberSaveable { mutableStateOf(currencyList[1].code) }
    var symbolTo by rememberSaveable { mutableStateOf(currencyList[1].symbol) }

    var amount by rememberSaveable { mutableStateOf("") }
    var result by rememberSaveable { mutableStateOf("") }

    var loadingIndication by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(8.dp, 8.dp, 8.dp, 85.dp)
    ) {
        Column(
            modifier = Modifier
        ) {
            ElevatedCard {
                Text(
                    text = symbolFrom + amount,
                    modifier = Modifier.fillMaxWidth(),
                    lineHeight = 90.sp,
                    textAlign = TextAlign.End,
                    fontSize = 80.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Column(
            modifier = Modifier.padding(2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            ) {
                Text(
                    text = stringResource(R.string.from),
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.weight(0.2f)
                )
                ExposedDropdownMenuBox(
                    expanded = expandedFrom,
                    onExpandedChange = {
                        expandedFrom = !expandedFrom
                    }) {
                    TextField(
                        value = selectedFromOption,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedFrom) },
                        modifier = Modifier.menuAnchor(),
                        textStyle = TextStyle.Default
                    )

                    ExposedDropdownMenu(
                        expanded = expandedFrom,
                        onDismissRequest = { expandedFrom = false }) {
                        currencyList.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item.code + " : " + item.name) },
                                onClick = {
                                    selectedFromOption = item.code
                                    expandedFrom = false
                                    symbolFrom = item.symbol
                                    result = ""
                                })
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            ) {
                Text(
                    text = stringResource(R.string.to),
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.weight(0.2f)
                )
                ExposedDropdownMenuBox(
                    expanded = expandedTo,
                    onExpandedChange = {
                        expandedTo = !expandedTo
                    }) {
                    TextField(
                        value = selectedToOption,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTo) },
                        modifier = Modifier.menuAnchor(),
                        textStyle = TextStyle.Default
                    )

                    ExposedDropdownMenu(
                        expanded = expandedTo,
                        onDismissRequest = { expandedTo = false }) {
                        currencyList.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item.code + " : " + item.name) },
                                onClick = {
                                    selectedToOption = item.code
                                    expandedTo = false
                                    symbolTo = item.symbol
                                    result = ""
                                })
                        }
                    }
                }
            }
        }
        Column(
            modifier = Modifier
        ) {
            ElevatedCard {
                Text(
                    text = symbolTo + result,
                    modifier = Modifier.fillMaxWidth(),
                    lineHeight = 90.sp,
                    textAlign = TextAlign.End,
                    fontSize = 80.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        if (loadingIndication){
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth().padding(2.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .weight(0.5f),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(2.dp), onClick = {
                    amount += "7"
                }) {
                    Text(text = "7")
                }
                Button(modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(2.dp), onClick = {
                    amount += "8"
                }) {
                    Text(text = "8")
                }
                Button(modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(2.dp), onClick = {
                    amount += "9"
                }) {
                    Text(text = "9")
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(2.dp), onClick = {
                    amount += "4"
                }) {
                    Text(text = "4")
                }
                Button(modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(2.dp), onClick = {
                    amount += "5"
                }) {
                    Text(text = "5")
                }
                Button(modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(2.dp), onClick = {
                    amount += "6"
                }) {
                    Text(text = "6")
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(2.dp), onClick = {
                    amount += "1"
                }) {
                    Text(text = "1")
                }
                Button(modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(2.dp), onClick = {
                    amount += "2"
                }) {
                    Text(text = "2")
                }
                Button(modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(2.dp), onClick = {
                    amount += "3"
                }) {
                    Text(text = "3")
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(2.dp), onClick = {
                    amount += "0"
                }) {
                    Text(text = "0")
                }
                Button(modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(2.dp), onClick = {
                    amount += "."
                }

                ) {
                    Text(text = ".")
                }
                Button(modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(2.dp),
                    onClick = {
                        amount = amount.dropLast(1)
                        result = ""
                    }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.Backspace,
                        contentDescription = stringResource(R.string.backspace)
                    )
                }
            }
            Row {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(2.dp),
                    onClick = {
                        loadingIndication = true
                        if (selectedFromOption == selectedToOption || amount == "" || amount == "0") {
                            result = amount
                        } else {
                            val call = ApiClient.apiService.convertAmount(
                                amount,
                                selectedFromOption,
                                selectedToOption
                            )
                            //Calls the API to get the conversion
                            call.enqueue(object : Callback<ConvertionResult> {
                                override fun onResponse(
                                    call: Call<ConvertionResult>,
                                    response: Response<ConvertionResult>
                                ) {
                                    if (response.isSuccessful) {
                                        val post = response.body()
                                        result =
                                            convertRateToValue(
                                                post?.rates.toString(),
                                                selectedToOption
                                            )
                                    } else {
                                        Toast.makeText(
                                            context,
                                            context.getString(R.string.conversion_failed),
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }

                                override fun onFailure(call: Call<ConvertionResult>, t: Throwable) {
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.connection_error),
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            })
                        }
                        loadingIndication = false
                    }
                ) {
                    Text(text = stringResource(R.string.convert_currency))
                }
            }
        }
    }
}

//Converts the received rate from the api to only the value
private fun convertRateToValue(rate: String, selectedToOption: String): String {
    val pattern = Regex("""$selectedToOption=(.*?)(?=,|$)""")
    val match = pattern.find(rate)
    return match?.groupValues?.get(1) ?: "0.0"
}


@Preview(showSystemUi = true)
@Composable
fun ConverterScreenPr() {
    val dataSource = DataSource()
    val context = LocalContext.current
    ConverterScreen(dataSource, context)
}