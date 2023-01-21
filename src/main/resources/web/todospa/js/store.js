let data = {};

export default {
	setUser: function(user) {
		console.log('Set user in store');
		data.user = user;
	},
	getUser: function() {
		console.log('Get user from store');
		return data.user;
	},
	setTodos: function(todos) {
		console.log('Set todos in store');
		data.todos = todos;
	},
	getTodos: function() {
		console.log('Get todos from store');
		return data.todos;
	},
	getTodo: function(id) {
		console.log(`Get todo ${id} from store`);
		return data.todos.find(todo => todo.id == id);
	},
	addTodo: function(todo) {
		console.log(`Add todo ${todo.id} to store`);
		if (!data.todos) return;
		data.todos.push(todo);
	},
	updateTodo: function(todo) {
		console.log(`Update todo ${todo.id} in store`);
		Object.assign(this.getTodo(todo.id), todo);
	},
	clear: function() {
		data = {};
	}
};
