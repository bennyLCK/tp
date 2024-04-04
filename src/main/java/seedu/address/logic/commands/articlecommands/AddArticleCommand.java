package seedu.address.logic.commands.articlecommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OUTLET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PUBLICATION_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOURCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.article.Article;

/**
 * Adds an article to the article book.
 */
public class AddArticleCommand extends ArticleCommand {

    public static final String COMMAND_WORD = "add";

    public static final String COMMAND_PREFIX = "-a";

    // To be edited for use in test cases later on.
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_PREFIX
            + ": Adds an article to the article book.\n"
            + "Parameters: "
            + PREFIX_TITLE + "HEADLINE "
            + "[" + PREFIX_AUTHOR + "CONTRIBUTOR]... "
            + PREFIX_PUBLICATION_DATE + "DATE] "
            + "[" + PREFIX_SOURCE + "INTERVIEWEE]... "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_OUTLET + "OUTLET]... "
            + PREFIX_STATUS + "STATUS\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_PREFIX + " "
            + PREFIX_TITLE + "The Great Article "
            + PREFIX_AUTHOR + "John Doe "
            + PREFIX_PUBLICATION_DATE + "2021-10-10 "
            + PREFIX_SOURCE + "Jane Doe "
            + PREFIX_TAG + "friends "
            + PREFIX_OUTLET + "The Great Outlet "
            + PREFIX_STATUS + "DRAFT";

    public static final String MESSAGE_SUCCESS = "New article added: %1$s";
    public static final String MESSAGE_DUPLICATE_ARTICLE = "This article already exists in the article book";

    private final Article toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Article}
     */
    public AddArticleCommand(Article article) {
        requireNonNull(article);
        toAdd = article;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasArticle(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ARTICLE);
        }

        model.addArticle(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddArticleCommand)) {
            return false;
        }

        AddArticleCommand otherAddArticleCommand = (AddArticleCommand) other;
        return toAdd.equals(otherAddArticleCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
