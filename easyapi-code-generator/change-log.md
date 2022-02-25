# 0.1.14 2022-02-24
1. 修复ts模型文件属性去掉public
2. 升级ts模型then的模式，因为请求easyapi-ts请求后已经在then内返回了res.data，所以res.data替换为res,并从easyapi-ts导入引用类型

# 0.1.13 2021-09-27
1. 代码生成boolean类型的getter使用标准getXxx代替之前的isXxx

# 0.1.12 2021-08-20
1. 修正没有components时报空指针的问题