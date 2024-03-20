package seedu.address.logic.parser;

import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.InternshipFindCommand.MODE_WITHALL;
import static seedu.address.logic.commands.InternshipFindCommand.MODE_WITHANY;
import static seedu.address.logic.parser.InternshipCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.InternshipCommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.InternshipFindCommand;
import seedu.address.model.internship.CompanyNameContainsKeywordsPredicate;
import seedu.address.model.internship.ContactNameContainsKeywordsPredicate;
import seedu.address.model.internship.DescriptionContainsKeywordsPredicate;
import seedu.address.model.internship.LocationContainsKeywordsPredicate;
import seedu.address.model.internship.RoleContainsKeywordsPredicate;
import seedu.address.model.internship.StatusContainsKeywordsPredicate;

public class InternshipFindCommandParserTest {

    private InternshipFindCommandParser parser = new InternshipFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, InternshipFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidMode_throwsParseException() {
        assertParseFailure(parser, "withsome /com Microsoft Google",
                String.format(InternshipFindCommand.INVALID_MODE_SPECIFIED));
    }

    @Test
    public void parse_noMode_throwsParseException() {
        assertParseFailure(parser, "/com Microsoft Google",
                String.format(InternshipFindCommand.INVALID_MODE_SPECIFIED));
    }

    @Test
    public void parse_noSearchKey_throwsParseException() {
        assertParseFailure(parser, MODE_WITHALL,
                String.format(InternshipFindCommand.NO_SEARCH_KEY_SPECIFIED));
    }

    @Test
    public void parseCompanyName_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        InternshipFindCommand expectedFindCommand =
                new InternshipFindCommand(new CompanyNameContainsKeywordsPredicate(
                        Arrays.asList("Microsoft", "Google")));

        assertParseSuccess(parser, MODE_WITHALL + " /com Microsoft Google", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, MODE_WITHALL + " /com \n Microsoft \n \t Google  \t", expectedFindCommand);
    }

    @Test
    public void parseContactName_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        InternshipFindCommand expectedFindCommand =
                new InternshipFindCommand(new ContactNameContainsKeywordsPredicate(
                        Arrays.asList("Jane", "Mark")));

        assertParseSuccess(parser, MODE_WITHALL + " /poc Jane Mark", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, MODE_WITHALL + " /poc \n Jane \n \t Mark  \t", expectedFindCommand);
    }

    @Test
    public void parseLocation_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        InternshipFindCommand expectedFindCommand =
                new InternshipFindCommand(new LocationContainsKeywordsPredicate(
                        Arrays.asList("remote", "randomLocation")));

        assertParseSuccess(parser, MODE_WITHALL + " /loc remote randomLocation", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, MODE_WITHALL + " /loc \n remote \n \t randomLocation  \t", expectedFindCommand);
    }

    @Test
    public void parseStatus_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        InternshipFindCommand expectedFindCommand =
                new InternshipFindCommand(new StatusContainsKeywordsPredicate(
                        Arrays.asList("accepted", "rejected", "randomStatus")));

        assertParseSuccess(parser, MODE_WITHALL + " /status accepted rejected randomStatus", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, MODE_WITHALL + " /status \n accepted \n rejected\t randomStatus  \t", expectedFindCommand);
    }

    @Test
    public void parseDescription_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        InternshipFindCommand expectedFindCommand =
                new InternshipFindCommand(new DescriptionContainsKeywordsPredicate(
                        Arrays.asList("hello", "high", "salary", "randomDescription")));

        assertParseSuccess(parser, MODE_WITHALL + " /desc hello high salary randomDescription", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, MODE_WITHALL + " /desc hello\n high \n salary\t randomDescription  \t", expectedFindCommand);
    }

    @Test
    public void parseRole_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        InternshipFindCommand expectedFindCommand =
                new InternshipFindCommand(new RoleContainsKeywordsPredicate(
                        Arrays.asList("Coffee", "Boy", "floor", "cleaner")));

        assertParseSuccess(parser, MODE_WITHANY + " /role Coffee Boy floor cleaner", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, MODE_WITHANY + " /role Coffee\n Boy \n floor\t cleaner  \t", expectedFindCommand);
    }
}
