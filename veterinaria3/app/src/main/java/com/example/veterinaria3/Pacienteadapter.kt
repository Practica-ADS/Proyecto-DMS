package com.example.veterinaria3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Pacienteadapter: RecyclerView.Adapter<Pacienteadapter.pacienteviewholder>()
{   private var stdlist: ArrayList<PacienteModel> = ArrayList()
    private var onClickItem:((PacienteModel) -> Unit)? = null

    fun addItems(items: ArrayList<PacienteModel>){
        this.stdlist = items

        notifyDataSetChanged()
    }
    fun setOnclickItem(callback:(PacienteModel)->Unit){

        this.onClickItem = callback

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =pacienteviewholder (
        LayoutInflater.from(parent.context).inflate(R.layout.card_iten_std, parent, false)
    )

    override fun getItemCount(): Int {
        return stdlist.size
    }

    override fun onBindViewHolder(holder: pacienteviewholder, position: Int) {
        val  std = stdlist[position]
        holder.bindView(std)
        holder.itemView.setOnClickListener{ onClickItem?.invoke(std)}
    }
    class pacienteviewholder(var view: View) : RecyclerView.ViewHolder(view){

        private var id = view.findViewById<TextView>(R.id.tvId)
        private var name = view.findViewById<TextView>(R.id.tvName)
        private var raza = view.findViewById<TextView>(R.id.tvraza)
        private var encargado = view.findViewById<TextView>(R.id.tven)
        private var btnDelete = view.findViewById<TextView>(R.id.btnDelete)

        fun bindView(std: PacienteModel){
            id.text = std.id.toString()
            name.text = std.name
            raza.text=std.raza
            encargado.text = std.encargado

        }

    }

}