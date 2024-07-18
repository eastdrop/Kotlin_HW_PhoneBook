package PhoneBookProjectKotlin.Commands

import PhoneBookProjectKotlin.ConsoleUI
import java.io.IOException

sealed class Command(val description: String, var consoleUI: ConsoleUI) {
    abstract fun execute()
    abstract fun isValid() : Boolean
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
class Show(consoleUI: ConsoleUI) : Command("Последний добавленный контакт: ", consoleUI){
    override fun execute() {
        super.consoleUI.show()
    }
    override fun isValid(): Boolean {
        TODO("Not yet implemented")
    }
}