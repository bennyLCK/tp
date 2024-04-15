package seedu.address.model.article;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Title(null));
    }

    @Test
    public void constructor_invalidTitle_throwsIllegalArgumentException() {
        String invalidTitle = " ";
        assertThrows(IllegalArgumentException.class, () -> new Title(invalidTitle));
    }

    @Test
    public void isValidTitle() {
        //null title
        assertThrows(NullPointerException.class, () -> Title.isValidTitle(null));

        // blank title
        assertTrue(Title.isValidTitle("")); // empty string

        // whitespaces only
        assertFalse(Title.isValidTitle(" ")); // space only
        assertFalse(Title.isValidTitle("  ")); // multiple spaces only

        // whitespaces in title
        assertTrue(Title.isValidTitle("mytitle ")); // trailing space
        assertTrue(Title.isValidTitle("mytitle  ")); // multiple trailing spaces
        assertTrue(Title.isValidTitle("my title")); // middle space
        assertTrue(Title.isValidTitle("my  title")); // multiple middle spaces

        // invalid titles
        assertFalse(Title.isValidTitle(" mytitle")); // leading space
        assertFalse(Title.isValidTitle("  mytitle")); // multiple leading spaces

        // valid titles
        assertTrue(Title.isValidTitle("mytitle")); // alphabets only
        assertTrue(Title.isValidTitle("12345")); // numbers only
        assertTrue(Title.isValidTitle("my title")); // alphabets with space
        assertTrue(Title.isValidTitle("123 45")); // numbers with space
        assertTrue(Title.isValidTitle("my title 123")); // alphanumeric with spaces
        assertTrue(Title.isValidTitle("~!@#$%^&*()_+`-={}|[]:,.?/;" + "\"")); // special characters only
        assertTrue(Title.isValidTitle("~!@#$%^&* ()_+`-={}|[]:,.?/;" + " \"")); // special characters with spaces
        assertTrue(Title.isValidTitle("my title: 123")); // alphanumeric with special characters and spaces
        assertTrue(Title.isValidTitle("my title: 123 ")); // alphanumeric with special characters and trailing space
        assertTrue(Title.isValidTitle("试试看")); // non-english unicode characters
        assertTrue(Title.isValidTitle("试试 看")); // non-english unicode characters with space
        assertTrue(Title.isValidTitle("试试看 123")); // non-english unicode characters with numbers and space
        assertTrue(Title.isValidTitle("试试看: #123@")); // unicode characters with special characters, and spaces
        assertTrue(Title.isValidTitle("试试看: #123@ ")); // mixed unicode characters with trailing space
    }

    @Test
    public void equals() {
        Title title = new Title("my title");

        // same values -> returns true
        assertTrue(title.equals(new Title("my title")));

        // same object -> returns true
        assertTrue(title.equals(title));

        // null -> returns false
        assertFalse(title.equals(null));

        // different types -> returns false
        assertFalse(title.equals(5.0f));

        // different title -> returns false
        assertFalse(title.equals(new Title("not my title")));
    }
}
