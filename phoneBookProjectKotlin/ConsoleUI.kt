package phoneBookProjectKotlin

import phoneBookProjectKotlin.commands.*
import kotlin.reflect.KClass

var work: Boolean = true
val subClasses: List<KClass<out Command>> = listOf(
    Exit::class,
    Help::class,
    Add::class,
    Show::class,
    AddPhone::class,
    AddEmail::class,
    Find::class,
    Export::class
)
val consoleUI = ConsoleUI()
class ConsoleUI {
    var contacts = mutableListOf<Person>()
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
        if (contacts.isEmpty()) println("List of contacts empty")
        else {
            println("Введите Имя человека, которому хотите добавить номер: ")
            val findPerson = readlnOrNull().orEmpty()
            for (person in contacts){
                if(person.firstName.contentEquals(findPerson, ignoreCase = true)){
                    do {
                        println("Введите телефон: (Телефон должен начинаться с '+'): ")
                        phone = readlnOrNull().orEmpty()
                    } while (!Add(this).isValid(this, "phone"))
                    person.phones.addLast(phone)
                    println("Phone added successfully")
                } else println("Contact not found")
            }
        }
    }

    fun addEmail(){
        if (contacts.isEmpty()) println("List of contacts empty")
        else {
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
    }

    //TODO: Add fun checkPersonByName(var name) : Person for addPhone and addEmail
    fun checkPersonByName(name: String) : List<Person>? {
        var list = mutableListOf<Person>()
        for (person in contacts){
            if(person.firstName.contentEquals(name, ignoreCase = true)){
                list.add(person)
            }
        }
        return when(list.size) {
            0 -> return null
            else -> return list
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

    fun exportContacts(filePath: String){
        val json = jsonArray {
            contacts.forEach { addElement(it.toJson()) }
        }
        export(json, filePath)
    }


}
fun getCommands() {
    println("List of commands:")
    subClasses.forEach{ println((it.simpleName))}
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


