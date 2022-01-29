function buildItem (book) {
    const tableBody = document.querySelector('#tableBody'),
        tr = document.createElement("tr"),
        thTitle = document.createElement("th"),
        thAuthor = document.createElement("th"),
        thLink = document.createElement("th"),
        thIsRead = document.createElement("th"),
        thDelete = document.createElement("th"),
        link = document.createElement("a")

    link.className = "btn btn-outline-primary"
    link.textContent = "Скачать"
    link.href = `book/download/${book.id}`

    thTitle.textContent = book.title
    thAuthor.textContent = book.author
    thLink.appendChild(link)
    thIsRead.appendChild(buildUpdateIsReadButton(book.id, book.isRead))
    thDelete.appendChild(buildDeleteButton(book.id))

    tr.appendChild(thTitle)
    tr.appendChild(thAuthor)
    tr.appendChild(thLink)
    tr.appendChild(thIsRead)
    tr.appendChild(thDelete)

    tableBody.appendChild(tr)
}
