package bootcamp.cl.proyecto_agenda.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bootcamp.cl.proyecto_agenda.Interfaces.RecyclerMeds
import bootcamp.cl.proyecto_agenda.Models.Meds
import bootcamp.cl.proyecto_agenda.Models.Option
import bootcamp.cl.proyecto_agenda.databinding.ItemMedsLayoutBinding
import com.squareup.picasso.Picasso

class RecyclerMedsAdapter(
    private val listMeds: List<Meds>,
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

    inner class ViewHolderMeds(private val binding: ItemMedsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(meds: Meds) {

            binding.MedName.text = meds.MedsName
            binding.MedIndication.text = meds.MedsIndication

            binding.root.setOnClickListener {

                medsListener.onClick(meds, adapterPosition)
            }

 /*           binding.btnDelete.setOnClickListener {

                medsListener.onDelete(meds, adapterPosition)

            }*/


        }


    }


}