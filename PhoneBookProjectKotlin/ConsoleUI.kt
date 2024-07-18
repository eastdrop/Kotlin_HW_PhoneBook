package PhoneBookProjectKotlin

import PhoneBookProjectKotlin.Commands.Command
import kotlin.reflect.KClass

var work: Boolean = true
val commandList = listOf("exit", "help", "add")

fun getCommands(): String {
    val stringBuilder = StringBuilder()
    for (element in commandList){
        stringBuilder.append(element)
        stringBuilder.append("\n")
    }
    return stringBuilder.toString()
}

fun readCommand() : KClass<out Command>? {
        val subClasses: List<KClass<out Command>> = Command::class.sealedSubclasses
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
        println("Choose action: ")
        println(getCommands())
        println("Input command: ")
        val commandClass = readCommand()
        if (commandClass != null){
            val command = commandClass.objectInstance ?: commandClass.constructors.first().call()
            command.execute()
        } else println("Unknown command. Please try again.")
    }
}
/*
fun readCommand(command: String) {
    if (command == "exit"){
        return Exit.exit()
    } else if (command == "help"){
        return Help.help()
    } else if (command == "add")
        return Add.add()
}
*/

open class ConsoleUI {
    fun add() {
        var contacts = mutableListOf<Person>()
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
        println("Exit - closing app")
        println("Help - info about commands")
        println("Add - add contact to your phonebook")
        readln()
    }
}

