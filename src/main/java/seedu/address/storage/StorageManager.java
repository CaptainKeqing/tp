package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyInternshipData;
import seedu.address.model.ReadOnlyInternshipUserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private InternshipDataStorage internshipDataStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    /**
     * Creates a {@code StorageManager} with the given {@code InternshipDataStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(InternshipDataStorage internshipDataStorage, UserPrefsStorage userPrefsStorage) {
        this.internshipDataStorage = internshipDataStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<InternshipUserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyInternshipUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ InternshipData methods ==============================
    @Override
    public Path getInternshipDataFilePath() {
        return internshipDataStorage.getInternshipDataFilePath();
    }

    @Override
    public Optional<ReadOnlyInternshipData> readInternshipData() throws DataLoadingException {
        return readInternshipData(internshipDataStorage.getInternshipDataFilePath());
    }

    @Override
    public Optional<ReadOnlyInternshipData> readInternshipData(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return internshipDataStorage.readInternshipData(filePath);
    }

    @Override
    public void saveInternshipData(ReadOnlyInternshipData internshipData) throws IOException {
        saveInternshipData(internshipData, internshipDataStorage.getInternshipDataFilePath());
    }

    @Override
    public void saveInternshipData(ReadOnlyInternshipData internshipData, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        internshipDataStorage.saveInternshipData(internshipData, filePath);
    }

}
