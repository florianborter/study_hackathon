import binding from '../binding.js';
import router from '../router.js';
import service from '../service.js';
import store from '../store.js';

export default {
	title: 'Edit Todo',
	templateUrl: 'editTodo.html',
	init: function(view, id) {
		let todo = {};
		Object.assign(todo, store.getTodo(id));
		if (todo) {
			binding.bind(view, todo);
			view.querySelector('#save').onclick = event => updateTodo(event, view, todo);
		} else {
			document.querySelector('footer').innerHTML = `Todo ${id} not found`;
		}
	}
}

function updateTodo(event, view, todo) {
	event.preventDefault();
	let form = view.querySelector('form');
	if (!form.reportValidity()) return;
	service.putTodo(store.getUser(), todo)
		.then(todo => {
			store.updateTodo(todo);
			router.navigate('/todoList');
		})
		.catch(error => document.querySelector('footer').innerHTML = 'Unexpected error occurred');
}
