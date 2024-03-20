package seedu.address.model.internship;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Internship}'s {@code location} matches any of the keywords given.
 */
public class LocationContainsKeywordsPredicate implements Predicate<Internship> {
    private final List<String> keywords;

    public LocationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Internship internship) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(internship.getLocation().toString(),
                        keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LocationContainsKeywordsPredicate)) {
            return false;
        }

        LocationContainsKeywordsPredicate otherLocationContainsKeywordsPredicate =
                (LocationContainsKeywordsPredicate) other;
        return keywords.equals(otherLocationContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
