let galleryDiv = document.getElementById("gallery")
let images = galleryDiv.children

let index = 1;
function flipImage() {
    for (let i = 0; i < images.length; i++) {
        images[i].hidden = true;
    }
    images[index].hidden = false
    index = ++index % (images.length)
}

for (let image of images) {
    image.addEventListener("click", flipImage)
}