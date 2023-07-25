package bootcamp.cl.proyecto_agenda.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bootcamp.cl.proyecto_agenda.Interfaces.Recyclers.RecyclerMeds
import bootcamp.cl.proyecto_agenda.Models.Meds
import bootcamp.cl.proyecto_agenda.databinding.ItemMedsLayoutBinding
class RecyclerMedsAdapter(
    private var listMeds: MutableList<Meds>,
    private val medsListener: RecyclerMeds
) : RecyclerView.Adapter<RecyclerMedsAdapter.ViewHolderMeds>() {

    /**
     * Creates a new ViewHolderMeds instance.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMeds {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMedsLayoutBinding.inflate(inflater, parent, false)
        return ViewHolderMeds(binding)
    }

    /**
     * Returns the number of items in the adapter.
     */
    override fun getItemCount(): Int = listMeds.size

    /**
     * Binds the data to the ViewHolder and sets up click listeners.
     */
    override fun onBindViewHolder(holder: ViewHolderMeds, position: Int) {
        val meds = listMeds[position]
        holder.bind(meds)
    }

    /**
     * Adds a Meds object to the adapter's list.
     */
    fun add(meds: Meds) {
        listMeds.add(meds)
        notifyDataSetChanged()
    }

    /**
     * Removes an item from the adapter's list at the specified position.
     */
    fun remove(adapterPosition: Int) {
        listMeds.removeAt(adapterPosition)
        notifyItemChanged(adapterPosition)
        notifyDataSetChanged()
    }

    /**
     * Deletes a specific Meds object from the adapter's list.
     */
    fun deleteMeds(meds: Meds) {
        val index = listMeds.lastIndexOf(meds)
        if (index != -1) {
            listMeds.removeAt(index)
            notifyItemChanged(index)
            notifyDataSetChanged()
        }
    }

    /**
     * Sets the list of Meds for the adapter.
     */
    fun setMedsList(meds: MutableList<Meds>) {
        listMeds = meds
        notifyDataSetChanged()
    }

    /**
     * ViewHolder class representing each item in the RecyclerView.
     */
    inner class ViewHolderMeds(private val binding: ItemMedsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the data to the ViewHolder's views.
         */
        fun bind(meds: Meds) {
            binding.MedName.text = meds.medsName
            binding.MedIndication.text = meds.medsIndication

            binding.btnDeleteMed.setOnClickListener {
                medsListener.onClick(meds, adapterPosition)
            }
        }
    }
}