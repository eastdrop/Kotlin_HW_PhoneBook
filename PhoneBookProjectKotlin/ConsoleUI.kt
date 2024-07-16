package PhoneBookProjectKotlin

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

fun start(){
    while (work){
        println("Choose action: ")
        println(getCommands())
        println("Input command: ")
        var choose: String = readLine().toString()
        execute(choose)
    }
}

fun execute(command: String){
    if (command == "exit"){
        exit()
    } else if (command == "help"){
        help()
    } else if (command == "add")
        add()
}

fun exit(){
    println("Closing app")
    work = false
}

fun help(){
    println("exit - closing app")
    println("help - info about commands")
    println("add - add contact to your phonebook")
    readln()
}


fun add(){
    var contacts = mutableListOf<Person>()
    lateinit var name: String
    lateinit var phone: String
    lateinit var email: String
    println("Добавить контакт \n Введите Имя: ")
    name = readLine().toString()
    println("Добавить контакт \n Введите телефон: ")
    phone = readLine().toString()
    println("Добавить контакт \n Введите почту: ")
    email = readLine().toString()
    val person = Person(name, phone, email)
    contacts.add(person)
    println(contacts)
}
class ConsoleUI {

}
