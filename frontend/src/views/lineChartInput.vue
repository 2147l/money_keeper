<template>
    <div class="banner">
        <img src="src/assets/image/左箭头.png" alt="" class="cancel" @click="goBack">
        <!-- 切换支出/收入页面 -->
        <select id="optType" @change="goto">
            <option>支出</option>
            <option selected>收入</option>
        </select>
        <span style="padding-left: 43px"></span>
        <!-- 周月年选择标签 -->
        <ul class="date">
            <li @click="changeState1" :class="{ chosen: flag1, unchosen: !flag1 }">周</li>
            <li @click="changeState2(); drawChart2(0)" :class="{ chosen: flag2, unchosen: !flag2 }">月</li>
            <li @click="changeState3(); drawChart3(0)" :class="{ chosen: flag3, unchosen: !flag3 }">年</li>
        </ul>
    </div>
    <!-- 周月年各自小标签 -->
    <!-- 这是周的：本周、上周、上上周 -->
    <div>
        <ul class="optLabel" v-show="flag1">
            <li @click="changeState11(); drawChart(2)" :class="{ chosenOptLabel: flag11, unchosenOptLabel: !flag11 }">上上周</li>
            <li @click="changeState12(); drawChart(1)" :class="{ chosenOptLabel: flag12, unchosenOptLabel: !flag12 }">上周</li>
            <li @click="changeState13(); drawChart(0)" :class="{ chosenOptLabel: flag13, unchosenOptLabel: !flag13 }">本周</li>
        </ul>
    </div>

    <!-- 周月年各自小标签 -->
    <!-- 这是月的：本月、上月、上上月 -->
    <div>
        <ul class="optLabel" v-show="flag2">
            <li @click="changeState21(); drawChart2(2)" :class="{ chosenOptLabel: flag21, unchosenOptLabel: !flag21 }">上上月</li>
            <li @click="changeState22(); drawChart2(1)" :class="{ chosenOptLabel: flag22, unchosenOptLabel: !flag22 }">上月</li>
            <li @click="changeState23" :class="{ chosenOptLabel: flag23, unchosenOptLabel: !flag23 }">本月</li>
        </ul>
    </div>

    <!-- 周月年各自小标签 -->
    <!-- 这是年的：今年、去年、前年 -->
    <div>
        <ul class="optLabel" v-show="flag3">
            <li @click="changeState31(); drawChart3(2)" :class="{ chosenOptLabel: flag31, unchosenOptLabel: !flag31 }">前年</li>
            <li @click="changeState32(); drawChart3(1)" :class="{ chosenOptLabel: flag32, unchosenOptLabel: !flag32 }">去年</li>
            <li @click="changeState33" :class="{ chosenOptLabel: flag33, unchosenOptLabel: !flag33 }">今年</li>
        </ul>
    </div>
    <br>
    <br>

    <!-- 注意：下面content11、content12、content13属于周的内容 -->
    <!-- 下面这个是收入上上周的内容，标为content11 -->
    <div class="content11" v-show="flag1 && flag11">
        <!-- 这是总览数据，总收入和平均值 -->
        <div class="overview">
            <label for="">总收入：{{ income }}</label>
            <br>
            <label for="">平均值：{{ average }}</label>
        </div>
        <!-- 由于这里图表不知道怎么表示，就先放张图片，后续再改 -->
        <div id="chart2"></div>
        <!-- 收入排行榜 -->
        <div class="title">
            <label for="">收入排行榜</label>
        </div>
        <!-- 下面是一些具体消费信息，由于不知道怎么调数据，这里也先固定写一些 -->
        <ul class="expense">
            <li>
                <img src="../assets/icon/salary.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如餐饮 69.2%    67 -->
                    <div>
                        <label for="" class="label1">工资 100.0%</label>
                        <label for="" class="label2">49</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 280px;"></div>
                    <div>
                    </div>
                </div>
            </li>
        </ul>
    </div>


    <!-- 下面这个是收入上周的内容，标为content12 -->
    <div class="content12" v-show="flag1 && flag12">
        <!-- 这是总览数据，总收入和平均值 -->
        <div class="overview">
            <label for="">总收入：{{ income }}</label>
            <br>
            <label for="">平均值：{{ average }}</label>
        </div>
        <!-- 由于这里图表不知道怎么表示，就先放张图片，后续再改 -->
        <div id="chart1"></div>
        <!-- 收入排行榜 -->
        <div class="title">
            <label for="">收入排行榜</label>
        </div>
        <!-- 下面是一些具体消费信息，由于不知道怎么调数据，这里也先固定写一些 -->
        <ul class="expense">
            <li>
                <img src="../assets/icon/salary.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如餐饮 69.2%    67 -->
                    <div>
                        <label for="" class="label1">工资 100.0%</label>
                        <label for="" class="label2">131</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 280px;"></div>
                    <div>
                    </div>
                </div>
            </li>
        </ul>
    </div>


    <!-- 下面这个是收入本周的内容，标为content13 -->
    <div class="content13" v-show="flag1 && flag13">
        <!-- 这是总览数据，总收入和平均值 -->
        <div class="overview">
            <label for="">总收入：{{ income }}</label>
            <br>
            <label for="">平均值：{{ average }}</label>
        </div>
        <!-- 由于这里图表不知道怎么表示，就先放张图片，后续再改 -->
        <div id="chart0"></div>
        <!-- 收入排行榜 -->
        <div class="title">
            <label for="">收入排行榜</label>
        </div>
        <!-- 下面是一些具体消费信息，由于不知道怎么调数据，这里也先固定写一些 -->
        <ul class="expense">
            <li>
                <img src="../assets/icon/salary.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如餐饮 69.2%    67 -->
                    <div>
                        <label for="" class="label1">工资 100.0%</label>
                        <label for="" class="label2">120</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 280px;"></div>
                    <div>
                    </div>
                </div>
            </li>
        </ul>
    </div>



    <!-- 注意：下面content21、content22、content23属于月的内容 -->
    <!-- 下面这个是收入上上月的内容，标为content21 -->
    <div class="content21" v-show="flag2 && flag21">
        <!-- 这是总览数据，总收入和平均值 -->
        <div class="overview">
            <label for="">总收入：{{ income }}</label>
            <br>
            <label for="">平均值：{{ average }}</label>
        </div>
        <!-- 由于这里图表不知道怎么表示，就先放张图片，后续再改 -->
        <div id="chart22"></div>
        <!-- 收入排行榜 -->
        <div class="title">
            <label for="">收入排行榜</label>
        </div>
        <!-- 下面是一些具体消费信息，由于不知道怎么调数据，这里也先固定写一些 -->
        <ul class="expense">
            <li>
                <img src="../assets/icon/salary.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如餐饮 69.2%    67 -->
                    <div>
                        <label for="" class="label1">工资 100.0%</label>
                        <label for="" class="label2">100</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 280px;"></div>
                    <div>
                    </div>
                </div>
            </li>
        </ul>
    </div>


    <!-- 下面这个是收入上月的内容，标为content22 -->
    <div class="content22" v-show="flag2 && flag22">
        <!-- 这是总览数据，总收入和平均值 -->
        <div class="overview">
            <label for="">总收入：{{ income }}</label>
            <br>
            <label for="">平均值：{{ average }}</label>
        </div>
        <!-- 由于这里图表不知道怎么表示，就先放张图片，后续再改 -->
        <div id="chart21"></div>
        <!-- 支出排行榜 -->
        <div class="title">
            <label for="">收入排行榜</label>
        </div>
        <!-- 下面是一些具体消费信息，由于不知道怎么调数据，这里也先固定写一些 -->
        <ul class="expense">
            <li>
                <img src="../assets/icon/salary.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如餐饮 69.2%    67 -->
                    <div>
                        <label for="" class="label1">工资 100.0%</label>
                        <label for="" class="label2">31</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 280px;"></div>
                    <div>
                    </div>
                </div>
            </li>
        </ul>
    </div>


    <!-- 下面这个是收入本月的内容，标为content23 -->
    <div class="content23" v-show="flag2 && flag23">
        <!-- 这是总览数据，总收入和平均值 -->
        <div class="overview">
            <label for="">总收入：{{ income }}</label>
            <br>
            <label for="">平均值：{{ average }}</label>
        </div>
        <!-- 由于这里图表不知道怎么表示，就先放张图片，后续再改 -->
        <div id="chart20"></div>
        <!-- 收入排行榜 -->
        <div class="title">
            <label for="">收入排行榜</label>
        </div>
        <!-- 下面是一些具体消费信息，由于不知道怎么调数据，这里也先固定写一些 -->
        <ul class="expense">
            <li>
                <img src="../assets/icon/shopping.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如餐饮 69.2%    67 -->
                    <div>
                        <label for="" class="label1">工资 100.0%</label>
                        <label for="" class="label2">220</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 280px;"></div>
                    <div>
                    </div>
                </div>
            </li>
        </ul>
    </div>

    <!-- 注意：下面content31、content32、content33属于年的内容 -->
    <!-- 下面这个是收入前年的内容，标为content31 -->
    <div class="content31" v-show="flag3 && flag31">
        <!-- 这是总览数据，总收入和平均值 -->
        <div class="overview">
            <label for="">总收入：{{ income }}</label>
            <br>
            <label for="">平均值：{{ average }}</label>
        </div>
        <!-- 由于这里图表不知道怎么表示，就先放张图片，后续再改 -->
        <div id="chart32"></div>
        <!-- 收入排行榜 -->
        <div class="title">
            <label for="">收入排行榜</label>
        </div>
        <!-- 下面是一些具体消费信息，由于不知道怎么调数据，这里也先固定写一些 -->
        <ul class="expense">
            <li>
                <img src="../assets/icon/salary.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如餐饮 69.2%    67 -->
                    <div>
                        <label for="" class="label1">工资 100.0%</label>
                        <label for="" class="label2">200</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 280px;"></div>
                    <div>
                    </div>
                </div>
            </li>
        </ul>
    </div>


    <!-- 下面这个是收入去年的内容，标为content32 -->
    <div class="content32" v-show="flag3 && flag32">
        <!-- 这是总览数据，总收入和平均值 -->
        <div class="overview">
            <label for="">总收入：{{ income }}</label>
            <br>
            <label for="">平均值：{{ average }}</label>
        </div>
        <!-- 由于这里图表不知道怎么表示，就先放张图片，后续再改 -->
        <div id="chart31"></div>

        <!-- 收入排行榜 -->
        <div class="title">
            <label for="">收入排行榜</label>
        </div>
        <!-- 下面是一些具体消费信息，由于不知道怎么调数据，这里也先固定写一些 -->
        <ul class="expense">
            <li>
                <img src="../assets/icon/salary.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如餐饮 69.2%    67 -->
                    <div>
                        <label for="" class="label1">工资 100.0%</label>
                        <label for="" class="label2">155</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 280px;"></div>
                    <div>
                    </div>
                </div>
            </li>
        </ul>
    </div>


    <!-- 下面这个是收入今年的内容，标为content33 -->
    <div class="content33" v-show="flag3 && flag33">
        <!-- 这是总览数据，总收入和平均值 -->
        <div class="overview">
            <label for="">总收入：{{ income }}</label>
            <br>
            <label for="">平均值：{{ average }}</label>
        </div>
        <!-- 由于这里图表不知道怎么表示，就先放张图片，后续再改 -->
        <div id="chart30"></div>
        <!-- 收入排行榜 -->
        <div class="title">
            <label for="">收入排行榜</label>
        </div>
        <!-- 下面是一些具体消费信息，由于不知道怎么调数据，这里也先固定写一些 -->
        <ul class="expense">
            <li>
                <img src="../assets/icon/salary.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如餐饮 69.2%    67 -->
                    <div>
                        <label for="" class="label1">工资 100.0%</label>
                        <label for="" class="label2">581</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 280px;"></div>
                    <div>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</template>
<script>
export default {
    data() {
        return {
            id: this.$route.query.id,
            income: "计算中",
            average: "计算中",
            optType: "/lineChartOutput",
            // week的相关标志,比如flag1是周标签,flag11、flag12、flag13则分别是上上周、上周、本周标签
            flag1: true,
            flag11: false,
            flag12: false,
            flag13: true,

            //month的相关标志,比如flag2是月标签,flag21、flag22、flag23则分别是上上月、上月、本月标签
            flag2: false,
            flag21: false,
            flag22: false,
            flag23: true,

            // year的相关标志,比如flag3是年标签,flag31、flag32、flag33则分别是前年、去年、今年标签
            flag3: false,
            flag31: false,
            flag32: false,
            flag33: true,
        }
    },
    mounted() {
        this.drawChart(0)
    },
    methods: {
        drawChart(flag) {
            let myChart = this.$echarts.init(document.getElementById('chart' + flag), null, {
                width: 390,
                height: 300
            });
            this.$axios.get('http://localhost:8080/bill/getThisWeek', {
                params: {
                    userId: this.id,
                    flag: flag
                }
            })
                .then(res => {
                    this.income = 0
                    for (var i = 0; i < 7; i++) {
                        this.income += res.data[i].income
                    }
                    this.income = Math.round((this.income) * 100) / 100
                    this.average = (this.income / 7).toFixed(2)
                    // 指定图表的配置项和数据
                    var option = {
                        xAxis: {
                            type: 'category',
                            data: [res.data[0].date, res.data[1].date, res.data[2].date, res.data[3].date, res.data[4].date, res.data[5].date, res.data[6].date]
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [
                            {
                                data: [res.data[0].income, res.data[1].income, res.data[2].income, res.data[3].income, res.data[4].income, res.data[5].income, res.data[6].income],
                                type: 'line',
                                label: {
                                    show: true,
                                    position: 'top',
                                    fontSize: 10,
                                    formatter: function (params) {
                                        if (params.value == 0) {  //为0时不显示
                                            return ''
                                        } else {
                                            return params.value
                                        }
                                    }
                                },
                                lineStyle: {
                                    color: 'gray',
                                    width: 2,
                                },
                                itemStyle: {
                                    color: 'gray',
                                }
                            }
                        ]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                })
                .catch(err => {
                    console.log(err)
                })

        },
        drawChart2(flag) {
            let myChart = this.$echarts.init(document.getElementById('chart2' + flag), null, {
                width: 390,
                height: 300
            });
            this.$axios.get('http://localhost:8080/bill/getThisMonth', {
                params: {
                    userId: this.id,
                    flag: flag
                }
            })
                .then(res => {
                    this.income = 0
                    let tmpx = [];
                    let tmpy = [];
                    for (var i = 0; i < res.data.length; i++) {
                        this.income += res.data[i].income
                        tmpx.push(i + 1)
                        tmpy.push(res.data[i].income)
                    }
                    this.income = Math.round((this.income) * 100) / 100
                    this.average = (this.income / res.data.length).toFixed(2)
                    // 指定图表的配置项和数据
                    var option = {
                        xAxis: {
                            type: 'category',
                            data: tmpx
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [
                            {
                                data: tmpy,
                                type: 'line',
                                label: {
                                    show: true,
                                    position: 'top',
                                    fontSize: 10,
                                    formatter: function (params) {
                                        if (params.value == 0) {  //为0时不显示
                                            return ''
                                        } else {
                                            return params.value
                                        }
                                    }
                                },
                                lineStyle: {
                                    color: 'gray',
                                    width: 2,
                                },
                                itemStyle: {
                                    color: 'gray',
                                }
                            }
                        ]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                })
                .catch(err => {
                    console.log(err)
                })

        },
        drawChart3(flag) {
            let myChart = this.$echarts.init(document.getElementById('chart3' + flag), null, {
                width: 390,
                height: 300
            });
            this.$axios.get('http://localhost:8080/bill/getThisYear', {
                params: {
                    userId: this.id,
                    flag: flag
                }
            })
                .then(res => {
                    this.income = 0
                    let tmpx = [];
                    let tmpy = [];
                    for (var i = 0; i < 12; i++) {
                        this.income += res.data[i].income
                        tmpx.push(i + 1)
                        tmpy.push(res.data[i].income)
                    }
                    this.income = Math.round((this.income) * 100) / 100
                    this.average = (this.income / 12).toFixed(2)
                    // 指定图表的配置项和数据
                    var option = {
                        xAxis: {
                            type: 'category',
                            data: tmpx
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [
                            {
                                data: tmpy,
                                type: 'line',
                                label: {
                                    show: true,
                                    position: 'top',
                                    fontSize: 10,
                                    formatter: function (params) {
                                        if (params.value == 0) {  //为0时不显示
                                            return ''
                                        } else {
                                            return params.value
                                        }
                                    }
                                },
                                lineStyle: {
                                    color: 'gray',
                                    width: 2,
                                },
                                itemStyle: {
                                    color: 'gray',
                                }
                            }
                        ]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                })
                .catch(err => {
                    console.log(err)
                })

        },
        goto() {
            this.$router.replace({ path: "/lineChartOutput", query: { id: this.id } })
        },
        goBack() {
            this.$router.go(-1)
        },
        changeState1() {
            this.flag1 = true;
            this.flag2 = false;
            this.flag3 = false;
        },
        changeState2() {
            this.flag1 = false;
            this.flag2 = true;
            this.flag3 = false;
        },
        changeState3() {
            this.flag1 = false;
            this.flag2 = false;
            this.flag3 = true;
        },

        // 上上周、上周、本周
        changeState11() {
            this.flag11 = true;
            this.flag12 = false;
            this.flag13 = false;
        },
        changeState12() {
            this.flag11 = false;
            this.flag12 = true;
            this.flag13 = false;
        },
        changeState13() {
            this.flag11 = false;
            this.flag12 = false;
            this.flag13 = true;
        },

        // 上上月、上月、本月
        changeState21() {
            this.flag21 = true;
            this.flag22 = false;
            this.flag23 = false;
        },
        changeState22() {
            this.flag21 = false;
            this.flag22 = true;
            this.flag23 = false;
        },
        changeState23() {
            this.flag21 = false;
            this.flag22 = false;
            this.flag23 = true;
        },

        // 前年、去年、今年
        changeState31() {
            this.flag31 = true;
            this.flag32 = false;
            this.flag33 = false;
        },
        changeState32() {
            this.flag31 = false;
            this.flag32 = true;
            this.flag33 = false;
        },
        changeState33() {
            this.flag31 = false;
            this.flag32 = false;
            this.flag33 = true;
        },

    }
}
</script>
<style scoped>
.banner {
    width: 390px;
    height: 90px;
    background-color: #FFD946;
    text-align: center;
}

.cancel {
    float: left;
    padding: 18px 0 0 15px;
}

#optType {
    width: 70px;
    height: 33px;
    margin-top: 12px;
    background-color: #FFD946;
    border: 0;
    font-size: 18px;
}

.date {
    margin-left: 20px;
    margin-top: 8px;
}

.date li {
    float: left;
    width: 117px;
    height: 24px;
    font-size: 16px;
    border: 1px solid #343233;
}

/* 当周月年分别被选中时的样式 */
.chosen {
    background-color: #343233;
    color: #FFD946;
}

.unchosen {
    background-color: #FFD946;
    color: #343233;
}

.optLabel {
    margin-left: 42px;
    margin-top: 8px;
    text-align: center;
    line-height: 29px;
}

.optLabel li {
    float: left;
    width: 102px;
    height: 29px;
}

/* 当本周、上周、上上周分别被选中时的样式 */
.chosenOptLabel {
    border-bottom: 1px solid #343233;
    color: #343233;
}

.unchosenOptLabel {
    border-bottom: 0;
    color: #C1C1C1;

}

.overview {
    margin-top: 15px;
    margin-left: 33px;
    font-size: 14px;
    color: #101010;
}

.chart {
    width: 390px;
    height: 140px;
    border-bottom: 1px solid #dddddd;
    padding-left: 20px;
    margin-top: 15px;

}

.title {
    margin-top: 15px;
    padding-left: 20px;
    font-size: 16px;
    color: #101010;
}

.expense {
    margin-top: 15px;
    margin-left: 20px;

}

.expense li {
    width: 330px;
    height: 40px;
    margin-bottom: 15px;
}

.data {
    float: right;
    width: 280px;
    height: 50px;
    color: #101010;
}

.label1 {
    float: left;
}

.label2 {
    float: right;
}

.percentBar {
    float: left;
    margin-top: 10px;
    height: 4px;
    background-color: #FFD946;
}
</style>