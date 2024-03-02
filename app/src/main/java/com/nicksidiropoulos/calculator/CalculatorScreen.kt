package com.nicksidiropoulos.calculator


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
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.murzagalin.evaluator.Evaluator

val evaluator = Evaluator()

@Composable
fun CalculatorScreen() {
    var expression by rememberSaveable {
        mutableStateOf("")
    }

    val expressionColorNormal = MaterialTheme.colorScheme.onSurface
    val expressionColorError = Color.Red
    var expressionColor by remember { mutableStateOf(expressionColorNormal) }

    Column (
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
                    text = expression,
                    modifier = Modifier
                        .fillMaxWidth(),
                    lineHeight = 90.sp,
                    textAlign = TextAlign.End,
                    fontSize = 80.sp,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    color = expressionColor
                )
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .fillMaxSize()
                .weight(0.5f),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(2.dp),
                    onClick = {
                        expression = ""
                        expressionColor = expressionColorNormal
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = stringResource(R.string.reset)
                    )
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(2.dp),
                    onClick = {
                        expression += "("
                        expressionColor = expressionColorNormal
                    }
                ) {
                    Text(text = "(")
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(2.dp),
                    onClick = {
                        expression += ")"
                        expressionColor = expressionColorNormal
                    }
                ) {
                    Text(text = ")")
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(2.dp),
                    onClick = {
                        expression = addTheOperant(expression,"/")
                        expressionColor = expressionColorNormal
                    }
                ) {
                    Text(text = "/")
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(2.dp),
                    onClick = {
                        expression += "7"
                        expressionColor = expressionColorNormal
                    }
                ) {
                    Text(text = "7")
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(2.dp),
                    onClick = {
                        expression += "8"
                        expressionColor = expressionColorNormal
                    }
                ) {
                    Text(text = "8")
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(2.dp),
                    onClick = {
                        expression += "9"
                        expressionColor = expressionColorNormal
                    }
                ) {
                    Text(text = "9")
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(2.dp),
                    onClick = {
                        expression = addTheOperant(expression,"*")
                        expressionColor = expressionColorNormal
                    }
                ) {
                    Text(text = "*")
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(2.dp),
                    onClick = {
                        expression += "4"
                        expressionColor = expressionColorNormal
                    }
                ) {
                    Text(text = "4")
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(2.dp),
                    onClick = {
                        expression += "5"
                        expressionColor = expressionColorNormal
                    }
                ) {
                    Text(text = "5")
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(2.dp),
                    onClick = {
                        expression += "6"
                        expressionColor = expressionColorNormal
                    }
                ) {
                    Text(text = "6")
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(2.dp),
                    onClick = {
                        expression = addTheOperant(expression,"-")
                        expressionColor = expressionColorNormal
                    }
                ) {
                    Text(text = "-")
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(2.dp),
                    onClick = {
                        expression += "1"
                        expressionColor = expressionColorNormal
                    }
                ) {
                    Text(text = "1")
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(2.dp),
                    onClick = {
                        expression += "2"
                        expressionColor = expressionColorNormal
                    }
                ) {
                    Text(text = "2")
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(2.dp),
                    onClick = {
                        expression += "3"
                        expressionColor = expressionColorNormal
                    }
                ) {
                    Text(text = "3")
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(2.dp),
                    onClick = {
                        expression = addTheOperant(expression,"+")
                        expressionColor = expressionColorNormal
                    }
                ) {
                    Text(text = "+")
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(2.dp),
                    onClick = {
                        expression += "0"
                        expressionColor = expressionColorNormal
                    }
                ) {
                    Text(text = "0")
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(2.dp)
                        ,
                    onClick = {
                        expression = addTheOperant(expression,".")
                        expressionColor = expressionColorNormal
                    }

                ) {
                    Text(text = ".")
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(2.dp),
                    onClick = {
                        expression = expression.dropLast(1)
                        expressionColor = expressionColorNormal
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.Backspace,
                        contentDescription = stringResource(R.string.backspace)
                    )
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(2.dp),
                    onClick = {
                        try {
                            expression = evaluateExpression(expression)
                            expressionColor = expressionColorNormal
                        }catch (ex: Exception){
                            expressionColor = expressionColorError
                        }
                    }
                ) {
                    Text(text = "=")
                }
            }
        }
    }
}

//Checks if expression already ends with an operant before adding a new one
fun addTheOperant(expression: String, operant: String): String{
    return if (expression.endsWith("+") ||
        expression.endsWith("-") ||
        expression.endsWith("*") ||
        expression.endsWith("/") ||
        expression.endsWith(".")){
        expression
    } else{
        expression+operant
    }
}

//Uses the Evaluator library to evaluate the expression into a result
fun evaluateExpression(expression: String): String {
    return try {
        val result = evaluator.evaluateDouble(expression).toString()
        if (result.endsWith(".0")){
            result.dropLast(2)
        } else{
            result
        }
    } catch (ex: Exception){
        throw Exception("Wrong Expression Format")
    }
}


@Preview(showSystemUi = true)
@Composable
fun CalculatorScreenPr(){
    CalculatorScreen()
}