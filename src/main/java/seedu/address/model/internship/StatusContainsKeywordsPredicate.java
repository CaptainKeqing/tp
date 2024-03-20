package seedu.address.model.internship;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Internship}'s {@code status} matches any of the keywords given.
 */
public class StatusContainsKeywordsPredicate implements Predicate<Internship> {
    private final List<String> keywords;

    public StatusContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Internship internship) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(internship.getApplicationStatus().toString(),
                        keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StatusContainsKeywordsPredicate)) {
            return false;
        }

        StatusContainsKeywordsPredicate otherStatusContainsKeywordsPredicate =
                (StatusContainsKeywordsPredicate) other;
        return keywords.equals(otherStatusContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
