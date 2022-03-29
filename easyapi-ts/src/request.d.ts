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

// export type Method =
//     | 'get'
//     | 'GET'
//     | 'delete'
//     | 'DELETE'
//     | 'head'
//     | 'HEAD'
//     | 'options'
//     | 'OPTIONS'
//     | 'post'
//     | 'POST'
//     | 'put'
//     | 'PUT'
//     | 'patch'
//     | 'PATCH'
//     | 'purge'
//     | 'PURGE'
//     | 'link'
//     | 'LINK'
//     | 'unlink'
//     | 'UNLINK';

export type Method = 'OPTIONS' | 'GET' | 'HEAD' | 'POST' | 'PUT' | 'DELETE';
// | 'TRACE'
// | 'CONNECT';

export type ResponseType =
    | 'arraybuffer'
    | 'blob'
    | 'document'
    | 'json'
    | 'text'
    | 'stream';

export interface RequestConfig {
    url: string;
    method?: Method;
    baseURL?: string;
    // transformRequest?: AxiosTransformer | AxiosTransformer[];
    // transformResponse?: AxiosTransformer | AxiosTransformer[];
    headers?: Record<string, string>;
    params?: any;
    paramsSerializer?: (params: any) => string;
    data?: any;
    timeout?: number;
    timeoutErrorMessage?: string;
    withCredentials?: boolean;
    // adapter?: AxiosAdapter;
    // auth?: AxiosBasicCredentials;
    responseType?: ResponseType;
    // xsrfCookieName?: string;
    // xsrfHeaderName?: string;
    // onUploadProgress?: (progressEvent: any) => void;
    // onDownloadProgress?: (progressEvent: any) => void;
    // maxContentLength?: number;
    // validateStatus?: ((status: number) => boolean) | null;
    // maxBodyLength?: number;
    // maxRedirects?: number;
    // socketPath?: string | null;
    // httpAgent?: any;
    // httpsAgent?: any;
    // proxy?: AxiosProxyConfig | false;
    // cancelToken?: CancelToken;
    // decompress?: boolean;
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

export enum Library {
    AXIOS,
    UNI,
}

export interface Config {
    baseURL?: string;
    timeout: number;
    errorHandler: (error: any, requestConfig?: RequestConfig) => Promise<any>;
    interceptors: Array<Interceptor>;
    readonly library: Library;
    messager: Messager;
}

export function requestConfig(set: (config: Config) => void): void;

export function request(config: RequestConfig): Promise<any>;

export function get(url: string, config?: RequestConfig): Promise<any>

export function del(url: string, config?: RequestConfig): Promise<any>

export function head(url: string, config?: RequestConfig): Promise<any>

export function options(url: string, config?: RequestConfig): Promise<any>

export function post(url: string, data?: any, config?: RequestConfig): Promise<any>

export function put(url: string, data?: any, config?: RequestConfig): Promise<any>
