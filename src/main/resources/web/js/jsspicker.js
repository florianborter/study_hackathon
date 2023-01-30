/*********************************/
/****** Types of variables *******/
/*********************************/

// Values of differenet types
let x = 12;
console.log(typeof x);    // number
x = "Hello";
console.log(typeof x);    // string

// Implicit type conversion
let y = 12 + "10";
console.log(typeof y);    // string
y = 12 - "10";
console.log(typeof y);    // number

// Explicit type conversion
y = 12 + Number("10");
console.log(typeof y);    // number


/*********************************/
/****** Equality/identical *******/
/*********************************/
// Equal but not identical
let x = 7;
let y = "7";

console.log(x == y);               // true
console.log(x === y);              // false
console.log(x === parseInt(y));    // true

/*********************************/
/************ Loops **************/
/*********************************/

let colors = ["red", "blue", "green"];

// for loop
for (let i = 0; i < colors.length; i++) {
    console.log(i + ":" + colors[i]);      // 0:red 1:blue 2:green
}

// for...of loop
for (let color of colors) {
    console.log(color);      // red blue green
}

// forEach method
colors.forEach(function(item, index) {
    console.log(index + ":" + item);    // 0:red 1:blue 2:green
});


/*********************************/
/************ Arrays *************/
/*********************************/

// Value insertion
a[a.length] = 7;    // [2, 3, 5, 7]
a[6] = 11;          // [2, 3, 5, 7, , , 11]

// Value removal
a.splice(4, 2);     // [2, 3, 5, 7, 11]

// Array as stack
a.push(13);         // [2, 3, 5, 7, 11, 13]
a.pop();            // [2, 3, 5, 7, 11]

/*********************************/
/*********** Closure *************/
/*********************************/
const initval = 0;

let getCounter = function() {
    let val = initval;           // local variable
    let counter = function() {
        return ++val;
    };
    return counter;
};

let counter1 = getCounter();    // closure
console.log(counter1());        // 1
console.log(counter1());        // 2
console.log(counter1());        // 3

let counter2 = getCounter();    // closure
console.log(counter2());        // 1

/*********************************/
/****** Objects / Prototypes *****/
/*********************************/
// Object literal
let alice = {
    name: "Alice",
    age: 21
};

alice.hobby = "photography";
delete alice.hobby;

alice.say = function(text) {
    console.log(this.name + " says " + text);
}
alice.say("Hello");    // Alice says Hello

// Constructor function
function Person(name, age) {
    this.name = name;
    this.age = age;
    this.say = function(text) { console.log(this.name + " says " + text); };
}

let alice2 = new Person("Alice", 21);
let bob = new Person("Bob", 25);

// Prototype chaining
Student.prototype = Object.create(Person.prototype);
Student.prototype.constructor = Student;

let alice = new Student("Alice", "maths");
alice.say("Hello");                       // Alice says Hello
console.log(alice instanceof Person);     // true
console.log(alice instanceof Student);    // true

// Adding methods
Student.prototype.study = function(subject) {
    console.log(this.name + " studies " + this.subject);
};
alice.say("Hello");    // Alice says Hello
alice.study();         // Alice studies maths


class Student extends Person {
    constructor(name, age, university) {
        super(name, age);
        this.university = university;
    }
    speak(phrase) {
        super.speak(phrase);
        console.log("And I'm a student at the " + this.university);
    }
}


/*********************************/
/********* DOM Traversal *********/
/*********************************/
// Get a HTMLCollection of nodes by tag name
document.getElementsByTagName('a');
// Get a HTMLCollection of nodes by class name
document.getElementsByClassName('external-link');
// A single node can be retrieved by id (only via document)
document.getElementById('logo');
// Elements can also be selected by using a CSS selector string
// A static NodeList is returned
document.querySelectorAll('p a.external-link');
// Like querySelectorAll but only the first matching node is returned
document.querySelector('#logo');

// Root node of the dom tree (html)
const root = document.documentElement;
// The child elements of a node (only element nodes, no text nodes)
// -> HTMLCollection(2) [head, body]
root.children;
// head and body can also be accessed directly
const head = document.head;
const body = document.body;
// All children of a node (including text nodes)
// -> NodeList(7) [#text, h1, #text, p, #text, p, #text]
body.childNodes;
// Accessing children and node type
body.childNodes[0].nodeType === Node.TEXT_NODE; // #text
body.childNodes[1].nodeType === Node.ELEMENT_NODE; // h1
body.firstChild; // #text
body.firstElementChild; // h1
// dito for last: lastChild, lastElementChild

const h1 = document.body.children[0];
// Parent node
h1.parentNode;
// Siblings
h1.nextSibling; // #text
h1.nextElementSibling; // p
// dito for previous: previousSibling, previousElementSibling

myList.insertBefore(myList.lastElementChild, myList.firstElementChild);
myList.lastElementChild.remove();

const li = document.createElement('li');
li.appendChild(document.createTextNode("New item"));
myList.appendChild(li);

// Nodes can also be created implicitly by using the innerHTML property
li.innerHTML = "An <b>important</b> item";

/*********************************/
/************ Events *************/
/*********************************/
// Event handlers can also be removed again
function once() {
    console.log('Done.');
    window.removeEventListener('click', once);
}
window.addEventListener('click', once);

console.log(e.target); // -> The element that triggered the event
console.log(e.button); // -> 0 for left, 2 for right button
console.log(e.pageX); // -> The x coordinate of the mouse pointer
// relative to the document
console.log(e.offsetY); // -> The y coordinate of the mouse pointer
// relative to the position of the target


/*********************************/
/************* AJAX **************/
/*********************************/
// Create a new XMLHttpRequest object
const xhr = new XMLHttpRequest();
// Specify the type of request: method and url
xhr.open('GET', '/ajax/time');
// Register a callback to be notified about state changes
xhr.onreadystatechange = function() {
// Check ready state: 4 means request completed
    if (this.readyState === 4) {
// Check HTTP status: 200 OK
        if (this.status === 200) {
// Update DOM with the response text
            document.getElementById('time').innerHTML = this.responseText;
        } else {
// Error handling...
            console.log("Something failed!");
        }
    }
};
// Send the request
xhr.send();

/*********************************/
/************ JQuery *************/
/*********************************/
// On document ready...
$(function() {
// Set the css property color of each div to red
    $('div').css('color', 'red');
// Load some content for each selected element
    $('.content').each(function(){
        $(this).load('/ajax/content', {id: this.id});
    });
// Register a click event handler. On each click a paragraph is added
// to the article element
    $('#add-content').click(function() {
        $('article').append($("<p>More content...</p>"));
    });
// Make an AJAX request and insert the received HTML into the DOM
    $.get('/ajax/test.html', function(data) {
        $('#result').html(data);
    });
});

$('div')
    .css('background-color', '#f00')
    .addClass('whatever')
    .click(function(){ console.log("Clicked!"); })
    .first()
    .fadeOut(400);

$.ajax({
    url: "/ajax/time",
    type: "GET",
    dataType: "text",
    success: function(data){ $("#time").html(data); },
    error: function(jqXHR, status, msg){ $("#error").html("Error! " + msg); }
});
// GET example with success callback
$.get("/ajax/time", function(data){ $("#time").html(data); });
// Load example
$("#time").load("/ajax/time");
// POST example with success callback and JSON response
$.post(
    "/ajax/get_person_data",
    $('#form').serialize(), // request data
    function(person) { $('#person').html(person.name + " is " + person.age); },
    "json");

/*********************************/
/************** Misc *************/
/*********************************/
const a = [1, 2, 3, 4];
const [first, second] = a;
console.log(first); // >> 1
console.log(second); // >> 2
const x = {a: "foo", b: "bar", c: 12};
const {b, c} = x;
console.log(b); // >> bar
console.log(c); // >> 12

/*********************************/
/******* Asynch / Promises *******/
/*********************************/
function computeAsync() {
    return new Promise((resolve, reject) => {
// ... Perform the asynchronous task (Promise is pending)
        if (success) resolve(result); // Promise will be fulfilled
        else reject(error); // Promise will be rejected
    });
}

//Chaining and Combining Examples
// Chaining
doAsync()
    .then(resultA => { /* ... */ })
    .then(resultB => { /* ... */ })
    .catch(error => { /* ... */ })
    .then(resultC => { /* ... */ })
    .catch(error => { /* ... */ });
// Add multiple handlers to the same promise
const promise = doAsync();
promise.then(result => { /* ... */ });
promise.then(result => { /* ... */ });
// ’Wait’ until all promises are fulfilled or one rejected
Promise.all([doAsync("A"), doAsync("B"), doAsync2()])
    .then(results => { /* ... */ })
    .catch(error => { /* ... */ });
// ’Wait’ until the first promise is fulfilled or rejected
Promise.race([doAsync("A"), doAsync("B"), doAsync2()])
    .then(result => { /* ... */ })
    .catch(error => { /* ... */ });

async function computeDiskArea(radius) {
    try {
        const PI = await computePI();
        const area = radius * radius * PI;
        console.log(`The area of a disk with r=${radius} is ${area}`);
    } catch(error) {
        console.log(`An error occurred: ${error}`);
    }
}

let test = await fetch('http://news.org', {
    method: 'POST',
    headers: {
        'Authorization:': 'Basic amRAZXhhbXBsZS5vcmc6MTIzNDU=',
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(article)
}).then(response => {
    if (!response.ok)
        // Error handling based on HTTP status
        return response.json();
})
    .then(data => {
        //...
    })
    .catch(error => {
        // Error handling (network error, JSON syntax error, etc.)
    });