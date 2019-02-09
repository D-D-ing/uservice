package dd.ing.uservice.backend.data

import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID
import javax.persistence.Id

@Document(collection = "user")
data class User(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val email: String,
    val password: String,
    val token: String? = null,
    var version: Int = 0,
    var accountNonExpired: Boolean = true,
    var accountNonLocked: Boolean = true,
    var credentialsNonExpired: Boolean = true,
    var enabled: Boolean = true

)
