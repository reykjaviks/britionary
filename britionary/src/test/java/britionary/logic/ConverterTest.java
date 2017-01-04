package britionary.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConverterTest {

    Converter converter;

    public ConverterTest() {
    }

    @Before
    public void setUp() {
        converter = new Converter();
    }

    @Test
    public void testGetWord() {
    }

    @Test
    public void testConvertLowerCase() {
        assertEquals("little", converter.convert("LITTLE"));
    }

    @Test
    public void testConvertUnderscores() {
        assertEquals("a great deal of", converter.convert("a_great_deal_of"));
    }

    @Test
    public void testConvertNumbers() {
        assertEquals("little", converter.convert("littl3e4"));
    }

    @Test
    public void testConvertLeadingSpace() {
        assertEquals("wee", converter.convert(" wee"));
    }

    @Test
    public void testConvertTracingSpace() {
        assertEquals("wee", converter.convert("wee "));
    }

}
