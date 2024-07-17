package PhoneBookProjectKotlin

data class Person (val firstName: String = "NoName", val phone: String? = null, val email: String? = null) {
   //TODO override toString??
    override fun toString(): String {
        return "Person(firstName='$firstName', phone=$phone, email=$email)"
    }
}