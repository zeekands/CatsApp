package com.zeekands.core.data.source.local.room

import androidx.room.*
import com.zeekands.core.data.source.local.entity.CatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCats(Cats: List<CatEntity>)

    @Update
    fun updateFavoriteCats(Cat: CatEntity)

    @Query("UPDATE catsEntities set favourite = :state where idDb=:idDb")
    fun updateFavourite(idDb: Int, state: Boolean)

    @Query("SELECT * FROM catsEntities where idDb=:idDb")
    fun getCat(idDb: Int): Flow<CatEntity>

    @Query("SELECT * FROM catsEntities")
    fun getCats(): Flow<List<CatEntity>>

    @Query("SELECT * FROM catsEntities WHERE name LIKE '%' || :search || '%'")
    fun getSearchCats(search: String): Flow<List<CatEntity>>

    @Query("SELECT * FROM catsEntities where favourite = 1")
    fun getFavoriteCats(): Flow<List<CatEntity>>
}