let acc = document.getElementsByClassName("accordion");
let i;

for (i = 0; i < acc.length; i++) {
    acc[i].addEventListener("click", function() {
        /* Toggle between hiding and showing the active panel */
        let article = this.nextElementSibling;
        if (article.style.display === "block") {
            article.style.display = "none";
        } else {
            article.style.display = "block";
        }
    });
}