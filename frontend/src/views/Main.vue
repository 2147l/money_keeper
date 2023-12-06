<template>
    <div class="Main_banner">
        <!-- 日期选择模块 -->
        <div class="date">
            <!-- 年份选择 -->
            <select id="year">
                <option>2021年</option>
                <option>2022年</option>
                <!-- 下拉框默认值用selected -->
                <option selected>2023年</option>
                <option>2024年</option>
            </select>
            <br>
            <!-- 月份选择 -->
            <select id="month">
                <option>1月</option>
                <option>2月</option>
                <option>3月</option>
                <option>4月</option>
                <option>5月</option>
                <option>7月</option>
                <option>8月</option>
                <option>9月</option>
                <option>10月</option>
                <option selected>11月</option>
                <option>12月</option>
                <!-- ...其他月份... -->
            </select>
            <br>
        </div>
        <div class="money">
            <!-- 收入金额显示 -->
            <div class="income">
                收入<br>
                <label>{{ income }}</label>
            </div>
            <!-- 支出金额显示 -->
            <div class="expenses">
                支出<br>
                <label>{{ expenses }}</label>
            </div>
        </div>
        <!-- 下面是一些功能软件 -->
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
    <!-- 这是主界面账单主体模块 -->
    <div class="Main_content">
        <div>
            <!-- 时间 -->
            <div for="" class="title" @click="changeFlag1">
                <label for="">11月10日星期五</label>
            </div>
            <br>
            <!-- 消费记录 -->
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
            <!-- 时间 -->
            <div for="" class="title" @click="changeFlag2">
                <label for="">11月9日星期四</label>
            </div>
            <br>
            <!-- 消费记录 -->
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
        </div>



    </div>
    <div id="addBill" @click="gotoOutput">
        <img src="../assets/image/记账.png" width="30" height="30" alt="记账加号" style="width: 80px;">
        <div>记账</div>
    </div>
</template>
<script>

export default {
    data() {
        return {
            id: this.$route.query.id,
            income: "0.00",
            expenses: "0.00",
            flag1: true,
            flag2: true,
            result: null
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
    },
    beforeMount() {
        this.$axios.get('http://localhost:8080/bill/list?userId=' + this.id)
            .then(res => res.data).then(res => {
                this.result = res
                console.log(res)
            })
    }
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