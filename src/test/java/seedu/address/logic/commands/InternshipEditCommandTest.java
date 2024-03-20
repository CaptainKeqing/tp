package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.InternshipCommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_COMPANY_NAME_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_CONTACT_EMAIL_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.InternshipCommandTestUtil.showInternshipAtIndex;
import static seedu.address.testutil.InternshipTypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.address.testutil.InternshipTypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.address.testutil.TypicalInternships.getTypicalInternshipData;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.InternshipMessages;
import seedu.address.logic.commands.InternshipEditCommand.EditInternshipDescriptor;
import seedu.address.model.InternshipData;
import seedu.address.model.InternshipModel;
import seedu.address.model.InternshipModelManager;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.internship.Internship;
import seedu.address.testutil.EditInternshipDescriptorBuilder;
import seedu.address.testutil.InternshipBuilder;

/**
 * Contains integration tests (interaction with the InternshipModel) and unit tests for InternshipEditCommand.
 */
public class InternshipEditCommandTest {

    private final InternshipModel model = new InternshipModelManager(getTypicalInternshipData(),
            new InternshipUserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Internship editedInternship = new InternshipBuilder().build();
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder(editedInternship).build();
        InternshipEditCommand editCommand = new InternshipEditCommand(INDEX_FIRST_INTERNSHIP, descriptor);

        String expectedMessage = String.format(InternshipEditCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS,
                InternshipMessages.format(editedInternship));

        InternshipModel expectedModel = new InternshipModelManager(new InternshipData(model.getInternshipData()),
                new InternshipUserPrefs());
        expectedModel.setInternship(model.getFilteredInternshipList().get(0), editedInternship);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastInternship = Index.fromOneBased(model.getFilteredInternshipList().size());
        Internship lastInternship = model.getFilteredInternshipList().get(indexLastInternship.getZeroBased());

        InternshipBuilder internshipInList = new InternshipBuilder(lastInternship);
        Internship editedInternship = internshipInList.withCompanyName(VALID_COMPANY_NAME_BOB)
                .withContactEmail(VALID_CONTACT_EMAIL_BOB).withDescription(VALID_DESCRIPTION_BOB).build();

        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_BOB).withContactEmail(VALID_CONTACT_EMAIL_BOB)
                .withDescription(VALID_DESCRIPTION_BOB).build();
        InternshipEditCommand editCommand = new InternshipEditCommand(indexLastInternship, descriptor);

        String expectedMessage = String.format(InternshipEditCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS,
                InternshipMessages.format(editedInternship));

        InternshipModel expectedModel = new InternshipModelManager(new InternshipData(model.getInternshipData()),
                new InternshipUserPrefs());
        expectedModel.setInternship(lastInternship, editedInternship);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        InternshipEditCommand editCommand = new InternshipEditCommand(INDEX_FIRST_INTERNSHIP,
                new EditInternshipDescriptor());
        Internship editedInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());

        String expectedMessage = String.format(InternshipEditCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS,
                InternshipMessages.format(editedInternship));

        InternshipModel expectedModel = new InternshipModelManager(new
                InternshipData(model.getInternshipData()), new InternshipUserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        Internship personInFilteredList = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        Internship editedInternship = new InternshipBuilder(personInFilteredList)
                .withCompanyName(VALID_COMPANY_NAME_BOB).build();
        InternshipEditCommand editCommand = new InternshipEditCommand(INDEX_FIRST_INTERNSHIP,
                new EditInternshipDescriptorBuilder().withCompanyName(VALID_COMPANY_NAME_BOB).build());

        String expectedMessage = String.format(InternshipEditCommand.MESSAGE_EDIT_INTERNSHIP_SUCCESS,
                InternshipMessages.format(editedInternship));

        InternshipModel expectedModel = new InternshipModelManager(new InternshipData(model.getInternshipData()),
                new InternshipUserPrefs());
        expectedModel.setInternship(model.getFilteredInternshipList().get(0), editedInternship);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateInternshipUnfilteredList_failure() {
        Internship firstInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder(firstInternship).build();
        InternshipEditCommand editCommand = new InternshipEditCommand(INDEX_SECOND_INTERNSHIP, descriptor);

        assertCommandFailure(editCommand, model, InternshipEditCommand.MESSAGE_DUPLICATE_INTERNSHIP);
    }

    @Test
    public void execute_duplicateInternshipFilteredList_failure() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);

        // edit person in filtered list into a duplicate in address book
        Internship personInList = model.getInternshipData().getInternshipList().get(INDEX_SECOND_INTERNSHIP
                .getZeroBased());
        InternshipEditCommand editCommand = new InternshipEditCommand(INDEX_FIRST_INTERNSHIP,
                new EditInternshipDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, InternshipEditCommand.MESSAGE_DUPLICATE_INTERNSHIP);
    }

    @Test
    public void execute_invalidInternshipIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInternshipList().size() + 1);
        EditInternshipDescriptor descriptor = new EditInternshipDescriptorBuilder()
                .withCompanyName(VALID_COMPANY_NAME_BOB).build();
        InternshipEditCommand editCommand = new InternshipEditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, InternshipMessages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidInternshipIndexFilteredList_failure() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);
        Index outOfBoundIndex = INDEX_SECOND_INTERNSHIP;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternshipData().getInternshipList().size());

        InternshipEditCommand editCommand = new InternshipEditCommand(outOfBoundIndex,
                new EditInternshipDescriptorBuilder().withCompanyName(VALID_COMPANY_NAME_BOB).build());

        assertCommandFailure(editCommand, model, InternshipMessages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final InternshipEditCommand standardCommand = new
                InternshipEditCommand(INDEX_FIRST_INTERNSHIP, DESC_AMY.build());

        // same values -> returns true
        EditInternshipDescriptor copyDescriptor = new EditInternshipDescriptor(DESC_AMY.build());
        InternshipEditCommand commandWithSameValues = new InternshipEditCommand(INDEX_FIRST_INTERNSHIP, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new InternshipClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new InternshipEditCommand(INDEX_SECOND_INTERNSHIP, DESC_AMY.build())));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new InternshipEditCommand(INDEX_FIRST_INTERNSHIP, DESC_BOB.build())));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditInternshipDescriptor editInternshipDescriptor = new EditInternshipDescriptor();
        InternshipEditCommand editCommand = new InternshipEditCommand(index, editInternshipDescriptor);
        String expected = InternshipEditCommand.class.getCanonicalName() + "{index=" + index
                + ", editInternshipDescriptor=" + editInternshipDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
