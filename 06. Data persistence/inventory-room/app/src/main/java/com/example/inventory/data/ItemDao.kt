package com.example.inventory.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    // 충돌 발생할 일 없으므로 onConflict = OnConflictStrategy.IGNORE 선언
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * from item where id = :id")
    fun getItem(id: Int): Flow<Item?>

    @Query("SELECT * from item ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}
