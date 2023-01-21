const TEMPLATES_ROOT = 'templates/';

const routes = {};

export default {
	register: function(path, component) {
		console.log(`Register component with path ${path}`);
		routes[path] = component;
	},
	navigate: function(path) {
		location.hash = path;
	}
};

window.onhashchange = event => navigate(location.hash.replace('#', ''));

function navigate(path) {
	console.log(`Navigate to path ${path}`);
	let [name, param] = path.split('/').splice(1);
	let component = routes['/' + name];
	if (component)
		show(component, param);
	else console.log(`Component ${name} not found`);
}

async function show(component, param) {
	document.querySelector('footer').innerHTML = '';
	let view = document.createElement('div');
	view.innerHTML = await getTemplate(component);
	component.init(view, param);
	document.title = component.title;
	document.querySelector('main').replaceChildren(view);
}

function getTemplate(component) {
	if (component.template) {
		return component.template;
	} else if (component.templateUrl) {
		return fetch(TEMPLATES_ROOT + component.templateUrl)
			.then(response => response.ok ? response.text() : Promise.reject(response))
			.catch(error => console.log(`Template ${component.templateUrl} not found`));
	} else return '';
}
