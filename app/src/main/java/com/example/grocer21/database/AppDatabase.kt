package com.example.grocer21.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.grocer21.database.daos.*
import com.example.grocer21.database.entities.*

@Database(entities = [
    Allergies::class,
    DietFoods::class,
    Diets::class,
    FoodAllergens::class,
    Foods::class,
    Profile::class,
    ShoppingList::class,
    UserAllergies::class,
    UserDiets::class
], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun allergiesDao(): AllergiesDao
    abstract fun dietFoodsDao(): DietFoodsDao
    abstract fun dietsDao(): DietsDao
    abstract fun foodAllergensDao(): FoodAllergensDao
    abstract fun foodsDao(): FoodsDao
    abstract fun profileDao(): ProfileDao
    abstract fun shoppingListDao(): ShoppingListDao
    abstract fun userAllergiesDao(): UserAllergiesDao
    abstract fun userDietsDao(): UserDietsDao

    companion object {
        @Volatile
        internal var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val mInstance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "App_database"
                ).build()
                instance = mInstance
                return mInstance
            }
        }
    }
}
