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
import { Response, ValidationError } from '../../../lib/api'
// Some imports not used depending on template conditions
// @ts-ignore

const BASE_PATH = "https://dev.app.cdzkdc.com/api/v1".replace(/^http[s]?:\/\/[\w.]*/, "");

import { AppVersionQueryDto } from './AppVersionQueryDto';
import { AppVersionSaveDto } from './AppVersionSaveDto';
import { AppVersionsDto } from './AppVersionsDto';

/**
 * app版本删除
 * @summary app版本删除
 * @param {number} appId 
 * @param {number} appVersionId 
 * @param {*} [options] Override http request option.
 * @throws {ValidationError}
 */
export function _delete(appId: number, appVersionId: number, options: any = {}): Promise<Response<any>> {
    // verify required parameter 'appId' is not null or undefined
    if (appId === null || appId === undefined) {
        throw new ValidationError('appId','Required parameter appId was null or undefined when calling _delete.');
    }
    // verify required parameter 'appVersionId' is not null or undefined
    if (appVersionId === null || appVersionId === undefined) {
        throw new ValidationError('appVersionId','Required parameter appVersionId was null or undefined when calling _delete.');
    }
    const localVarPath = `/app-version/delete/appId-{appId}/appVersionId-{appVersionId}`
        .replace(`{${"appId"}}`, encodeURIComponent(String(appId)))
        .replace(`{${"appVersionId"}}`, encodeURIComponent(String(appVersionId)));
    // use dummy base URL string because the URL constructor only accepts absolute URLs.
    const localVarUrlObj = new URL(localVarPath, 'https://example.com');
    const localVarRequestOptions = { method: 'DELETE', ...options};
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
    return new Promise<Response<any>>((resolve,reject) => {
        request(localVarRequestOptions).then((res) => {
            resolve(res.data);
        }).catch((res) => {
            reject(res);
        });
    });
}
/**
 * app版本条件查询接口 @Page 
 * @summary app版本条件查询接口
 * @param {string} [name] app名称
 * @param {number} [version] 版本号
 * @param {number} [appId] appId
 * @param {number} [p] 页数
 * @param {*} [options] Override http request option.
 * @throws {ValidationError}
 */
export function getAppVersionList(name?: string, version?: number, appId?: number, p?: number, options: any = {}): Promise<AppVersionsDto> {
    const localVarPath = `/app-version`;
    // use dummy base URL string because the URL constructor only accepts absolute URLs.
    const localVarUrlObj = new URL(localVarPath, 'https://example.com');
    const localVarRequestOptions = { method: 'GET', ...options};
    const localVarHeaderParameter = {} as any;
    const localVarQueryParameter = {} as any;

    if (name !== undefined) {
        localVarQueryParameter['name'] = name;
    }

    if (version !== undefined) {
        localVarQueryParameter['version'] = version;
    }

    if (appId !== undefined) {
        localVarQueryParameter['appId'] = appId;
    }

    if (p !== undefined) {
        localVarQueryParameter['p'] = p;
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
    return new Promise<AppVersionsDto>((resolve,reject) => {
        request(localVarRequestOptions).then((res) => {
            resolve(res.data);
        }).catch((res) => {
            reject(res);
        });
    });
}
/**
 * app版本保存 @Login 
 * @summary app版本保存
 * @param {AppVersionSaveDto} body 
 * @param {*} [options] Override http request option.
 * @throws {ValidationError}
 */
export function save(body: AppVersionSaveDto, options: any = {}): Promise<Response<any>> {
    // verify required parameter 'body' is not null or undefined
    if (body === null || body === undefined) {
        throw new ValidationError('body','Required parameter body was null or undefined when calling save.');
    }
    const localVarPath = `/app-version/save`;
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
 * app版本修改 @Login 
 * @summary app版本修改
 * @param {AppVersionQueryDto} body 
 * @param {*} [options] Override http request option.
 * @throws {ValidationError}
 */
export function update(body: AppVersionQueryDto, options: any = {}): Promise<Response<any>> {
    // verify required parameter 'body' is not null or undefined
    if (body === null || body === undefined) {
        throw new ValidationError('body','Required parameter body was null or undefined when calling update.');
    }
    const localVarPath = `/app-version/update`;
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
_delete,getAppVersionList,save,update,
}