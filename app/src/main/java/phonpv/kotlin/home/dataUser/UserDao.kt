package phonpv.kotlin.home.dataUser

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

@Query("SElECT * FROM users WHERE username = :username LIMIT 1")
suspend fun getUserByUserName(username:String): User?
}