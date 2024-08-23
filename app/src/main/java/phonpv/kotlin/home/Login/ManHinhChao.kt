package phonpv.kotlin.home.Login

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import phonpv.kotlin.home.R
import phonpv.kotlin.home.ROUTE_SCREEN_NAME

@Composable
fun ManHinhChao(navController: NavHostController) {
    Box (
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.manhinhchao),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(30.dp)
                .padding(top = 211.dp)
        ) {
            Text(
                text = "MAKE YOUR",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF606060),

            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "HOME BEAUTIFUL",
                fontSize = 30.sp,
                color = Color(0xFF303030),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text ="Nơi đơn giản nhất nơi bạn khám phá" +
                        " những đồ nội thất tuyệt vời nhất và hãy làm cho bạn " +
                        "nhà đẹp",
                fontSize = 18.sp,
                modifier = Modifier
                    .width(286.dp)
                    .padding(start = 30.dp),
                color = Color(0xFF808080),
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 500.dp),
            contentAlignment = Alignment.Center
        ){
            Button(
                onClick = {
                    navController.navigate(ROUTE_SCREEN_NAME.SIGNIN.name){
                        popUpTo(ROUTE_SCREEN_NAME.WELCOME.name){inclusive=true}
                    }

                },
                colors = ButtonDefaults.buttonColors(Color.Black),
                shape = RectangleShape
            ) {
                Text(
                    text = "Get Started",
                    color = Color.White

                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun Preview(){
    val Welcome= rememberNavController()
    ManHinhChao(navController = Welcome)

}


