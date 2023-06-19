package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import bootcamp.cl.proyecto_agenda.databinding.FragmentNewDocAppoitmentBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewDocAppoitmentFragment : Fragment() {

    lateinit var binding: FragmentNewDocAppoitmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewDocAppoitmentBinding.inflate(layoutInflater,container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newDate.setOnClickListener {

            showDatePickerDialog()

        }

        binding.newTime.setOnClickListener {

            showTimePickerDialog()

        }


    }

    private fun showTimePickerDialog() {

        val timePicker = TimePickerFragment {onTimeSelected(it)}
        timePicker.show(childFragmentManager, "Time")
    }

    private fun onTimeSelected(time:String){

        binding.newTime.setText("$time:00")

    }

    private fun showDatePickerDialog() {

        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(parentFragmentManager,"datePicker")
    }

    private fun onDateSelected(day:Int, month:Int, year:Int){
        binding.newDate.setText(" $day-$month-$year")
    }

    /*private fun saveDateAndTimeSelected():LocalDateTime{

        var selectedDate = binding.newDate.text.toString()
        var selectedTime = binding.newTime.text.toString()

        var formatedDate = formatDate(selectedDate)

        //String que representa la fecha y hora seleccionada
        var dateAndTimeFormat = "$formatedDate $selectedTime"

        var dateAndTime = LocalDateTime.parse(dateAndTimeFormat)

        return dateAndTime

    }

    private fun formatDate(dateSelected:String):String{

        val formatterInput = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formatterOutput = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(dateSelected, formatterInput)
        return date.format(formatterOutput)
    }*/

}