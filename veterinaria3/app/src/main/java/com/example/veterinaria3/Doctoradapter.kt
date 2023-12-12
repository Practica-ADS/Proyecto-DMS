package com.example.veterinaria3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Doctoradapter: RecyclerView.Adapter<Doctoradapter.doctorviewholder>()
{   private var stdlist: ArrayList<DoctorModel> = ArrayList()
    private var onClickItem:((DoctorModel) -> Unit)? = null
    private var onClickDeleteItem:((DoctorModel) -> Unit)? = null

    fun addItems(items: ArrayList<DoctorModel>){
        this.stdlist = items

        notifyDataSetChanged()
    }
    fun setOnclickItem(callback:(DoctorModel)->Unit){

        this.onClickItem = callback

    }

    fun setOnclickDeleteItem(callback:(DoctorModel)->Unit){
        this.onClickDeleteItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = doctorviewholder (
        LayoutInflater.from(parent.context).inflate(R.layout.card_iten_std2, parent, false)
    )

    override fun getItemCount(): Int {
        return stdlist.size
    }

    override fun onBindViewHolder(holder: doctorviewholder, position: Int) {
        val  std = stdlist[position]
        holder.bindView(std)
        holder.itemView.setOnClickListener{ onClickItem?.invoke(std)}
        holder.btnDelete.setOnClickListener { onClickDeleteItem?.invoke(std) }
    }
    class doctorviewholder(var view: View) : RecyclerView.ViewHolder(view){

        private var id = view.findViewById<TextView>(R.id.tvId)
        private var name = view.findViewById<TextView>(R.id.tvName)
        private var puesto = view.findViewById<TextView>(R.id.tvpuesto)
        private var area = view.findViewById<TextView>(R.id.tvarea)
        var btnDelete = view.findViewById<TextView>(R.id.btnDelete)

        fun bindView(std: DoctorModel){
            id.text = std.id.toString()
            name.text = std.name
            puesto.text=std.puesto
            area.text = std.area

        }

    }

}