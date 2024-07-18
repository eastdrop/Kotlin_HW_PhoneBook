package PhoneBookProjectKotlin

data class Person (val firstName: String, val phone: String, val email: String) {
   //TODO override toString??
    override fun toString(): String {
        return "Person(firstName='$firstName', phone=$phone, email=$email)"
    }
}