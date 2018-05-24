package amefuri.amefuriapp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import amefuri.amefuriapp.db.Frase;
import amefuri.amefuriapp.db.FraseViewModel;

public class FrasesFragment extends Fragment {
    private FraseViewModel mFraseViewModel;
    TextView campoFrase;
    Button maisUma;
    List<Frase> listaFrases;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mFraseViewModel = ViewModelProviders.of(this).get(FraseViewModel.class);
        return inflater.inflate(R.layout.fragment_frases, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        campoFrase = view.findViewById(R.id.campoFrase);
        view.findViewById(R.id.maisUma).setOnClickListener(maisUmaClickListener);
    }

    public void buscaFrases(FraseViewModel mFraseViewModel){
        mFraseViewModel.getAllFrases().observe(this, new Observer<List<Frase>>() {
            @Override
            public void onChanged(@Nullable final List<Frase> frases) {
                listaFrases = frases;
            }
        });
    }

    private View.OnClickListener maisUmaClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            buscaFrases(mFraseViewModel);
        }
    };

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
