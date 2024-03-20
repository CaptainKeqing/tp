package seedu.address.model.internship;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Internship}'s {@code contactNumber} matches any of the keywords given.
 */
public class ContactNumberContainsKeywordsPredicate implements Predicate<Internship> {
    private final List<String> keywords;

    public ContactNumberContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Internship internship) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(internship.getContactNumber().value,
                        keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContactNumberContainsKeywordsPredicate)) {
            return false;
        }

        ContactNumberContainsKeywordsPredicate otherContactNumberContainsKeywordsPredicate =
                (ContactNumberContainsKeywordsPredicate) other;
        return keywords.equals(otherContactNumberContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
