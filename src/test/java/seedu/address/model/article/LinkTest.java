package seedu.address.model.article;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LinkTest {

    @Test
    public void constructor_null_createsEmptyLink() {
        assertTrue(new Link("").equals(new Link(null)));
    }

    @Test
    public void constructor_invalidLink_throwsIllegalArgumentException() {
        String invalidLink = " ";
        assertThrows(IllegalArgumentException.class, () -> new Link(invalidLink));
    }

    @Test
    public void isValidLink() {
        //null Link
        assertThrows(NullPointerException.class, () -> Link.isValidLink(null));

        // blank Link
        assertTrue(Link.isValidLink("")); // empty string

        // whitespaces only
        assertFalse(Link.isValidLink(" ")); // space only
        assertFalse(Link.isValidLink("  ")); // multiple spaces only

        // whitespaces in Link
        assertTrue(Link.isValidLink("mylink ")); // trailing space
        assertTrue(Link.isValidLink("mylink  ")); // multiple trailing spaces
        assertTrue(Link.isValidLink("my link")); // middle space
        assertTrue(Link.isValidLink("my  link")); // multiple middle spaces

        // invalid Links
        assertFalse(Link.isValidLink(" mylink")); // leading space
        assertFalse(Link.isValidLink("  mylink")); // multiple leading spaces

        // valid Links
        assertTrue(Link.isValidLink("mylink")); // alphabets only
        assertTrue(Link.isValidLink("12345")); // numbers only
        assertTrue(Link.isValidLink("my link")); // alphabets with space
        assertTrue(Link.isValidLink("123 45")); // numbers with space
        assertTrue(Link.isValidLink("my link 123")); // alphanumeric with spaces
        assertTrue(Link.isValidLink("~!@#$%^&*()_+`-={}|[]:,.?/;" + "\"")); // special characters only
        assertTrue(Link.isValidLink("~!@#$%^&* ()_+`-={}|[]:,.?/;" + " \"")); // special characters with spaces
        assertTrue(Link.isValidLink("my link: 123")); // alphanumeric with special characters and spaces
        assertTrue(Link.isValidLink("my link: 123 ")); // alphanumeric with special characters and trailing space
        assertTrue(Link.isValidLink("试试看")); // non-english unicode characters
        assertTrue(Link.isValidLink("试试 看")); // non-english unicode characters with space
        assertTrue(Link.isValidLink("试试看 123")); // non-english unicode characters with numbers and space
        assertTrue(Link.isValidLink("试试看: #123@")); // unicode characters with special characters, and spaces
        assertTrue(Link.isValidLink("试试看: #123@ ")); // mixed unicode characters with trailing space
    }

    @Test
    public void equals() {
        Link link = new Link("my link");

        // same values -> returns true
        assertTrue(link.equals(new Link("my link")));

        // same object -> returns true
        assertTrue(link.equals(link));

        // null -> returns false
        assertFalse(link.equals(null));

        // different types -> returns false
        assertFalse(link.equals(5.0f));

        // different link -> returns false
        assertFalse(link.equals(new Link("not my link")));
    }
}
