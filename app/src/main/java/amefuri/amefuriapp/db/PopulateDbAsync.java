package amefuri.amefuriapp.db;

import android.os.AsyncTask;

class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
    private final FraseDAO mDao;

    PopulateDbAsync(AppDatabase db) {
        mDao = db.fraseDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {
        mDao.deleteAll();
        Frase frase = new Frase();
        frase.setFrase("Kappa do Kappa");
        mDao.insert(frase);
        frase = new Frase();
        frase.setFrase("Fon do Fon");
        mDao.insert(frase);
        return null;
    }
}
