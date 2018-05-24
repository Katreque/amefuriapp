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


    public void insert (Frase frase) {
        new insertAsyncTask(mFraseDao).execute(frase);
    }

    private static class insertAsyncTask extends AsyncTask<Frase, Void, Void> {

        private FraseDAO mAsyncTaskDao;

        insertAsyncTask(FraseDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Frase... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}