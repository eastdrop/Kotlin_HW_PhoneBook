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
    override fun isValid(): Boolean {
        return true
    }
}

class Help(private val consoleUI: ConsoleUI): Command{
    override val description = "Show available commands"
    override fun execute() {
        consoleUI.help()
    }
    override fun isValid(): Boolean {
        return true
    }
}
class Add(private val consoleUI: ConsoleUI) : Command {
    override val description = "Add a new contact"
    override fun execute() {
        consoleUI.add()
    }
    override fun isValid(): Boolean {

        fun checkPhone(phoneToCheck: String): Boolean{
            return phoneToCheck.startsWith("+")&& phoneToCheck.removePrefix("+").all { it.isDigit() }
        }
        fun checkMail(mailToCheck: String): Boolean{
            return mailToCheck.contains("@") && mailToCheck.contains(".")
        }
        if (checkPhone(consoleUI.phone) && checkMail(consoleUI.email) == true) return true
        else return false
    }
}
class Show(private val consoleUI: ConsoleUI) : Command{
    override val description = "Show last added contact"
    override fun execute() {
        consoleUI.show()
    }
    override fun isValid(): Boolean {
        TODO("Not yet implemented")
    }
}