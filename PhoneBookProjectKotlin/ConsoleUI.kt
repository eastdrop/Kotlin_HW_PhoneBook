package PhoneBookProjectKotlin

import PhoneBookProjectKotlin.Commands.*
import kotlin.reflect.KClass

var work: Boolean = true
//val subClasses: List<KClass<out Command>> = Command::class.sealedSubclasses
val subClasses: List<KClass<out Command>> = listOf(
    Exit::class,
    Help::class,
    Add::class,
    Show::class
)
val consoleUI = ConsoleUI()

fun getCommands() {
    println("List of commands:")
    for (element in subClasses){
        println(element.simpleName)
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
        getCommands()
        println("Input command: ")
        val commandClass = readCommand()
        if (commandClass != null){
            val command = commandClass.constructors.first().call(consoleUI)
            command.description
            command.execute()
        } else println("Unknown command. Please try again.")
    }
}

class ConsoleUI {
    val checkAdd = Add(consoleUI)
    private var contacts = mutableListOf<Person>()
    lateinit var phone: String
    lateinit var email: String
    fun add() {
        println("Add contact \nEnter Name: ")
        val name = readlnOrNull().toString().ifBlank { "NoName" }

        do {
            println("Введите телефон: (Телефон должен начинаться с '+'): ")
            phone = readlnOrNull().toString()
            println("Введите почту (Почта должна содержать  '@' и '.'): ")
            email = readlnOrNull().toString()
        } while (!checkAdd.isValid())
        val person = Person(name, phone, email)
        contacts.add(person)
    }

    fun exit(){
        work = false
    }
    fun help() {
        for (element in subClasses){
            val e = element.constructors.first().call(consoleUI)
            println("${element.simpleName}: ${e.description}")
        }
    }
    fun show(){
        if (contacts.isEmpty()) println("Not initialized")
        else println(contacts.last())
    }
}

