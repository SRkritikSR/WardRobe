import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import org.example.wardrobe.database.OutfitCombination
import org.example.wardrobe.database.OutfitDao

@Database(
    entities = [OutfitCombination::class],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun outfitDao(): OutfitDao
}
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
//@Suppress("NO_ACTUAL_FOR_EXPECT")
//expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
//    override fun initialize(): AppDatabase
//}
//
//
//fun getRoomDatabase(
//    builder: RoomDatabase.Builder<AppDatabase>
//): AppDatabase {
//    return builder
//        .addMigrations()
//        .fallbackToDestructiveMigrationOnDowngrade(false)
//        .setDriver(BundledSQLiteDriver())
//        .setQueryCoroutineContext(Dispatchers.Default)
//        .build()
//}
//
//
