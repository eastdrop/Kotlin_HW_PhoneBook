package PhoneBookProjectKotlin

import PhoneBookProjectKotlin.Commands.Command
import kotlin.reflect.KClass

var work: Boolean = true
val subClasses: List<KClass<out Command>> = Command::class.sealedSubclasses
fun getCommands() {
    println("List of commands:")
    for (element in subClasses){
        println(element)
    }
}

fun readCommand() : KClass<out Command>? {
        val command: String = readlnOrNull().toString().lowercase().replaceFirstChar { it.uppercase() }
        for (element in subClasses){
            var strElement: String = element.simpleName ?: ""
            if (strElement.equals(command, ignoreCase = true)) {
                return element
            }
        }
    return null
}

fun start(){
    while (work){
        println(getCommands())
        println("Input command: ")
        val commandClass = readCommand()
        if (commandClass != null){
            val command = commandClass.objectInstance ?: commandClass.constructors.first().call()
            command.execute()
        } else println("Unknown command. Please try again.")
    }
}

class ConsoleUI {
    private var contacts = mutableListOf<Person>()
    fun add() {
        println("Add contact \nEnter Name: ")
        val name = readlnOrNull().toString().ifBlank { "NoName" }
        var phone: String
        var email: String
        do {
            println("Добавить контакт \n Введите телефон: (Телефон должен начинаться с '+'): ")
            phone = readlnOrNull().toString()
        } while (!checkPhone(phone))
        do {
            println("Добавить контакт \n Введите почту (Почта должна содержать  '@' и '.'): ")
            email = readlnOrNull().toString()
        } while (!checkMail(email))
        val person = Person(name, phone, email)
        contacts.add(person)
        println(contacts)
    }
    private fun checkPhone(phoneToCheck: String): Boolean{
        return phoneToCheck.startsWith("+").and(phoneToCheck.removePrefix("+").all { c: Char -> c.isDigit() })
    }
    private fun checkMail(mailToCheck: String): Boolean{
        return mailToCheck.any { "@" in mailToCheck && "." in mailToCheck}
    }
    fun exit(){
        work = false
    }
    fun help() {
        for (element in subClasses){
            var e = element.constructors.first().call()
            e.description
        }
        /*println("Exit - closing app")
        println("Help - info about commands")
        println("Add - add contact to your phonebook")
        readln()*/
    }
    fun show(){
        if (contacts.isEmpty()) println("Not initialized")
        else println(contacts.last())
    }
}

