import store from '../store.js';

export default {
	title: 'Todo List',
	template: `
		<h1>Todo List</h1>
		<ul></ul>
		<a href="#/newTodo" class="button primary">New Todo</a>
	`,
	init: function(view) {
		let todos = store.getTodos();
		renderTodos(todos, view);
	}
}

function renderTodos(todos, view) {
	let list = view.querySelector('ul');
	list.innerHTML = '';
	if (todos.length === 0)
		list.innerHTML = 'No todos available';
	else todos.forEach(todo => list.append(renderTodo(todo)));
}

function renderTodo(todo) {
	let item = document.createElement('li');
	item.className = 'todo';
	item.innerHTML = `
		<b>${todo.title}</b><br>
		Category: ${todo.category || ''}<br>
		Due Date: ${todo.dueDate || ''}
		<a href="#/editTodo/${todo.id}" class="right">Edit</a>
	`;
	return item;
}
