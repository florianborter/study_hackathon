import binding from '../binding.js';
import router from '../router.js';
import service from '../service.js';
import store from '../store.js';

export default {
	title: 'New Todo',
	templateUrl: 'newTodo.html',
	init: function(view) {
		let todo = {};
		binding.bind(view, todo);
		view.querySelector('#save').onclick = event => addTodo(event, view, todo);
	}
}

function addTodo(event, view, todo) {
	event.preventDefault();
	let form = view.querySelector('form');
	if (!form.reportValidity()) return;
	service.postTodo(store.getUser(), todo)
		.then(todo => {
			store.addTodo(todo);
			router.navigate('/todoList');
		})
		.catch(error => document.querySelector('footer').innerHTML = 'Unexpected error occurred');
}
