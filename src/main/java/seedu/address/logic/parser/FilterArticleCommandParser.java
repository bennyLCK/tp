package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.address.logic.commands.articlecommands.FilterArticleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.article.PublicationDate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a FilterArticleCommand object
 */
public class FilterArticleCommandParser implements Parser<FilterArticleCommand> {

    @Override
    public FilterArticleCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_STATUS,
                PREFIX_TAG, PREFIX_START, PREFIX_END);
        if (!arePrefixesPresent(argMultimap, PREFIX_STATUS, PREFIX_TAG, PREFIX_START, PREFIX_END)) {
            throw new ParseException("Invalid command format!\nfilter: Applies a filter. "
                    + "Parameters: " + PREFIX_STATUS + "STATUS " + PREFIX_TAG + "TAG "
                    + PREFIX_START + "START DATE " + PREFIX_END + "END DATE"
                    + "\nExample: filter -a " + PREFIX_STATUS
                    + "DRAFT " + PREFIX_TAG + "Product Releases "
                    + PREFIX_START + "01-01-2001 " + PREFIX_END + "03-03-2023"
            );
        }
        String status = argMultimap.getValue(PREFIX_STATUS).get();
        String tagName = argMultimap.getValue(PREFIX_TAG).get();
        String start = argMultimap.getValue(PREFIX_START).get();
        String end = argMultimap.getValue(PREFIX_END).get();
        PublicationDate startDate;
        PublicationDate endDate;
        Tag tag = null;
        if (start.trim().equals("")) {
            startDate = new PublicationDate(LocalDateTime.MIN);
        } else {
            startDate = ParserUtil.parsePublicationDate(start.trim());
        }
        if (end.trim().equals("")) {
            endDate = new PublicationDate(LocalDateTime.MAX);
        } else {
            endDate = ParserUtil.parsePublicationDate(end.trim());
        }
        if (!tagName.trim().equals("")) {
            tag = ParserUtil.parseTag(tagName);
        }
        return new FilterArticleCommand(status, tag, startDate, endDate);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
