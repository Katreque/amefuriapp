package amefuri.amefuriapp.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.concurrent.Executors;

@Database(entities = {Frase.class}, version = 2)
abstract class AppDatabase extends RoomDatabase {
    public abstract FraseDAO fraseDao();
    private static AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "frase_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(new Callback() {
                                @Override
                                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                    super.onOpen(db);
                                    Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            INSTANCE.fraseDao().deleteAll();
                                            for(int i = 0; i < 8; i++){
                                                INSTANCE.fraseDao().insert(Frase.populateData(i));
                                            }
                                        }
                                    });
                                }
                            })
                            .build();

                }
            }
        }
        return INSTANCE;
    }
}
