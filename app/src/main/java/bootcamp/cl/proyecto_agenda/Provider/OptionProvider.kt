package bootcamp.cl.proyecto_agenda.Provider

import bootcamp.cl.proyecto_agenda.Models.Option

class OptionProvider {

    companion object {

        val listOfOptions = mutableListOf<Option>(

            Option(1,
            "Medicamentos",
            "https://i.postimg.cc/kV1tFtQ9/Red-Cross.png"),

            Option(2,
            "Horas medicas",
            "https://i.postimg.cc/ftVSHTCd/Appointments.png"),

            Option(3,
            "Horas a examenes",
            "https://i.postimg.cc/yg2g24pb/horas-Medicas.png"),

            Option(4,
            "Indicaciones",
            "https://i.postimg.cc/CnDBgPWL/Indicaciones.png")



        )


    }

}