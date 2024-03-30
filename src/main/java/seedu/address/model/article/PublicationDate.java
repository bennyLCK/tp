package seedu.address.model.article;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ParserUtil.parseDateToString;
import static seedu.address.logic.parser.ParserUtil.parsePublicationDate;

import java.time.LocalDateTime;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents an Article's publication date in the article book.
 * Guarantees: immutable;
 */
public class PublicationDate implements Comparable<PublicationDate> {
    public static final String MESSAGE_CONSTRAINTS =
            "Publication date should be a valid date in the format of [dd-MM-yyyy] [HH:mm].";
    public final LocalDateTime date;

    /**
     * Constructs a {@code PublicationDate}.
     *
     * @param publicationDate A valid publication date.
     */
    public PublicationDate(LocalDateTime publicationDate) {
        requireNonNull(publicationDate);
        this.date = publicationDate;
    }

    /**
     * Returns true if a given string is a valid publication date.
     *
     * @param test The string to be tested.
     * @return True if the string is a valid publication date.
     */
    public static boolean isValidPublicationDate(String test) {
        try {
            parsePublicationDate(test);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    @Override
    public String toString() {
        return parseDateToString(date);
    }

    @Override
    public int compareTo(PublicationDate other) {
        return this.date.compareTo(other.date);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PublicationDate)) {
            return false;
        }

        PublicationDate otherDate = (PublicationDate) other;
        return otherDate.date.equals(this.date);
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
