function buildUpdateIsReadButton(id, isRead) {

    const button = document.createElement("input")

    const params = new URLSearchParams({
        id: id,
        isRead: !isRead
    })

    button.onclick = () =>{
        fetch(`book?${params}`,{
            method:"PUT"
        }).then(res => {
            if(res.ok){
                window.location.reload()
            }
        })
    }

    button.type = "button"

    if(isRead){
        button.value = "Прочитано"
        button.className = "btn btn-primary"
    }else {
        button.value = "Не прочитано"
        button.className = "btn btn-danger"
    }

    return button;
}


function buildDeleteButton (id){

    const button = document.createElement("input")
    button.type = "button"
    button.className = "btn btn-danger text-dark"
    button.value = "Удалить"

    const params = new URLSearchParams({
        id: id,
    })

    button.onclick = () =>{
        fetch(`book?${params}`,{
            method:"DELETE"
        }).then(res => {
            if(res.ok){
                window.location.reload()
            }
        })
    }

    return button;
}
