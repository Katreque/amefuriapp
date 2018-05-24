package amefuri.amefuriapp.db;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class FraseViewModel extends AndroidViewModel {
    private FraseRepository mRepository;
    private LiveData<List<Frase>> mAllFrases;

    public FraseViewModel(Application application) {
        super(application);
        mRepository = new FraseRepository(application);
        mAllFrases = mRepository.getAllFrases();
    }

    public LiveData<List<Frase>> getAllFrases() { return mAllFrases; }
    public void insert(Frase frase) { mRepository.insert(frase); }
}
