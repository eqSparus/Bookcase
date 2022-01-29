
const buildTable = (body) => {
    for (b of body) {
        const book = {
            title:b.title,
            author:b.author,
            isRead:b.read,
            id:b.id
        }
        buildItem(book)
    }
}


window.onload = () => {
    const resJson = fetch("book/all", {
        method: "GET"
    }).then(response => {
        if (response.ok) {
            return response.json()
        }
    })

    resJson.then(res => {
        buildTable(res)
    })
}