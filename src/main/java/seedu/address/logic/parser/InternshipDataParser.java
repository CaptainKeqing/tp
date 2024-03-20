package seedu.address.logic.parser;

import static seedu.address.logic.InternshipMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.InternshipMessages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.InternshipAddCommand;
import seedu.address.logic.commands.InternshipClearCommand;
import seedu.address.logic.commands.InternshipCommand;
import seedu.address.logic.commands.InternshipDeleteCommand;
import seedu.address.logic.commands.InternshipEditCommand;
import seedu.address.logic.commands.InternshipExitCommand;
import seedu.address.logic.commands.InternshipFindCommand;
import seedu.address.logic.commands.InternshipHelpCommand;
import seedu.address.logic.commands.InternshipListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class InternshipDataParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(InternshipDataParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public InternshipCommand parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InternshipHelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {

        case InternshipAddCommand.COMMAND_WORD:
            return new InternshipAddCommandParser().parse(arguments);

        case InternshipEditCommand.COMMAND_WORD:
            return new InternshipEditCommandParser().parse(arguments);

        case InternshipDeleteCommand.COMMAND_WORD:
            return new InternshipDeleteCommandParser().parse(arguments);

        case InternshipClearCommand.COMMAND_WORD:
            return new InternshipClearCommand();

        case InternshipFindCommand.COMMAND_WORD:
            return new InternshipFindCommandParser().parse(arguments);

        case InternshipListCommand.COMMAND_WORD:
            return new InternshipListCommand();

        case InternshipExitCommand.COMMAND_WORD:
            return new InternshipExitCommand();

        case InternshipHelpCommand.COMMAND_WORD:
            return new InternshipHelpCommand();

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
