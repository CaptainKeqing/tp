package seedu.address.model.internship;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Internship}'s {@code description} matches any of the keywords given.
 */
public class DescriptionContainsKeywordsPredicate implements Predicate<Internship> {
    private final List<String> keywords;

    public DescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Internship internship) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(internship.getDescription().description,
                        keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DescriptionContainsKeywordsPredicate)) {
            return false;
        }

        DescriptionContainsKeywordsPredicate otherDescriptionContainsKeywordsPredicate =
                (DescriptionContainsKeywordsPredicate) other;
        return keywords.equals(otherDescriptionContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
