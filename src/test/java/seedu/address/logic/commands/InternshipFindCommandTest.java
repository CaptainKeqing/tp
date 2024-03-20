package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.InternshipMessages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.ALICE_MICROSOFT;
import static seedu.address.testutil.TypicalInternships.CARL_OPENAI;
import static seedu.address.testutil.TypicalInternships.getTypicalInternshipData;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.InternshipModel;
import seedu.address.model.InternshipModelManager;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.internship.CompanyNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the InternshipModel) for {@code InternshipFindCommand}.
 */
public class InternshipFindCommandTest {
    private InternshipModel model = new InternshipModelManager(getTypicalInternshipData(), new InternshipUserPrefs());
    private InternshipModel expectedModel = new InternshipModelManager(getTypicalInternshipData(),
            new InternshipUserPrefs());

    @Test
    public void equals() {
        CompanyNameContainsKeywordsPredicate firstPredicate =
                new CompanyNameContainsKeywordsPredicate(Collections.singletonList("first"));
        CompanyNameContainsKeywordsPredicate secondPredicate =
                new CompanyNameContainsKeywordsPredicate(Collections.singletonList("second"));

        InternshipFindCommand findFirstCommand = new InternshipFindCommand(firstPredicate);
        InternshipFindCommand findSecondCommand = new InternshipFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        InternshipFindCommand findFirstCommandCopy = new InternshipFindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different internship -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, 0);
        CompanyNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        InternshipFindCommand command = new InternshipFindCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredInternshipList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, 2);
        CompanyNameContainsKeywordsPredicate predicate = preparePredicate("microsoft openai");
        InternshipFindCommand command = new InternshipFindCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE_MICROSOFT, CARL_OPENAI), model.getFilteredInternshipList());
    }

    @Test
    public void toStringMethod() {
        CompanyNameContainsKeywordsPredicate predicate =
                new CompanyNameContainsKeywordsPredicate(Arrays.asList("keyword"));
        InternshipFindCommand findCommand = new InternshipFindCommand(predicate);
        String expected = InternshipFindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code CompanyNameContainsKeywordsPredicate}.
     */
    private CompanyNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new CompanyNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
