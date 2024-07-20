package PhoneBookProjectKotlin.Commands

import PhoneBookProjectKotlin.ConsoleUI

interface Command {
    val description: String
    abstract fun execute()
    abstract fun isValid() : Boolean
}
class Exit(private val consoleUI: ConsoleUI): Command{
    override val description = "Exit the app"
    override fun execute() {
        println("Closing the application")
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
    override fun isValid(): Boolean {

        val checkPhone: (String) -> Boolean = {it.startsWith("+") && it.removePrefix("+").all(Char::isDigit)}
        val checkMail: (String) -> Boolean = { it.contains("@") && it.contains(".") }
        return checkPhone(consoleUI.phone) && checkMail(consoleUI.email)
    }
}
class Show(private val consoleUI: ConsoleUI) : Command{
    override val description = "Show last added contact"
    override fun execute() {
        consoleUI.show()
    }
    override fun isValid(): Boolean = true
}