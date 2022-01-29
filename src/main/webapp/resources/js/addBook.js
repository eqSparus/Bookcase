
function createErrorMessage (message){
    const errorMessage = document.querySelector("#error > div")
    if (errorMessage !== null) {
        errorMessage.remove()
    }
    const messageContainer = document.querySelector('#error')
    const messageText = document.createElement("div")
    messageText.className = `alert text-center alert-warning`
    messageText.textContent = message.toString()
    messageContainer.appendChild(messageText)
}

document.querySelector("#addBook").onclick = () => {


    const titleBook = document.querySelector("[id=nameBook]"),
        authorBook = document.querySelector("[id=authorBook]"),
        fileBook = document.querySelector("[id=bookFile]")

    const book = {
        title: titleBook.value,
        author: authorBook.value
    }

    const file = fileBook.files[0]


    if(book.title !== "" && book.author !== "" && file !== undefined){

        if(file.type === "application/pdf"){
            const formData = new FormData()
            formData.append("file", file)

            const params = new URLSearchParams({
                title: book.title,
                author: book.author
            })

            console.log(formData.get("file"))

            fetch(`book?${params}`, {
                method: "POST",
                body: formData
            }).then(res => {
                if(res.ok){
                    titleBook.value = ""
                    authorBook.value = ""
                    fileBook.value = null
                    window.location.reload()
                }else {
                    res.json().then(r => createErrorMessage(r.message))
                }
            })
        }else {
            createErrorMessage("Тип файла должен быть pdf")
        }
    }else {
        createErrorMessage("Заполните пустые поля")
    }
}