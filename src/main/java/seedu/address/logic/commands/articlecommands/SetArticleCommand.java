package seedu.address.logic.commands.articlecommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ARTICLES;

import java.util.function.Predicate;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.article.Article;
import seedu.address.model.article.ArticleMatchesStatusPredicate;
import seedu.address.model.article.exceptions.InvalidStatusException;

/**
 * Use to set filter for Articles
 */
public class SetArticleCommand extends ArticleCommand {
    public static final String COMMAND_WORD = "set";

    public static final String COMMAND_PREFIX = "-a";

    public static final String MESSAGE_SUCCESS = "Filter Updated";
    private Predicate<Article> finalPredicate;

    /**
     * Constructs a SetArticleCommand object.
     * @param status The status to be filtered by.
     */
    public SetArticleCommand(String status) {
        try {
            finalPredicate = new ArticleMatchesStatusPredicate(status);
        } catch (InvalidStatusException e) {
            finalPredicate = PREDICATE_SHOW_ALL_ARTICLES;
        }
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.getFilter().updateFilter(finalPredicate);
        model.updateFilteredArticleList(finalPredicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
