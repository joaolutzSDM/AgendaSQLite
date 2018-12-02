package br.edu.ifspsaocarlos.agenda.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.ifspsaocarlos.agenda.model.Contato;
import br.edu.ifspsaocarlos.agenda.R;

import java.util.List;


public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder> {

    private List<Contato> contatos;
    private Context context;

    private static ItemClickListener clickListener;


    public ContatoAdapter(List<Contato> contatos, Context context) {
        this.contatos = contatos;
        this.context = context;
    }

    @Override
    public ContatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contato_celula, parent, false);
        return new ContatoViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ContatoViewHolder holder, int position) {
        Contato contato  = contatos.get(position);
        holder.nome.setText(contato.getNome());
        holder.imgFavorite.setImageResource(contato.isFavorito() ? R.drawable.ic_favorite_black_24dp : R.drawable.ic_favorite_border_black_24dp);
    }

    @Override
    public int getItemCount() {
        return contatos.size();
    }


    public void setClickListener(ItemClickListener itemClickListener) {
        clickListener = itemClickListener;
    }


    public  class ContatoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final TextView nome;
        final ImageView imgFavorite;

        ContatoViewHolder(View view) {
            super(view);
            nome = view.findViewById(R.id.nome);
            imgFavorite = view.findViewById(R.id.imgFavorite);
            view.setOnClickListener(this);
            imgFavorite.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Contato contato = contatos.get(getAdapterPosition());
            if(view == imgFavorite) {
                if (clickListener != null) {
                    clickListener.onFavoriteClick(contato);
                }
            } else {
                if (clickListener != null) {
                    clickListener.onItemClick(contato);
                }
            }
        }
    }

    public interface ItemClickListener {
        void onItemClick(Contato contato);
        void onFavoriteClick(Contato contato);
    }

}