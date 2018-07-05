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

import java.lang.reflect.Array;
import java.util.List;
import java.util.Random;

import amefuri.amefuriapp.db.Frase;
import amefuri.amefuriapp.db.FraseViewModel;

public class FrasesFragment extends Fragment {
    private FraseViewModel mFraseViewModel;
    TextView campoFrase, campoNomeAutor;
    Button maisUma;
    Frase[] arrayRetorno;
    public int random;

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
        campoNomeAutor = view.findViewById(R.id.campoNomeAutor);
        view.findViewById(R.id.maisUma).setOnClickListener(maisUmaClickListener);

        buscaFrases(mFraseViewModel);
    }

    public void buscaFrases(FraseViewModel mFraseViewModel){
        mFraseViewModel.getAllFrases().observe(this, new Observer<List<Frase>>() {
            @Override
            public void onChanged(final List<Frase> frases) {
                arrayRetorno = new Frase[frases.size()];
                for(int i = 0; i < frases.size(); i++){
                    arrayRetorno[i] = frases.get(i);
                }
            }
        });
    }

    public void generateRandomFrase(Frase[] arrayRetorno) {
        int rnd = new Random().nextInt(arrayRetorno.length);

        while(true) {
            if (rnd == random) {
                rnd = new Random().nextInt(arrayRetorno.length);
            } else {
                break;
            }
        }

        campoFrase.setText(arrayRetorno[rnd].getFrase());
        campoNomeAutor.setText(arrayRetorno[rnd].getNomeAutor());
        random = rnd;
    }

    private View.OnClickListener maisUmaClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            generateRandomFrase(arrayRetorno);
        }
    };

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
