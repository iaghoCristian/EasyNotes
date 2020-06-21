import com.example.easynotes.model.Frequency
import java.io.Serializable
import java.util.*

class Note: Serializable{
    var id: Long;
    var title: String;
    var resume: String;
    var description: String;
    var creationDate: Date;
    var frequency: Frequency;
    var alert: Date;


    constructor(
        id: Long,
        title: String,
        resume: String,
        description: String,
        creationDate: Date,
        frequency: Frequency,
        alert: Date
    ) {
        this.id = id
        this.title = title
        this.resume = resume
        this.description = description
        this.creationDate = creationDate
        this.frequency = frequency
        this.alert = alert
    }
}