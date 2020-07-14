package signInBlock

class SignInPresenter {

    private val view = SignInFragment()
    private val repository = SignInRepository()

    fun onSignInButtonClicked(email: String, password: String) {
        if (!repository.checkEmail(email)) {
            view.signInFailed(ErrorType.EMAIL_IS_NOT_REGISTERED)
        }
        else if (!repository.getPassword(email).equals(password)) {
            view.signInFailed(ErrorType.PASSWORD_IS_NOT_CORRECT)
        }
        else {
            TODO("Чекнуть внутренней хранилище на наличие информации профиля. Если ее нет - " +
                    "запросить у модела и все передать в функцию")
            view.showProfile("something")
        }
    }
}