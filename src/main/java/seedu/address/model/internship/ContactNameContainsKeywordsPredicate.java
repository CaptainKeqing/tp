package seedu.address.model.internship;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Internship}'s {@code contactName} matches any of the keywords given.
 */
public class ContactNameContainsKeywordsPredicate implements Predicate<Internship> {
    private final List<String> keywords;

    public ContactNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Internship internship) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(internship.getContactName().contactName,
                        keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContactNameContainsKeywordsPredicate)) {
            return false;
        }

        ContactNameContainsKeywordsPredicate otherContactNameContainsKeywordsPredicate =
                (ContactNameContainsKeywordsPredicate) other;
        return keywords.equals(otherContactNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
