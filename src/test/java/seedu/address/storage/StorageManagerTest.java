package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalInternships.getTypicalInternshipData;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.AddressBook;
import seedu.address.model.InternshipData;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyInternshipData;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager addressBookStorageManager;
    private StorageManager internshipDataStorageManager;

    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(getTempFilePath("ab"));
        JsonInternshipDataStorage internshipDataStorage = new JsonInternshipDataStorage(getTempFilePath("id"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        addressBookStorageManager = new StorageManager(addressBookStorage, userPrefsStorage);
        internshipDataStorageManager = new StorageManager(internshipDataStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        InternshipUserPrefs original = new InternshipUserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        internshipDataStorageManager.saveUserPrefs(original);
        InternshipUserPrefs internshipDataRetrieved = internshipDataStorageManager.readUserPrefs().get();
        assertEquals(original, internshipDataRetrieved);
    }

    // ================ AddressBook methods ==============================

    @Test
    public void addressBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         */
        AddressBook original = getTypicalAddressBook();
        addressBookStorageManager.saveAddressBook(original);
        ReadOnlyAddressBook retrieved = addressBookStorageManager.readAddressBook().get();
        assertEquals(original, new AddressBook(retrieved));
    }

    @Test
    public void getAddressBookFilePath() {
        assertNotNull(addressBookStorageManager.getAddressBookFilePath());
    }

    // ================ InternshipData methods ==============================


    @Test
    public void internshipDataReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonInternshipDataStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonInternshipDataStorageTest} class.
         */
        InternshipData original = getTypicalInternshipData();
        internshipDataStorageManager.saveInternshipData(original);
        ReadOnlyInternshipData retrieved = internshipDataStorageManager.readInternshipData().get();
        assertEquals(original, new InternshipData(retrieved));
    }

    @Test
    public void getInternshipDataFilePath() {
        assertNotNull(internshipDataStorageManager.getInternshipDataFilePath());
    }
}
