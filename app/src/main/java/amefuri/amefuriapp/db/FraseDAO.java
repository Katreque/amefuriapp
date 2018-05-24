package amefuri.amefuriapp.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FraseDAO {
    @Insert
    void insert(Frase frase);

    @Query("DELETE FROM frase_table")
    void deleteAll();

    @Query("SELECT * from frase_table ORDER BY frase ASC")
    LiveData<List<Frase>> getAll();
}
