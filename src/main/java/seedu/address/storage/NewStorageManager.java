package seedu.address.storage;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyDatabase;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Manages storage of MediConnect data in local storage.
 */
public class NewStorageManager implements NewStorage {

    private static final Logger logger = LogsCenter.getLogger(NewStorageManager.class);
    private DatabaseStorage databaseStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code NewStorageManager} with the given {@code DatabaseStorage} and {@code UserPrefStorage}.
     */
    public NewStorageManager(DatabaseStorage databaseStorage, UserPrefsStorage userPrefsStorage) {
        this.databaseStorage = databaseStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ Database methods ==============================

    @Override
    public Path getDatabaseFilePath() {
        return databaseStorage.getDatabaseFilePath();
    }

    @Override
    public Optional<ReadOnlyDatabase> readDatabase() throws DataLoadingException {
        return readDatabase(databaseStorage.getDatabaseFilePath());
    }

    @Override
    public Optional<ReadOnlyDatabase> readDatabase(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return databaseStorage.readDatabase(filePath);
    }

    @Override
    public void saveDatabase(ReadOnlyDatabase database) throws IOException {
        saveDatabase(database, databaseStorage.getDatabaseFilePath());
    }

    @Override
    public void saveDatabase(ReadOnlyDatabase database, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        databaseStorage.saveDatabase(database, filePath);
    }

}
