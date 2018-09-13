package amefuri.amefuriapp.youtube.api;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import amefuri.amefuriapp.InterfaceRecuperaPlaylsit;

public class RecuperaPlaylist {
    String ecchistoriaPlaylistId;
    Credenciais cred = new Credenciais();
    InterfaceRecuperaPlaylsit mListener;

    public RecuperaPlaylist(InterfaceRecuperaPlaylsit context) {
        ecchistoriaPlaylistId = "PL74zhL2-anbeLIQDvuz_zrdpbjEo_1WXT";
        mListener = context;
    }

    public void retornaPlaylist(Context context) {
        String URL = "https://content.googleapis.com/youtube/v3/playlistItems";
        String query = "?maxResults=10&part=snippet&playlistId="+ecchistoriaPlaylistId+"&key="+cred.apiKey()+"";
        final String[][]retornoFinal = new String[3][];

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL + query,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        mListener.getPlaylistTodosVideosAmefuri(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("DueloFailed", error.toString());
                    }
                }
        );

        requestQueue.add(objectRequest);
    }
}
