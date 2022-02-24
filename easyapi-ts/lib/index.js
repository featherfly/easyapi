export var Library;
(function (Library) {
    Library[Library["AXIOS"] = 0] = "AXIOS";
    Library[Library["UNI"] = 1] = "UNI";
})(Library || (Library = {}));
const req = {};
// function importUni() {
//     import('./uni/request').then((module) => {
//         Object.assign(req, module)
//     })
// }
// function importAxios() {
//     import('./axios/request').then((module) => {
//         Object.assign(req, module)
//     })
// }
// if (import.meta["env"]) {
//     const env = import.meta["env"];
//     if (env.EASYAPI_REQUEST_LIB && env.EASYAPI_REQUEST_LIB == 'uni') {
//         importUni();
//     } else {
//         importAxios();
//     }
// } else if (process.env) {
//     const env = process.env;
//     if (env.EASYAPI_REQUEST_LIB && env.EASYAPI_REQUEST_LIB == 'uni') {
//         importUni();
//     } else {
//         importAxios();
//     }
// } else {
//     importAxios();
// }
let env;
if (import.meta["env"]) {
    env = import.meta["env"];
}
else if (process.env) {
    env = process.env;
}
if (env && env.EASYAPI_REQUEST_LIB && env.EASYAPI_REQUEST_LIB == 'uni') {
    import('./uni/request').then((module) => {
        Object.assign(req, module);
    });
}
else {
    import('./axios/request').then((module) => {
        Object.assign(req, module);
    });
}
const requestConfig = req.requestConfig;
const request = req.request;
const get = req.get;
const post = req.post;
const put = req.put;
const del = req.del;
const head = req.head;
const options = req.options;
export { requestConfig, request, get, post, put, del, head, options };
export default req.default;
//# sourceMappingURL=index.js.map