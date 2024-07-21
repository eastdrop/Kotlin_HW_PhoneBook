package PhoneBookProjectKotlin

import PhoneBookProjectKotlin.Commands.*
import kotlin.reflect.KClass

var work: Boolean = true
val subClasses: List<KClass<out Command>> = listOf(
    Exit::class,
    Help::class,
    Add::class,
    Show::class,
    AddPhone::class,
    AddEmail::class,
    Find::class
)
val consoleUI = ConsoleUI()
class ConsoleUI {
    var contacts = mutableSetOf<Person>()
    lateinit var phone : String
    lateinit var email : String
    fun add() {
        var phones = mutableListOf<String>()
        var emails = mutableListOf<String>()
        println("Add contact \nEnter Name: ")
        var name = readlnOrNull().orEmpty().ifBlank { "NoName" }
        do {
            println("Введите телефон: (Телефон должен начинаться с '+'): ")
            phone = readlnOrNull().orEmpty()
        } while (!Add(this).isValid(this, "phone"))
        do {
            println("Введите почту (Почта должна содержать  '@' и '.'): ")
            email = readlnOrNull().orEmpty()
        } while (!Add(this).isValid(this, "email"))
        phones.addLast(phone)
        emails.addLast(email)
        val person = Person(name, phones, emails)
        contacts.add(person)
    }

    fun addPhone(){
        println("Введите Имя человека, которому хотите добавить номер: ")
        val findPerson = readlnOrNull().orEmpty()
        for (person in contacts){
            if(person.firstName.contentEquals(findPerson, ignoreCase = true)){
                do {
                    phone = readlnOrNull().orEmpty()
                    println("Введите телефон: (Телефон должен начинаться с '+'): ")
                } while (!Add(this).isValid(this, "phone"))
                person.phones.addLast(phone)
                println("Phone added successfully")
            } else println("Contact not found")
        }
    }

    fun addEmail(){
        println("Введите Имя человека, которому хотите добавить почту: ")
        val findPerson = readlnOrNull().orEmpty()
        for (person in contacts){
            if(person.firstName.contentEquals(findPerson, ignoreCase = true)){
                do {
                    println("Введите почту (Почта должна содержать  '@' и '.'): ")
                    email = readlnOrNull().orEmpty()
                } while (!Add(this).isValid(this, "email"))
                person.emails.addLast(email)
                println("Email added successfully")
            } else println("Contact not found")
        }
    }

    fun exit(){
        println("Closing the application")
        work = false
    }
    fun help() {
        subClasses.forEach{
            val fe = it.constructors.first().call(this)
            println("${it.simpleName}: ${fe.description}")
        }
    }
    fun show(){
        println("Input name: ")
        val name = readlnOrNull().orEmpty()
        if (contacts.isEmpty()) println("Contacts is empty")
        else if (contacts.isNotEmpty()) {
            for (person in contacts){
                if(person.firstName.contentEquals(name, ignoreCase = true)) println(person)
                else println("Person not found")
            }
        }
    }
    fun find(){
        println("Input phone or email of contact")
        val searchData = readlnOrNull().orEmpty()
        for (person in contacts){
            if (person.phones.contains(searchData) || person.emails.contains(searchData)) println(person)
            else println("Person not found")
        }
    }
    //TODO: make fun find(val mail or phone) and make list of all persons those contain this mail or phone
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
            /*if (command.equals("Exit")) command.execute()
            else if (!command.equals("Exit")) {println("Want to continue? \n Type 'No' if you want to end work with application or press Enter to continue")
            val choice = readlnOrNull().orEmpty()
            if (choice.equals("no", ignoreCase = true)) Exit(consoleUI).execute()}
            else continue*/
        } else println("Unknown command. Please try again.")
    }
}



