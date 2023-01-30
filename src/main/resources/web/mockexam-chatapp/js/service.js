const BASE_URI = '/api';

export default {
	getMessages: function(subject) {
		let url = BASE_URI + '/messages?subject=' + subject;
		console.log('Send GET request to ' + url);
		return fetch(url)
			.then(response => response.ok ? response.json() : Promise.reject(response));
	},
	getSubjects: function() {
		let url = BASE_URI + '/subjects';
		console.log('Send GET request to ' + url);
		return fetch(url)
			.then(response => response.ok ? response.json() : Promise.reject(response));
	},

	postMessage: function(message) {
		let url = BASE_URI + '/messages';
		let options = {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify(message)
		};
		console.log('Send POST request to ' + url);
		return fetch(url, options)
			.then(response => response.ok ? response.json() : Promise.reject(response));
	}
};
