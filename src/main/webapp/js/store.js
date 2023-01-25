let data = {};

export default {
    setSubject: function (subject) {
        console.log("set subject into store")
        data.subject = subject;
        console.log(data);
    },
    getSubject: function () {
        console.log("get subject from store");
        console.log(data);
        return data.subject;
    }
};
