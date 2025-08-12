import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

actual class Factory(
    private val app: Application
) {
    actual fun getAppDatabase() : AppDatabase  {
        val dbFile = app.getDatabasePath("outfit_combinations.db")
        return Room.databaseBuilder<AppDatabase>(
            context =app,
            name = dbFile.absolutePath,
        )
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}
