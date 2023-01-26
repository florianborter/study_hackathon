const quoteUrl = 'https://distsys.ch/services/quote.php';
const translateUrl = 'https://distsys.ch/services/translate.php';

export default {
    getQuote: function() {
        return fetch(quoteUrl)
            .then(response => response.ok ? response.json() : Promise.reject(response));
    },

    translate: function(text) {
        return fetch(translateUrl + '?text=' + text)
            .then(response => response.ok ? response.text() : Promise.reject(response));
    },

    getTranslatedQuote: function() {
        let translatedQuote = {};
        return this.getQuote()
            .then(quote => {
                translatedQuote.author = quote.author;
                return this.translate(quote.text)
            })
            .then(translatedText => {
                translatedQuote.text = translatedText;
                return translatedQuote;
            })
            .catch(error => this.getTranslatedQuote());
    }

    /*
    getTranslatedQuote: async function() {
        let quote = await this.getQuote();
        let translatedText = await this.translate(quote.text);
        return { text: translatedText, author: quote.author };
    }
    */
};
