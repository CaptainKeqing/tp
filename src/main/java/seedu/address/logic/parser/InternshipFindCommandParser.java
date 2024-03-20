package seedu.address.logic.parser;

import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.InternshipFindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.CompanyNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new InternshipFindCommand object
 */
public class InternshipFindCommandParser implements InternshipParser<InternshipFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the InternshipFindCommand
     * and returns a InternshipFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InternshipFindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, InternshipFindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new InternshipFindCommand(new CompanyNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
