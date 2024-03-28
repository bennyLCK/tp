package seedu.address.logic.parser;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_STATUS);
        if (!arePrefixesPresent(argMultimap, PREFIX_STATUS) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException("The set argument does not follow the correct format");
        }
        String status = argMultimap.getValue(PREFIX_STATUS).get();
        return new SetArticleCommand(status);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
