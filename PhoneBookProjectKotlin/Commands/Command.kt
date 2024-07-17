package PhoneBookProjectKotlin.Commands

import PhoneBookProjectKotlin.ConsoleUI
import PhoneBookProjectKotlin.work

sealed class Command() {
    lateinit var consoleUI: ConsoleUI
    abstract fun isValid() : Boolean
    abstract fun execute()
}

class Exit() : Command() {
    override fun execute() {
        println("Closing app")
        work = false
    }
    override fun isValid(): Boolean {
        TODO("Not yet implemented")
    }
}

class Help(): Command() {
    fun help() {
        println("exit - closing app")
        println("help - info about commands")
        println("add - add contact to your phonebook")
        readln()
    }
    override fun isValid(): Boolean {
        TODO("Not yet implemented")
    }

    override fun execute() {
        TODO("Not yet implemented")
    }
}
class Add() : Command() {

    override fun execute() {
        super.consoleUI.add()
    }

    override fun isValid(): Boolean {
        TODO("Not yet implemented")
    }




}