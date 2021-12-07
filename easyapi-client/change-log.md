# 0.3.1 2021-11-17
1. 依赖兼容性升级

# 0.3.0 2021-11-17
1. 升级common-serialization:0.2.2以解决android调试卡死的问题
2. 升级common-http:0.5.2
3. DomainManagerImpl加入域名分组
4. 加入HttpRequestManager,实现按api type分组HttpRequest

# 0.2.2 2021-03-08
1. 修复HttpFormParamApiInvoker参数类型错误导致请求出错

# 0.2.1 2021-03-07
1. 加入EmptyDataResult
2. 升级common-model-api

# 0.2.0 2021-03-05
1. 升级common-http
2. AbstractHttpRequest,DefaultHttpRequest去掉构造函数(Environment, HttpRequestConfig)