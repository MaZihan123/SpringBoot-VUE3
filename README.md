# 一、配置git环境
安装Git Bash（使用Mac和Linux可以跳过这一步）：https://gitforwindows.org/
# 二、后端用IDEA、前端用VsCode
踩坑避雷：jdk用8，springboot的版本要小于3
# 三、关于VUE的安装
## 1.VUE官网：https://vuejs.org/

## 2.安装Nodejs——安装地址：https://nodejs.org/en/

## 3.安装@vue/cli
打开Git Bash，执行： `npm i -g @vue/cli `
****如果执行后面的操作有bug，可能是最新版有问题，可以尝试安装早期版本，比如：`npm i -g @vue/cli@4`

## 4.启动vue自带的图形化项目管理界面
终端输入：`vue ui`(不行的话前面加上`sudo`试一下）
常见问题1：Windows上运行vue，提示无法加载文件，表示用户权限不足。
解决方案：用管理员身份打开终端，输入`set-ExecutionPolicy RemoteSigned`，然后输入`y`

vue内部插件安装：vuex和前面的router都安装，直接进界面安装
项目依赖要安装一个jquery 我选择的是3.7.1版本，还有bootstrap，我选择的是5.3.3
依赖中还需添加一个@popperjs/core

ac_app和web都是在VUE中创建的项目，所以都需要安装依赖，同上。

#前端开发中
可参考MDN官方文档：https://developer.mozilla.org/zh-CN/docs/Web/HTML/Element/a 
可参考Bootstrap官方文档：https://getbootstrap.com/docs/5.3/components/navbar/ 








