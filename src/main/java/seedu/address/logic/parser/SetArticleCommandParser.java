package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.stream.Stream;

import seedu.address.logic.commands.articlecommands.SetArticleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a SetArticleCommand object
 */
public class SetArticleCommandParser implements Parser<SetArticleCommand> {

    @Override
    public SetArticleCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_STATUS, PREFIX_START, PREFIX_END);
        if (!arePrefixesPresent(argMultimap, PREFIX_STATUS, PREFIX_START, PREFIX_END)) {
            throw new ParseException("The set command does not follow the correct format");
        }
        String status = argMultimap.getValue(PREFIX_STATUS).get();
        String start = argMultimap.getValue(PREFIX_START).get();
        String end = argMultimap.getValue(PREFIX_END).get();
        return new SetArticleCommand(status, start, end);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
