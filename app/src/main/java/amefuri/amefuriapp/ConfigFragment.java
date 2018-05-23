package amefuri.amefuriapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

public class ConfigFragment extends PreferenceFragmentCompat {
    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreatePreferences(Bundle bundle, String s){
        addPreferencesFromResource(R.xml.preferencias);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
