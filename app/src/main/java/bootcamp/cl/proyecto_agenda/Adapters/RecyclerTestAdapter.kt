package bootcamp.cl.proyecto_agenda.Adapters;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bootcamp.cl.proyecto_agenda.Interfaces.Recyclers.RecyclerTestAppointments
import bootcamp.cl.proyecto_agenda.Models.TestAppointment
import bootcamp.cl.proyecto_agenda.databinding.ItemTestAppointmentBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RecyclerTestAdapter(
    private var listTestAppointments: MutableList<TestAppointment>,
    private var testAppointmentListener: RecyclerTestAppointments
) : RecyclerView.Adapter<RecyclerTestAdapter.ViewHolderTest>() {

    /**
     * Creates a new ViewHolderTest instance.
     */

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolderTest {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTestAppointmentBinding.inflate(inflater, parent, false)
        return ViewHolderTest(binding)
    }

    /**
     * Binds the data to the ViewHolder and sets up click listeners.
     */

    override fun onBindViewHolder(holder: ViewHolderTest, position: Int) {
        val testAppointment = listTestAppointments[position]
        holder.bind(testAppointment)
    }

    /**
     * Returns the number of items in the adapter.
     */
    override fun getItemCount(): Int {
        return listTestAppointments.size
    }

    /**
     * Deletes a specific DocAppointment from the adapter's list.
     */
    fun deleteTestAppointments(testAppointment: TestAppointment) {

        val index = listTestAppointments.lastIndexOf(testAppointment)
        if (index != -1) {

            listTestAppointments.removeAt(index)
            notifyItemChanged(index)
            notifyDataSetChanged()

        }

    }

    /**
     * Sets the list of DocAppointments for the adapter.
     */
    fun setTestAppointmentsList(testAppointment: MutableList<TestAppointment>) {

        listTestAppointments = testAppointment
        notifyDataSetChanged()

    }

    /**
     * ViewHolder class representing each item in the RecyclerView.
     */
    inner class ViewHolderTest(private val binding: ItemTestAppointmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the data to the ViewHolder's views.
         */

        fun bind(testAppointment: TestAppointment) {
            val dateTimeSplit = testAppointment.dateAndTime.toString().split("T")
            var date = formatDate(dateTimeSplit.first())
            var time = dateTimeSplit.last()

            binding.TestType.text = testAppointment.testType
            binding.DateAndTime.text = "fecha:$date / hora $time"
            binding.Location.text = testAppointment.location
            binding.Indications.text = testAppointment.indication

            binding.btnDeleteTestAppointment.setOnClickListener {
                testAppointmentListener.onClick(testAppointment, adapterPosition)
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
