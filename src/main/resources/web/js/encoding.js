function CharCoding(character, code) {
    this.character = character;
    this.code = code;
}

function Coding(name, charCodings, delimiter) {
    this.name = name;
    this.charCodings = charCodings;
    this.delimiter = delimiter;
}

Coding.prototype.encodeChar = function (char) {
    for(let coding of this.charCodings) {
        if (coding.character === char) return coding.code;
    }
    return "?";
}

Coding.prototype.encode = function (text) {
    let encoded = "";
    for (let character of text) {
        encoded += this.encodeChar(character) + this.delimiter;
    }

    return encoded;
}


let morseAlphabet =
    'a=.-;b=-...;c=-.-.;d=-..;e=.;f=..-.;g=--.;h=....;i=..;j=.---;k=-.-;l=.-..;' +
    'm=--;n=-.;o=---;p=.--.;q=--.-;r=.-.;s=...;t=-;u=..-;v=...-;w=.--;x=-..-;' +
    'y=-.--;z=--..; =;.=.-.-.-;,=--..--;?=..--..;!=-.-.--';

let charCodings = [];
for (let pair of morseAlphabet.split(";")) {
    let [char, code] = pair.split("="); //shortcut for: let char = pair.split("=")[0]; let code = pair.split("=")[1];
    charCodings.push(new CharCoding(char, code))
}

let morseCoding = new Coding("morse", charCodings, "/");
morseCoding.encode = function (text) {
    return Object.getPrototypeOf(this).encode.call(this, text.toLowerCase());
}

function test(text) {
    console.log(morseCoding.encode(text));
}