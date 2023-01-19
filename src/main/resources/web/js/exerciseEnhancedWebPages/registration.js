let pw1 = document.getElementById("pw1")
let pw2 = document.getElementById("pw2")
let submit = document.getElementById("submit")

let validatePw = function () {
    submit.disabled = !(pw1.value !== "" && pw2.value !== "" && pw1.value === pw2.value);
}

pw1.addEventListener("keyup", validatePw)
pw2.addEventListener("keyup", validatePw)