package dd.ing.uservice.backend.graphql.query

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import dd.ing.uservice.backend.dao.UserDao
import org.springframework.stereotype.Component

@Component
class UserQueryResolver(
    private val userDao: UserDao
) : GraphQLQueryResolver {
    fun user(id: String) =
        userDao.getUserById(id)

    fun userByName(name: String) =
        userDao.getUserByName(name)

    fun userByEmail(email: String) =
            userDao.getUserByEmail(email)
}
