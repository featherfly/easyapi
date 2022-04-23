"use strict";
var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
Object.defineProperty(exports, "__esModule", { value: true });
exports.put = exports.post = exports.options = exports.head = exports.del = exports.get = exports.request = exports.requestConfig = exports.ValidationError = void 0;
var request_d_1 = require("./request.d");
/**
 *
 * @export
 * @class ValidationError
 * @extends {Error}
 */
var ValidationError = /** @class */ (function (_super) {
    __extends(ValidationError, _super);
    function ValidationError(field, msg) {
        var _this = _super.call(this, msg) || this;
        _this.field = field;
        _this.name = "ValidationError";
        return _this;
    }
    return ValidationError;
}(Error));
exports.ValidationError = ValidationError;
var globalConfig = {
    errorHandler: function (error, requestConfig) {
        return Promise.reject(error);
    },
    interceptors: new Array(),
    library: request_d_1.Library.UNI,
    timeout: 1000 * 60,
    messager: {
        dialog: function (message) {
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
        error: function (message) {
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
        info: function (message) {
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
function requestConfig(set) {
    set(globalConfig);
}
exports.requestConfig = requestConfig;
// const baseUrl = 'http://localhost:8787'
var _request = function (options) {
    return new Promise(function (resolve, rejects) {
        if (options.baseURL == null) {
            options.baseURL = globalConfig.baseURL;
        }
        globalConfig.interceptors.forEach(function (interceptor) {
            interceptor.request(options);
        });
        uni.request({
            url: options.baseURL ? options.baseURL + options.url : options.url,
            header: options.headers,
            // ...(uni.getStorageSync('token') ? {Authorization: uni.getStorageSync('token')} : {})
            method: options.method || 'GET',
            data: options.data,
            dataType: options.responseType || 'json',
            success: function (res) {
                var data = res.data;
                globalConfig.interceptors.forEach(function (interceptor) {
                    res.requestConfig = options;
                    interceptor.response(res);
                });
                if (data.code == 'OK') {
                    resolve(data);
                }
                else {
                    rejects(res);
                }
            },
            fail: function (e) {
                globalConfig.interceptors.forEach(function (interceptor) {
                    e.requestConfig = options;
                    interceptor.response(e);
                });
                rejects(e);
            },
        });
    });
};
function request(config) {
    return _request(config)
        .then(function (response) { return response.data; })
        .catch(function (error) { return globalConfig.errorHandler(error, config); });
}
exports.request = request;
function get(url, config) {
    if (!config) {
        config = {
            url: url,
            method: 'GET'
        };
    }
    return _request(config)
        .then(function (response) { return response.data; })
        .catch(function (error) { return globalConfig.errorHandler(error, config); });
    // return axiosRequest.get(url, config).catch((error) => globalConfig.errorHandler(error));
}
exports.get = get;
function del(url, config) {
    if (!config) {
        config = {
            url: url,
            method: 'DELETE'
        };
    }
    return _request(config)
        .then(function (response) { return response.data; })
        .catch(function (error) { return globalConfig.errorHandler(error, config); });
}
exports.del = del;
function head(url, config) {
    if (!config) {
        config = {
            url: url,
            method: 'HEAD'
        };
    }
    return _request(config)
        .then(function (response) { console.log(response); return response.data; })
        .catch(function (error) { return globalConfig.errorHandler(error, config); });
}
exports.head = head;
function options(url, config) {
    if (!config) {
        config = {
            url: url,
            method: 'OPTIONS'
        };
    }
    return _request(config)
        .then(function (response) { return response.data; })
        .catch(function (error) { return globalConfig.errorHandler(error, config); });
}
exports.options = options;
function post(url, data, config) {
    if (!config) {
        config = {
            url: url,
            data: data,
            method: 'POST'
        };
    }
    return _request(config)
        .then(function (response) { return response.data; })
        .catch(function (error) { return globalConfig.errorHandler(error, config); });
}
exports.post = post;
function put(url, data, config) {
    if (!config) {
        config = {
            url: url,
            data: data,
            method: 'PUT'
        };
    }
    return _request(config)
        .then(function (response) { return response.data; })
        .catch(function (error) { return globalConfig.errorHandler(error, config); });
}
exports.put = put;
exports.default = {
    request: request,
    get: get,
    post: post,
    put: put,
    del: del,
    head: head,
    options: options,
    requestConfig: requestConfig
};
