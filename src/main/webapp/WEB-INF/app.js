$(function () {
    //DODAWANIE KSIĄŻKI

    var bookAddForm = $('.bookAddForm');
    var bookAddSubmit = $('button[type="submit"]');

    var bookTitle = $('#bookTitle');
    var bookAuthor = $('#bookAuthor');
    var bookPublisher = $('#bookPublisher');
    var bookType = $('#bookType');
    var bookISBN = $('#bookISBN');

    bookAddSubmit.on('click', function (e) {
        e.preventDefault();

        //tworzenie nowego obiektu książki, który wyślemy do API
        var newBook = {
            title: bookTitle.val(),
            author: bookAuthor.val(),
            publisher: bookPublisher.val(),
            type: bookType.val(),
            isbn: bookISBN.val()
        };
        //wysyłamy dane ajaxem do apki
        $.ajax({
            url: 'http://localhost:8282/books/',
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(newBook),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).done(function (result) {
            console.log(result);
            location.reload();
        });
    });
    //KONIEC


    // funcja tworząca HTML książki
    $.ajax({
        url: 'http://localhost:8282/books/',
        type: 'GET',
        dataType: 'json',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).done(function (result) {
        //result to lista książek trzeba ją wyświetlić na głównej stronie
        for (var i = 0; i < result.length; i++) {
            var newTitle = $('<h4 id="' + result[i].id + '">');
            newTitle.text('Tytuł ' + result[i].id + ': ' + result[i].title);
            bookAddForm.append(newTitle);

            var newInformation = $('<div></div><');
            newInformation.hide();
            bookAddForm.append(newInformation);

            var deleteLink = $('<a href="http://localhost:8282/books/' + result[i].id + '">USUŃ KSIĄŻKĘ</a>');
            bookAddForm.append(deleteLink);
        }
    });


    bookAddForm.on('click', 'h4', function () {
        console.log('h4 is clicked');
        var nextDiv = $(this).next();
        nextDiv.html('');
        nextDiv.show();
        var readBookId = $(this).attr('id');
        // console.log(readBookId);  //spr czy dobrze wczytuje id
        $.ajax({
            url: 'http://localhost:8282/books/' + readBookId,
            type: 'GET',
            dataType: 'json',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).done(function (result) {
            //result to książka dla którego rozwijamy diva
            var newAut = $('<p>Autor: ' + result.author + '</p>');
            var newPub = $('<p>Wydawca: ' + result.publisher + '</p>');
            var newIsbn = $('<p>Isbn: ' + result.isbn + '</p>');
            var newTyp = $('<p>Typ: ' + result.type + '</p>');
            nextDiv.append(newAut);
            nextDiv.append(newPub);
            nextDiv.append(newTyp);
            nextDiv.append(newIsbn);
        });
    });


    bookAddForm.on('click','a',function (e) {
        e.preventDefault();
        var urlLink = $(this).attr('href');
        $.ajax({
            url: urlLink,
            type: 'DELETE',
            dataType: 'json',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).done(function (result) {
            console.log('usuwanie');
            location.reload();
        })

    })
});
