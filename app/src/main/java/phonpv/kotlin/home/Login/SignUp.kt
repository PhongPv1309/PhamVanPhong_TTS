package phonpv.kotlin.home.Login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import phonpv.kotlin.home.R
import phonpv.kotlin.home.ROUTE_SCREEN_NAME
import phonpv.kotlin.home.dataUser.AppDatabase
import phonpv.kotlin.home.dataUser.User
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Composable
fun SignUp(navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.padding(top = 60.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.login1),
                    contentDescription = null,
                    modifier = Modifier.size(120.dp)
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
                    modifier = Modifier.size(120.dp)
                )
            }

            Text(
                text = "WELCOME",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF303030)
            )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                modifier = Modifier.fillMaxWidth()
            )

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(90.dp))

            Button(
                onClick = {
                    when {
                        name.isEmpty() || email.isEmpty() -> {
                            // Thông báo nếu tên hoặc email rỗng
                            Toast.makeText(context, "Vui lòng nhập tên và email", Toast.LENGTH_SHORT).show()
                        }
                        password != confirmPassword -> {
                            // Thông báo
                            Toast.makeText(context, "Mật khẩu và xác nhận mật khẩu không khớp", Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> {
                            // Nếu mọi thông tin hợp lệ
                            scope.launch {
                                val db = AppDatabase.getDatabase(context)
                                db.userDao().insert(User(username = name, password = password))
                                navController.navigate(ROUTE_SCREEN_NAME.SIGNIN.name) {
                                    popUpTo(ROUTE_SCREEN_NAME.SIGNUP.name) { inclusive = true }
                                }
                            }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier.size(width = 280.dp, height = 60.dp)
            ) {
                Text(
                    text = "Sign Up",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }

            Button(
                onClick = {
                    navController.navigate(ROUTE_SCREEN_NAME.SIGNIN.name) {
                        popUpTo(
                            ROUTE_SCREEN_NAME.SIGNUP.name
                        ) { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Đã có tài khoản? SIGN IN", color = Color.Black)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUp() {
    val signUpNavController = rememberNavController()
    SignUp(navController = signUpNavController)
}