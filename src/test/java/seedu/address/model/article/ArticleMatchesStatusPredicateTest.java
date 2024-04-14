package seedu.address.model.article;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalArticles.FIVE;
import static seedu.address.testutil.TypicalArticles.FOUR;
import static seedu.address.testutil.TypicalPredicates.DRAFT;
import static seedu.address.testutil.TypicalPredicates.PUBLISHED;

import org.junit.jupiter.api.Test;

import seedu.address.model.article.exceptions.InvalidStatusException;

public class ArticleMatchesStatusPredicateTest {
    //EP valid status in capital letters
    @Test
    public void newPredicate_validStatus_noError() {
        ArticleMatchesStatusPredicate predicate = new ArticleMatchesStatusPredicate("PUBLISHED");
        //assert(predicate.equals(PUBLISHED));
    }
    //EP valid status with no capitalization
    @Test
    public void newPredicate_validStatusNoCap_noError() {
        ArticleMatchesStatusPredicate predicate = new ArticleMatchesStatusPredicate("draft");
    }

    //EP invalid status null
    @Test
    public void newPredicate_invalidStatusNull_error() {
        assertThrows(NullPointerException.class, () -> new ArticleMatchesStatusPredicate(null));
    }
    //EP invalid status non-null
    @Test
    public void newPredicate_invalidStatusNonNull_error() {
        assertThrows(InvalidStatusException.class, () -> new ArticleMatchesStatusPredicate("Qwerty"));
    }
    //EP test status match
    @Test
    public void test_statusMatch_returnTrue() {
        ArticleMatchesStatusPredicate predicate = DRAFT;
        Article article = FIVE;
        assert(predicate.test(article));
    }
    //EP test status do not match
    @Test
    public void test_statusMismatch_returnFalse() {
        ArticleMatchesStatusPredicate predicate = PUBLISHED;
        Article article = FOUR;
        assert(!(predicate.test(article)));
    }
}
