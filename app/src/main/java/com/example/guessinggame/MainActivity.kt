package com.example.guessinggame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random.Default.nextInt
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import com.example.guessinggame.ui.theme.GuessingGameTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var randomNumber = nextInt(1,1000)

        setContent {
            GuessingGameTheme {
                Surface(modifier = Modifier.fillMaxSize(),color = MaterialTheme.colors.background){
                    GuessingGameScreen(randomNumber)
                }
            }
        }
    }
}
@Composable
fun GuessingGameScreen(randomNumber : Int) {
    var randomNumber by remember { mutableStateOf(randomNumber) }
    var inputNumber by remember { mutableStateOf("") }
    val input = inputNumber.toIntOrNull()
    var hint by remember { mutableStateOf("Let's Play") }
    var count by remember { mutableStateOf(0) }
    var guess by remember { mutableStateOf(true) }
    
    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_name),
            color = MaterialTheme.colors.surface,
            fontSize = 25.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(15.dp))
        Text(
            text = stringResource(R.string.text_header),
            color = MaterialTheme.colors.surface,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(150.dp))

        EditNumberField(value = inputNumber,
            onValueChange = { inputNumber = it }
        )
        Spacer(Modifier.height(200.dp))

        if (guess) {
            Text(
                text = stringResource(R.string.hint, hint),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = MaterialTheme.colors.surface,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = {
                    count += 1

                    if (input == null) {
                        hint = "\n Let's Play"
                    } else if (input > randomNumber) {
                        hint = "\nYour answer is Higher ! "
                    } else if (input < randomNumber) {
                        hint = "\nYour answer is Lower !"
                    } else {
                        guess = false
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = MaterialTheme.colors.onPrimary
                )
            ) {
                Text(stringResource(R.string.guess))
            }
        } else {
            Text(
                text = stringResource(R.string.success),
                color = MaterialTheme.colors.surface,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.count, count),
                color = MaterialTheme.colors.surface,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(20.dp))
        }
        Button(onClick = {
            count = 0
            guess = true
            inputNumber = ""
            hint = "Let's Play"
            randomNumber = nextInt(1, 1000) },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.onBackground,
                contentColor = Color.White)
        ){
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(stringResource(R.string.restart))
        }
    }
}

@Composable
fun EditNumberField(
    value: String,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.your_guess)) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() })
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun LightThemePreview() {
    var randomNumber = nextInt(1, 1000)

    GuessingGameTheme(darkTheme = false) {
        GuessingGameScreen(randomNumber)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF673AB7)
@Composable
fun DarkThemePreview() {
    var randomNumber = nextInt(1, 1000)

    GuessingGameTheme(darkTheme = true) {
        GuessingGameScreen(randomNumber)
    }
}


