package amefuri.amefuriapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import amefuri.amefuriapp.youtube.api.RecuperaPlaylist;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_tv.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_tv#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_tv extends Fragment implements InterfaceRecuperaPlaylsit{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    String[]titulos;
    String[]descricoes;
    String[]urls;

    public Fragment_tv() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_tv.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_tv newInstance(String param1, String param2) {
        Fragment_tv fragment = new Fragment_tv();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View frag = inflater.inflate(R.layout.fragment_tv, container, false);
        return frag;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        RecuperaPlaylist rp = new RecuperaPlaylist(this);
        rp.retornaPlaylist(this.getContext());

        mRecyclerView = (RecyclerView) getView().findViewById(R.id.tv_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        String[]init = new String[0];

        mAdapter = new fragmentTvAdapter(init, init, init);
        mRecyclerView.setAdapter(mAdapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void getPlaylistTodosVideosAmefuri(JSONObject response) {
        try {
            JSONArray items = response.getJSONArray("items");
            int itemLength = items.length();
            titulos = new String[itemLength];
            descricoes = new String[itemLength];
            urls = new String[itemLength];

            for(int i = 0; i < itemLength; i++){
                JSONObject item = items.getJSONObject(i);

                JSONObject snippet = item.getJSONObject("snippet");
                String titulo = snippet.get("title").toString();
                String descricao = snippet.get("description").toString();

                JSONObject thumbnail = snippet.getJSONObject("thumbnails");
                JSONObject maxRes = thumbnail.getJSONObject("maxres");
                String url = maxRes.get("url").toString();

                titulos[i] = titulo;
                descricoes[i] = descricao;
                urls[i] = url;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mAdapter = new fragmentTvAdapter(titulos, descricoes, urls);
        mRecyclerView.setAdapter(mAdapter);
    }
}
