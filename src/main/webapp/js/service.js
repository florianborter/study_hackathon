const BASE_URI = '/api3';

export default {
	getMessages: function(subject) {
		let settings = {
			url: BASE_URI + `/messages?subject=${subject}`,
			type: 'GET',
			dataType: 'json'
		};
		console.log('Sending ' + settings.type + ' request to ' + settings.url);
		return $.ajax(settings);
	},
	postMessage: function(message) {
		let settings = {
			url: BASE_URI + '/messages',
			type: 'POST',
			dataType: 'json',
			contentType: 'application/json',
			data: JSON.stringify(message)
		};
		console.log('Sending ' + settings.type + ' request to ' + settings.url);
		return $.ajax(settings);
	},
	getSubjects: function() {
		let settings = {
			url: BASE_URI + '/subjects',
			type: 'get',
			dataType: 'json',
		};
		console.log('Sending ' + settings.type + ' request to ' + settings.url);
		return $.ajax(settings);
	}
};
