export interface ApiResponse<D> {
    /**
     *
     * @type {string}
     * @memberof Result
     */
    code: string;
    /**
     *
     * @type {string}
     * @memberof Result
     */
    message?: string;
    /**
     *
     * @type {any}
     * @memberof Result
     */
    data: any;
}
/**
 *
 * @export
 * @class ValidationError
 * @extends {Error}
 */
export declare class ValidationError extends Error {
    field: string;
    constructor(field: string, msg?: string);
}
export declare type Method = 'OPTIONS' | 'GET' | 'HEAD' | 'POST' | 'PUT' | 'DELETE';
export declare type ResponseType = 'arraybuffer' | 'blob' | 'document' | 'json' | 'text' | 'stream';
export interface RequestConfig {
    url: string;
    method?: Method;
    baseURL?: string;
    headers?: Record<string, string>;
    params?: any;
    paramsSerializer?: (params: any) => string;
    data?: any;
    timeout?: number;
    timeoutErrorMessage?: string;
    withCredentials?: boolean;
    responseType?: ResponseType;
    props?: Record<string, any>;
}
export interface Response<T = any> {
    data: T;
    status: number;
    statusText: string;
    headers: any;
    config: RequestConfig;
    request?: any;
}
export interface Interceptor {
    request?: (request: RequestConfig) => RequestConfig | Response;
    response?: (response: Response) => Response;
}
export interface Messager {
    notice: (message: string) => Promise<undefined>;
    error: (message: string) => Promise<undefined>;
    dialog: (message: string) => Promise<undefined>;
}
export declare enum Library {
    AXIOS = 0,
    UNI = 1
}
export interface Config {
    baseURL?: string;
    timeout: number;
    errorHandler: (error: any, requestConfig?: RequestConfig) => Promise<any>;
    interceptors: Array<Interceptor>;
    readonly library: Library;
    messager: Messager;
}
declare const requestConfig: typeof import("./uni/request").requestConfig & typeof import("./axios/request").requestConfig;
declare const request: typeof import("./uni/request").request & typeof import("./axios/request").request;
declare const get: typeof import("./uni/request").get & typeof import("./axios/request").get;
declare const post: typeof import("./uni/request").post & typeof import("./axios/request").post;
declare const put: typeof import("./uni/request").put & typeof import("./axios/request").put;
declare const del: typeof import("./uni/request").del & typeof import("./axios/request").del;
declare const head: typeof import("./uni/request").head & typeof import("./axios/request").head;
declare const options: typeof import("./uni/request").options & typeof import("./axios/request").options;
export { requestConfig, request, get, post, put, del, head, options };
declare const _default: {
    request: typeof import("./uni/request").request;
    get: typeof import("./uni/request").get;
    post: typeof import("./uni/request").post;
    put: typeof import("./uni/request").put;
    del: typeof import("./uni/request").del;
    head: typeof import("./uni/request").head;
    options: typeof import("./uni/request").options;
} & {
    request: typeof import("./axios/request").request;
    get: typeof import("./axios/request").get;
    post: typeof import("./axios/request").post;
    put: typeof import("./axios/request").put;
    del: typeof import("./axios/request").del;
    head: typeof import("./axios/request").head;
    options: typeof import("./axios/request").options;
};
export default _default;
