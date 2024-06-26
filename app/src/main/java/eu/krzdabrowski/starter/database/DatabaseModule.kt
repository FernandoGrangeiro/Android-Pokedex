package eu.krzdabrowski.starter.database

import android.content.Context
import androidx.room.Room
import com.fernando.pokedex.data.local.dao.PokedexDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import eu.krzdabrowski.starter.basicfeature.data.local.dao.RocketDao
import javax.inject.Singleton

private const val APP_DATABASE_NAME = "pokedex_app"

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            APP_DATABASE_NAME,
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideRocketDao(database: AppDatabase): RocketDao {
        return database.rocketDao()
    }

    @Singleton
    @Provides
    fun providePokedexDao(database: AppDatabase): PokedexDao {
        return database.pokedexDao()
    }
}
