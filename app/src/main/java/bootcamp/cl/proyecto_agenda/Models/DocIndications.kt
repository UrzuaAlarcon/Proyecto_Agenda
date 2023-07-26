package bootcamp.cl.proyecto_agenda.Models

data class DocIndications (
    val id:Long = 0,
    val diagnostic:String,
    val indication:String?,
    val imagePath:String?,
    val userId:String
        )