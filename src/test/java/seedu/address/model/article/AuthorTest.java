package seedu.address.model.article;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AuthorTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Author(null));
    }

    @Test
    public void constructor_invalidAuthor_throwsIllegalArgumentException() {
        String invalidAuthor = "";
        assertThrows(IllegalArgumentException.class, () -> new Author(invalidAuthor));
    }

    @Test
    public void isValidAuthor() {
        // null author name
        assertThrows(NullPointerException.class, () -> Author.isValidAuthorName(null));

        // blank author name
        assertFalse(Author.isValidAuthorName("")); // empty string

        // whitespaces only
        assertTrue(Author.isValidAuthorName(" ")); // space only
        assertTrue(Author.isValidAuthorName("  ")); // multiple spaces only
        assertFalse(Author.isValidAuthorName(" ".trim())); // space only with expected parser pre-processing
        assertFalse(Author.isValidAuthorName("  ".trim())); // multiple spaces with expected parser pre-processing

        // whitespaces in author name
        assertTrue(Author.isValidAuthorName("myauthor")); // alphabetical input
        assertTrue(Author.isValidAuthorName("myauthor ")); // trailing space
        assertTrue(Author.isValidAuthorName("myauthor  ")); // multiple trailing spaces
        assertTrue(Author.isValidAuthorName("my author")); // middle space
        assertTrue(Author.isValidAuthorName("my  author")); // multiple middle spaces
        assertTrue(Author.isValidAuthorName(" myauthor")); // leading space
        assertTrue(Author.isValidAuthorName("  myauthor")); // multiple leading spaces
        assertTrue(Author.isValidAuthorName("myauthor123")); // alphanumeric input
        assertTrue(Author.isValidAuthorName("myauthor 123")); // alphanumeric with space
        assertTrue(Author.isValidAuthorName("my author 123")); // alphanumeric with multiple spaces
        assertTrue(Author.isValidAuthorName("my author 123 ")); // alphanumeric with trailing space
        assertTrue(Author.isValidAuthorName("my author 123  ")); // alphanumeric with trailing spaces
        assertTrue(Author.isValidAuthorName(" my author 123")); // alphanumeric with leading space
        assertTrue(Author.isValidAuthorName("  my author 123")); // alphanumeric with leading spaces

        // invalid author names
        assertFalse(Author.isValidAuthorName(":")); // special characters
        assertFalse(Author.isValidAuthorName("my:author")); // special characters with alphabetical input
        assertFalse(Author.isValidAuthorName("my author.")); // special characters with space
        assertFalse(Author.isValidAuthorName("myauthor:123")); // special characters with alphanumeric input
        assertFalse(Author.isValidAuthorName("my author: 123")); // special characters with alphanumeric and space
    }

    @Test
    public void equals() {
        Author author = new Author("my author");

        // same values -> returns true
        assertTrue(author.equals(new Author("my author")));

        // same object -> returns true
        assertTrue(author.equals(author));

        // null -> returns false
        assertFalse(author.equals(null));

        // different types -> returns false
        assertFalse(author.equals(5.0f));

        // different author name -> returns false
        assertFalse(author.equals(new Author("not my author")));
    }
}
