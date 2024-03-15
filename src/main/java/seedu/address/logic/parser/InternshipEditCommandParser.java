package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.InternshipEditCommand;
import seedu.address.logic.commands.InternshipEditCommand.EditInternshipDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class InternshipEditCommandParser implements InternshipParser<InternshipEditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InternshipEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY, PREFIX_CONTACT_NAME, PREFIX_CONTACT_EMAIL,
                        PREFIX_CONTACT_NUMBER, PREFIX_LOCATION, PREFIX_STATUS, PREFIX_DESCRIPTION, PREFIX_ROLE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, InternshipEditCommand.MESSAGE_USAGE),
                    pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COMPANY, PREFIX_CONTACT_NAME, PREFIX_CONTACT_EMAIL,
                PREFIX_CONTACT_NUMBER, PREFIX_LOCATION, PREFIX_STATUS, PREFIX_DESCRIPTION, PREFIX_ROLE);

        EditInternshipDescriptor editPersonDescriptor = new EditInternshipDescriptor();

        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            editPersonDescriptor.setCompanyName(InternshipParserUtil.parseCompanyName(argMultimap
                    .getValue(PREFIX_COMPANY).get()));
        }
        if (argMultimap.getValue(PREFIX_CONTACT_NAME).isPresent()) {
            editPersonDescriptor.setContactName(InternshipParserUtil.parseContactName(argMultimap
                    .getValue(PREFIX_CONTACT_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_CONTACT_NUMBER).isPresent()) {
            editPersonDescriptor.setContactNumber(InternshipParserUtil.parseContactNumber(argMultimap
                    .getValue(PREFIX_CONTACT_NUMBER).get()));
        }
        if (argMultimap.getValue(PREFIX_CONTACT_EMAIL).isPresent()) {
            editPersonDescriptor.setContactEmail(InternshipParserUtil.parseContactEmail(argMultimap
                    .getValue(PREFIX_CONTACT_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editPersonDescriptor.setLocation(InternshipParserUtil.parseLocation(argMultimap
                    .getValue(PREFIX_LOCATION).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editPersonDescriptor.setApplicationStatus(InternshipParserUtil.parseStatus(argMultimap
                    .getValue(PREFIX_STATUS).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editPersonDescriptor.setDescription(InternshipParserUtil.parseDescription(argMultimap
                    .getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            editPersonDescriptor.setRole(InternshipParserUtil.parseRole(argMultimap
                    .getValue(PREFIX_ROLE).get()));
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(InternshipEditCommand.MESSAGE_NOT_EDITED);
        }

        return new InternshipEditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
