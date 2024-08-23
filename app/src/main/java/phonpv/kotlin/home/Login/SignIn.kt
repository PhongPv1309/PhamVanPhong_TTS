package phonpv.kotlin.home.Login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import phonpv.kotlin.home.R
import phonpv.kotlin.home.ROUTE_SCREEN_NAME
import phonpv.kotlin.home.dataUser.AppDatabase
import phonpv.kotlin.home.dataUser.User

@Composable
fun SignIn(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.login1),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 120.dp, height = 115.dp)

                )
                Image(
                    painter = painterResource(id = R.drawable.login),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .padding(25.dp)

                )
                Image(
                    painter = painterResource(id = R.drawable.login1),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 120.dp, height = 115.dp)

                )

            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .padding(30.dp),
            ){
                Text(
                    text = "Hello !\n" + "WELCOME BACK",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF303030)

                )
            }

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            )

            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
            }
            Spacer(modifier = Modifier.height(90.dp))

            Button(
                onClick = {
                    //Xu LY LOGIN
                    scope.launch {
                        val db = AppDatabase.getDatabase(context)
                        val user: User? =db.userDao().getUserByUserName(username)
                        if (user !=null && user.password == password){
                            navController.navigate(ROUTE_SCREEN_NAME.HOME.name){
                                popUpTo(ROUTE_SCREEN_NAME.SIGNIN.name){inclusive=true}
                            }
                        }else{
                            Toast.makeText(context, "Thông tin hoặc tài khoản không chính xác", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier.size(width = 280.dp, height = 50.dp)

            ) {
                Text(
                    text = "Login",
                    color = Color.White,
                    fontSize = 18.sp
                )

            }

            Button(
                onClick = {
                    navController.navigate(ROUTE_SCREEN_NAME.SIGNUP.name) {
                        popUpTo(ROUTE_SCREEN_NAME.SIGNIN.name) { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Sign Up", color = Color.Black)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLogin() {
    val signInNavController = rememberNavController()
    SignIn(navController = signInNavController)
}