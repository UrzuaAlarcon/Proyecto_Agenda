package bootcamp.cl.proyecto_agenda.Provider

import bootcamp.cl.proyecto_agenda.Models.Meds

class MedsProvider {

    companion object {

        val listOfMeds = mutableListOf<Meds>(

            Meds(1,
                "Losartan",
            "1 comprimido de 50 mg cada 12 horas (mañana y noche)",
                1
            ),

            Meds(
                2,
                "Metformina",

                "2 comprimidos de 850 mg al dia, antes del almuerzo y antes d ela cena",
                1
            )

        )


    }

}