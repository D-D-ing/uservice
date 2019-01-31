package dd.ing.uservice.backend.data

import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID
import javax.persistence.Id

@Document(collection = "user")
data class User(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val name: String
)
