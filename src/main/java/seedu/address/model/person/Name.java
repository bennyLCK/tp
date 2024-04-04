package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name implements Comparable<Name> {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public int compareTo(Name other) {
        return this.fullName.compareTo(other.fullName);
    }

    private boolean areSameNames(String[] name, String[] otherName) {
        if (name.length != otherName.length) {
            return false;
        }
        for (int i = 0; i < name.length; i++) {
            if (!name[i].equalsIgnoreCase(otherName[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Name)) {
            return false;
        }

        Name otherName = (Name) other;
        String[] splitName = fullName.split("\\s+");
        String[] splitOtherName = otherName.fullName.split("\\s+");

        return areSameNames(splitName, splitOtherName);
    }

    // It is safe to implement hashCode() this way because there are already precautions
    // in place to ensure that no two Name instances can be kept in persons in the Address Book
    // that are equal and yet have different hashcode values.
    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
