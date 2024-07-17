package PhoneBookProjectKotlin

import PhoneBookProjectKotlin.Commands.Add
import PhoneBookProjectKotlin.Commands.Command
import PhoneBookProjectKotlin.Commands.Exit
import PhoneBookProjectKotlin.Commands.Help
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
            var strElement: String = element.toString()
            if (strElement.contains(command)) {
                /*for (f in element.functions){
                    if (f.returnType.equals(Unit)){
                        return f
                    }
                }*/
                return element
            }
        }
       /*if (command == "exit"){
            return Exit
        } else if (command == "help"){
            return Help
        } else if (command == "add")
            return Add*/
    return null
}

fun start(){
    while (work){
        println("Choose action: ")
        println(getCommands())
        println("Input command: ")
        readCommand()
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

class ConsoleUI {
    fun add() {
        var contacts = mutableListOf<Person>()
        lateinit var name: String
        lateinit var phone: String
        lateinit var email: String
        fun checkPhone(phoneToCheck: String): Boolean{
            return !phoneToCheck.startsWith("+").and(phoneToCheck.removePrefix("+").all { c: Char -> c.isDigit() })
        }
        fun checkMail(mailToCheck: String): Boolean{
            return !mailToCheck.any { "@" in mailToCheck && "." in mailToCheck}
        }
        println("Добавить контакт \n Введите Имя: ")
        name = readLine().toString()
        if (name == null) {
            name = "NoName"
        }
        println("Добавить контакт \n Введите телефон: ")
        phone = readLine().toString()
        do {
            println("Телефон должен начинаться с \"+\"")
            phone = readLine().toString()
        } while (checkPhone(phone))
        println("Добавить контакт \n Введите почту: ")
        email = readLine().toString()
        do {
            println("Почта должна содержать  \"@\" и \".\"")
            email = readLine().toString()
        } while (checkMail(email))
        val person = Person(name, phone, email)
        contacts.add(person)
        println(contacts)

    }
}
