package bootcamp.cl.proyecto_agenda.Provider

import bootcamp.cl.proyecto_agenda.Models.Meds
import bootcamp.cl.proyecto_agenda.Models.Option

class MedsProvider {

    companion object {

        val listOfMeds = mutableListOf<Meds>(

            Meds(
                "Losartan",
            "1 comprimido de 50 mg cada 12 horas (mañana y noche)"
            ),

            Meds(
                "Metformina",

                "2 comprimidos de 850 mg al dia, antes del almuerzo y antes d ela cena"
            )




        )


    }

}