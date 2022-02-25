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
    info: (message: string) => Promise<undefined>;
    error: (message: string) => Promise<undefined>;
    dialog: (message: string) => Promise<boolean>;
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
declare type REQUEST = typeof import('./uni/request') & typeof import('./axios/request');
declare const req: REQUEST;
export declare function requestConfig(set: (config: Config) => void): void;
export declare function request(config: RequestConfig): Promise<any>;
export declare function get(url: string, config?: RequestConfig): Promise<any>;
export declare function del(url: string, config?: RequestConfig): Promise<any>;
export declare function head(url: string, config?: RequestConfig): Promise<any>;
export declare function options(url: string, config?: RequestConfig): Promise<any>;
export declare function post(url: string, data?: any, config?: RequestConfig): Promise<any>;
export declare function put(url: string, data?: any, config?: RequestConfig): Promise<any>;
export default req;
