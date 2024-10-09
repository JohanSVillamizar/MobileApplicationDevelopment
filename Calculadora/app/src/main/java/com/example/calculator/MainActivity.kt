package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import net.objecthunter.exp4j.ExpressionBuilder
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.theme.CalculatorTheme
import kotlin.math.exp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                CalculatorScreen()
            }
        }
    }
}

@Composable
fun CalculatorScreen() {
    var expression by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    val buttonSize = dimensionResource(id = R.dimen.button_size)
    val buttonPadding = dimensionResource(id = R.dimen.padding)

    val lightGrayBackground = colorResource(id = R.color.light_gray)
    val operationColor = Color.Black
    val resultColor = Color.Gray.copy(alpha = 0.7f) // Color gris no tan claro

    // Contenedor general
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(buttonPadding)
            .background(lightGrayBackground),
        verticalArrangement = Arrangement.spacedBy(buttonPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Contenedor para la visualización de cálculos y resultados
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f) // Ocupa espacio hasta que el usuario lo llene
                .padding(buttonPadding),
            horizontalAlignment = Alignment.End // Ajusta el texto a la derecha
        ) {
            // Línea para mostrar operaciones
            Text(
                text = expression, // Este valor se debería actualizar dinámicamente
                fontSize = 32.sp,
                color = operationColor,
                lineHeight = 32.sp // Ajusta el espacio entre las líneas
            )
            // Espacio entre las líneas
            Spacer(modifier = Modifier.height(8.dp)) // Añadir espacio entre operaciones y resultado
            // Línea para mostrar el resultado
            Text(
                text = result, // Este valor se debería actualizar dinámicamente
                fontSize = 32.sp,
                color = resultColor
            )
        }

        // Añadir la línea divisoria
        HorizontalDivider(
            modifier = Modifier
                .padding(vertical = 8.dp) // Espacio alrededor de la línea
                .align(Alignment.CenterHorizontally),
            thickness = 2.dp,
            color = colorResource(id = R.color.SkyBlue)
        )

        // Contenedor de botones
        Column(
            verticalArrangement = Arrangement.spacedBy(buttonPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Fila 1
            Row(
                modifier = Modifier.padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(buttonPadding)
            ) {
                CalcButton(text = "C", modifier = Modifier.size(buttonSize), color = colorResource(id = R.color.DeleteAllcolor)) {
                    expression = "" // Borrar expresión
                    result = ""     // Borrar resultado
                }
                CalcButton(text = "(", modifier = Modifier.size(buttonSize), color = colorResource(id = R.color.ParenthesisColor)) {
                    if (!expression.contains("(")) {
                        expression += "("
                        updateResult(expression, { result = it })
                    }
                }
                CalcButton(text = ")", modifier = Modifier.size(buttonSize), color = colorResource(id = R.color.ParenthesisColor)) {
                    if (!expression.contains(")")) {
                        expression += ")"
                        updateResult(expression, { result = it })
                    }
                }
                CalcButton(text = "/", modifier = Modifier.size(buttonSize), color = colorResource(id = R.color.OperatorsColor)) {
                    if (isExpressionValid(expression)) {
                        expression += "/"
                        updateResult(expression, { result = it })
                    }
                }
            }
            // Fila 2
            Row(
                modifier = Modifier.padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(buttonPadding)
            ) {
                CalcButton(text = "7", modifier = Modifier.size(buttonSize)){
                    expression += "7"
                    updateResult(expression, { result = it })
                }
                CalcButton(text = "8", modifier = Modifier.size(buttonSize)){
                    expression += "8"
                    updateResult(expression, { result = it })
                }
                CalcButton(text = "9", modifier = Modifier.size(buttonSize)){
                    expression += "9"
                    updateResult(expression, { result = it })
                }
                CalcButton(text = "*", modifier = Modifier.size(buttonSize), color = colorResource(id = R.color.OperatorsColor)){
                    if (isExpressionValid(expression)) {
                        expression += "*"
                        updateResult(expression, { result = it })
                    }
                }
            }
            // Fila 3
            Row(
                modifier = Modifier.padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(buttonPadding)
            ) {
                CalcButton(text = "4", modifier = Modifier.size(buttonSize)){
                    expression += "4"
                    updateResult(expression, { result = it })
                }
                CalcButton(text = "5", modifier = Modifier.size(buttonSize)){
                    expression += "5"
                    updateResult(expression, { result = it })
                }
                CalcButton(text = "6", modifier = Modifier.size(buttonSize)){
                    expression += "6"
                    updateResult(expression, { result = it })
                }
                CalcButton(text = "+", modifier = Modifier.size(buttonSize), color = colorResource(id = R.color.OperatorsColor)){
                    if (isExpressionValid(expression)) {
                        expression += "+"
                        updateResult(expression, { result = it })
                    }
                }
            }
            // Fila 4
            Row(
                modifier = Modifier.padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(buttonPadding)
            ) {
                CalcButton(text = "1", modifier = Modifier.size(buttonSize)){
                    expression += "1"
                    updateResult(expression, { result = it })
                }
                CalcButton(text = "2", modifier = Modifier.size(buttonSize)){
                    expression += "2"
                    updateResult(expression, { result = it })
                }
                CalcButton(text = "3", modifier = Modifier.size(buttonSize)){
                    expression += "3"
                    updateResult(expression, { result = it })
                }
                CalcButton(text = "-", modifier = Modifier.size(buttonSize), color = colorResource(id = R.color.OperatorsColor)){
                    if (isExpressionValid(expression)) {
                        expression += "-"
                        updateResult(expression, { result = it })
                    }
                }
            }
            // Fila 5
            Row(
                modifier = Modifier.padding(bottom = 25.dp),
                horizontalArrangement = Arrangement.spacedBy(buttonPadding)
            ) {
                CalcButton(text = "AC", modifier = Modifier.size(buttonSize), color = colorResource(id = R.color.BackspaceColor)){
                    if (expression.isNotEmpty()) {
                        expression = expression.dropLast(1) // Elimina el último carácter
                        updateResult(expression, { result = it })
                    }
                }
                CalcButton(text = "0", modifier = Modifier.size(buttonSize)){expression += "0"}
                CalcButton(text = ".", modifier = Modifier.size(buttonSize), color = colorResource(id = R.color.ParenthesisColor)){
                    if (!expression.contains(".")) {
                        expression += "."
                        updateResult(expression, { result = it })
                    }
                }
                CalcButton(text = "=", modifier = Modifier.size(buttonSize), color = colorResource(id = R.color.OperatorsColor)){
                    try {
                        val expr = ExpressionBuilder(expression).build() // Evalúa la expresión
                        result = expr.evaluate().toString()
                    } catch (e: Exception) {
                        result = "Error"
                    }
                }
            }
        }
    }
}

fun updateResult(expression: String, updateResult: (String) -> Unit) {
    // Solo evaluar si hay al menos un número en la expresión
    if (expression.isNotEmpty()) {
        try {
            val expr = ExpressionBuilder(expression).build()
            val evaluationResult = expr.evaluate().toString()
            val formattedResult = formatResult(evaluationResult)
            updateResult(formattedResult)
        } catch (e: Exception) {
            updateResult("") // Resultados vacíos si hay un error
        }
    } else {
        updateResult("") // Resultado vacío si no hay expresión
    }
}

fun isExpressionValid(expression: String): Boolean {
    // Comprueba si la expresión es válida para ser evaluada
    // Verificar que no termine en un operador o que no contenga operadores consecutivos
    if (expression.isEmpty() || expression.last() in setOf('+', '-', '*', '/')) {
        return false
    }

    // Verificar si hay operadores consecutivos
    val operators = setOf('+', '-', '*', '/')
    for (i in 1 until expression.length) {
        if (expression[i] in operators && expression[i - 1] in operators) {
            return false
        }
    }
    return true
}


fun formatResult(result: String): String {
    return try {
        // Formatear el número con comas como separadores de miles
        val number = result.toDouble()
        val formattedResult = String.format("%,d", number.toLong()) // Formato para enteros
        if (number % 1 != 0.0) {
            // Si es un número decimal, agregar el decimal
            val decimalPart = number.toString().substringAfter(".")
            "$formattedResult.$decimalPart" // Combina la parte entera y decimal
        } else {
            formattedResult
        }
    } catch (e: Exception) {
        "Error" // Si no se puede formatear, devuelve "Error"
    }
}

@Composable
fun CalcButton(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Gray,
    onClick: () -> Unit // Asegúrate de que esto esté definido
) {
    Button(
        onClick = { onClick() }, // Llama a la función onClick pasada como parámetro
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        modifier = modifier
            .shadow(elevation = 7.dp, shape = CircleShape)
            .border(width = 2.dp, color = Color.White, shape = CircleShape)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 24.sp
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    CalculatorTheme {
        CalculatorScreen()
    }
}
