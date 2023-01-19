function getQuote() {
    // Create a new XMLHttpRequest object
    const xhr = new XMLHttpRequest();
    // Specify the type of request: method and url
    xhr.open('GET', 'https://distsys.ch/services/quote.php');
    // Register a callback to be notified about state changes
    xhr.onreadystatechange = function () {
        // Check ready state: 4 means request completed
        if (this.readyState === 4) {
            // Check HTTP status: 200 OK
            if (this.status === 200) {
                // Update DOM with the response text
                let data = JSON.parse(this.responseText)
                console.log(data);
                document.getElementById('quote').innerHTML = data.text;
                document.getElementById('author').innerHTML = data.author;
            } else {
                // Error handling...
                document.getElementById('quote').innerHTML = "Something failed!";
                console.log("Something failed!");
            }
        }
    };
    // Send the request
    xhr.send();
}