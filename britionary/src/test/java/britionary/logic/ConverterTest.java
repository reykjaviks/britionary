package britionary.logic;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ConverterTest {

    public ConverterTest() {
    }

    @Test
    public void testConvertLowerCase() {
        assertEquals("little", Converter.convert("LITTLE"));
    }

    @Test
    public void testConvertUnderscores() {
        assertEquals("a great deal of", Converter.convert("a_great_deal_of"));
    }

    @Test
    public void testConvertLeadingSpace() {
        assertEquals("wee", Converter.convert(" wee"));
    }

    @Test
    public void testConvertTracingSpace() {
        assertEquals("wee", Converter.convert("wee "));
    }

}
