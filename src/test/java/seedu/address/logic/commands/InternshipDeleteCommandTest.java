package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.InternshipCommandTestUtil.showInternshipAtIndex;
import static seedu.address.testutil.InternshipTypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.address.testutil.InternshipTypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.address.testutil.TypicalInternships.getTypicalInternshipData;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.InternshipMessages;
import seedu.address.model.InternshipModel;
import seedu.address.model.InternshipModelManager;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.internship.Internship;

/**
 * Contains integration tests (interaction with the InternshipModel) and unit tests for {@code InternshipDeleteCommand}.
 */
public class InternshipDeleteCommandTest {

    private InternshipModel model = new InternshipModelManager(getTypicalInternshipData(), new InternshipUserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Internship internshipToDelete = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        InternshipDeleteCommand deleteCommand = new InternshipDeleteCommand(INDEX_FIRST_INTERNSHIP);

        String expectedMessage = String.format(InternshipDeleteCommand.MESSAGE_DELETE_INTERNSHIP_SUCCESS,
                InternshipMessages.format(internshipToDelete));

        InternshipModelManager expectedModel = new InternshipModelManager(model.getInternshipData(),
                new InternshipUserPrefs());
        expectedModel.deleteInternship(internshipToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);
        InternshipDeleteCommand deleteCommand = new InternshipDeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, InternshipMessages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Internship internshipToDelete = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        InternshipDeleteCommand deleteCommand = new InternshipDeleteCommand(INDEX_FIRST_INTERNSHIP);

        String expectedMessage = String.format(InternshipDeleteCommand.MESSAGE_DELETE_INTERNSHIP_SUCCESS,
                InternshipMessages.format(internshipToDelete));

        InternshipModel expectedModel = new InternshipModelManager(model.getInternshipData(),
                new InternshipUserPrefs());
        expectedModel.deleteInternship(internshipToDelete);
        showNoInternship(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Index outOfBoundIndex = INDEX_SECOND_INTERNSHIP;
        // ensures that outOfBoundIndex is still in bounds of internship data list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternshipData().getInternshipList().size());

        InternshipDeleteCommand deleteCommand = new InternshipDeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, InternshipMessages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        InternshipDeleteCommand deleteFirstCommand = new InternshipDeleteCommand(INDEX_FIRST_INTERNSHIP);
        InternshipDeleteCommand deleteSecondCommand = new InternshipDeleteCommand(INDEX_SECOND_INTERNSHIP);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        InternshipDeleteCommand deleteFirstCommandCopy = new InternshipDeleteCommand(INDEX_FIRST_INTERNSHIP);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different internship -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        InternshipDeleteCommand deleteCommand = new InternshipDeleteCommand(targetIndex);
        String expected = InternshipDeleteCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoInternship(InternshipModel model) {
        model.updateFilteredInternshipList(p -> false);

        assertTrue(model.getFilteredInternshipList().isEmpty());
    }
}
