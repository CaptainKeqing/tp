package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.InternshipRemarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.Remark;

/**
 * Parses input arguments and creates a new InternshipRemarkCommand object
 */
public class InternshipRemarkCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the InternshipRemarkCommand
     * and returns an InternshipRemarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InternshipRemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REMARK);

        Index index;
        Remark remark;

        if (argMultimap.getPreamble().isEmpty() || argMultimap.getValue(PREFIX_REMARK).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InternshipRemarkCommand.MESSAGE_USAGE));
        }

        try {
            index = InternshipParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX, pe);
        }

        remark = InternshipParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get());

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_REMARK);

        return new InternshipRemarkCommand(index, remark);
    }
}
