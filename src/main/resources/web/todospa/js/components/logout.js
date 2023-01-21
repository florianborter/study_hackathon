import store from '../store.js';

export default {
	title: 'Logout',
	template: `
		<h1>Logout</h1>
		<p>You have successfully logged out</p>
	`,
	init: function() {
		store.clear();
		document.querySelector('nav').innerHTML = '<a href="#/login">Login</a>';
	}
}
