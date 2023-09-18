import com.example.detection.ui.authen.repo.AuthenticationRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class TokenManager @Inject constructor(authRepository: AuthenticationRepository) {
    private val authRepository: AuthenticationRepository

    init {
        this.authRepository = authRepository
    }

    val bearerToken: String
        get() = authRepository.getToken()

    /**
     * Token validation now is based on local timestamp. So it could be invalid
     * in case user adjust time in the phone.
     * This method is used to handle that case by forcing to get a new token without local validation.
     */
    fun forceRefreshToken(): Single<Boolean> {
        return authRepository.refreshToken()
    }
}
