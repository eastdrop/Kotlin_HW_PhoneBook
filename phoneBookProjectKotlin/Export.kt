package phoneBookProjectKotlin
import java.io.File

fun export(json: JsonArray, filePath: String){
    File(filePath).writeText(json.toString())
}