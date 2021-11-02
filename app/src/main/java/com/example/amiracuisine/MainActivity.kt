package com.example.amiracuisine

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.amiracuisine.ui.theme.AmiraCuisineTheme
import com.google.android.material.shape.TriangleEdgeTreatment
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           /** AmiraCuisineTheme {
                // A surface container using the 'background' color from the theme
                //Surface(color = MaterialTheme.colors.background) {
                 //   MessageCard(Message("Android","Jetpack Compose"))
                //}
            }**/
            Surface(color = MaterialTheme.colors.background) {
                Navigation()
              

            }
        }
    }
}
@Preview
@Composable
fun Navigation(){

    val navController = rememberNavController()
    NavHost(navController = navController,startDestination = "splash_screen" ){
        composable("splash_screen"){
            SplashScreen(navController = navController)
        }
        composable("main_screen"){
            Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
                Text(text = "MAIN SCREEN", color = Color.Black)
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController){
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)

    }
    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(2000L)
        navController.navigate("main_screen")
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.ac_splashscreen),
            contentDescription = "logo"
        )
    }
}
data class Message(val author:String,val body:String)

@Composable
fun MessageCard(msg: Message) {

    Row(modifier=Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)

        )
        Spacer(modifier=Modifier.width(8.dp))

        Column {

            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {

                Text(
                    text = msg.body,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Preview
@Composable
fun PrevivewMessageCard() {
    AmiraCuisineTheme {
        MessageCard(
            msg = Message("Colleague", "Hey, take a look")
        )
    }
}