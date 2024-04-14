package seedu.address.model.article;

import static java.util.Objects.requireNonNull;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Checks if article has matching tag
 */
public class ArticleMatchesTagPredicate implements Predicate<Article> {
    private Tag tag;

    /**
     * Constructs a predicate that returns true if tag matches.
     * @param tag The tag to be checked against
     */
    public ArticleMatchesTagPredicate(Tag tag) {
        //Ensures that tag is not null
        assert(tag instanceof Tag);
        this.tag = tag;
    }

    @Override
    public boolean test(Article article) {
        requireNonNull(article);
        Set<Tag> others = article.getTags();
        boolean isMatch = false;
        requireNonNull(tag);
        for (Tag other : others) {
            requireNonNull(other);
            if (other.equals(tag)) {
                isMatch = true;
            }
        }
        return isMatch;
    }
}
