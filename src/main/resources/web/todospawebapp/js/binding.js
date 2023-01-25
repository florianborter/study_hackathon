export default {
	bind: function(view, object) {
		view.querySelectorAll('input').forEach(element => {
			let name = element.getAttribute('name');
			element.value = object[name] || '';
			element.onchange = event => object[name] = element.value;
		});
	}
};
