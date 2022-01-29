const alertError = "alert-danger"
const alertOkResponse = "alert-primary"
const alertWarning = "alert-warning"

const createMessage = (message, blockStyle) => {
    const messageDiv = document.querySelector("#message > div")
    if (messageDiv !== null) {
        messageDiv.remove()
    }

    const messageContainer = document.querySelector('#message')
    const messageText = document.createElement("div")
    messageText.className = `alert text-center ${blockStyle}`
    messageText.textContent = message.toString()
    messageContainer.appendChild(messageText)
}


document.querySelector('#registration').onclick = () => {

    const login = document.querySelector('[id=login]'),
        password = document.querySelector('[id=password]'),
        repeatPassword = document.querySelector('[id=repeatPassword]')

    const user = {
        login: login.value,
        password: password.value,
        repeatPassword: repeatPassword.value
    }

    if (user.login !== "" && user.password !== "" && user.repeatPassword !== "") {
        if (user.password !== user.repeatPassword) {
            createMessage("Пароли не совпадают", alertWarning)
        } else {
            const jsonRes = fetch('registration', {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    login: user.login,
                    password: user.password
                })
            }).then(response => {
                return response
            })

            jsonRes.then(res => {
                if (res.ok) {
                    res.json().then(m => createMessage(m.message, alertOkResponse))
                } else {
                    res.json().then(m => createMessage(m.message, alertError))
                }
            })
        }
    } else {
        createMessage("Заполните все поля", alertWarning)
    }

}
