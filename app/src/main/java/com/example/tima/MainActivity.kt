package com.example.tima

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tima.ui.theme.TimaTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			makeBirdFly(Bird())

			makeBirdFly(Pigeon())
			TimaTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					Greeting(
						name = "Android"
						,
						modifier = Modifier.padding(innerPadding)
					)
				}
			}
		}
	}
}
//SOLID
//S принцип единственной ответственности
class UserSearch{
	fun search(){}
}
class UserSaving{
	fun saving(){}
}

//O принцип открытости,закрытости
interface PaymentMethod{
	fun pay()
}

class CashPayment : PaymentMethod{
	override fun pay() {println("оплата наличными")
	}
}
class CardPayment : PaymentMethod{
	override fun pay() {println("оплата картой")
	}
}

//L принцип подстановки Барбары Лисков
open class Bird{
	open fun fly(){
		println("летит")
	}
}
class Pigeon : Bird() {
	override fun fly() {
		println("голубь летит")
	}
}

fun makeBirdFly(bird : Bird){
	bird.fly()
}

//I принцип разделения интерфейсов
interface Workable{
	fun work()
}
interface Eatable {
	fun eat()
}

class Robot : Workable{
	override fun work() {}
}
class Human : Workable, Eatable{
	override fun work() {}
	override fun eat() {}
}

//D принцип инверсии зависимостей
interface Notifier{
	fun send()
}

class SmsNotifier : Notifier{
	override fun send() {}
}
class EmailNotifier : Notifier{
	override fun send() {}
}
class UserService(private val notifier: SmsNotifier){
	fun notifyUser(){
		notifier.send()
	}
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
	Text(
		text = "Hello $name!",
		modifier = modifier
	)
}
@Composable
fun MyScreen (){
	Card {
		Column {
			Card {
				Column {
					Text("Счета и карты")
					Text("MBANK")
					Card (modifier = Modifier.padding(all = 16.dp)){
						Column {
							Row {
								Text("номер карты")

							}
							Row {  }
							Row {  }

						}
					}
				}
			}
			Card {
				Column {
					Text("мои продукты")
					Row {

					}
				}
			}

		}

	}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	TimaTheme {
		Greeting("Android")
	}
}