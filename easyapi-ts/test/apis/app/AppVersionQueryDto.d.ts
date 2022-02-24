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
import { AppVersionDto } from './AppVersionDto';
import { PlatformDto } from './PlatformDto';
/**
 * app查询对象
 * @export
 * @interface AppVersionQueryDto
 */
export interface AppVersionQueryDto extends AppVersionDto {
    /**
     * 主键id
     * @type {number}
     * @memberof AppVersionQueryDto
     */
    id?: number;
    /**
     * app名称
     * @type {string}
     * @memberof AppVersionQueryDto
     */
    appName?: string;
    /**
     * appCode
     * @type {string}
     * @memberof AppVersionQueryDto
     */
    appCode?: string;
    /**
     * app操作系统
     * @type {string}
     * @memberof AppVersionQueryDto
     */
    appPlatformName?: string;
}
