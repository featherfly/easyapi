/**
 * 自定义 request 网络请求工具,基于uni.request
 */
import {ApiResponse as ResponseData} from "./request.d"
import {Messager, Config, Interceptor, Library, RequestConfig, Response} from "./request.d";

/**
 *
 * @export
 * @class ValidationError
 * @extends {Error}
 */
 export class ValidationError extends Error {
    constructor(public field: string, msg?: string) {
        super(msg);
        this.name = "ValidationError";
    }
}

const globalConfig : Config = {
    errorHandler: (error: any, requestConfig?: RequestConfig) : Promise<any> => {
        return Promise.reject(error);
    },
    interceptors : new Array<Interceptor>(),
    library : Library.UNI,
    timeout: 1000 * 60,
    messager: {
        dialog: (message: string) : Promise<boolean> =>{
            return new Promise<boolean>(function(resolve, reject) {
                uni.showModal({
                    content: message,
                    success: function (res) {
                        if (res.confirm) {
                            console.log('用户点击确定');
                            resolve(true);
                        } else if (res.cancel) {
                            console.log('用户点击取消');
                            resolve(false);
                        }
                    },
                    fail: function() {
                        reject();
                    }
                });
            });
        },
        error: (message: string) : Promise<undefined> =>{
            return new Promise<undefined>(function(resolve, reject) {
                uni.showToast({
                    title: message,
                    icon: "none",
                    duration: 3000,
                    success: function () {
                        resolve(undefined);
                    },
                    fail: function() {
                        reject();
                    }
                });
            });
        },
        info: (message: string) : Promise<undefined> =>{
            return new Promise<undefined>(function(resolve, reject) {
                uni.showToast({
                    title: message,
                    icon: "none",
                    duration: 2000,
                    success: function () {
                        resolve(undefined);
                    },
                    fail: function() {
                        reject();
                    }
                });
            });
        }
    }
}

export function requestConfig(set : (config: Config) => void) : void {
    set(globalConfig);
}

// const baseUrl = 'http://localhost:8787'
const _request = (options: RequestConfig) : Promise<any> => {
    return new Promise<ResponseData<any>>((resolve, rejects) => {
        if (options.baseURL == null) {
            options.baseURL = globalConfig.baseURL;
        }
        globalConfig.interceptors.forEach((interceptor) => {
            interceptor.request(options);
        });
        uni.request({
            url: options.baseURL ? options.baseURL + options.url : options.url,
            header: options.headers,
                // ...(uni.getStorageSync('token') ? {Authorization: uni.getStorageSync('token')} : {})
            method: options.method || 'GET',
            data: options.data,
            dataType: options.responseType || 'json',
            success: (res: any) => {
                const data: ResponseData<any> = res.data;
                globalConfig.interceptors.forEach((interceptor) => {
                    res.requestConfig = options
                    interceptor.response(res)
                });
                if (data.code == 'OK') {
                    resolve(data);
                } else {
                    rejects(res);
                }
            },
            fail: (e: any) => {
                globalConfig.interceptors.forEach((interceptor) => {
                    e.requestConfig = options
                    interceptor.response(e)
                });
                rejects(e);
            },
        });
    });
};

export function request(
    config: RequestConfig
): Promise<any> {
    return _request(config)
        .then((response: ResponseData<any>) => response.data)
        .catch((error) => globalConfig.errorHandler(error, config));
}

export function get(
    url: string,
    config?: RequestConfig
): Promise<any> {
    if (!config) {
        config = {
            url : url,
            method : 'GET'
        }
    }
    return _request(config)
        .then((response: ResponseData<any>) => response.data)
        .catch((error) => globalConfig.errorHandler(error, config));
    // return axiosRequest.get(url, config).catch((error) => globalConfig.errorHandler(error));
}

export function del(
    url: string,
    config?: RequestConfig
): Promise<any> {
    if (!config) {
        config = {
            url : url,
            method : 'DELETE'
        }
    }
    return _request(config)
        .then((response: ResponseData<any>) => response.data)
        .catch((error) => globalConfig.errorHandler(error, config));
}

export function head(
    url: string,
    config?: RequestConfig
): Promise<any> {
    if (!config) {
        config = {
            url : url,
            method : 'HEAD'
        }
    }
    return _request(config)
        .then((response: ResponseData<any>) => {console.log(response); return response.data})
        .catch((error) => globalConfig.errorHandler(error, config));
}

export function options(
    url: string,
    config?: RequestConfig
): Promise<any> {
    if (!config) {
        config = {
            url : url,
            method : 'OPTIONS'
        }
    }
    return _request(config)
        .then((response: ResponseData<any>) => response.data)
        .catch((error) => globalConfig.errorHandler(error, config));
}

export function post(
    url: string,
    data?: any,
    config?: RequestConfig
): Promise<any> {
    if (!config) {
        config = {
            url : url,
			data: data,
            method : 'POST'
        }
    }
    return _request(config)
        .then((response: ResponseData<any>) => response.data)
        .catch((error) => globalConfig.errorHandler(error, config));
}

export function put(
    url: string,
    data?: any,
    config?: RequestConfig
): Promise<any> {
    if (!config) {
        config = {
            url : url,
			data: data,
            method : 'PUT'
        }
    }
    return _request(config)
        .then((response: ResponseData<any>) => response.data)
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
