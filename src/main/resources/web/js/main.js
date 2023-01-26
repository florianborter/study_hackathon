import service from './service.js';

let main = document.querySelector('main');
main.innerHTML = 'Loading quotes, please wait...';

function displayQuote(quote) {
    main.innerHTML += `<p>${quote.text}<br>&mdash; ${quote.author || 'Unbekannter Autor'}</p>`
}

function displayError(error) {
    main.innerHTML = `<p>Error: ${error.statusText} (${error.status})</p>`;
}

let promises = [];
for (let i = 0; i < 3; i++) {
    promises[i] = service.getTranslatedQuote();
}
Promise.all(promises)
    .then(quotes => {
        main.innerHTML = '';
        quotes.forEach(quote => displayQuote(quote));
    })

/*
let quotes = [];
let count = 0;
while (count < 3) {
	try {
		quotes[count] = await service.getTranslatedQuote();
		count++;
	} catch (error) {
	}
}
main.innerHTML = '';
quotes.forEach(quote => displayQuote(quote));
*/
