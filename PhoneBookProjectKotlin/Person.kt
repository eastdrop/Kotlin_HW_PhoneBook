package PhoneBookProjectKotlin

data class Person (val firstName: String, val phone: String, val email: String) {
    override fun toString(): String {
        return """
            |Contact Details:
            |Name: $firstName
            |Phone: $phone
            |Email: $email
        """.trimMargin()
    }
}