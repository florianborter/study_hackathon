const BASE_URI = '/api2';

export default {
	postUser: function(user) {
		let url = BASE_URI + '/users';
		let options = {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(user)
		};
		console.log('Send ' + options.method + ' request to ' + url);
		return fetch(url, options)
			.then(response => response.ok ? response.text() : Promise.reject(response));
	},

	getTodos: function(user) {
		let url = BASE_URI + '/todos';
		let options = {
			method: 'GET',
			headers: { 'Authorization': 'Basic ' + btoa(user.name + ':' + user.password) }
		};
		console.log('Send ' + options.method + ' request to ' + url);
		return fetch(url, options)
			.then(response => response.ok ? response.json() : Promise.reject(response));
	},

	getTodo: function(user, id) {
		let url = BASE_URI + '/todos/' + id;
		let options = {
			method: 'GET',
			headers: { 'Authorization': 'Basic ' + btoa(user.name + ':' + user.password) }
		};
		console.log('Send ' + options.method + ' request to ' + url);
		return fetch(url, options)
			.then(response => response.ok ? response.json() : Promise.reject(response));
	},

	postTodo: function(user, todo) {
		let url = BASE_URI + '/todos';
		let options = {
			method: 'POST',
			headers: {
				'Authorization': 'Basic ' + btoa(user.name + ':' + user.password),
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(todo)
		};
		console.log('Send ' + options.method + ' request to ' + url);
		return fetch(url, options)
			.then(response => response.ok ? response.json() : Promise.reject(response));
	},

	putTodo: function(user, todo) {
		let url = BASE_URI + '/todos/' + todo.id;
		let options = {
			method: 'PUT',
			headers: {
				'Authorization': 'Basic ' + btoa(user.name + ':' + user.password),
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(todo)
		};
		console.log('Send ' + options.method + ' request to ' + url);
		return fetch(url, options)
			.then(response => response.ok ? response.json() : Promise.reject(response));
	}
};
