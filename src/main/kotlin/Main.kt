import com.lowagie.text.pdf.AcroFields
import com.lowagie.text.pdf.PdfReader
import com.lowagie.text.pdf.PdfStamper
import configs.configResourcePath
import fillable.fill
import kotlinx.serialization.UnstableDefault
import java.io.FileOutputStream


@UnstableDefault
fun main(args: Array<String>) {
    val character: VTMCharacter = CharacterGenerator().generateCharacter()
    println(character.toJson())

    val pdf = Object::class.java.getResourceAsStream("$configResourcePath/sheet.pdf")
    val reader = PdfReader(pdf)
    val stamp = PdfStamper(reader, FileOutputStream("generated.pdf"))
    val form: AcroFields = stamp.acroFields

    fill(form, character)

    stamp.close()
}
