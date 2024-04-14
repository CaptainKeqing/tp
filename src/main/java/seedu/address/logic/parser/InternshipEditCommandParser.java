package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.InternshipParserUtil.anyPrefixesPresent;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.InternshipEditCommand;
import seedu.address.logic.commands.InternshipEditCommand.EditInternshipDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new InternshipEditCommand object
 */
public class InternshipEditCommandParser implements InternshipParser<InternshipEditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the InternshipEditCommand
     * and returns an InternshipEditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InternshipEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY, PREFIX_CONTACT_NAME, PREFIX_CONTACT_EMAIL,
                        PREFIX_CONTACT_NUMBER, PREFIX_LOCATION, PREFIX_STATUS, PREFIX_DESCRIPTION, PREFIX_ROLE,
                        PREFIX_REMARK);

        Index index;

        if (!anyPrefixesPresent(argMultimap, PREFIX_COMPANY, PREFIX_DESCRIPTION, PREFIX_STATUS, PREFIX_CONTACT_NAME,
                PREFIX_CONTACT_EMAIL, PREFIX_CONTACT_NUMBER, PREFIX_LOCATION, PREFIX_ROLE, PREFIX_REMARK)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InternshipEditCommand.MESSAGE_USAGE));
        }

        try {
            index = InternshipParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COMPANY, PREFIX_CONTACT_NAME, PREFIX_CONTACT_EMAIL,
                PREFIX_CONTACT_NUMBER, PREFIX_LOCATION, PREFIX_STATUS, PREFIX_DESCRIPTION, PREFIX_ROLE, PREFIX_REMARK);

        EditInternshipDescriptor editInternshipDescriptor = new EditInternshipDescriptor();

        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            editInternshipDescriptor.setCompanyName(InternshipParserUtil.parseCompanyName(argMultimap
                    .getValue(PREFIX_COMPANY).get()));
        }
        if (argMultimap.getValue(PREFIX_CONTACT_NAME).isPresent()) {
            editInternshipDescriptor.setContactName(InternshipParserUtil.parseContactName(argMultimap
                    .getValue(PREFIX_CONTACT_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_CONTACT_NUMBER).isPresent()) {
            editInternshipDescriptor.setContactNumber(InternshipParserUtil.parseContactNumber(argMultimap
                    .getValue(PREFIX_CONTACT_NUMBER).get()));
        }
        if (argMultimap.getValue(PREFIX_CONTACT_EMAIL).isPresent()) {
            editInternshipDescriptor.setContactEmail(InternshipParserUtil.parseContactEmail(argMultimap
                    .getValue(PREFIX_CONTACT_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editInternshipDescriptor.setLocation(InternshipParserUtil.parseOptionalLocation(argMultimap
                    .getValue(PREFIX_LOCATION)));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editInternshipDescriptor.setApplicationStatus(InternshipParserUtil.parseStatus(argMultimap
                    .getValue(PREFIX_STATUS).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editInternshipDescriptor.setDescription(InternshipParserUtil.parseDescription(argMultimap
                    .getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            editInternshipDescriptor.setRole(InternshipParserUtil.parseOptionalRole(argMultimap
                    .getValue(PREFIX_ROLE)));
        }
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            editInternshipDescriptor.setRemark(InternshipParserUtil.parseRemark(argMultimap
                    .getValue(PREFIX_REMARK).get()));
        }

        if (!editInternshipDescriptor.isAnyFieldEdited()) {
            throw new ParseException(InternshipEditCommand.MESSAGE_NOT_EDITED);
        }

        return new InternshipEditCommand(index, editInternshipDescriptor);
    }
}
