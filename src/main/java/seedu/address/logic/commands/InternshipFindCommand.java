package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.InternshipMessages;
import seedu.address.model.InternshipModel;
import seedu.address.model.internship.Internship;

/**
 * Finds and lists all internships in internships list whose fields contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class InternshipFindCommand extends InternshipCommand {

    public static final String COMMAND_WORD = "find";

    public static final String MODE_WITHALL = "withall";

    public static final String MODE_WITHANY = "withany";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all internships whose fields, specified using "
            + "the prefix, contain all/any (depending on mode) of the specified keywords (case-insensitive) "
            + "and displays them as a list with index numbers. \n"
            + "Parameters: MODE ('withall' or 'withany', to specify if all keywords "
            + "must be present or just any keyword must be present) "
            + "[" + PREFIX_COMPANY + "COMPANY_NAME_KEYWORD [MORE_COMPANY_NAME_KEYWORDS] "
            + "[" + PREFIX_CONTACT_NAME + "CONTACT_NAME_KEYWORD [MORE_CONTACT_NAME_KEYWORDS] "
            + "[" + PREFIX_CONTACT_EMAIL + "CONTACT_EMAIL_KEYWORD [MORE_CONTACT_EMAIL_KEYWORDS] "
            + "[" + PREFIX_CONTACT_NUMBER + "CONTACT_NUMBER_KEYWORD [MORE_CONTACT_NUMBER_KEYWORDS] "
            + "[" + PREFIX_LOCATION + "LOCATION_KEYWORD [MORE_LOCATION_KEYWORDS] "
            + "[" + PREFIX_STATUS + "STATUS_KEYWORD [MORE_STATUS_KEYWORDS] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION_KEYWORD [MORE_DESCRIPTION_KEYWORDS] "
            + "[" + PREFIX_ROLE + "ROLE_KEYWORD [MORE_ROLE_KEYWORDS]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMPANY + "Tiktok [MORE_PREFIXES_AND_KEYWORDS]";
    private final Predicate<Internship> predicate;

    public InternshipFindCommand(Predicate<Internship> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(InternshipModel model) {
        requireNonNull(model);
        model.updateFilteredInternshipList(predicate);
        return new CommandResult(
                String.format(InternshipMessages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW,
                        model.getFilteredInternshipList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipFindCommand)) {
            return false;
        }

        InternshipFindCommand otherFindCommand = (InternshipFindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
