/**
 *
 * @export
 * @class ValidationError
 * @extends {Error}
 */
export class ValidationError extends Error {
    constructor(public field: string, msg?: string) {
        super(msg);
        this.name = "ValidationError";
    }
}