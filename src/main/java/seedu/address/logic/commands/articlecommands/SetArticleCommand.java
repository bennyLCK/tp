package seedu.address.logic.commands.articlecommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ARTICLES;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Use to set filter for Articles
 */
public class SetArticleCommand extends ArticleCommand {
    public static final String COMMAND_WORD = "set";

    public static final String COMMAND_PREFIX = "-a";

    public static final String MESSAGE_SUCCESS = "Filter Updated";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredArticleList(PREDICATE_SHOW_ALL_ARTICLES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
