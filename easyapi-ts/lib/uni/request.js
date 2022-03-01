import { Library } from "../";
const globalConfig = {
    errorHandler: (error, requestConfig) => {
        return Promise.reject(error);
    },
    interceptors: new Array(),
    library: Library.UNI,
    timeout: 1000 * 60,
    messager: {
        dialog: (message) => {
            return new Promise(function (resolve, reject) {
                uni.showModal({
                    content: message,
                    success: function (res) {
                        if (res.confirm) {
                            console.log('用户点击确定');
                            resolve(true);
                        }
                        else if (res.cancel) {
                            console.log('用户点击取消');
                            resolve(false);
                        }
                    },
                    fail: function () {
                        reject();
                    }
                });
            });
        },
        error: (message) => {
            return new Promise(function (resolve, reject) {
                uni.showToast({
                    title: message,
                    icon: "none",
                    duration: 3000,
                    success: function () {
                        resolve(undefined);
                    },
                    fail: function () {
                        reject();
                    }
                });
            });
        },
        info: (message) => {
            return new Promise(function (resolve, reject) {
                uni.showToast({
                    title: message,
                    icon: "none",
                    duration: 2000,
                    success: function () {
                        resolve(undefined);
                    },
                    fail: function () {
                        reject();
                    }
                });
            });
        }
    }
};
export function requestConfig(set) {
    set(globalConfig);
}
// const baseUrl = 'http://localhost:8787'
const _request = (options) => {
    return new Promise((resolve, rejects) => {
        if (options.baseURL == null) {
            options.baseURL = globalConfig.baseURL;
        }
        uni.request({
            url: options.baseURL ? options.baseURL + options.url : options.url,
            header: options.headers,
            // ...(uni.getStorageSync('token') ? {Authorization: uni.getStorageSync('token')} : {})
            method: options.method || 'GET',
            data: options.data,
            dataType: options.responseType || 'json',
            success: (res) => {
                const data = res.data;
                if (data.code == 'OK') {
                    resolve(data);
                }
                else {
                    rejects(res);
                }
            },
            fail: (e) => {
                rejects(e);
            },
        });
    });
};
export function request(config) {
    return _request(config)
        .then((response) => response.data)
        .catch((error) => globalConfig.errorHandler(error, config));
}
export function get(url, config) {
    if (!config) {
        config = {
            url: url,
            method: 'GET'
        };
    }
    return _request(config)
        .then((response) => response.data)
        .catch((error) => globalConfig.errorHandler(error, config));
    // return axiosRequest.get(url, config).catch((error) => globalConfig.errorHandler(error));
}
export function del(url, config) {
    if (!config) {
        config = {
            url: url,
            method: 'DELETE'
        };
    }
    return _request(config)
        .then((response) => response.data)
        .catch((error) => globalConfig.errorHandler(error, config));
}
export function head(url, config) {
    if (!config) {
        config = {
            url: url,
            method: 'HEAD'
        };
    }
    return _request(config)
        .then((response) => { console.log(response); return response.data; })
        .catch((error) => globalConfig.errorHandler(error, config));
}
export function options(url, config) {
    if (!config) {
        config = {
            url: url,
            method: 'OPTIONS'
        };
    }
    return _request(config)
        .then((response) => response.data)
        .catch((error) => globalConfig.errorHandler(error, config));
}
export function post(url, data, config) {
    if (!config) {
        config = {
            url: url,
            data: data,
            method: 'POST'
        };
    }
    return _request(config)
        .then((response) => response.data)
        .catch((error) => globalConfig.errorHandler(error, config));
}
export function put(url, data, config) {
    if (!config) {
        config = {
            url: url,
            data: data,
            method: 'PUT'
        };
    }
    return _request(config)
        .then((response) => response.data)
        .catch((error) => globalConfig.errorHandler(error, config));
}
export default {
    request,
    get,
    post,
    put,
    del,
    head,
    options,
    requestConfig
};
//# sourceMappingURL=request.js.map