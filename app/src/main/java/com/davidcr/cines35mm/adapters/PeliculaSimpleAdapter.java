package com.davidcr.cines35mm.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.davidcr.cines35mm.R;
import com.davidcr.cines35mm.dominio.PeliculaSimple;

import java.util.List;

public class PeliculaSimpleAdapter extends RecyclerView.Adapter<PeliculaSimpleAdapter.ViewHolder> {

    private List<PeliculaSimple> moviesList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo, anno;

        public ViewHolder(View view) {
            super(view);
            titulo = (TextView) view.findViewById(R.id.title);
            anno = (TextView) view.findViewById(R.id.year);
        }
    }


    public PeliculaSimpleAdapter(List<PeliculaSimple> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pelicula_simple_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PeliculaSimple movie = moviesList.get(position);
        holder.titulo.setText(movie.getTitulo());
        holder.anno.setText(movie.getAnno());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
