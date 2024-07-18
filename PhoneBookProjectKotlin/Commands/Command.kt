package PhoneBookProjectKotlin.Commands

import PhoneBookProjectKotlin.ConsoleUI
import PhoneBookProjectKotlin.work

sealed class Command(val description: String, var consoleUI: ConsoleUI) {
    //var consoleUI: ConsoleUI? = null
    abstract fun isValid() : Boolean
    abstract fun execute()
}
class Exit(consoleUI: ConsoleUI): Command("Closing app", consoleUI) {
    override fun execute() {
        super.consoleUI.exit()
    }
    override fun isValid(): Boolean {
        TODO("Not yet implemented")
    }
}

class Help(consoleUI: ConsoleUI): Command("HELP!!!", consoleUI) {
    override fun execute() {
        super.consoleUI.help()
    }
    override fun isValid(): Boolean {
        TODO("Not yet implemented")
    }

}
class Add(consoleUI: ConsoleUI) : Command("Добавить контакт", consoleUI) {
    override fun execute() {
        super.consoleUI.add()
    }
    override fun isValid(): Boolean {
        TODO("Not yet implemented")
    }
}

