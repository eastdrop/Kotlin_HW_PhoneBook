package phoneBookProjectKotlin.commands

import phoneBookProjectKotlin.ConsoleUI

interface Command {
    val description: String
    abstract fun execute()
    fun isValid() : Boolean = true
}
class Exit(private val consoleUI: ConsoleUI): Command{
    override val description = "Exit the app"
    override fun execute() {
        consoleUI.exit()
    }
    override fun isValid(): Boolean = true
}

class Help(private val consoleUI: ConsoleUI): Command{
    override val description = "Show available commands"
    override fun execute() {
        consoleUI.help()
    }
    override fun isValid(): Boolean = true
}
class Add(private val consoleUI: ConsoleUI) : Command {
    override val description = "Add a new contact"
    override fun execute() {
        consoleUI.add()
    }
    fun isValid(consoleUI: ConsoleUI, checkType: String): Boolean {
        val checkPhone: (String) -> Boolean = {it.startsWith("+") && it.removePrefix("+").all(Char::isDigit)}
        val checkMail: (String) -> Boolean = { it.contains("@") && it.contains(".") }
        return when (checkType){
            "phone" -> checkPhone(consoleUI.phone)
            "email" -> checkMail(consoleUI.email)
            else -> false
        }
    }
}
class Show(private val consoleUI: ConsoleUI) : Command{
    override val description = "Show contact by name"
    override fun execute() {
        consoleUI.show()
    }
    override fun isValid(): Boolean = true
}

class AddPhone(private val consoleUI: ConsoleUI): Command{
    override val description = "Add a new phone to contact"
    override fun execute() {
        consoleUI.addPhone()
    }
}

class AddEmail(private val consoleUI: ConsoleUI): Command{
    override val description = "Add a new email to contact"
    override fun execute() {
        consoleUI.addEmail()
    }
}
class Find(private val consoleUI: ConsoleUI): Command{
    override val description = "Search a contact by phone or email"
    override fun execute() {
        consoleUI.find()
    }
}

class Export(private val consoleUI: ConsoleUI): Command{
    override val description = "Export contacts to a JSON file"

    override fun execute() {
        println("Enter file path to export contacts: ")
        val filePath = readlnOrNull().orEmpty()
        if(filePath.isNotBlank()){
            consoleUI.exportContacts(filePath)
            println("Contacts exported to $filePath")
        } else println("Invalid file path")
    }

    override fun isValid(): Boolean = true
}