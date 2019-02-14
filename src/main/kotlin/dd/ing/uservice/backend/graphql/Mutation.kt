package dd.ing.uservice.backend.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import dd.ing.uservice.backend.dao.UserDao
import dd.ing.uservice.backend.graphql.input.AuthDataInput
import org.springframework.stereotype.Component

@Component
class Mutation(private val userDao: UserDao) : GraphQLMutationResolver {

    fun registerUser(name: String, input: AuthDataInput) =
        userDao.registerUser(name, AuthDataInput(input.email, input.password))


    fun loginUser(input: AuthDataInput) = userDao.loginUser(input)

    fun logoutUser(email: String) = userDao.logoutUser(email)
}
