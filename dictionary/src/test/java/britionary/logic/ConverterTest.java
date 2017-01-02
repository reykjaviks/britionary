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
        converter.convert("LITTLE");
        assertEquals("little", converter.getWord());
    }

    @Test
    public void testConvertUnderscores() {
        converter.convert("a_great_deal_of");
        assertEquals("a great deal of", converter.getWord());
    }

    @Test
    public void testConvertNumbers() {
        converter.convert("littl3e4");
        assertEquals("little", converter.getWord());
    }

    @Test
    public void testConvertLeadingSpace() {
        converter.convert(" wee");
        assertEquals("wee", converter.getWord());
    }

    @Test
    public void testConvertTracingSpace() {
        converter.convert("wee ");
        assertEquals("wee", converter.getWord());
    }

}
