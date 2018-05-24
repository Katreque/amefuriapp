package amefuri.amefuriapp.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "frase_table")
public class Frase {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "frase")
    private String frase;

    public int getId() {
        return id;
    }

    public String getFrase() {
        return frase;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }
}
