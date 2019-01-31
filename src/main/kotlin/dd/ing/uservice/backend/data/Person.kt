package dd.ing.uservice.backend.data

import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID
import javax.persistence.Id

@Document(collection = "person")
data class Person(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val name: String
)
