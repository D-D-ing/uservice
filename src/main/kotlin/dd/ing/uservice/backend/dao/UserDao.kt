package dd.ing.uservice.backend.dao

import dd.ing.uservice.backend.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class  UserDao(
    private val userRepository: UserRepository
) {
    fun getUserById(id: String) = userRepository.findById(id)

    fun getUserByName(name: String) = userRepository.findByNameLike(name)
}
