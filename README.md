# 前端必看

## 路由

> 安装路由： npm install vue-router

``` js
// 一种写法
this.$router.push('/login')

//另一种写法，让组件展示在占位符view处
<router-link to='/login'></router-link>
<router-view><router-view>
```
调用组件需要先在router/routes.js文件下注册路径，搂一眼就知道咋写了

## 调用后台接口

> 安装axios： npm install axios

``` js
this.$axios.get('http://localhost:8080/接口地址', 参数可选)
    .then(res=>res.data).then(res=>{
        console.log(res) // 控制台打印返回结果观察
        obj = res // 将结果res保存在任意位置
    })
```

# 后端必看

## yml配置

打开`money_keeper\backend\src\main\resources\application.yml`
根据提示修改一下配置文件即可

## 数据库

新建一个数据库，然后控制台执行`money_keeper.sql`里的代码即可，当前包含三个表，user、bill以及category，
各个字段的意义都有注释