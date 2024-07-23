package PhoneBookProjectKotlin

data class Person (val firstName: String, val phones: MutableList<String>, val emails: MutableList<String>) {
    override fun toString(): String {
        return """
            |Contact Details:
            |Name: $firstName Phones: $phones Emails: $emails
        """.trimMargin()
    }

    fun toJson(): JsonObject{
        return jsonObject {
            addProperty("name", firstName)
            addProperty("phones", phones)
            addProperty("emails", emails)
        }
    }
}