<template>
    <div class="background">
        <!-- 标题 -->
        <div class="title1">
            <div class="exit"><img src="../assets/image/左箭头.png" alt="" @click="goback"></div>
            {{ year }}年{{ month }}月账单
        </div>
        <!-- 总览 -->
        <div class="box" v-if="isShow1">
            <div class="title2">{{ month }}月账单</div>
            <div id="summary">本月结余：</div>
            <div id="money">{{ balance.toFixed(2) }}</div>
            <div style="margin: 10px 0; vertical-align: middle;">
                <!-- 支出比例条条 -->
                <span class="barInfo">支出</span>
                <span class="bar" :style="{ width: - 240 * expense / (income - expense) + 'px' }"></span>
                <span class="barInfo">{{ expense.toFixed(2) }}</span>
            </div>
            <div style="margin: 10px 0; vertical-align: middle;">
                <!-- 收入比例条条 -->
                <span class="barInfo">收入</span>
                <span class="bar" :style="{ width: 240 * income / (income - expense) + 'px' }"></span>
                <span class="barInfo">{{ income.toFixed(2) }}</span>
            </div>
        </div>
        <!-- 支出饼图 -->
        <div class="box">
            <div class="title2">支出类别</div>
            <div id="pie"></div>
        </div>
        <!-- 支出排行 -->
        <div class="box" v-if="isShow2">
            <div class="title2">支出排行</div>
            <ul v-for="(value, index) in res" :key="index">
                <li>
                    <span>{{ index + 1 }}</span>
                    <img :src=value.icon alt="">
                    <span>{{ value.name }}</span>
                    <span>{{ value.money.toFixed(2) }}</span>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            id: this.$route.query.id,
            year: this.$route.query.year,
            month: this.$route.query.month,
            expense: 0,
            income: 0,
            balance: 0,
            isShow1: false,
            isShow2: false,
            res: {
                money: null,
                percentage: null,
                name: null,
                icon: null
            },
            pieArr: [],
        }
    },
    methods: {
        goback() {
            this.$router.go(-1)
        },
        getBalance() {
            this.$axios.get('http://localhost:8080/bill/getYearMonthSum', {
                params: {
                    userId: this.id,
                    year: this.year
                }
            })
                .then(res => {
                    this.income = res.data[this.month - 1].income
                    this.expense = res.data[this.month - 1].expense
                    this.balance = this.income + this.expense
                    this.isShow1 = true
                })
                .catch(err => {
                    console.log(err)
                })
        },
        getRank() {
            this.$axios.get('http://localhost:8080/bill/getRank', {
                params: {
                    userId: this.id,
                    year: this.year,
                    month: this.month,
                    flag: false
                }
            })
                .then(res => {
                    this.res = res.data
                    for (var i = 0; i < res.data.length; i++) {
                        var obj = { name: res.data[i].name, value: -res.data[i].money }
                        this.pieArr.push(obj)
                    }
                    this.isShow2 = true
                    this.drawPieCharts()
                })
                .catch(err => {
                    console.log(err)
                })
        },
        drawPieCharts() {
            let myChart = this.$echarts.init(document.getElementById('pie'), null, {
                width: 350,
                height: 300
            });
            var option = {
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    top: 'center',
                    left: '75%'
                },
                series: [
                    {
                        name: 'Access From',
                        type: 'pie',
                        radius: ['50%', '70%'],
                        center: ['35%', '50%'],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 10,
                            borderColor: '#fff',
                            borderWidth: 2
                        },
                        label: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: 40,
                                fontWeight: 'bold'
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: this.pieArr
                    }
                ]
            };
            myChart.setOption(option)
            this.isShow2 = true
        }
    },
    mounted() {
        this.getBalance()
        this.getRank()
    }
}
</script>

<style scoped>
.background {
    width: 390px;
    margin: 0 auto 0 auto;
    background-color: #f6f6f6;
}

.title1 {
    position: relative;
    width: 390px;
    height: 60px;
    background-color: #FFD946;
    text-align: center;
    font-size: 20px;
    color: #101010;
    line-height: 60px;
}

.title2 {
    width: fit-content;
    font-size: 16px;
    font-weight: bold;
    margin: 0 0 10px 0;
}

.exit {
    position: absolute;
    margin-left: 15px;
    float: left;
}

.box {
    padding: 20px;
    margin: 0 0 10px 0;
    background-color: white;
}

#summary {
    width: fit-content;
    color: #101010;
    font-size: 14px;
}

#money {
    width: fit-content;
    color: black;
    font-size: 26px;
}

.barInfo {
    color: black;
    font-size: 14px;
}

.bar {
    height: 12px;
    display: inline-block;
    background-color: #FFD946;
    margin: 0 10px;
    border-radius: 0 7px 7px 0;
}

li {
    font-size: 16px;
    display: flex;
    align-items: center;
    margin: 0 0 10px 0;
    border-bottom: #0003 solid 1px;
}

li span:nth-child(1) {
    margin: 0 10px 0 10px;
}

li span:nth-child(4) {
    margin-left: auto;
}

img {
    width: 40px;
    height: 40px;
    margin: 0 10px 0 10px;
}
</style>