package seedu.address.testutil;

import seedu.address.model.article.ArticleMatchesStatusPredicate;

/**
 * A utility class consisting of predicates to use in tests.
 */
public class TypicalPredicates {
    public static final ArticleMatchesStatusPredicate DRAFT = new ArticleMatchesStatusPredicate("DRAFT");
    public static final ArticleMatchesStatusPredicate PUBLISHED = new ArticleMatchesStatusPredicate("PUBLISHED");
    public static final ArticleMatchesStatusPredicate ARCHIVED = new ArticleMatchesStatusPredicate("ARCHIVED");
}
