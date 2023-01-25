import service from '../service.js';
import status from '../status.js';
import store from "../store.js";

const template = `
	<div>
		<h1>Chat</h1>
		<form>
			<input id="message" placeholder="Enter new message" class="large" required>
			<button id="postMessage">Post</button>
		</form>
		<ul id="messages"></ul>
		<a href="#/home">Home</a>
	</div>
`;

export default {
	title: 'Chat',
	render: function() {
		let $view = $(template);
		fetchMessages($view);
		let timeout = setInterval(timedUpdate, 10000, $view);
		$('#postMessage', $view).click(event => postMessage(event, $view));
		$('#refresh', $view).click(event => fetchMessages($view));
		$('h1', $view).text(store.getSubject() + " Chat");
		$('a', $view).click(() => clearInterval(timeout));
		return $view;
	}
};

function fetchMessages($view) {
	service.getMessages(store.getSubject())
		.then(messages => renderMessages(messages, $view))
		.catch(xhr => status.error('Unexpected error (' + xhr.status + ')'));
}

function renderMessages(messages, $view) {
	let $messages = $('#messages', $view);
	$messages.empty();
	for (let message of messages) {
		let $item = $('<li>').text(message.text);
		$messages.append($item);
	}
}

function postMessage(event, $view) {
	event.preventDefault();
	if (!$('form', $view)[0].reportValidity()) return;
	let message = {
		text: $('#message', $view).val(),
		subject: store.getSubject()
	};
	service.postMessage(message)
		.then(message => {
			fetchMessages($view);
			$('#message', $view).val('');
		})
		.catch(xhr => status.error('Unexpected error (' + xhr.status + ')'));
}

function timedUpdate($view) {
	console.log("Hi, refreshing the chats for you :D")
	fetchMessages($view)
}