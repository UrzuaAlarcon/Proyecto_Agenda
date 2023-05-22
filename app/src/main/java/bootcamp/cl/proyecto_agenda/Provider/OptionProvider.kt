package bootcamp.cl.proyecto_agenda.Provider

import bootcamp.cl.proyecto_agenda.Models.Option

class OptionProvider {

    companion object {

        val listOfOptions = mutableListOf<Option>(

            Option(1,
            "Medicamentos",
            "https://postimg.cc/S2HtgkV2][img]https://i.postimg.cc/S2HtgkV2/Red-Cross.png"),

            Option(2,
            "Horas medicas",
            "https://postimg.cc/bGC52QtR][img]https://i.postimg.cc/bGC52QtR/Appointments.png"),

            Option(3,
            "Horas a examenes",
            "https://postimg.cc/XBF1hmD2][img]https://i.postimg.cc/XBF1hmD2/horas-Medicas.png["),

            Option(4,
            "Indicaciones",
            "https://postimg.cc/qgCFz6M8][img]https://i.postimg.cc/qgCFz6M8/Indicaciones.png")



        )


    }

}