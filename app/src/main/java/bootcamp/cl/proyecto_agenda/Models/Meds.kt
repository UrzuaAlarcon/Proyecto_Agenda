package bootcamp.cl.proyecto_agenda.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    "Meds", foreignKeys =
    [ForeignKey(
        User::class,
        ["id"],
        ["userId"],
        ForeignKey.CASCADE
    )
    ]
)
data class Meds(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var MedsName: String,
    var MedsIndication: String,
    val userId: Int
)
