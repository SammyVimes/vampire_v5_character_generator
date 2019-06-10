import kotlinx.serialization.UnstableDefault

@UnstableDefault
fun main(args: Array<String>) {
    val character = CharacterGenerator().generateCharacter()
    println(character.toJson())
}