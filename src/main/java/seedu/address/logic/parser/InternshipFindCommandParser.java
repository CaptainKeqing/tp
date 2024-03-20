package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.InternshipFindCommand.MODE_WITHALL;
import static seedu.address.logic.commands.InternshipFindCommand.MODE_WITHANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.InternshipFindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.CompanyNameContainsKeywordsPredicate;
import seedu.address.model.internship.ContactNameContainsKeywordsPredicate;
import seedu.address.model.internship.DescriptionContainsKeywordsPredicate;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.LocationContainsKeywordsPredicate;
import seedu.address.model.internship.RoleContainsKeywordsPredicate;
import seedu.address.model.internship.StatusContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new InternshipFindCommand object
 */
public class InternshipFindCommandParser implements InternshipParser<InternshipFindCommand> {
    private static final Prefix[] supportedPrefixes = {PREFIX_COMPANY, PREFIX_CONTACT_NAME, PREFIX_LOCATION,
        PREFIX_STATUS, PREFIX_DESCRIPTION, PREFIX_ROLE};

    /**
     * Parses the given {@code String} of arguments in the context of the InternshipFindCommand
     * and returns a InternshipFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InternshipFindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, InternshipFindCommandParser.supportedPrefixes);

        String mode = argMultimap.getPreamble().trim();
        if (!mode.equals(MODE_WITHALL) && !mode.equals(MODE_WITHANY)) {
            throw new ParseException(InternshipFindCommand.INVALID_MODE_SPECIFIED);
        }

        if (!anyPrefixesPresent(argMultimap, InternshipFindCommandParser.supportedPrefixes)) {
            throw new ParseException(InternshipFindCommand.NO_SEARCH_KEY_SPECIFIED);
        }

        if (mode.equals(MODE_WITHALL)) {
            return new InternshipFindCommand(createPredicateWithAll(argMultimap));
        } else {
            return new InternshipFindCommand(createPredicateWithAny(argMultimap));
        }
    }

    /**
     * Creates a predicate that combines all the predicates specified by the user.
     * For each prefix that is present, a predicate is created and combined with the rest using the AND operator.
     *
     * @param argMultimap map of prefixes and their search keywords
     * @return the result of combining all the predicates with AND.
     */
    private Predicate<Internship> createPredicateWithAll(ArgumentMultimap argMultimap) {
        return createPredicates(argMultimap).stream().reduce(Predicate::and).orElse(x -> true);
    }

    /**
     * Creates a predicate that combines all the predicates specified by the user.
     * For each prefix that is present, a predicate is created and combined with the rest using the OR operator.
     *
     * @param argMultimap map of prefixes and their search keywords
     * @return the result of combining all the predicates with OR.
     */
    private Predicate<Internship> createPredicateWithAny(ArgumentMultimap argMultimap) {
        return createPredicates(argMultimap).stream().reduce(Predicate::or).orElse(x -> false);
    }

    /**
     * Returns true if any of the prefixes contains non-empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * @param argMultimap map of prefixes and their search keywords
     * @return a list of predicates that correspond to the prefixes present in the map
     */
    protected List<Predicate<Internship>> createPredicates(ArgumentMultimap argMultimap) {
        List<Predicate<Internship>> predicates = new ArrayList<>();
        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            predicates.add(createCompanyNamePredicate(argMultimap.getValue(PREFIX_COMPANY).get()));
        }
        if (argMultimap.getValue(PREFIX_CONTACT_NAME).isPresent()) {
            predicates.add(createContactNamePredicate(argMultimap.getValue(PREFIX_CONTACT_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            predicates.add(createLocationPredicate(argMultimap.getValue(PREFIX_LOCATION).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            predicates.add(createStatusPredicate(argMultimap.getValue(PREFIX_STATUS).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            predicates.add(createDescriptionPredicate(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            predicates.add(createRolePredicate(argMultimap.getValue(PREFIX_ROLE).get()));
        }

        return predicates;
    }

    /**
     * Creates a predicate that checks if an internship's company name contains any of the keywords.
     *
     * @param companyNameKeywords the company name keywords to search for
     * @return a predicate that checks if an internship's company name contains any of the keywords
     */
    protected CompanyNameContainsKeywordsPredicate createCompanyNamePredicate(String companyNameKeywords) {
        return new CompanyNameContainsKeywordsPredicate(getKeywords(companyNameKeywords));
    }

    /**
     * Creates a predicate that checks if an internship's contact name contains any of the keywords.
     */
    protected ContactNameContainsKeywordsPredicate createContactNamePredicate(String contactNameKeywords) {
        return new ContactNameContainsKeywordsPredicate(getKeywords(contactNameKeywords));
    }

    /**
     * Creates a predicate that checks if an internship's location contains any of the keywords.
     */
    protected LocationContainsKeywordsPredicate createLocationPredicate(String locationKeywords) {
        return new LocationContainsKeywordsPredicate(getKeywords(locationKeywords));
    }

    /**
     * Creates a predicate that checks if an internship's contact name contains any of the keywords.
     */
    protected StatusContainsKeywordsPredicate createStatusPredicate(String statusKeywords) {
        return new StatusContainsKeywordsPredicate(getKeywords(statusKeywords));
    }

    /**
     * Creates a predicate that checks if an internship's description contains any of the keywords.
     */
    protected DescriptionContainsKeywordsPredicate createDescriptionPredicate(String descriptionKeywords) {
        return new DescriptionContainsKeywordsPredicate(getKeywords(descriptionKeywords));
    }

    /**
     * Creates a predicate that checks if an internship's role contains any of the keywords.
     */
    protected RoleContainsKeywordsPredicate createRolePredicate(String roleKeywords) {
        return new RoleContainsKeywordsPredicate(getKeywords(roleKeywords));
    }

    protected List<String> getKeywords(String keywords) {
        return Arrays.asList(keywords.split("\\s+"));
    }
}
