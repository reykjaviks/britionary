package britionary.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConverterTest {

    public ConverterTest() {
    }
    
    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testSearchNumbers() {
        Converter.convert("675");
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
    public void testConvertNumbers() {
        assertEquals("little", Converter.convert("littl3e4"));
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
