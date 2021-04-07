package com.meza.moviesapp.ui.activity.splash


sealed class StateInitiate
object Initiate : StateInitiate()
data class NotInitiate(val message: String)  : StateInitiate()




