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

## 图表组件

> 安装echarts： npm install echarts

在template中定义一个div，设置id后，用事件触发渲染即可

``` js
    // 基于准备好的dom，初始化echarts实例
    let myChart = this.$echarts.init(document.getElementById('chartId'), null, {
        width: 390,
        height: 300
    });
    // 指定图表的配置项和数据
    var option = {
    title: {
        text: 'ECharts 入门示例'
    },
    tooltip: {},
    legend: {
        data: ['销量']
    },
    xAxis: {
        data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
    },
    yAxis: {},
    series: [
        {
        name: '销量',
        type: 'bar',
        data: [5, 20, 36, 10, 10, 20]
        }
    ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
```

# 后端必看

## yml配置

打开`money_keeper\backend\src\main\resources\application.yml`
根据提示修改一下配置文件即可

## 数据库

新建一个数据库，然后控制台执行`money_keeper.sql`里的代码即可。