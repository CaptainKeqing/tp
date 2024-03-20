package seedu.address.logic.parser;

import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.InternshipCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.InternshipCommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.InternshipFindCommand;
import seedu.address.model.internship.CompanyNameContainsKeywordsPredicate;

public class InternshipFindCommandParserTest {

    private InternshipFindCommandParser parser = new InternshipFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, InternshipFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        InternshipFindCommand expectedFindCommand =
                new InternshipFindCommand(new CompanyNameContainsKeywordsPredicate(
                        Arrays.asList("Microsoft", "Google")));

        assertParseSuccess(parser, "Microsoft Google", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Microsoft \n \t Google  \t", expectedFindCommand);
    }
}
