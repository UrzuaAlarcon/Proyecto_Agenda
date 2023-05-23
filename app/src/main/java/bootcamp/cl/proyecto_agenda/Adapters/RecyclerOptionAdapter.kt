package bootcamp.cl.proyecto_agenda.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bootcamp.cl.proyecto_agenda.Interfaces.RecyclerMenu
import bootcamp.cl.proyecto_agenda.Models.Option
import bootcamp.cl.proyecto_agenda.databinding.ItemMenuLayoutBinding
import com.squareup.picasso.Picasso

class RecyclerOptionAdapter(
    private val listOptions: List<Option>,
    private val optionListener: RecyclerMenu
) : RecyclerView.
Adapter<RecyclerOptionAdapter.ViewHolderOption>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):ViewHolderOption {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMenuLayoutBinding.inflate(inflater, parent, false)
        return ViewHolderOption(binding)
    }

    override fun getItemCount(): Int = listOptions.size

    override fun onBindViewHolder(holder: RecyclerOptionAdapter.ViewHolderOption, position: Int) {

        val option = listOptions[position]

        holder.bind(option)

    }

    inner class ViewHolderOption(private val binding: ItemMenuLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(option: Option) {

            Picasso.get().load(option.image).fit().into(binding.MenuImage)

            binding.root.setOnClickListener {

                optionListener.onClick(option, adapterPosition)
            }

            binding.btnDelete.setOnClickListener {

                optionListener.onDelete(option, adapterPosition)

            }


        }


    }


}