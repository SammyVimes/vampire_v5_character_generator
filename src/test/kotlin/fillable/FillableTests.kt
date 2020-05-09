package fillable

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import stats.Attribute

internal class FillableTests {

    @Test
    fun testAttrLocation() {
        assertEquals("Attributes.3.1.1", getAttrPoint(Attribute.MANIPULATION, 3))
        assertEquals("Attributes.3.2.0", getAttrPoint(Attribute.STAMINA, 3))
    }

}
