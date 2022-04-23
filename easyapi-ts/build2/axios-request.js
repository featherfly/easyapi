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
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.put = exports.post = exports.options = exports.head = exports.del = exports.get = exports.request = exports.requestConfig = exports.ValidationError = void 0;
/**
 * 自定义 request 网络请求工具,基于axios
 */
var axios_1 = __importDefault(require("axios"));
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
    library: request_d_1.Library.AXIOS,
    timeout: 1000 * 60,
    messager: {
        dialog: function (message) {
            alert(message);
            return Promise.resolve(true);
        },
        error: function (message) {
            alert(message);
            return Promise.resolve(undefined);
        },
        info: function (message) {
            alert(message);
            return Promise.resolve(undefined);
        }
    }
};
function requestConfig(set) {
    set(globalConfig);
    axiosRequest = createAxiosInstance();
}
exports.requestConfig = requestConfig;
function createAxiosInstance() {
    return axios_1.default.create({
        // baseURL: env.VITE_WECHAT_HOST, // url = api url + request url
        withCredentials: true,
        timeout: globalConfig.timeout, // 请求超时时间,5000(单位毫秒) / 0 不做限制
    });
}
/**
 * 配置request请求时的默认参数
 */
var axiosRequest = createAxiosInstance();
var _request = function (options) {
    return new Promise(function (resolve, rejects) {
        if (options.baseURL == null) {
            options.baseURL = globalConfig.baseURL;
        }
        globalConfig.interceptors.forEach(function (interceptor) {
            interceptor.request(options);
        });
        var method = options.method + "";
        return axiosRequest({
            url: options.url,
            method: options.method || 'GET',
            baseURL: options.baseURL,
            headers: options.headers,
            params: options.params,
            paramsSerializer: options.paramsSerializer,
            data: options.data,
            timeout: options.timeout,
            timeoutErrorMessage: options.timeoutErrorMessage,
            withCredentials: options.withCredentials,
            responseType: options.responseType || 'json',
        }).then(function (response) {
            var data = response.data;
            globalConfig.interceptors.forEach(function (interceptor) {
                interceptor.response({
                    data: data,
                    status: response.status,
                    statusText: response.statusText,
                    headers: response.headers,
                    requestConfig: options,
                });
            });
            if (data.code == 'OK') {
                return resolve(data.data);
            }
            else {
                return rejects(response);
            }
        }).catch(function (error) {
            globalConfig.interceptors.forEach(function (interceptor) {
                interceptor.response({
                    data: error.data,
                    status: error.status,
                    statusText: error.statusText,
                    headers: error.headers,
                    requestConfig: options,
                });
            });
            globalConfig.errorHandler(error, options);
            rejects(error);
        });
    });
};
// export function request(config: AxiosRequestConfig, cancelShowError?: boolean): AxiosPromise<any> {
//     return axiosRequest(config).then((response: AxiosResponse) => response.data).catch((error) => errorHandler(error, cancelShowError));
// }
function request(config) {
    return _request(config);
}
exports.request = request;
function get(url, config) {
    if (!config) {
        config = {
            url: url,
            method: 'GET'
        };
    }
    return _request(config);
}
exports.get = get;
function del(url, config) {
    if (!config) {
        config = {
            url: url,
            method: 'DELETE'
        };
    }
    return _request(config);
}
exports.del = del;
function head(url, config) {
    if (!config) {
        config = {
            url: url,
            method: 'HEAD'
        };
    }
    return _request(config);
}
exports.head = head;
function options(url, config) {
    if (!config) {
        config = {
            url: url,
            method: 'OPTIONS'
        };
    }
    return _request(config);
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
    return _request(config);
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
    return _request(config);
}
exports.put = put;
// export function patch(
//     url: string,
//     data?: any,
//     config?: RequestConfig
// ): Promise<any> {
//     if (!config) {
//         config = {
//             url : url,
// 			data: data,
//             method : 'PATCH'
//         }
//     }
//     return _request(config)
//         .then((response: ResponseData<any>) => response.data);
// }
exports.default = {
    request: request,
    get: get,
    post: post,
    put: put,
    del: del,
    head: head,
    options: options,
    requestConfig: requestConfig
    // patch
};
