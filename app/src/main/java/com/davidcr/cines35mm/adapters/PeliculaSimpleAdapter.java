package com.davidcr.cines35mm.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.davidcr.cines35mm.DetallePeliculaActivity;
import com.davidcr.cines35mm.R;
import com.davidcr.cines35mm.dominio.PeliculaSimple;

import java.util.List;

public class PeliculaSimpleAdapter extends RecyclerView.Adapter<PeliculaSimpleAdapter.ViewHolder> {

    private List<PeliculaSimple> moviesList;
    public Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo, anno;
        public RelativeLayout parentLayout;

        public ViewHolder(View view) {
            super(view);
            titulo = (TextView) view.findViewById(R.id.title);
            anno = (TextView) view.findViewById(R.id.year);
            parentLayout = view.findViewById(R.id.layout_pelicula_simple);
        }
    }


    public PeliculaSimpleAdapter(List<PeliculaSimple> moviesList, Context mContext) {
        this.moviesList = moviesList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pelicula_simple_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PeliculaSimple movie = moviesList.get(position);
        holder.titulo.setText(movie.getTitulo());
        holder.anno.setText(movie.getAnno());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetallePeliculaActivity.class);
                intent.putExtra("peliculaSimple", movie);
                mContext.startActivity(intent);
            }
        });
    }

    public Context getmContext() {
        return mContext;
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
