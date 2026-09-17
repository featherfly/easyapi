# 0.4.0 2026-09-17
feat:
1. convertor generate copy methods toTarget(Target object) and fromTarget(Target object)
2. convertor generate method add javadoc  

# 0.3.0 2026-09-03
feat:
1. code generation for conversion when request parameters are defined as enum
2. add BeanCodegen setter and getter for EasyapiModuleJavaCodegen

fix:
1. fix proeprty convertor for [String|Integer|Long] to Enum

chore:
1. upgrade dependencies

# 0.2.3 2026-08-18
feat:
1. add convertor for model object with x-convertor argument in model part

chore:
1. upgrade dependencies

# 0.2.2 2026-03-05
1. 修复@Tag未导入的问题

# 0.2.1 2026-03-05
1. 修复@Tag未导入的问题

# 0.2.0 2026-03-04
1. 使用自定义的模板加载器，只需要在自定义的模板目录存放修改的模板，未修改模板使用swagger-code-generator默认的 

# 0.1.0 2024-06-18
1. 完成openapi3的适配