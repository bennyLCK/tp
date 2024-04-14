package seedu.address.storage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalArticles.ONCE;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.article.Article;

public class JsonAdaptedArticleTest {
    private static final String VALID_TITLE = ONCE.getTitle().toString();
    private static final List<JsonAdaptedAuthor> VALID_AUTHORS = ONCE.getAuthors().stream()
            .map(JsonAdaptedAuthor::new).collect(Collectors.toList());
    private static final String VALID_DATE = ONCE.getPublicationDateAsString();
    private static final List<JsonAdaptedSource> VALID_SOURCES = ONCE.getSources().stream()
            .map(JsonAdaptedSource::new).collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = ONCE.getTags().stream()
            .map(JsonAdaptedTag::new).collect(Collectors.toList());
    private static final List<JsonAdaptedOutlet> VALID_OUTLETS = ONCE.getOutlets().stream()
            .map(JsonAdaptedOutlet::new).collect(Collectors.toList());
    private static final Article.Status VALID_STATUS = ONCE.getStatus();
    private static final String VALID_LINK = ONCE.getLink().toString();
    @Test
    public void toModelType_validArticleDetails_returnArticle() throws IllegalValueException {
        JsonAdaptedArticle article = new JsonAdaptedArticle(ONCE);
        assertEquals(ONCE, article.toModelType());
    }
    @Test
    public void toModelType_invalidName_returnException() {
        JsonAdaptedArticle article = new JsonAdaptedArticle(null, VALID_AUTHORS, VALID_SOURCES, VALID_TAGS,
                VALID_OUTLETS, VALID_DATE, VALID_STATUS, VALID_LINK);
        assertThrows(IllegalValueException.class, article::toModelType);
    }
    //Test exception throwing when status is null.
    @Test
    public void toModelType_invalidStatus_returnException() {
        JsonAdaptedArticle article = new JsonAdaptedArticle(VALID_TITLE, VALID_AUTHORS, VALID_SOURCES, VALID_TAGS,
                VALID_OUTLETS, VALID_DATE, null, VALID_LINK);
        assertThrows(IllegalValueException.class, article::toModelType);
    }
    @Test
    public void toModelType_invalidDate_returnException() {
        JsonAdaptedArticle article = new JsonAdaptedArticle(VALID_TITLE, VALID_AUTHORS, VALID_SOURCES,
                VALID_TAGS, VALID_OUTLETS, "December", VALID_STATUS, VALID_LINK);
        assertThrows(IllegalValueException.class, article::toModelType);
    }
    @Test
    public void toModelType_invalidDateNull_returnException() {
        JsonAdaptedArticle article = new JsonAdaptedArticle(VALID_TITLE, VALID_AUTHORS, VALID_SOURCES,
                VALID_TAGS, VALID_OUTLETS, null, VALID_STATUS, VALID_LINK);
        assertThrows(NullPointerException.class, article::toModelType);
    }
}
