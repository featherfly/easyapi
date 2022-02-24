/**
 *
 * @export
 * @class ValidationError
 * @extends {Error}
 */
export class ValidationError extends Error {
    field;
    constructor(field, msg) {
        super(msg);
        this.field = field;
        this.name = "ValidationError";
    }
}
//# sourceMappingURL=api.js.map