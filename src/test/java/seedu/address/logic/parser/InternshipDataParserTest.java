package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.InternshipMessages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.InternshipTypicalIndexes.INDEX_FIRST_INTERNSHIP;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.InternshipAddCommand;
import seedu.address.logic.commands.InternshipClearCommand;
import seedu.address.logic.commands.InternshipDeleteCommand;
import seedu.address.logic.commands.InternshipEditCommand;
import seedu.address.logic.commands.InternshipEditCommand.EditInternshipDescriptor;
import seedu.address.logic.commands.InternshipExitCommand;
import seedu.address.logic.commands.InternshipFindCommand;
import seedu.address.logic.commands.InternshipHelpCommand;
import seedu.address.logic.commands.InternshipListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.CompanyNameContainsKeywordsPredicate;
import seedu.address.model.internship.Internship;
import seedu.address.testutil.EditInternshipDescriptorBuilder;
import seedu.address.testutil.InternshipBuilder;
import seedu.address.testutil.InternshipUtil;

public class InternshipDataParserTest {

    private final InternshipDataParser parser = new InternshipDataParser();

    @Test
    public void parseCommand_add() throws Exception {
        Internship person = new InternshipBuilder().build();
        InternshipAddCommand command = (InternshipAddCommand) parser.parseCommand(InternshipUtil.getAddCommand(person));
        assertEquals(new InternshipAddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(InternshipClearCommand.COMMAND_WORD) instanceof InternshipClearCommand);
        assertTrue(parser.parseCommand(InternshipClearCommand.COMMAND_WORD + " 3") instanceof InternshipClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        InternshipDeleteCommand command = (InternshipDeleteCommand) parser.parseCommand(
                InternshipDeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_INTERNSHIP.getOneBased());
        assertEquals(new InternshipDeleteCommand(INDEX_FIRST_INTERNSHIP), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Internship person = new InternshipBuilder().build();
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder(person).build();
        InternshipEditCommand command = (InternshipEditCommand) parser.parseCommand(
                InternshipEditCommand.COMMAND_WORD + " " + INDEX_FIRST_INTERNSHIP.getOneBased()
                        + " " + InternshipUtil.getEditInternshipDescriptorDetails(descriptor));
        assertEquals(new InternshipEditCommand(INDEX_FIRST_INTERNSHIP, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(InternshipExitCommand.COMMAND_WORD) instanceof InternshipExitCommand);
        assertTrue(parser.parseCommand(InternshipExitCommand.COMMAND_WORD + " 3") instanceof InternshipExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        InternshipFindCommand command = (InternshipFindCommand) parser.parseCommand(
                InternshipFindCommand.COMMAND_WORD + " " + String.join(" ", keywords));
        assertEquals(new InternshipFindCommand(new CompanyNameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(InternshipHelpCommand.COMMAND_WORD) instanceof InternshipHelpCommand);
        assertTrue(parser.parseCommand(InternshipHelpCommand.COMMAND_WORD + " 3") instanceof InternshipHelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(InternshipListCommand.COMMAND_WORD) instanceof InternshipListCommand);
        assertTrue(parser.parseCommand(InternshipListCommand.COMMAND_WORD + " 3") instanceof InternshipListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                InternshipHelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
