package seedu.address.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.article.Article;
import seedu.address.model.article.Author;
import seedu.address.model.article.Source;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Article}.
 */
public class JsonAdaptedArticle {
    private final String title;
    private final List<JsonAdaptedAuthor> authors = new ArrayList<>();
    //Should be able to be null
    private final LocalDateTime publicationDate;
    private final List<JsonAdaptedSource> sources = new ArrayList<>();
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final Article.Status status;
    private final String link;

    /**
     * Construct a {@code JsonAdaptedArticle} with the given article details.
     */
    @JsonCreator
    public JsonAdaptedArticle(@JsonProperty("title") String title,
                              @JsonProperty("authors") List<JsonAdaptedAuthor> authors,
                              @JsonProperty("publicationDate") LocalDateTime publicationDate,
                              @JsonProperty("sources") List<JsonAdaptedSource> sources,
                              @JsonProperty("tags") List<JsonAdaptedTag> tags,
                              @JsonProperty("status") Article.Status status,
                              @JsonProperty("link") String link) {
        this.title = title;
        if (authors != null) {
            this.authors.addAll(authors);
        }
        this.publicationDate = publicationDate;
        if (sources != null) {
            this.sources.addAll(sources);
        }
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.status = status;
        this.link = link;
    }
    /**
     * Construct a {@code JsonAdaptedArticle} with neccessary details
     * @param sourceArticle
     */
    public JsonAdaptedArticle(Article sourceArticle) {
        title = sourceArticle.getTitle();
        authors.addAll(sourceArticle.getAuthors().stream()
                .map(JsonAdaptedAuthor::new)
                .collect(Collectors.toList()));
        publicationDate = sourceArticle.getPublicationDate();
        sources.addAll(sourceArticle.getSources().stream()
                .map(JsonAdaptedSource::new)
                .collect(Collectors.toList()));
        tags.addAll(sourceArticle.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        status = sourceArticle.getStatus();
        link = sourceArticle.getLink();
    }

    /**
     * Convert this object into Model's object
     * @return Model's object
     * @throws IllegalValueException if data constraints are violated
     */
    public Article toModelType() throws IllegalValueException {
        if (title == null) {
            throw new IllegalValueException("The title is missing");
        }
        if (status == null) {
            throw new IllegalValueException("The status is missing");
        }

        final List<Tag> articleTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            articleTags.add(tag.toModelType());
        }
        final List<Author> articleAuthors = new ArrayList<>();
        for (JsonAdaptedAuthor author : authors) {
            articleAuthors.add(author.toModelType());
        }
        final List<Source> articleSources = new ArrayList<>();
        for (JsonAdaptedSource source : sources) {
            articleSources.add(source.toModelType());
        }

        final Set<Author> modelAuthors = new HashSet<>(articleAuthors);

        final Set<Source> modelSources = new HashSet<>(articleSources);

        final Set<Tag> modelTags = new HashSet<>(articleTags);

        return new Article(title, modelAuthors, publicationDate, modelSources, modelTags, status, link);
    }
}
