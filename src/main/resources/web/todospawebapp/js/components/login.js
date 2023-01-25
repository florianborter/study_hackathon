import binding from '../binding.js';
import service from '../service.js';
import store from '../store.js';
import router from '../router.js';

export default {
	title: 'Login',
	templateUrl: 'login.html',
	init: function(view) {
		document.querySelector('nav').innerHTML = '';
		let user = {};
		binding.bind(view, user)
		view.querySelector('#login').onclick = event => login(event, view, user);
		view.querySelector('#register').onclick = event => register(event, view, user);
	}
}

function login(event, view, user) {
	event.preventDefault();
	let form = view.querySelector('form');
	if (!form.reportValidity()) return;
	service.getTodos(user)
		.then(todos => {
			store.setUser(user);
			store.setTodos(todos);
			document.querySelector('nav').innerHTML = `User ${user.name} | <a href="#/logout">Logout</a>`;
			router.navigate('/todoList');
		})
		.catch(error => document.querySelector('footer').innerHTML =
			error.status === 401 ? 'Invalid username or password' : 'Unexpected error occurred');
}

function register(event, view, user) {
	event.preventDefault();
	let form = view.querySelector('form');
	if (!form.reportValidity()) return;
	service.postUser(user)
		.then(() => {
			store.setUser(user);
			store.setTodos([]);
			document.querySelector('nav').innerHTML = `User ${user.name} | <a href="#/logout">Logout</a>`;
			router.navigate('/todoList');
		})
		.catch(error => document.querySelector('footer').innerHTML =
			error.status === 409 ? 'User already exists' : 'Unexpected error occurred');
}
