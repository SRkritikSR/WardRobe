import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSFileManager
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSUserDomainMask
// shared/src/iosMain/kotlin/Database.kt

actual class Factory {
    actual fun getAppDatabase() : AppDatabase {
        return getDatabaseBuilder()
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
    fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
        val dbFilePath = documentDirectory() + "/outfits_combinations.db"
        return Room.databaseBuilder<AppDatabase>(
            name = dbFilePath,
        )
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun documentDirectory(): String {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        return requireNotNull(documentDirectory?.path)
    }

}



