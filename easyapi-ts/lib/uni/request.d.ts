import { Config, RequestConfig } from "../";
export declare function requestConfig(set: (config: Config) => void): void;
export declare function request(config: RequestConfig): Promise<any>;
export declare function get(url: string, config?: RequestConfig): Promise<any>;
export declare function del(url: string, config?: RequestConfig): Promise<any>;
export declare function head(url: string, config?: RequestConfig): Promise<any>;
export declare function options(url: string, config?: RequestConfig): Promise<any>;
export declare function post(url: string, data?: any, config?: RequestConfig): Promise<any>;
export declare function put(url: string, data?: any, config?: RequestConfig): Promise<any>;
declare const _default: {
    request: typeof request;
    get: typeof get;
    post: typeof post;
    put: typeof put;
    del: typeof del;
    head: typeof head;
    options: typeof options;
};
export default _default;
