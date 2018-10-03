package amefuri.amefuriapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class fragmentTvAdapter extends RecyclerView.Adapter<fragmentTvAdapter.MyViewHolder>{
    private String[] titulos;
    private String[] descricoes;
    private String[] urls;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cv;
        public TextView titulo;
        public TextView descricao;
        public ImageView thumbnail;
        public MyViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.tv_card);
            titulo = (TextView)itemView.findViewById(R.id.tv_text_titulo);
            descricao = (TextView)itemView.findViewById(R.id.tv_text_descricao);
            thumbnail = (ImageView)itemView.findViewById(R.id.tv_img_thumb);
        }
    }

    public fragmentTvAdapter(String[] titulos, String[] descricoes, String[] urls) {
        this.titulos = titulos;
        this.descricoes = descricoes;
        this.urls = urls;
    }

    @Override
    public fragmentTvAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tv_card_view, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream((InputStream)new URL(urls[position]).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        holder.thumbnail.setImageBitmap(bitmap);
        holder.titulo.setText(titulos[position]);
        holder.descricao.setText("Descrição!");
    }

    @Override
    public int getItemCount() {
        return titulos.length;
    }
}
