package com.example.diceroller

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diceroller.ui.theme.DiceRollerTheme
import kotlin.math.min

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                DiceRollerApp()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DiceRollerApp() {
    var result by remember { mutableStateOf(1) }
    var moves by remember { mutableStateOf(0) }
    val maxMoves = 999

    val diceRoll = {
        result = (1..6).random()
        moves = min(++moves, maxMoves)
    }

    DiceRollerWithImage(
        side = result,
        moves = moves,
        modifier = Modifier
            .fillMaxSize()
            .combinedClickable(
                onLongClick = { moves = 0 },
                onClick = { diceRoll() }
            )
            .wrapContentSize(Alignment.Center))
}

@Composable
fun DiceRollerWithImage(side: Int, moves: Int, modifier: Modifier = Modifier) {
    val diceSide = when(side) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }



    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box (modifier = Modifier.wrapContentSize()){
                Text(
                    text = "${moves}ª" ,
                    fontSize = 31.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier
                        .align(Alignment.TopEnd),

                )
                Text(
                    text = "$side",
                    fontSize = 150.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(start = 40.dp, end = 40.dp)
                        .wrapContentSize()
                )
            }
        }

        Image(
            painter = painterResource(id = diceSide),
            contentDescription = null)

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(R.string.touch_me),
            fontSize = 35.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            color = Color.Gray)

        Text(
            text = stringResource(R.string.long_click_message),
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            color = Color.Gray)
    }
}
