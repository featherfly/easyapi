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
