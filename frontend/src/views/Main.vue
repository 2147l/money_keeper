<template>
    <div class="Main_banner">
        <div class="date">
            <select id="year" v-model="year" @change="list">
                <option value="2021">2021年</option>
                <option value="2022">2022年</option>
                <option value="2023">2023年</option>
                <option value="2024">2024年</option>
            </select>
            <br>
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
            </select>
            <br>
        </div>
        <div class="money">
            <div class="income">
                收入<br>
                <label>{{ income.toFixed(2) }}</label>
            </div>
            <div class="expenses">
                支出<br>
                <label>{{ expenses.toFixed(2) }}</label>
            </div>
        </div>
        <div class="item">
            <ul>
                <li><a href="#"><img src="../assets/image/main_item1.png" width="50" height="50" alt=""><br>
                        <div class="item_content">账&nbsp;单</div>
                    </a></li>
                <li><a href="#"><img src="../assets/image/main_item2.png" width="50" height="50" alt=""><br>
                        <div class="item_content">预&nbsp;算</div>
                    </a></li>
                <li><a href="#"><img src="../assets/image/main_item3.png" width="50" height="50" alt=""><br>
                        <div class="item_content">汇&nbsp;率</div>
                    </a></li>
                <li><a href="#"><img src="../assets/image/main_item4.png" width="50" height="50" alt=""><br>
                        <div class="item_content">账&nbsp;本</div>
                    </a></li>
                <li><a href="#"><img src="../assets/image/main_item5.png" width="50" height="50" alt=""><br>
                        <div class="item_content">更&nbsp;多</div>
                    </a></li>

            </ul>
        </div>
    </div>
    <div class="Main_content">
        <!-- <div>
            <div for="" class="title" @click="changeFlag1">
                <label for="">11月10日星期五</label>
            </div>
            <br>
            <ul v-show="flag1">
                <li><img src="../assets/icon/餐饮.png" width="30" height="30" alt="">&nbsp;&nbsp;<input type="text"
                        value="餐饮"><input type="text" value="-12.5" class="expense_detail"></li>
                <li><img src="../assets/icon/购物.png" width="30" height="30" alt="">&nbsp;&nbsp;<input type="text"
                        value="购物"><input type="text" value="-25.8" class="expense_detail"></li>
                <li><img src="../assets/icon/交通.png" width="30" height="30" alt="">&nbsp;&nbsp;<input type="text"
                        value="交通"><input type="text" value="-5" class="expense_detail"></li>
            </ul>
        </div>
        <br>
        <div>
            <div for="" class="title" @click="changeFlag2">
                <label for="">11月9日星期四</label>
            </div>
            <br>
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
        </div> -->

        <div>
            <div for="" class="title">
                <label for="">12月</label>
            </div>
            <br>
            <ul v-if="id">
                <li v-for="(bill, bill_index) in result" key="bill_index">
                    <img :src="bill.icon" width="30" height="30" :alt="bill.name">&nbsp;&nbsp;
                    <input type="text" :value=bill.name><input type="text" :value=bill.amount class="expense_detail">
                </li>
            </ul>
        </div>

    </div>
    <div id="addBill" @click="gotoOutput">
        <img src="../assets/image/记账.png" width="30" height="30" alt="记账加号" style="width: 80px; height: 80px;">
        <div>记账</div>
    </div>
</template>
<script>

export default {
    data() {
        return {
            id: this.$route.query.id,
            year: "2023",
            month: "12",
            income: 0,
            expenses: 0,
            flag1: true,
            flag2: true,
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
    watch: {
        result: {
            handler (val, newVal) {
                this.count()
            }
        }
    },
    methods: {
        changeFlag1() {
            // console.log("点击了内容1");
            this.flag1 = !this.flag1;
        },
        changeFlag2() {
            // console.log("点击了内容2");
            this.flag2 = !this.flag2;
        },
        // 跳转到支出页面
        gotoOutput() {
            this.$router.push({ path: '/output', query: { id: this.id } })
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
                })
                .catch(err => {
                    console.log(err)
                    this.$router.push('/')
                })
        },
        // 计算支出和收入
        count() {
            this.income = 0
            this.expenses = 0
            for(var i in this.result) {
                console.log(this.result[Number(i)].amount)
                if (this.result[Number(i)].type) {
                    this.income += this.result[Number(i)].amount
                } else {
                    this.expenses += this.result[Number(i)].amount
                }
            }
        }
    },
    created() {
        this.list()
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
}

.expenses {
    float: right;
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

.item_content {
    text-align: center;
}

.item ul li {
    display: inline-block;
    padding-right: 8px;
}

.title {
    display: block;
    padding-left: 22px;
    width: 390px;
    height: 28px;
    line-height: 28px;
    border-bottom: 1px solid #ccc;

}


.Main_content ul li {
    padding-left: 22px;
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

#addBill {
    width: 80px;
    position: fixed;
    bottom: 25px;
    left: 155px;
    text-align: center;
    font-size: large;
}
</style>