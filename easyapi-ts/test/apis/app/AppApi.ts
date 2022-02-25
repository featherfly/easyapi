/* tslint:disable */
/* eslint-disable */
/**
 * app
 * app有关的API 
 *
 * OpenAPI spec version: 1.0.0
 * Contact: zhongj@cdzkdc.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
import http, {request} from '../../../';
import { Response, ValidationError } from '../../../'
// Some imports not used depending on template conditions
// @ts-ignore

const BASE_PATH = "https://dev.app.cdzkdc.com/api/v1".replace(/^http[s]?:\/\/[\w.]*/, "");

import { AppQueryDto } from './AppQueryDto';
import { AppSaveDto } from './AppSaveDto';
import { AppsDto } from './AppsDto';
import { PlatformDto } from './PlatformDto';

/**
 * app删除
 * @summary app删除
 * @param {number} [id] appId
 * @param {*} [options] Override http request option.
 * @throws {ValidationError}
 */
export function _delete(id?: number, options: any = {}): Promise<Response<any>> {
    const localVarPath = `/app/delect/id-{id}`;
    // use dummy base URL string because the URL constructor only accepts absolute URLs.
    const localVarUrlObj = new URL(localVarPath, 'https://example.com');
    const localVarRequestOptions = { method: 'DELETE', ...options};
    const localVarHeaderParameter = {} as any;
    const localVarQueryParameter = {} as any;

    if (id !== undefined) {
        localVarQueryParameter['id'] = id;
    }

    const query = new URLSearchParams(localVarUrlObj.search);
    for (const key in localVarQueryParameter) {
        query.set(key, localVarQueryParameter[key]);
    }
    for (const key in options.query) {
        query.set(key, options.query[key]);
    }
    localVarUrlObj.search = (new URLSearchParams(query)).toString();
    let headersFromBaseOptions = {};
    localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

    localVarRequestOptions.url = BASE_PATH + localVarUrlObj.pathname + localVarUrlObj.search + localVarUrlObj.hash;
    return new Promise<Response<any>>((resolve,reject) => {
        request(localVarRequestOptions).then((res) => {
            resolve(res.data);
        }).catch((res) => {
            reject(res);
        });
    });
}
/**
 * app条件查询接口 @Page 
 * @summary app条件查询接口
 * @param {string} [name] app名称
 * @param {string} [code] appCode
 * @param {number} [platformId] 应用平台
 * @param {*} [options] Override http request option.
 * @throws {ValidationError}
 */
export function getAppList(name?: string, code?: string, platformId?: number, options: any = {}): Promise<AppsDto> {
    const localVarPath = `/app`;
    // use dummy base URL string because the URL constructor only accepts absolute URLs.
    const localVarUrlObj = new URL(localVarPath, 'https://example.com');
    const localVarRequestOptions = { method: 'GET', ...options};
    const localVarHeaderParameter = {} as any;
    const localVarQueryParameter = {} as any;

    if (name !== undefined) {
        localVarQueryParameter['name'] = name;
    }

    if (code !== undefined) {
        localVarQueryParameter['code'] = code;
    }

    if (platformId !== undefined) {
        localVarQueryParameter['platformId'] = platformId;
    }

    const query = new URLSearchParams(localVarUrlObj.search);
    for (const key in localVarQueryParameter) {
        query.set(key, localVarQueryParameter[key]);
    }
    for (const key in options.query) {
        query.set(key, options.query[key]);
    }
    localVarUrlObj.search = (new URLSearchParams(query)).toString();
    let headersFromBaseOptions = {};
    localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

    localVarRequestOptions.url = BASE_PATH + localVarUrlObj.pathname + localVarUrlObj.search + localVarUrlObj.hash;
    return new Promise<AppsDto>((resolve,reject) => {
        request(localVarRequestOptions).then((res) => {
            resolve(res.data);
        }).catch((res) => {
            reject(res);
        });
    });
}
/**
 * 平台类型查询接口 
 * @summary 平台类型查询接口
 * @param {*} [options] Override http request option.
 * @throws {ValidationError}
 */
export function getPlatForms(options: any = {}): Promise<Array<PlatformDto>> {
    const localVarPath = `/app/platForm`;
    // use dummy base URL string because the URL constructor only accepts absolute URLs.
    const localVarUrlObj = new URL(localVarPath, 'https://example.com');
    const localVarRequestOptions = { method: 'GET', ...options};
    const localVarHeaderParameter = {} as any;
    const localVarQueryParameter = {} as any;

    const query = new URLSearchParams(localVarUrlObj.search);
    for (const key in localVarQueryParameter) {
        query.set(key, localVarQueryParameter[key]);
    }
    for (const key in options.query) {
        query.set(key, options.query[key]);
    }
    localVarUrlObj.search = (new URLSearchParams(query)).toString();
    let headersFromBaseOptions = {};
    localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};

    localVarRequestOptions.url = BASE_PATH + localVarUrlObj.pathname + localVarUrlObj.search + localVarUrlObj.hash;
    return new Promise<Array<PlatformDto>>((resolve,reject) => {
        request(localVarRequestOptions).then((res) => {
            resolve(res.data);
        }).catch((res) => {
            reject(res);
        });
    });
}
/**
 * app保存 @Login 
 * @summary app保存
 * @param {AppSaveDto} body 
 * @param {*} [options] Override http request option.
 * @throws {ValidationError}
 */
export function save(body: AppSaveDto, options: any = {}): Promise<Response<any>> {
    // verify required parameter 'body' is not null or undefined
    if (body === null || body === undefined) {
        throw new ValidationError('body','Required parameter body was null or undefined when calling save.');
    }
    const localVarPath = `/app/save`;
    // use dummy base URL string because the URL constructor only accepts absolute URLs.
    const localVarUrlObj = new URL(localVarPath, 'https://example.com');
    const localVarRequestOptions = { method: 'POST', ...options};
    const localVarHeaderParameter = {} as any;
    const localVarQueryParameter = {} as any;

    localVarHeaderParameter['Content-Type'] = 'application/json';

    const query = new URLSearchParams(localVarUrlObj.search);
    for (const key in localVarQueryParameter) {
        query.set(key, localVarQueryParameter[key]);
    }
    for (const key in options.query) {
        query.set(key, options.query[key]);
    }
    localVarUrlObj.search = (new URLSearchParams(query)).toString();
    let headersFromBaseOptions = {};
    localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};
    const needsSerialization = (typeof body !== "string") || localVarRequestOptions.headers['Content-Type'] === 'application/json';
    localVarRequestOptions.data =  needsSerialization ? JSON.stringify(body !== undefined ? body : {}) : (body || "");

    localVarRequestOptions.url = BASE_PATH + localVarUrlObj.pathname + localVarUrlObj.search + localVarUrlObj.hash;
    return new Promise<Response<any>>((resolve,reject) => {
        request(localVarRequestOptions).then((res) => {
            resolve(res.data);
        }).catch((res) => {
            reject(res);
        });
    });
}
/**
 * app修改 @Login 
 * @summary app修改
 * @param {AppQueryDto} body 
 * @param {*} [options] Override http request option.
 * @throws {ValidationError}
 */
export function update(body: AppQueryDto, options: any = {}): Promise<Response<any>> {
    // verify required parameter 'body' is not null or undefined
    if (body === null || body === undefined) {
        throw new ValidationError('body','Required parameter body was null or undefined when calling update.');
    }
    const localVarPath = `/app/update`;
    // use dummy base URL string because the URL constructor only accepts absolute URLs.
    const localVarUrlObj = new URL(localVarPath, 'https://example.com');
    const localVarRequestOptions = { method: 'PUT', ...options};
    const localVarHeaderParameter = {} as any;
    const localVarQueryParameter = {} as any;

    localVarHeaderParameter['Content-Type'] = 'application/json';

    const query = new URLSearchParams(localVarUrlObj.search);
    for (const key in localVarQueryParameter) {
        query.set(key, localVarQueryParameter[key]);
    }
    for (const key in options.query) {
        query.set(key, options.query[key]);
    }
    localVarUrlObj.search = (new URLSearchParams(query)).toString();
    let headersFromBaseOptions = {};
    localVarRequestOptions.headers = {...localVarHeaderParameter, ...headersFromBaseOptions, ...options.headers};
    const needsSerialization = (typeof body !== "string") || localVarRequestOptions.headers['Content-Type'] === 'application/json';
    localVarRequestOptions.data =  needsSerialization ? JSON.stringify(body !== undefined ? body : {}) : (body || "");

    localVarRequestOptions.url = BASE_PATH + localVarUrlObj.pathname + localVarUrlObj.search + localVarUrlObj.hash;
    return new Promise<Response<any>>((resolve,reject) => {
        request(localVarRequestOptions).then((res) => {
            resolve(res.data);
        }).catch((res) => {
            reject(res);
        });
    });
}

export default {
_delete,getAppList,getPlatForms,save,update,
}