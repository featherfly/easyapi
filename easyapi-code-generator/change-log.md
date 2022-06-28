# 0.3.0 2022-06-28

1. GenerateCode加入specFolder参数，设置后会把spec文件复制到该目录（和sourceFolder一样，都是output的子目录）

# 0.2.2 2022-04-22

1. 修正生成POJO的@ApiModel value值不对导致UI界面没有参数显示的问题
2. 为Controller的@Api 加入tags信息

# 0.2.0 2022-04-11

1. TypeScriptAxiosCodegen加入secondModule支持
2. 修正springmvc @Page @Login加入多个后少逗号的问题

# 0.1.15 2022-03-07

1. 找不到configFile对应的文件抛出异常
2. 加入secondModule属性

# 0.1.14 2022-02-24

1. 修复ts模型文件属性去掉public
2. 升级ts模型then的模式，因为请求easyapi-ts请求后已经在then内返回了res.data，所以res.data替换为res,并从easyapi-ts导入引用类型

# 0.1.13 2021-09-27

1. 代码生成boolean类型的getter使用标准getXxx代替之前的isXxx

# 0.1.12 2021-08-20

1. 修正没有components时报空指针的问题