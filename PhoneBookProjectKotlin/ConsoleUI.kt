package PhoneBookProjectKotlin

import PhoneBookProjectKotlin.Commands.*
import kotlin.reflect.KClass

var work: Boolean = true
val subClasses: List<KClass<out Command>> = listOf(
    Exit::class,
    Help::class,
    Add::class,
    Show::class
)
val consoleUI = ConsoleUI()
class ConsoleUI {
    private var contacts = mutableListOf<Person>()
    lateinit var phone: String
    lateinit var email: String
    fun add() {
        println("Add contact \nEnter Name: ")
        val name = readlnOrNull().orEmpty().ifBlank { "NoName" }
        do {
            println("Введите телефон: (Телефон должен начинаться с '+'): ")
            phone = readlnOrNull().orEmpty()
            println("Введите почту (Почта должна содержать  '@' и '.'): ")
            email = readlnOrNull().orEmpty()
        } while (!Add(this).isValid())
        val person = Person(name, phone, email)
        contacts.add(person)
    }

    fun exit(){
        work = false
    }
    fun help() {
        subClasses.forEach{
            val fe = it.constructors.first().call(this)
            println("${it.simpleName}: ${fe.description}")
        }
    }
    fun show(){
        if (contacts.isEmpty()) println("Not initialized") else println(contacts.last())
    }
}

fun getCommands() {
    println("List of commands:")
    for (element in subClasses){
        println(element.simpleName)
    }
}

fun readCommand() : KClass<out Command>? {
    val command: String = readlnOrNull().orEmpty().lowercase().replaceFirstChar(Char::uppercase )
    return subClasses.firstOrNull { it.simpleName.equals(command, ignoreCase = true) }
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



