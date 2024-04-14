package seedu.address.logic.commands.articlecommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ARTICLES;

import java.util.function.Predicate;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.article.Article;
import seedu.address.model.article.ArticleMatchesStatusPredicate;
import seedu.address.model.article.ArticleMatchesTagPredicate;
import seedu.address.model.article.ArticleMatchesTimePeriodPredicate;
import seedu.address.model.article.PublicationDate;
import seedu.address.model.article.exceptions.InvalidDatesException;
import seedu.address.model.article.exceptions.InvalidStatusException;
import seedu.address.model.tag.Tag;

/**
 * Use to set filter for Articles
 */
public class FilterArticleCommand extends ArticleCommand {
    public static final String COMMAND_WORD = "filter";

    public static final String COMMAND_PREFIX = "-a";

    public static final String MESSAGE_SUCCESS = "Filter online\nUse " + RemoveArticleFilterCommand.COMMAND_WORD
        + " -a to display all articles again";
    private Predicate<Article> finalPredicate;

    /**
     * Constructs a FilterArticleCommand object.
     * @param status The status to be filtered by.
     */
    public FilterArticleCommand(String status, Tag tag, PublicationDate start,
                                PublicationDate end) throws ParseException {
        try {
            finalPredicate = new ArticleMatchesStatusPredicate(status);
        } catch (InvalidStatusException e) {
            finalPredicate = PREDICATE_SHOW_ALL_ARTICLES;
        }
        try {
            Predicate<Article> timePredicate = new ArticleMatchesTimePeriodPredicate(start, end);
            finalPredicate = finalPredicate.and(timePredicate);
        } catch (InvalidDatesException e) {
            throw new ParseException(e.getMessage());
        }
        if (tag instanceof Tag) {
            Predicate<Article> tagPredicate = new ArticleMatchesTagPredicate(tag);
            finalPredicate = finalPredicate.and(tagPredicate);
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
