# 软件工程大作业

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