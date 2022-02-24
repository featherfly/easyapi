/**
 * 自定义 request 网络请求工具,基于axios
 */
import axios from 'axios';
import { Library } from "../";
const globalConfig = {
    errorHandler: (error, requestConfig) => {
        return Promise.reject(error);
    },
    interceptors: new Array(),
    library: Library.AXIOS,
    timeout: 1000 * 60,
    messager: {
        dialog: (message) => {
            alert(message);
            return Promise.resolve(undefined);
        },
        error: (message) => {
            alert(message);
            return Promise.resolve(undefined);
        },
        notice: (message) => {
            alert(message);
            return Promise.resolve(undefined);
        }
    }
};
export function requestConfig(set) {
    set(globalConfig);
    axiosRequest = createAxiosInstance();
}
function createAxiosInstance() {
    return axios.create({
        // baseURL: env.VITE_WECHAT_HOST, // url = api url + request url
        withCredentials: true,
        timeout: globalConfig.timeout, // 请求超时时间,5000(单位毫秒) / 0 不做限制
    });
}
/**
 * 配置request请求时的默认参数
 */
let axiosRequest = createAxiosInstance();
const _request = (options) => {
    return new Promise((resolve, rejects) => {
        if (options.baseURL == null) {
            options.baseURL = globalConfig.baseURL;
        }
        let method = options.method + "";
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
        }).then((response) => {
            const data = response.data;
            if (data.code == 'OK') {
                return resolve(data);
            }
            else {
                return rejects(response);
            }
        }).catch((error) => {
            globalConfig.errorHandler(error, options);
            rejects(error);
        });
    });
};
// export function request(config: AxiosRequestConfig, cancelShowError?: boolean): AxiosPromise<any> {
//     return axiosRequest(config).then((response: AxiosResponse) => response.data).catch((error) => errorHandler(error, cancelShowError));
// }
export function request(config) {
    return _request(config)
        .then((response) => response.data);
}
export function get(url, config) {
    if (!config) {
        config = {
            url: url,
            method: 'GET'
        };
    }
    return _request(config)
        .then((response) => response.data);
}
export function del(url, config) {
    if (!config) {
        config = {
            url: url,
            method: 'DELETE'
        };
    }
    return _request(config)
        .then((response) => response.data);
}
export function head(url, config) {
    if (!config) {
        config = {
            url: url,
            method: 'HEAD'
        };
    }
    return _request(config)
        .then((response) => { console.log(response); return response.data; });
}
export function options(url, config) {
    if (!config) {
        config = {
            url: url,
            method: 'OPTIONS'
        };
    }
    return _request(config)
        .then((response) => response.data);
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
        .then((response) => response.data);
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
        .then((response) => response.data);
}
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
export default {
    request,
    get,
    post,
    put,
    del,
    head,
    options
    // patch
};
//# sourceMappingURL=request.js.map