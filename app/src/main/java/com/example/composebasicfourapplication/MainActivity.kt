package com.example.composebasicfourapplication

import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.widget.Button
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasicfourapplication.ui.MyComposeAppTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeAppTheme() {
                MyApp()
            }
        }
    }
}
@Composable
fun MyApp(){
    var shouldShowObBoardingScreen by rememberSaveable { mutableStateOf(true)}
    if(shouldShowObBoardingScreen){
        onBoardingScreen(onContinueClicked = {shouldShowObBoardingScreen = false})
    }else{
        Greetings()
    }
}
@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(10){"$it"}
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(names) { name ->
            Greeting(name)
        }
    }
}

@Composable
fun Greeting(name: String) {
    //this value need to recompose its dependency when it's change now it
    // it return a state instead of boolean
    var expanded by  remember { mutableStateOf(false)}
    val extraPadding by animateDpAsState(targetValue =
    if(expanded) 48.dp else 0.dp,
    animationSpec = tween(2000)
        )
    Surface(color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)) {
                Text(text = "Hello, ")
                Text(text = name)
            }
            OutlinedButton(
                onClick = { expanded = !expanded }
            ) {
                //text changes when the value of variable changes.
                //text composable will be recomposable and then show different text
                Text(if (expanded)"Show less" else "Show more")
            }
            }
    }

}



@Composable
fun onBoardingScreen(onContinueClicked:() ->Unit){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

        ){
        Text(text = "Welcome to the Basic Colab!")
      Button(modifier = Modifier.padding(vertical = 24.dp), onClick = onContinueClicked ) {
          Text(text = "Continue")
      }

    }
}
@Preview(showBackground = true, widthDp = 320, heightDp = 320, uiMode = UI_MODE_NIGHT_YES)

//@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun PreviewOnBordingScreen(){
    MyComposeAppTheme {
        onBoardingScreen(onContinueClicked = {})
    }
}
@Preview(showBackground = true, widthDp = 320)
@Composable
fun PreviewGreeting() {
    MyApp()
}