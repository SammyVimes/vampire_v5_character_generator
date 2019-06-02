package configs

import org.junit.jupiter.api.Test

internal class NameConfigKtTest {
    @Test
    fun namesAreSane() {
        // Don't make assumptions about names
        val names = loadNames()
        assert(names.filterKeys { it in validNameCountries } == names)
    }
}