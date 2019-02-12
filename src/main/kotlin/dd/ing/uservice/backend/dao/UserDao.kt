package dd.ing.uservice.backend.dao

import dd.ing.uservice.backend.data.User
import dd.ing.uservice.backend.graphql.input.AuthDataInput
import dd.ing.uservice.backend.repository.UserRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.security.Key


@Component
class UserDao(
    private val userRepository: UserRepository
) {
    var key: Key = Keys.secretKeyFor(SignatureAlgorithm.HS256)

    fun getUserById(id: String) = userRepository.findById(id)

    fun getUserByName(name: String) = userRepository.findByNameLike(name)

    fun getUserByEmail(email: String) = userRepository.findByEmailLike(email)

    fun registerUser(name: String, authData: AuthDataInput): User {
        return userRepository.save(
            User(
                name = name,
                email = authData.email,
                password = BCryptPasswordEncoder().encode(authData.password)
            )
        )
    }

    fun loginUser(input: AuthDataInput): User? {
        try {
            val user = getUserByEmail(input.email)

            if (!user.enabled) throw Exception("The account for the user ${input.email} is disabled.")
            if (!user.accountNonExpired) throw Exception("The account for the user ${input.email} is expired")
            if (!user.accountNonLocked) throw Exception("The account for the user ${input.email} is locked.")
            if (!user.credentialsNonExpired) throw Exception("The credentials for the user ${input.email} are expired")

            if (BCryptPasswordEncoder().matches(input.password, user.password)) {
                print(key)
                user.token = Jwts.builder().setSubject("Joe").signWith(key).compact()
                return user
            } else {
                throw Exception("The password provided is wrong.")
            }
        } catch (e: EmptyResultDataAccessException) {
            throw Exception("The user with the email ${input.email} cannot be found.")
        }
    }

    fun logoutUser(email: String): Boolean {
        return true
    }
}
