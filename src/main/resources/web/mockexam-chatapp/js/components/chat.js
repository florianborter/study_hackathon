import service from '../service.js';
import store from '../store.js';
import router from '../router.js';

export default {
	title: 'Chat',
	template: `
		<h1>Chat</h1>
		<form>
			<input name="message" placeholder="Enter new message" class="large" required>
			<button id="post">Post</button>
		</form>
		<ul></ul>
		<button id="refresh">Refresh</button>
	`,
	init: function(view) {
		if (!store.subject) {
			router.navigate('/');
		}

		fetchMessages(view);
		view.querySelector('#post').onclick = event => postMessage(event, view);
		view.querySelector('#refresh').onclick = event => fetchMessages(view);
	}
};

function fetchMessages(view) {
	service.getMessages(store.subject)
		.then(messages => renderMessages(messages, view))
		.catch(error => {
			console.log(error)
			document.querySelector('footer').innerHTML = 'Unexpected error occurred'
		});
}

function renderMessages(messages, view) {
	let list = view.querySelector('ul');
	list.innerHTML = '';
	for (let message of messages) {
		let item = document.createElement('li');
		item.innerHTML = message.text;
		list.append(item);
	}
}

function postMessage(event, view) {
	event.preventDefault();
	let form = view.querySelector('form');
	if (!form.reportValidity()) return;
	let message = {
		text: form.message.value
	};
	service.postMessage(message)
		.then(message => {
			fetchMessages(view);
			form.message.value = '';
		})
		.catch(error => document.querySelector('footer').innerHTML = 'Unexpected error occurred');
}
