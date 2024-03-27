package seedu.address.testutil;

import java.util.HashSet;

import seedu.address.model.article.Article;
import seedu.address.model.article.Article.Status;
import seedu.address.model.util.SampleArticleDataUtil;

/**
 * A utility class to help with building Article objects.
 */
public class ArticleBuilder {

    public static final String DEFAULT_TITLE = "Once upon a time";
    public static final String DEFAULT_AUTHOR = "Barney Loo";
    public static final String DEFAULT_PUBLICATION_DATE = "03-01-2021"; // In dd-MM-yyyy format.
    public static final String DEFAULT_SOURCE = "Domo Dragto";
    public static final String DEFAULT_TAG = "Fantasy";
    public static final String DEFAULT_STATUS = "DRAFT";

    private Title title;
    private Set<Author> authors;
    private PublicationDate publicationDate;
    private Set<Source> sources;
    private Set<Tag> tags;
    private Status status;


    /**
     * Creates a {@code ArticleBuilder} with the default details.
     */
    public ArticleBuilder() {
        title = new Title(DEFAULT_TITLE);
        authors = new HashSet<>();
        authors.add(new Author(DEFAULT_AUTHOR));
        publicationDate = new PublicationDate(DEFAULT_PUBLICATION_DATE);
        sources = new HashSet<>();
        sources.add(new Source(DEFAULT_SOURCE));
        tags = new HashSet<>();
        tags.add(new Tag(DEFAULT_TAG));
        status = Status.valueOf(DEFAULT_STATUS);
    }

    /**
     * Initializes the ArticleBuilder with the data of {@code articleToCopy}.
     */
    public ArticleBuilder(Article articleToCopy) {
        title = articleToCopy.getTitle();
        authors = articleToCopy.getAuthors();
        publicationDate = articleToCopy.getPublicationDate();
        sources = articleToCopy.getSources();
        tags = articleToCopy.getTags();
        status = articleToCopy.getStatus();
    }

    /**
     * Sets the {@code Title} of the {@code Article} that we are building.
     */
    public ArticleBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Parses the {@code authors} into a {@code Set<Author>} and set it to the {@code Article} that we are building.
     */
    public ArticleBuilder withAuthors(String ... authors) {
        this.authors = SampleArticleDataUtil.getAuthorSet(authors);
        return this;
    }

    /**
     * Sets the {@code PublicationDate} of the {@code Article} that we are building.
     */
    public ArticleBuilder withPublicationDate(String publicationDate) {
        this.publicationDate = new PublicationDate(publicationDate);
        return this;
    }

    /**
     * Parses the {@code sources} into a {@code Set<Source>} and set it to the {@code Article} that we are building.
     */
    public ArticleBuilder withSources(String ... sources) {
        this.sources = SampleArticleDataUtil.getSourceSet(sources);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Article} that we are building.
     */
    public ArticleBuilder withTags(String ... tags) {
        this.tags = SampleArticleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Article} that we are building.
     */
    public ArticleBuilder withStatus(String status) {
        this.status = Status.valueOf(status);
        return this;
    }

    public Article build() {
        return new Article(title, authors, publicationDate, sources, tags, status);
    }
}
