<template>
    <div class="Main_banner">
        <!-- 选择展示记录的年份月份 -->
        <div class="date">
            <!-- 年份选择 -->
            <select id="year" v-model="year" @change="list">
                <option value="2021">2021年</option>
                <option value="2022">2022年</option>
                <option value="2023">2023年</option>
                <option value="2024">2024年</option>
            </select>
            <br>
            <!-- 月份选择 -->
            <select id="month" v-model="month" @change="list">
                <option value="01">1月</option>
                <option value="02">2月</option>
                <option value="03">3月</option>
                <option value="04">4月</option>
                <option value="05">5月</option>
                <option value="06">6月</option>
                <option value="07">7月</option>
                <option value="08">8月</option>
                <option value="09">9月</option>
                <option value="10">10月</option>
                <option value="11">11月</option>
                <option value="12">12月</option>
                <!-- ...其他月份... -->
            </select>
            <br>
        </div>
        <div class="money">
            <!-- 收入金额显示 -->
            <div class="income">
                收入<br>
                <label>{{ income.toFixed(2) }}</label>
            </div>
            <!-- 支出金额显示 -->
            <div class="expenses">
                支出<br>
                <label>{{ expenses.toFixed(2) }}</label>
            </div>
        </div>
        <!-- 其他功能（待实现） -->
        <div class="item">
            <span>预算：{{ budget > 0 ? budget : "未设置" }}</span>
            <span>已用：{{ percentage }}%</span>
            <div class="totalProgress">
                <div class="currProgress" :style="{
                    width:percentage <= 100? percentage+'%': '100%',
                    backgroundColor: percentage <= 60 ? '#81DD1C' : (percentage <= 80 ? '#F68C00': '#F63434')
                    }"></div>
            </div>
            <!-- 测试进度条样式 <input type="number" v-model="percentage"> -->
        </div>
    </div>
    <!-- 这是主界面账单主体模块 -->
    <div class="Main_content">
        <div v-if="isShow">
            <!-- 时间
            <div for="" class="title" @click="changeFlag2">
                <label for="">11月9日星期四</label>
            </div>
            <br>
            消费记录
            <ul v-show="flag2">
                <li><img src="../assets/icon/餐饮.png" width="30" height="30" alt="">&nbsp;&nbsp;<input type="text"
                        value="餐饮"><input type="text" value="-15" class="expense_detail"></li>
                <li><img src="../assets/icon/交通.png" width="30" height="30" alt="">&nbsp;&nbsp;<input type="text"
                        value="交通"><input type="text" value="-7" class="expense_detail"></li>
                <li><img src="../assets/icon/运动.png" width="30" height="30" alt="">&nbsp;&nbsp;<input type="text"
                        value="运动"><input type="text" value="-12.5" class="expense_detail"></li>
                <li><img src="../assets/icon/餐饮.png" width="30" height="30" alt="">&nbsp;&nbsp;<input type="text"
                        value="餐饮"><input type="text" value="-12" class="expense_detail"></li>
            </ul>
            <br> -->
            <!-- 遍历获取到的每条账单记录 -->
            <ul v-for="(bill, bill_index) in result" :key="bill_index">
                <!-- 当第一条记录，或者与上一条记录的日期不相同时，才打印日期 -->
                <div for="" class="title" v-if="bill_index == 0 || result[bill_index].date != result[bill_index - 1].date">
                    <label for="">{{ bill.date }}</label>
                </div>
                <!-- 打印记录的类型，金额 -->
                <li>
                    <img :src="bill.icon" width="30" height="30" :alt="bill.name">&nbsp;&nbsp;
                    <input type="text" :value=bill.name><input type="text" :value=bill.amount class="expense_detail">
                </li>
            </ul>
        </div>

    </div>
    <tabBar :id="this.id"></tabBar>
</template>
<script>
import tabBar from '../components/tabBar.vue'
export default {
    data() {
        return {
            id: this.$route.query.id,
            isShow: false,
            year: new Date().getFullYear(),
            month: new Date().getMonth() + 1,
            income: 0,
            expenses: 0,
            budget: 0,
            percentage: 0,
            result: {
                id: null,
                userId: null,
                categoryId: null,
                type: false,
                amount: 0,
                date: null,
                note: null,
                name: null,
                icon: null
            }
        }
    },
    components: {
        tabBar
    },
    methods: {
        // 跳转到其他界面
        goto(url) {
            this.$router.push({ path: url, query: { id: this.id } })
        },
        // 查询最近的账单记录
        list() {
            this.$axios.get('http://localhost:8080/bill/listByYM', {
                params: {
                    userId: this.id,
                    year: this.year,
                    month: this.month
                }
            })
                .then(res => {
                    this.result = res.data
                    console.log(res.data)
                    let weeks = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
                    let month, day, week
                    for (var x in this.result) {
                        let dt = new Date(this.result[x].date)
                        month = dt.getMonth() + 1
                        day = dt.getDate()
                        week = dt.getDay().toLocaleString()
                        this.result[x].date = month + "月" + day + "日 " + weeks[week]
                    }
                    this.count()
                    this.isShow = true
                })
                .catch(err => {
                    console.log(err)
                })
        },
        // 查询预算情况
        getBudget() {
            this.$axios.get('http://localhost:8080/budget/get', { params: { userId: this.id } })
            .then(res => {
                this.budget = res.data.plan
                this.percentage = res.data.percentage
            })
            .catch(err => {
                console.log(err)
            })
        },
        // 计算支出和收入
        count() {
            this.income = 0
            this.expenses = 0
            for (var i in this.result) {
                // console.log(this.result[Number(i)].amount)
                if (this.result[Number(i)].type) {
                    this.income += this.result[Number(i)].amount
                } else {
                    this.expenses += this.result[Number(i)].amount
                }
            }
        },
    },
    created() {
        this.list()
        this.getBudget()
    },
}
</script>
<style scoped>
.Main_banner {
    position: relative;
    width: 390px;
    height: 195px;
    background-color: #FFD946;
}

.date {
    /* position: absolute; */
    float: left;
    margin-left: 38px;
    margin-top: 45px;
    width: 68px;
    height: 60px;
    text-align: center;
}

select {
    border: 0;
    color: #101010;
    background-color: #FFD946;
}

#year {
    font-size: 18px;
}

#month {
    font-size: 20px;
}

option {
    font-size: 10px;
}

.money {
    /* position: absolute; */
    float: right;
    margin-top: 40px;
    margin-right: 56px;
    color: #101010;
    font-size: 18px;
    width: 164px;
}

.income {
    float: left;
    text-align: center;
}

.expenses {
    float: right;
    text-align: center;
}

.item {
    float: left;
    margin-top: 10px;
    margin-left: 15px;
    width: 360px;
    height: 77px;
    background-color: #fff;
    text-align: center;
    border-radius: 10px;
}

.item span:nth-child(1) {
    float: left;
    margin: 10px;
    font-size: 16px;
}

.item span:nth-child(2) {
    float: right;
    margin: 10px;
    font-size: 16px;
}

.totalProgress {
    height: 20px;
    width: 340px;
    margin: 38px 10px 0 10px;;
    background-color: #efefef;
    border-radius: 10px;
}

.currProgress {
    height: 20px;
    /* width: 50%; */
    margin-top: 38px;
    background-color: red;
    border-radius: 10px;
}

.title {
    display: block;
    padding-left: 22px;
    width: 390px;
    height: 28px;
    line-height: 28px;
    border-bottom: 1px solid #ccc;

}

.Main_content {
    margin-bottom: 100px;
}

.Main_content ul li {
    padding: 10px 0 0 22px;
    width: 390px;
    height: 60px;
    font-size: 14px;
    color: #101010;

}

.Main_content ul li input {
    font-size: 14px;
    color: #101010;
}

.expense_detail {
    margin-top: 10px;
    text-align: right;
    width: 80px;
    float: right;
    margin-right: 30px;
}

</style>