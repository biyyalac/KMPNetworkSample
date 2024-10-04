package com.example.a6
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds
class Greeting {
    private val rocketComponent = RocketComponent()
    fun greet(): Flow<String> = flow {
       // emit(if (Random.nextBoolean()) "Hi!" else "Hello!")

        //emit("Guess what this is! > ${platform().reversed()}")
       // delay(1.seconds)
        emit(daysPhrase())
        val list=rocketComponent.launchPhrase()
        list.forEach {
            delay(1.seconds)
            emit(it)
        }

    }
}