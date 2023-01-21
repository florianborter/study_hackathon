function getQuote() {
    return fetch( "https://distsys.ch/services/quote.php")
        .then(response =>{
            //do sth with response
            if (!response.ok) {
                console.log("Error occured", response)
                return;
            }
            // Error handling based on HTTP status
           return response.json()
        })
        .then(data => {
            return translate(data.text);
        })
        .catch(error => {
            console.log("Error occurred", error)
            return "error occurred"
        })
}

function translate(text) {
    return fetch("https://distsys.ch/services/translate.php",
        {method: "post",
            body: `text: ${text}`})
        .then(response => {
            //do sth with response
            if (!response.ok) {
                console.log("Error occurred", response)
                return "error occurred";
            }
            // Error handling based on HTTP status
            return response.json()
        })
        .catch(error => {
            console.log("Error occurred", error);
            return "error occurred";
            })
}

function getTranslatedQuote() {
    Promise.all([getQuote(), getQuote(), getQuote()])
        .then(results => {console.log("resultate:", results)})

}