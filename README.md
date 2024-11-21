# 基础环境配置
## 一、配置git环境
安装Git Bash（使用Mac和Linux可以跳过这一步）：https://gitforwindows.org/
## 二、后端用IDEA、前端用VsCode
踩坑避雷：jdk用8，springboot的版本要小于3
## 三、关于VUE的安装
### 1.VUE官网：https://vuejs.org/

### 2.安装Nodejs——安装地址：https://nodejs.org/en/

### 3.安装@vue/cli
打开Git Bash，执行： `npm i -g @vue/cli `
****如果执行后面的操作有bug，可能是最新版有问题，可以尝试安装早期版本，比如：`npm i -g @vue/cli@4`

### 4.启动vue自带的图形化项目管理界面
终端输入：`vue ui`(不行的话前面加上`sudo`试一下）
常见问题1：Windows上运行vue，提示无法加载文件，表示用户权限不足。
解决方案：用管理员身份打开终端，输入`set-ExecutionPolicy RemoteSigned`，然后输入`y`

vue内部插件安装：vuex和前面的router都安装，直接进界面安装
项目依赖要安装一个jquery 我选择的是3.7.1版本，还有bootstrap，我选择的是5.3.3
依赖中还需添加一个@popperjs/core

ac_app和web都是在VUE中创建的项目，所以都需要安装依赖，同上。

### 前端开发中
可参考MDN官方文档：https://developer.mozilla.org/zh-CN/docs/Web/HTML/Element/a 
可参考Bootstrap官方文档：https://getbootstrap.com/docs/5.3/components/navbar/ 

### 关于VUE目录结构：
```
├── mock                       # 项目mock 模拟数据
├── public                     # 静态资源
│   │── favicon.ico            # favicon图标
├── src                        # 源代码
│   ├── api                    # 所有请求
│   ├── assets                 # 主题 字体等静态资源
│   ├── components             # 全局公用组件
│   ├── directive              # 全局指令
│   ├── filters                # 全局 filter
│   ├── icons                  # 项目所有 svg icons
│   ├── lang                   # 国际化 language
│   ├── layout                 # 全局 layout
│   ├── router                 # 路由
│   ├── store                  # 全局 store管理
│   ├── styles                 # 全局样式
│   ├── utils                  # 全局公用方法
│   ├── vendor                 # 公用vendor
│   ├── views                  # views 所有页面
│   ├── App.vue                # 入口页面
│   ├── main.ts                # 入口文件 加载组件 初始化等
│   └── permission.ts          # 权限管理
├── .env.xxx                   # 环境变量配置
├── .eslintrc.cjs              # eslint 配置项
├── index.html                 # html模板
├── vite.config.ts             # vite 配置
└── package.json               # package.json
```
## 四、关于后端的安装
### 1.安装IDEA 
在新建项目的时候，组：com.kob 工件backend，jdk：corretto-1.8，java:8
选择maven

依赖：web中选择spring web，在Template Engines中选择thymeleaf


## 五、关于数据库
### 1.安装MySQL
#### 1.1下载地址 
windows：https://dev.mysql.com/downloads/windows/installer/8.0.html  
mac我个人推荐homebrew安装，具体先下一个homebrew，然后brew install mysql
#### 1.2安装
windows：Choosing a Setup Type选择Custom 
然后选择MySQL Server ->MySQL Server ->MySQL Server 8.0->MySQL Server 8.0.xxx具体版本看自己安装的
然后都选择默认即可
mac的简单不用说了
#### 1.3配置环境变量
windows：将 `C:\Program Files\MySQL\MySQL Server 8.0\bin`（如果安装到了其他目录，填写相应目录的地址即可）
添加到环境变量PATH中，这样就可以在任意目录的终端中执行`mysql`命令了。
#### 1.4操作
连接用户名：root 密码：123321
创建数据库：
        ```create database kob; ```
咱们项目是科比 我就用了kob

使用数据库：
        ```use kob; ```
创建表：
        ```create table user(id varchar(10) primary key,phone_number varchar(15),user_name varchar(100),password varchar(100),email varchar(10000)); ```
##### 数据库中user有id、电话、用户名、密码、email
##### 测试数据库中密码：
- 马梓涵——123321
- 科比布莱恩特-123456

# 六、SpringBoot
## 1.` SpringBoot `中的常用模块：
- ·` pojo `层：将数据库中的表对应成Java中的Class（table直接翻译成class）
##一个表对应一个pojo 对应一个mapper
- ·` mapper `层（也叫Dao层）：将pojo层的class中的操作，映射成sql语句（将class⬆️中的crud操作转化成sql语句）
- ·` service `层：写具体的业务逻辑，组合使用mapper中的操作（用到多个mapper操作，实现业务）
- ·` controller` 层：负责请求转发，接受页面过来的参数，传给Service处理，接到返回值，再传给页面（调度service）

## 2.关于MyBatis-Plus
官方网站： https://baomidou.com/  


调试：404找不到页面 403:
写API 1.controller 调用service中的接口  2.在service中写接口 3.service->impl中写接口的具体实现




token:"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3NzE3ZDhmYzljNWE0YjJmODM1ZWUxODdiNTQ0MWIwYyIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTczMjA5ODQ3MywiZXhwIjoxNzMzMzA4MDczfQ.oyl5aTeCRlaj8XfFE2zab4MTk4xieBSrKc9pifzHQ80"


