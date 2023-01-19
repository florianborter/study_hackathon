let alphabet =
    'a=.-;b=-...;c=-.-.;d=-..;e=.;f=..-.;g=--.;h=....;i=..;j=.---;k=-.-;l=.-..;' +
    'm=--;n=-.;o=---;p=.--.;q=--.-;r=.-.;s=...;t=-;u=..-;v=...-;w=.--;x=-..-;' +
    'y=-.--;z=--..; =;.=.-.-.-;,=--..--;?=..--..;!=-.-.--';

function encodeMorse(text) {
    let characters = text.split("");

    let morseLetters = alphabet.split(";");

    let res = "";
    for(let i = 0; i < characters.length; i++) {
        let morseLetter = morseLetters.find(letter => letter.includes(characters[i]));
        res += morseLetter.split("=")[1]
        res += "/"
    }
    return res;
}