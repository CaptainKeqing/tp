package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.UniqueInternshipList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class InternshipData implements ReadOnlyInternshipData {

    private final UniqueInternshipList internshipList;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        internshipList = new UniqueInternshipList();
    }

    public InternshipData() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public InternshipData(ReadOnlyInternshipData toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setInternships(List<Internship> internships) {
        this.internshipList.setInternships(internships);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyInternshipData newData) {
        requireNonNull(newData);
        setInternships(newData.getInternshipList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return internshipList.contains(internship);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addInternship(Internship i) {
        internshipList.add(i);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setInternship(Internship target, Internship editedInternship) {
        requireNonNull(editedInternship);

        internshipList.setInternship(target, editedInternship);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeInternship(Internship key) {
        internshipList.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Internship list", internshipList)
                .toString();
    }

    @Override
    public ObservableList<Internship> getInternshipList() {
        return internshipList.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipData)) {
            return false;
        }

        InternshipData otherAddressBook = (InternshipData) other;
        return internshipList.equals(otherAddressBook.internshipList);
    }

    @Override
    public int hashCode() {
        return internshipList.hashCode();
    }
}
