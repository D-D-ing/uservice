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
import java.time.LocalDate
import java.time.ZoneId
import java.util.UUID
import java.util.Date


@Component
class UserDao(
    private val userRepository: UserRepository
) {
    var key: Key = Keys.secretKeyFor(SignatureAlgorithm.HS512)

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

                val now = LocalDate.now()
                val expiration = now.plusDays(7)

                user.token = Jwts.builder()
                    .setIssuer("uservice")
                    .setSubject("login")
                    .setAudience("dding")
                    .setExpiration(Date.from(expiration.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                    .setIssuedAt(Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                    .setId(UUID.randomUUID().toString()).signWith(key).compact()
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
