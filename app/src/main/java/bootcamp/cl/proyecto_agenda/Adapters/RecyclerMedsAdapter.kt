package bootcamp.cl.proyecto_agenda.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bootcamp.cl.proyecto_agenda.Interfaces.RecyclerMeds
import bootcamp.cl.proyecto_agenda.Models.Meds
import bootcamp.cl.proyecto_agenda.databinding.ItemMedsLayoutBinding

class RecyclerMedsAdapter(
    private var listMeds: MutableList<Meds>,
    private val medsListener: RecyclerMeds
) : RecyclerView.Adapter<RecyclerMedsAdapter.ViewHolderMeds>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):ViewHolderMeds {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMedsLayoutBinding.inflate(inflater, parent, false)
        return ViewHolderMeds(binding)
    }

    override fun getItemCount(): Int = listMeds.size

    override fun onBindViewHolder(holder: ViewHolderMeds, position: Int) {

        val meds = listMeds[position]

        holder.bind(meds)

    }

    fun add(meds: Meds) {
        listMeds.add(meds)
        notifyDataSetChanged()
    }

    fun remove(adapterPosition: Int) {
        listMeds.removeAt(adapterPosition)
        notifyItemChanged(adapterPosition)
        notifyDataSetChanged()
    }

    fun deleteMeds(meds: Meds){

        val index = listMeds.lastIndexOf(meds)
        if (index != -1) {

            listMeds.removeAt(index)
            notifyItemChanged(index)
            notifyDataSetChanged()

        }

    }

    fun setMedsList(planets: MutableList<Meds>) {

        listMeds = planets
        notifyDataSetChanged()

    }


    inner class ViewHolderMeds(private val binding: ItemMedsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(meds: Meds) {

            binding.MedName.text = meds.medsName
            binding.MedIndication.text = meds.medsIndication

            binding.btnDeleteMed.setOnClickListener {

                medsListener.onClick(meds, adapterPosition)
            }

        }


    }


}