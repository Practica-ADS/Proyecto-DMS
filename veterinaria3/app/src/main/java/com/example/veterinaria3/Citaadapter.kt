package com.example.veterinaria3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Citaadapter: RecyclerView.Adapter<Citaadapter.citaviewholder>()
{   private var stdlist: ArrayList<CitaModel> = ArrayList()
    private var onClickItem:((CitaModel) -> Unit)? = null
    private var onClickDeleteItem:((CitaModel) -> Unit)? = null

    fun addItems(items: ArrayList<CitaModel>){
        this.stdlist = items

        notifyDataSetChanged()
    }
    fun setOnclickItem(callback:(CitaModel)->Unit){

        this.onClickItem = callback

    }

    fun setOnclickDeleteItem(callback:(CitaModel)->Unit){
        this.onClickDeleteItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = citaviewholder (
        LayoutInflater.from(parent.context).inflate(R.layout.card_iten_std3, parent, false)
    )

    override fun getItemCount(): Int {
        return stdlist.size
    }

    override fun onBindViewHolder(holder: citaviewholder, position: Int) {
        val  std = stdlist[position]
        holder.bindView(std)
        holder.itemView.setOnClickListener{ onClickItem?.invoke(std)}
        holder.btnDelete.setOnClickListener { onClickDeleteItem?.invoke(std) }
    }
    class citaviewholder(var view: View) : RecyclerView.ViewHolder(view){

        private var id = view.findViewById<TextView>(R.id.tvId)
        private var name = view.findViewById<TextView>(R.id.tvName)
        private var fecha = view.findViewById<TextView>(R.id.tvfecha)
        private var hora = view.findViewById<TextView>(R.id.tvhora)
        private var encargado = view.findViewById<TextView>(R.id.tvencargado)
        var btnDelete = view.findViewById<TextView>(R.id.btnDelete)

        fun bindView(std: CitaModel){
            id.text = std.id.toString()
            name.text = std.name
            fecha.text=std.fecha
            hora.text = std.hora
            encargado.text = std.encargado

        }

    }

}