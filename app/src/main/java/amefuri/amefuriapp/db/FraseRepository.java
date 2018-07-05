package amefuri.amefuriapp.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class FraseRepository {
    private FraseDAO mFraseDao;
    private LiveData<List<Frase>> mAllFrases;

    FraseRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mFraseDao = db.fraseDao();
        mAllFrases = mFraseDao.getAll();
    }

    LiveData<List<Frase>> getAllFrases() {
        return mAllFrases;
    }
}