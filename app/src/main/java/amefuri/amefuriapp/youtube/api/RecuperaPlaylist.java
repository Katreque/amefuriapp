package amefuri.amefuriapp.youtube.api;

import android.content.Context;
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

public class RecuperaPlaylist {
    Credenciais cred = new Credenciais();
    public String ecchistoriaPlaylistId = "PL74zhL2-anbeLIQDvuz_zrdpbjEo_1WXT";
    public void retornaPlaylist(String idPlaylist, Context context) {
        String URL = "https://content.googleapis.com/youtube/v3/playlistItems";
        String query = "?maxResults=10&part=snippet&playlistId="+idPlaylist+"&key="+cred.apiKey()+"";

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL + query,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray items = response.getJSONArray("items");
                            int itemLength = items.length();
                            String[]titulos = new String[itemLength];
                            String[]descricoes = new String[itemLength];
                            String[]urls = new String[itemLength];

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

                            Log.e(titulos[0], descricoes[0]);
                            Log.e("url", urls[0]);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
