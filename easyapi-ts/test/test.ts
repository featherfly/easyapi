import http, {get,requestConfig, Library} from '../lib/request';

requestConfig((config)=> {
    config.interceptors.push();
    if (config.library == Library.AXIOS) {
    }
});