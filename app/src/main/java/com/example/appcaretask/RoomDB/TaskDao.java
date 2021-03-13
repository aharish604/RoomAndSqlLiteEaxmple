package com.example.appcaretask.RoomDB;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.appcaretask.Bean.ProfileBean;
import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM ProfileBean")
    List<ProfileBean> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ProfileBean task);

    @Delete
    void delete(ProfileBean task);

}
