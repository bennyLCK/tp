package seedu.address.model.article;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SourceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Source(null));
    }

    @Test
    public void constructor_invalidSource_throwsIllegalArgumentException() {
        String invalidSource = "";
        assertThrows(IllegalArgumentException.class, () -> new Source(invalidSource));
    }

    @Test
    public void isValidSource() {
        // null source name
        assertThrows(NullPointerException.class, () -> Source.isValidSourceName(null));

        // blank source name
        assertFalse(Source.isValidSourceName("")); // empty string

        // whitespaces only
        assertTrue(Source.isValidSourceName(" ")); // space only
        assertTrue(Source.isValidSourceName("  ")); // multiple spaces only
        assertFalse(Source.isValidSourceName(" ".trim())); // space only with expected parser pre-processing
        assertFalse(Source.isValidSourceName("  ".trim())); // multiple spaces with expected parser pre-processing

        // whitespaces in source name
        assertTrue(Source.isValidSourceName("mysource")); // alphabetical input
        assertTrue(Source.isValidSourceName("mysource ")); // trailing space
        assertTrue(Source.isValidSourceName("mysource  ")); // multiple trailing spaces
        assertTrue(Source.isValidSourceName("my source")); // middle space
        assertTrue(Source.isValidSourceName("my  source")); // multiple middle spaces
        assertTrue(Source.isValidSourceName(" mysource")); // leading space
        assertTrue(Source.isValidSourceName("  mysource")); // multiple leading spaces
        assertTrue(Source.isValidSourceName("mysource123")); // alphanumeric input
        assertTrue(Source.isValidSourceName("mysource 123")); // alphanumeric with space
        assertTrue(Source.isValidSourceName("my source 123")); // alphanumeric with multiple spaces
        assertTrue(Source.isValidSourceName("my source 123 ")); // alphanumeric with trailing space
        assertTrue(Source.isValidSourceName("my source 123  ")); // alphanumeric with trailing spaces
        assertTrue(Source.isValidSourceName(" my source 123")); // alphanumeric with leading space
        assertTrue(Source.isValidSourceName("  my source 123")); // alphanumeric with leading spaces

        // invalid source names
        assertFalse(Source.isValidSourceName(":")); // special characters
        assertFalse(Source.isValidSourceName("my:source")); // special characters with alphabetical input
        assertFalse(Source.isValidSourceName("my source.")); // special characters with space
        assertFalse(Source.isValidSourceName("mysource:123")); // special characters with alphanumeric input
        assertFalse(Source.isValidSourceName("my source: 123")); // special characters with alphanumeric and space
    }

    @Test
    public void equals() {
        Source source = new Source("my source");

        // same values -> returns true
        assertTrue(source.equals(new Source("my source")));

        // same object -> returns true
        assertTrue(source.equals(source));

        // null -> returns false
        assertFalse(source.equals(null));

        // different types -> returns false
        assertFalse(source.equals(5.0f));

        // different source name -> returns false
        assertFalse(source.equals(new Source("not my source")));
    }
}
