
package dd.ing.uservice.backend.repository

import dd.ing.uservice.backend.data.User

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User, String> {
    fun findByNameLike(name: String): List<User>
}
