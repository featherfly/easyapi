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
if (process.env) {
    env = process.env;
}
else if (import.meta && import.meta["env"]) {
    env = import.meta["env"];
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
export function requestConfig(set) {
    req.requestConfig(set);
}
export function request(config) {
    return req.request(config);
}
export function get(url, config) {
    return req.get(url, config);
}
export function del(url, config) {
    return req.del(url, config);
}
export function head(url, config) {
    return req.head(url, config);
}
export function options(url, config) {
    return req.options(url, config);
}
export function post(url, data, config) {
    return req.post(url, data, config);
}
export function put(url, data, config) {
    return req.put(url, data, config);
}
export default req;
//# sourceMappingURL=index.js.map