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
import { AppVersionQueryDto } from './AppVersionQueryDto';
/**
 * 问题返回结果集
 * @export
 * @interface AppVersionsDto
 */
export interface AppVersionsDto {
    /**
     * 
     * @type {Array&lt;AppVersionQueryDto&gt;}
     * @memberof AppVersionsDto
     */
    appVersions?: Array<AppVersionQueryDto>;
    /**
     * 总数
     * @type {number}
     * @memberof AppVersionsDto
     */
    total?: number;
}
