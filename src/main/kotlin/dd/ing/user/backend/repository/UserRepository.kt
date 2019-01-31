package dd.ing.user.backend.repository

import dd.ing.user.backend.data.Person

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : MongoRepository<Person, String> {
    fun findByNameLike(name: String): List<Person>
}
