import service from '../service.js';
import router from '../router.js';
import store from '../store.js';

export default {
	title: 'Home',
	template: `
		<h1>Chat Subject</h1>
		<form>
			<select id="subjects" />
			<button id="post">Post</button>
		</form>
		<ul></ul>
		<button id="refresh">Refresh</button>
	`,
	init: function(view) {
		fetchSubjects(view);
		view.querySelector('select').onchange = event => selectSubject(event, view);
		// view.querySelector('#post').onclick = event => postMessage(event, view);
		// view.querySelector('#refresh').onclick = event => fetchMessages(view);
	}
};

function fetchSubjects(view) {
	service.getSubjects()
		.then(subjects => renderSubjects(subjects, view))
		.catch(error => {
			console.log(error)
			document.querySelector('footer').innerHTML = 'Unexpected error occurred'
		});
}

function renderSubjects(subjects, view) {
	let select = view.querySelector('select');
	select.innerHTML = '';
	const defaultElement = document.createElement('option')
	defaultElement.innerHTML = "Select subject";
	select.append(defaultElement);
	for (let subject of subjects) {
		let item = document.createElement('option');
		item.value = subject;
		item.innerHTML = subject;
		select.append(item);
	}
}

function selectSubject(event, view) {
	const subject = view.querySelector('select').value;
	if (subject === '') {
		return;
	}
	store.subject = subject;
	router.navigate('/chat')
}
