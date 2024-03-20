package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.internship.Internship;

/**
 * The API of the Model component.
 */
public interface InternshipModel {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Internship> PREDICATE_SHOW_ALL_INTERNSHIPS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyInternshipUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyInternshipUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getInternshipDataFilePath();

    /**
     * Sets the user prefs 'address book file path.
     */
    void setInternshipDataFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setInternshipData(ReadOnlyInternshipData internshipData);

    /** Returns the AddressBook */
    ReadOnlyInternshipData getInternshipData();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasInternship(Internship internship);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteInternship(Internship target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addInternship(Internship internship);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setInternship(Internship target, Internship editedInternship);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Internship> getFilteredInternshipList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredInternshipList(Predicate<Internship> predicate);
}
