package seedu.address.model.article;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalArticles.FOUR;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class ArticleMatchesTagPredicateTest {
    //EP valid tag
    @Test
    public void newPredicate_validTag_noError() {
        Tag tag = new Tag("tag");
        ArticleMatchesTagPredicate predicate = new ArticleMatchesTagPredicate(tag);
    }

    //EP invalid tag null
    @Test
    public void newPredicate_invalidTagNull_assertionError() {
        assertThrows(AssertionError.class, () -> new ArticleMatchesTagPredicate(null));
    }
    //EP test when tags match
    @Test
    public void test_tagMatches_returnTrue() {
        Tag tag = new Tag("Fiction");
        ArticleMatchesTagPredicate predicate = new ArticleMatchesTagPredicate(tag);
        assert(predicate.test(FOUR));
    }
    //EP test when tags don't match
    @Test
    public void test_tagMismatch_returnFalse() {
        Tag tag = new Tag("Function");
        ArticleMatchesTagPredicate predicate = new ArticleMatchesTagPredicate(tag);
        assert(!predicate.test(FOUR));
    }
}
