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

    @ColumnInfo(name = "autor")
    private String nomeAutor;

    public int getId() {
        return id;
    }

    public String getFrase() {
        return frase;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public static Frase populateData(int i) {
        String[] frases = new String[]{
                "Quando você ama, há o risco de odiar.",
                "Se você não pode ganhar o jogo... Se você não conseguir resolver o enigma... Você é apenas outro perdedor...",
                "Meu coração é puro... pura maldade!!",
                "Como um mundo pode ser tão cruel e tão bonito ao mesmo tempo?",
                "Importante não é o que os outros pensam de você, mas como você age perante isso.",
                "Só podem atirar os que estiverem prontos para levar um tiro.",
                "Não importa quanta vezes eu caio, mas sim quantas vezes eu vou conseguir levantar.",
                "Não a lição sem dor, mas eu juro que se um dia eu te fizer chorar serão lágrimas de alegria."
        };

        String[] autores = new String[]{
                "Naruto",
                "L",
                "Vegeta",
                "Mikasa (Shingeki no Kyojin)",
                "Meliodas (Nanatsu no Taizai)",
                "Lelouch (Code Geass)",
                "Seiya de Pégasus",
                "Edward Elric"
        };

        Frase frase = new Frase();

        frase = new Frase();
        frase.setFrase(frases[i]);
        frase.setNomeAutor(autores[i]);
        return frase;
    }
}
