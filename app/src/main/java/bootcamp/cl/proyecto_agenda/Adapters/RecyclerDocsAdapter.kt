package bootcamp.cl.proyecto_agenda.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bootcamp.cl.proyecto_agenda.Interfaces.Recyclers.RecyclerDocAppointments
import bootcamp.cl.proyecto_agenda.Models.DocAppointment
import bootcamp.cl.proyecto_agenda.databinding.ItemDocAppointmentsLayoutBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RecyclerDocsAdapter(
    private var listDocsAppointment: MutableList<DocAppointment>,
    private val docAppointmentListener: RecyclerDocAppointments
) : RecyclerView.Adapter<RecyclerDocsAdapter.ViewHolderDoc>() {

    /**
     * Creates a new ViewHolderDoc instance.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDoc {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDocAppointmentsLayoutBinding.inflate(inflater, parent, false)
        return ViewHolderDoc(binding)
    }

    /**
     * Returns the number of items in the adapter.
     */
    override fun getItemCount(): Int = listDocsAppointment.size

    /**
     * Binds the data to the ViewHolder and sets up click listeners.
     */
    override fun onBindViewHolder(holder: ViewHolderDoc, position: Int) {
        val docAppointment = listDocsAppointment[position]
        holder.bind(docAppointment)
    }

    /**
     * Deletes a specific DocAppointment from the adapter's list.
     */
    fun deleteDocAppointments(docAppointment: DocAppointment) {
        val index = listDocsAppointment.lastIndexOf(docAppointment)
        if (index != -1) {
            listDocsAppointment.removeAt(index)
            notifyItemChanged(index)
            notifyDataSetChanged()
        }
    }

    /**
     * Sets the list of DocAppointments for the adapter.
     */
    fun setDocAppointmentsList(docAppointment: MutableList<DocAppointment>) {
        listDocsAppointment = docAppointment
        notifyDataSetChanged()
    }

    /**
     * ViewHolder class representing each item in the RecyclerView.
     */
    inner class ViewHolderDoc(private val binding: ItemDocAppointmentsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the data to the ViewHolder's views.
         */
        fun bind(docAppointment: DocAppointment) {

            val dateTimeSplit = docAppointment.dateAndTime.toString().split("T")
            var date = formatDate(dateTimeSplit.first())
            var time = dateTimeSplit.last()

            binding.DoctorName.text = docAppointment.doctorName
            binding.Specialty.text = docAppointment.specialty
            binding.dateAndTime.text = "Fecha: $date / Hora $time"
            binding.Location.text = docAppointment.location

            binding.btnDeleteDocAppointment.setOnClickListener {
                docAppointmentListener.onClick(docAppointment, adapterPosition)
            }
        }
    }

    // Formats the received date  to a specific format.
    fun formatDate(receivedDate: String): String {
        val formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatterOutput = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = LocalDate.parse(receivedDate, formatterInput)
        return date.format(formatterOutput)
    }
}