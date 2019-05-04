import org.junit.jupiter.api.Test

internal class ConfigTest {
    @Test
    fun testLoadConfigs() {
        val defaultPointsToSpend = loadDefaultPointsToSpend()
        val playerCharacterTypes = loadPlayerCharacterTypes()
    }
}