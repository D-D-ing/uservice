package dd.ing.uservice.backend.dao

import dd.ing.uservice.backend.data.User
import dd.ing.uservice.backend.graphql.input.AuthDataInput
import dd.ing.uservice.backend.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserDao(
    private val userRepository: UserRepository
) {
    fun getUserById(id: String) = userRepository.findById(id)

    fun getUserByName(name: String) = userRepository.findByNameLike(name)

    fun getUserByEmail(email: String) = userRepository.findByEmailLike(email)

    fun registerUser(name: String, authData: AuthDataInput): User {
        return User(name = name, email = authData.email, password = authData.password)
    }

    fun loginUser(input: AuthDataInput): User {
        return getUserByEmail(input.email)
    }

    fun logoutUser(email: String): Boolean {
        return true
    }
}
