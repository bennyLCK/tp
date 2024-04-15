package seedu.address.model.article;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class OutletTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Outlet(null));
    }

    @Test
    public void constructor_invalidOutlet_throwsIllegalArgumentException() {
        String invalidOutlet = "";
        assertThrows(IllegalArgumentException.class, () -> new Outlet(invalidOutlet));
    }

    @Test
    public void isValidOutlet() {
        // null outlet name
        assertThrows(NullPointerException.class, () -> Outlet.isValidOutletName(null));

        // blank outlet name
        assertFalse(Outlet.isValidOutletName("")); // empty string

        // whitespaces only
        assertTrue(Outlet.isValidOutletName(" ")); // space only
        assertTrue(Outlet.isValidOutletName("  ")); // multiple spaces only
        assertFalse(Outlet.isValidOutletName(" ".trim())); // space only with expected parser pre-processing
        assertFalse(Outlet.isValidOutletName("  ".trim())); // multiple spaces with expected parser pre-processing

        // whitespaces in outlet name
        assertTrue(Outlet.isValidOutletName("myoutlet")); // alphabetical input
        assertTrue(Outlet.isValidOutletName("myoutlet ")); // trailing space
        assertTrue(Outlet.isValidOutletName("myoutlet  ")); // multiple trailing spaces
        assertTrue(Outlet.isValidOutletName("my outlet")); // middle space
        assertTrue(Outlet.isValidOutletName("my  outlet")); // multiple middle spaces
        assertTrue(Outlet.isValidOutletName(" myoutlet")); // leading space
        assertTrue(Outlet.isValidOutletName("  myoutlet")); // multiple leading spaces
        assertTrue(Outlet.isValidOutletName("myoutlet123")); // alphanumeric input
        assertTrue(Outlet.isValidOutletName("myoutlet 123")); // alphanumeric with space
        assertTrue(Outlet.isValidOutletName("my outlet 123")); // alphanumeric with multiple spaces
        assertTrue(Outlet.isValidOutletName("my outlet 123 ")); // alphanumeric with trailing space
        assertTrue(Outlet.isValidOutletName("my outlet 123  ")); // alphanumeric with trailing spaces
        assertTrue(Outlet.isValidOutletName(" my outlet 123")); // alphanumeric with leading space
        assertTrue(Outlet.isValidOutletName("  my outlet 123")); // alphanumeric with leading spaces

        // invalid outlet names
        assertFalse(Outlet.isValidOutletName(":")); // special characters
        assertFalse(Outlet.isValidOutletName("my:outlet")); // special characters with alphabetical input
        assertFalse(Outlet.isValidOutletName("my outlet.")); // special characters with space
        assertFalse(Outlet.isValidOutletName("myoutlet:123")); // special characters with alphanumeric input
        assertFalse(Outlet.isValidOutletName("my outlet: 123")); // special characters with alphanumeric and space
    }

    @Test
    public void equals() {
        Outlet outlet = new Outlet("my outlet");

        // same values -> returns true
        assertTrue(outlet.equals(new Outlet("my outlet")));

        // same object -> returns true
        assertTrue(outlet.equals(outlet));

        // null -> returns false
        assertFalse(outlet.equals(null));

        // different types -> returns false
        assertFalse(outlet.equals(5.0f));

        // different outlet name -> returns false
        assertFalse(outlet.equals(new Outlet("not my outlet")));
    }
}
