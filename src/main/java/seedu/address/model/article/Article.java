package seedu.address.model.article;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.ParserUtil.parseDateToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents an article in the address book.
 */
public class Article {
    private final Title title;
    private final Set<Outlet> outlets = new HashSet<>();
    private final Set<Author> authors = new HashSet<>();
    private final Set<Source> sources = new HashSet<>();
    private final Set<Tag> tags = new HashSet<>();
    private final PublicationDate publicationDate;

    public List<Person> persons = new ArrayList<>();

    /**
     * Enumeration of Status of an article.
     */
    public enum Status {
        DRAFT, PUBLISHED, ARCHIVED
    }

    private final Status status;

    /**
     * Constructs an Article object.
     *
     * @param title the title of the article.
     * @param authors the authors of the article.
     * @param publicationDate the date of publication.
     * @param sources the people interviewed.
     * @param tags the subject of the article.
     * @param status the current status of the article.
     */
    public Article(Title title, Set<Author> authors, Set<Source> sources, Set<Tag> tags,
                   Set<Outlet> outlets, PublicationDate publicationDate, Status status) {
        requireAllNonNull(title, authors, sources, tags, outlets, publicationDate, status);
        this.title = title;
        this.authors.addAll(authors);
        this.sources.addAll(sources);
        this.tags.addAll(tags);
        this.outlets.addAll(outlets);
        this.publicationDate = publicationDate;
        this.status = status;
    }

    public Title getTitle() {
        return title;
    }

    public Set<Author> getAuthors() {
        return Collections.unmodifiableSet(authors);
    }

    public PublicationDate getPublicationDate() {
        return this.publicationDate;
    }

    public String getPublicationDateAsString() {
        return parseDateToString(this.publicationDate.date);
    }

    public Set<Outlet> getOutlets() {
        return Collections.unmodifiableSet(outlets);
    }

    public Set<Source> getSources() {
        return Collections.unmodifiableSet(sources);
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Status getStatus() {
        return this.status;
    }

    /**
     * Returns true if all the attributes of Article class are identical to the attributes of an existing Article.
     *
     * @param otherArticle
     * @return
     */
    public boolean isSameArticle(Article otherArticle) {
        if (otherArticle == this) {
            return true;

        /*
         * If it is not draft and has same title as another article,
         * consider it as same article
        */
        } else if (otherArticle.getStatus() != Status.DRAFT && this.getStatus() != Status.DRAFT
                && otherArticle.getTitle().equals(this.title)) {
            return true;
        } else {
            return false;
        }
    }

    public void makeLinks(List<Person> persons) {
        for (Person person : persons) {
            makeLink(person);
        }
    }

    public void makeLink(Person person) {
        for (Author author : authors) {
            if (author.authorName.equals(person.getName().fullName)) {
                persons.add(person);
                person.addArticle(this);
            }
        }
        for (Source source : sources) {
            if (source.sourceName.equals(person.getName().fullName)) {
                persons.add(person);
                person.addArticle(this);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Article)) {
            return false;
        }

        Article otherArticle = (Article) other;
        return title.equals(otherArticle.title)
                && authors.equals(otherArticle.authors)
                && sources.equals(otherArticle.sources)
                && publicationDate.equals(otherArticle.publicationDate)
                && tags.equals(otherArticle.tags)
                && status.equals(otherArticle.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, authors, publicationDate, sources, tags, status);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("title", title)
                .add("authors", authors)
                .add("publicationDate", publicationDate)
                .add("sources", sources)
                .add("tags", tags)
                .add("status", status)
                .toString();
    }
}
