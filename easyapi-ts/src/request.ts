// // import {Messager, ResponseData, Config, Interceptor, Library, RequestConfig, Response} from "./request.d";

// // import {Config} from './request.d';

// // export type Method =
// //     | 'OPTIONS'
// //     | 'GET'
// //     | 'HEAD'
// //     | 'POST'
// //     | 'PUT'
// //     | 'DELETE';
// //     // | 'TRACE'
// //     // | 'CONNECT';

// // export type ResponseType =
// //     | 'arraybuffer'
// //     | 'blob'
// //     | 'document'
// //     | 'json'
// //     | 'text'
// //     | 'stream';

// // export interface RequestConfig {
// //     url: string;
// //     method?: Method;
// //     baseURL?: string;
// //     // transformRequest?: AxiosTransformer | AxiosTransformer[];
// //     // transformResponse?: AxiosTransformer | AxiosTransformer[];
// //     headers?: Record<string, string>;
// //     params?: any;
// //     paramsSerializer?: (params: any) => string;
// //     data?: any;
// //     timeout?: number;
// //     timeoutErrorMessage?: string;
// //     withCredentials?: boolean;
// //     // adapter?: AxiosAdapter;
// //     // auth?: AxiosBasicCredentials;
// //     responseType?: ResponseType;
// //     // xsrfCookieName?: string;
// //     // xsrfHeaderName?: string;
// //     // onUploadProgress?: (progressEvent: any) => void;
// //     // onDownloadProgress?: (progressEvent: any) => void;
// //     // maxContentLength?: number;
// //     // validateStatus?: ((status: number) => boolean) | null;
// //     // maxBodyLength?: number;
// //     // maxRedirects?: number;
// //     // socketPath?: string | null;
// //     // httpAgent?: any;
// //     // httpsAgent?: any;
// //     // proxy?: AxiosProxyConfig | false;
// //     // cancelToken?: CancelToken;
// //     // decompress?: boolean;
// //     props?: Record<string, any>;
// // }

// // export interface Response<T = any> {
// //     data: T;
// //     status: number;
// //     statusText: string;
// //     headers: any;
// //     config: RequestConfig;
// //     request?: any;
// // }

// // export interface Interceptor {
// //     request?: (request: RequestConfig) => RequestConfig | Response;
// //     response?: (response: Response) => Response;
// // }

// // export interface Messager {
// //     notice: (message: string) => Promise<undefined>;
// //     error: (message: string) => Promise<undefined>;
// //     dialog: (message: string) => Promise<undefined>;
// // }

// // export enum Library {
// //     AXIOS,
// //     UNI,
// // }

// // export interface Config {
// //     baseURL?: string;
// //     timeout: number;
// //     errorHandler: (error: any, requestConfig?: RequestConfig) => Promise<any>;
// //     interceptors: Array<Interceptor>;
// //     readonly library: Library;
// //     messager: Messager;
// // }

// type REQUEST = typeof import('./uni/request') & typeof import('./axios/request')

// const req = <REQUEST>{ }

// // function importUni() {
// //     import('./uni/request').then((module) => {
// //         Object.assign(req, module)
// //     })
// // }

// // function importAxios() {
// //     import('./axios/request').then((module) => {
// //         Object.assign(req, module)
// //     })
// // }

// // if (import.meta["env"]) {
// //     const env = import.meta["env"];
// //     if (env.EASYAPI_REQUEST_LIB && env.EASYAPI_REQUEST_LIB == 'uni') {
// //         importUni();
// //     } else {
// //         importAxios();
// //     }
// // } else if (process.env) {
// //     const env = process.env;
// //     if (env.EASYAPI_REQUEST_LIB && env.EASYAPI_REQUEST_LIB == 'uni') {
// //         importUni();
// //     } else {
// //         importAxios();
// //     }
// // } else {
// //     importAxios();
// // }

// let env;

// if (import.meta["env"]) {
//     env = import.meta["env"];
// } else if (process.env) {
//     env = process.env;
// }

// if (env && env.EASYAPI_REQUEST_LIB && env.EASYAPI_REQUEST_LIB == 'uni') {
//     import('./uni/request').then((module) => {
//         Object.assign(req, module)
//     })
// } else {
//     import('./axios/request').then((module) => {
//         Object.assign(req, module)
//     })
// }

// const requestConfig = req.requestConfig;
// const request = req.request;
// const get = req.get;
// const post = req.post;
// const put = req.put;
// const del = req.del;
// const head = req.head;
// const options = req.options;

// export {requestConfig, request, get, post, put, del, head, options};

// export default req.default;