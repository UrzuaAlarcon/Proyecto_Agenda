package bootcamp.cl.proyecto_agenda.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bootcamp.cl.proyecto_agenda.Interfaces.RecyclerDocAppointments
import bootcamp.cl.proyecto_agenda.Models.DocAppointment
import bootcamp.cl.proyecto_agenda.databinding.ItemDocAppointmentsLayoutBinding
import kotlin.time.times

class RecyclerDocsAdapter (
    private var listDocsAppointment:MutableList<DocAppointment>,
    private val docAppointmentListener: RecyclerDocAppointments
        ): RecyclerView.Adapter<RecyclerDocsAdapter.ViewHolderDoc>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderDoc {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDocAppointmentsLayoutBinding.inflate(inflater)

        return ViewHolderDoc(binding)

    }

    override fun getItemCount(): Int = listDocsAppointment.size

    override fun onBindViewHolder(holder: ViewHolderDoc, position: Int) {

        val docAppointments = listDocsAppointment[position]

        holder.bind(docAppointments)

    }

    fun deleteDocAppointments(docAppointment: DocAppointment){

        val index = listDocsAppointment.lastIndexOf(docAppointment)

        if (index != -1){

            listDocsAppointment.removeAt(index)
            notifyItemChanged(index)
            notifyDataSetChanged()
        }

    }

    fun setDocAppointmentsList(docAppointment: MutableList<DocAppointment>){

        listDocsAppointment = docAppointment
        notifyDataSetChanged()
    }


    inner class ViewHolderDoc(private val binding: ItemDocAppointmentsLayoutBinding)
        : RecyclerView.ViewHolder(binding.root){

            fun bind(docAppointment: DocAppointment){

                binding.DoctorName.text = docAppointment.doctorName
                binding.Specialty.text = docAppointment.specialty
                binding.dateAndTime.text = docAppointment.dateAndTime.toString()

                binding.btnDeleteDocAppointment.setOnClickListener {

                    docAppointmentListener.onClick(docAppointment, adapterPosition)

                }
            }

        }


}