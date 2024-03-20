package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.ALICE_MICROSOFT;
import static seedu.address.testutil.TypicalInternships.getTypicalInternshipData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.ContactEmail;
import seedu.address.model.internship.ContactName;
import seedu.address.model.internship.ContactNumber;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Location;
import seedu.address.model.internship.Role;
import seedu.address.testutil.InternshipBuilder;

public class InternshipDataTest {
    private final InternshipData internshipData = new InternshipData();

    @Test
    public void testAddInternship() {
        Internship internship = new Internship(
                new CompanyName("Amazon"),
                new ContactName("Mark Johnson"),
                new ContactEmail("markjohnson@example.com"),
                new ContactNumber("45678901"),
                new Location("remote"),
                new ApplicationStatus("rejected"),
                new Description("Business Development Internship"),
                new Role("Business Development Associate")
        );

        internshipData.addInternship(internship);
        assertTrue(internshipData.hasInternship(internship));
    }

    @Test
    public void testRemoveInternship() {
        Internship internship = new Internship(
                new CompanyName("Google"),
                new ContactName("John Doe"),
                new ContactEmail("johndoe@example.com"),
                new ContactNumber("12345678"),
                new Location("remote"),
                new ApplicationStatus("pending"),
                new Description("Software Engineering Internship"),
                new Role("Software Engineer")
        );
        InternshipData data = new InternshipData();
        data.addInternship(internship);

        data.removeInternship(internship);

        assertFalse(data.hasInternship(internship));
    }

    @Test
    public void testSetInternships() {
        InternshipData data = new InternshipData();
        Internship internship1 = new Internship(
                new CompanyName("Facebook"),
                new ContactName("Alice Smith"),
                new ContactEmail("alicesmith@example.com"),
                new ContactNumber("98765432"),
                new Location("remote"),
                new ApplicationStatus("accepted"),
                new Description("Marketing Internship"),
                new Role("Marketing Intern")
        );
        Internship internship2 = new Internship(
                new CompanyName("Microsoft"),
                new ContactName("Bob Brown"),
                new ContactEmail("bobbrown@example.com"),
                new ContactNumber("87654321"),
                new Location("remote"),
                new ApplicationStatus("pending"),
                new Description("Software Development Internship"),
                new Role("Software Developer")
        );
        List<Internship> internships = new ArrayList<>();
        internships.add(internship1);
        internships.add(internship2);

        data.setInternships(internships);

        assertTrue(data.hasInternship(internship1));
        assertTrue(data.hasInternship(internship2));
    }

    @Test
    public void testResetData() {
        Internship internship = new Internship(
                new CompanyName("Apple"),
                new ContactName("Eve Green"),
                new ContactEmail("evegreen@example.com"),
                new ContactNumber("13579246"),
                new Location("remote"),
                new ApplicationStatus("rejected"),
                new Description("Product Management Internship"),
                new Role("Product Manager")
        );
        InternshipData data = new InternshipData();
        data.addInternship(internship);

        InternshipData newData = new InternshipData();
        Internship newInternship = new Internship(
                new CompanyName("Tesla"),
                new ContactName("Elon Musk"),
                new ContactEmail("elonmusk@example.com"),
                new ContactNumber("98765432"),
                new Location("remote"),
                new ApplicationStatus("accepted"),
                new Description("Engineering Internship"),
                new Role("Engineering Intern")
        );
        newData.addInternship(newInternship);

        data.resetData(newData);

        assertFalse(data.hasInternship(internship));
        assertTrue(data.hasInternship(newInternship));
    }

    @Test
    public void testHasInternship() {
        Internship internship = new Internship(
                new CompanyName("SpaceX"),
                new ContactName("Elon Musk"),
                new ContactEmail("elonmusk@example.com"),
                new ContactNumber("98765432"),
                new Location("remote"),
                new ApplicationStatus("accepted"),
                new Description("Engineering Internship"),
                new Role("Engineering Intern")
        );
        InternshipData data = new InternshipData();
        data.addInternship(internship);

        assertTrue(data.hasInternship(internship));
    }

    @Test
    public void testSetInternship() {
        Internship internship1 = new Internship(
                new CompanyName("Amazon"),
                new ContactName("Mark Johnson"),
                new ContactEmail("markjohnson@example.com"),
                new ContactNumber("45678901"),
                new Location("remote"),
                new ApplicationStatus("rejected"),
                new Description("Business Development Internship"),
                new Role("Business Development Associate")
        );
        Internship internship2 = new Internship(
                new CompanyName("Google"),
                new ContactName("John Doe"),
                new ContactEmail("johndoe@example.com"),
                new ContactNumber("12345678"),
                new Location("remote"),
                new ApplicationStatus("pending"),
                new Description("Software Engineering Internship"),
                new Role("Software Engineer")
        );
        InternshipData data = new InternshipData();
        data.addInternship(internship1);

        data.setInternship(internship1, internship2);

        assertFalse(data.hasInternship(internship1));
        assertTrue(data.hasInternship(internship2));
    }

    @Test
    public void testEquals() {
        InternshipData data1 = new InternshipData();
        InternshipData data2 = new InternshipData();

        assertEquals(data1, data2);
    }

    @Test
    public void testGetInternshipList() {
        InternshipData data = new InternshipData();
        ObservableList<Internship> internshipList = data.getInternshipList();

        assertNotNull(internshipList);
        assertTrue(internshipList.isEmpty());
    }
    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), internshipData.getInternshipList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internshipData.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyInternshipData_replacesData() {
        InternshipData newData = getTypicalInternshipData();
        internshipData.resetData(newData);
        assertEquals(newData, internshipData);
    }
    @Test
    public void hasInternship_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> internshipData.hasInternship(null));
    }

    @Test
    public void hasInternship_internshipNotInInternshipData_returnsFalse() {
        assertFalse(internshipData.hasInternship(ALICE_MICROSOFT));
    }

    @Test
    public void hasInternship_internshipInInternshipData() {
        internshipData.addInternship(ALICE_MICROSOFT);
        assertTrue(internshipData.hasInternship(ALICE_MICROSOFT));
    }

    @Test
    public void hasInternship_internshipWithSameIdentityFieldsInInternshipData() {
        internshipData.addInternship(ALICE_MICROSOFT);
        Internship editedAlice = new InternshipBuilder(ALICE_MICROSOFT).withCompanyName("Google")
                .withContactName("John Doe").build();
        assertFalse(internshipData.hasInternship(editedAlice));
    }

    @Test
    public void getInternshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> internshipData.getInternshipList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = InternshipData.class.getCanonicalName() + "{Internship list="
                + internshipData.getInternshipList() + "}";
        assertEquals(expected, internshipData.toString());
    }

    /**
     * A stub ReadOnlyInternshipData whose internships list can violate interface constraints.
     */
    private static class InternshipDataStub implements ReadOnlyInternshipData {
        private final ObservableList<Internship> internships = FXCollections.observableArrayList();

        InternshipDataStub(Collection<Internship> internships) {
            this.internships.setAll(internships);
        }

        @Override
        public ObservableList<Internship> getInternshipList() {
            return internships;
        }
    }
}


