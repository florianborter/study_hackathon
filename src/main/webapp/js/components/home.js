import service from '../service.js';
import status from '../status.js';
import router from "../router.js";
import store from "../store.js";

const template = `
	<div>
	    <label for="subjects">Choose a Subject:</label>
		<select name="subjects" id="subjects">
		</select>
		<br><br>
	    <label for="newSubject">Create new Subject:</label>
	    <input type="text" id="newSubject" name="newSubject" placeholder="New Subject">
	    <button type="button" id="addSubject">Add Subject</button>
	</div>
`;

export default {
    title: 'Chat',
    render: function () {
        let $view = $(template);
        fetchSubjects($view);
        $('#subjects', $view).change(event => loadChats(event, $view));
        $('#addSubject', $view).click(() => addSubject($view))
        return $view;
    }
};

function fetchSubjects($view) {
    service.getSubjects()
        .then(subjects => renderSubjects(subjects, $view))
        .catch(xhr => status.error('Unexpected error (' + xhr.status + ')'));
}

function renderSubjects(subjects, $view) {
    let dropdown = $('#subjects', $view);
    dropdown.empty();
    dropdown.append(`<option value="">Select Option</option>`)
    for (let subject of subjects) {
        dropdown.append(addOption(subject));
    }
}

function addOption(subject) {
    return `<option value="${subject}">${subject}</option>`
}

function loadChats(event, $view) {
    for (let option of $('#subjects', $view).children()) {
        if (option.selected) {
            store.setSubject(option.value);
        }
    }

    router.navigate("/chat")
}

function addSubject($view) {
    console.log($('#newSubject', $view));
    let subject = $('#newSubject', $view)[0].value;
    store.setSubject(subject);

    router.navigate("/chat")
}