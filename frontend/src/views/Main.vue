<template>
    <div class="Main_banner">
        <div class="date">
            <select id="year">
                <option>2021年</option>
                <option>2022年</option>
                <!-- 下拉框默认值用selected -->
                <option selected>2023年</option>
                <option>2024年</option>
            </select>
            <br>
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
            <div class="income">
                收入<br>
                <label>{{ income }}</label>
            </div>
            <div class="expenses">
                支出<br>
                <label>{{ expenses }}</label>
            </div>
        </div>
        <div class="item">
            <ul>
                <li><a href="#"><img src="../assets/image/main_item1.png" alt=""><br>&nbsp;账&nbsp;单</a></li>
                <li><a href="#"><img src="../assets/image/main_item2.png" alt=""><br>&nbsp;预&nbsp;算</a></li>
                <li><a href="#"><img src="../assets/image/main_item3.png" alt=""><br>&nbsp;汇&nbsp;率</a></li>
                <li><a href="#"><img src="../assets/image/main_item4.png" alt=""><br>&nbsp;账&nbsp;本</a></li>
                <li><a href="#"><img src="../assets/image/main_item5.png" alt=""><br>&nbsp;更&nbsp;多</a></li>

            </ul>
        </div>
    </div>
    <div class="Main_content">
        <div @click="changeFlag1">
            <div for="" class="title">
                <label for="">11月10日星期五</label>
            </div>
            <ul v-show="flag1">
                <li><img src="../assets/image/餐饮.png" alt="">&nbsp;&nbsp;<input type="text" value="餐饮"><input type="text"
                        value="-12.5" class="expense_detail"></li>
                <li><img src="../assets/image/购物.png" alt="">&nbsp;&nbsp;<input type="text" value="购物"><input type="text"
                        value="-25.8" class="expense_detail"></li>
                <li><img src="../assets/image/交通.png" alt="">&nbsp;&nbsp;<input type="text" value="交通"><input type="text"
                        value="-5" class="expense_detail"></li>
            </ul>
        </div>
        <br>
        <div @click="changeFlag2">
            <div for="" class="title">
                <label for="">11月9日星期四</label>
            </div>
            <ul v-show="flag2">
                <li><img src="../assets/image/餐饮.png" alt="">&nbsp;&nbsp;<input type="text" value="餐饮"><input type="text"
                        value="-15" class="expense_detail"></li>
                <li><img src="../assets/image/交通.png" alt="">&nbsp;&nbsp;<input type="text" value="交通"><input type="text"
                        value="-7" class="expense_detail"></li>
                <li><img src="../assets/image/水果.png" alt="">&nbsp;&nbsp;<input type="text" value="水果"><input type="text"
                        value="-12.5" class="expense_detail"></li>
                <li><img src="../assets/image/餐饮.png" alt="">&nbsp;&nbsp;<input type="text" value="餐饮"><input type="text"
                        value="-12" class="expense_detail"></li>
            </ul>
        </div>


    </div>
</template>
<script>
export default {
    data() {
        return {
            income: "0.00",
            expenses: "89.80",
            flag1: true,
            flag2: true
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
        }
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

.item ul li {
    display: inline-block;
    margin-right: 8px;
}

.title {
    display: block;
    margin-left: 22px;
    width: 390px;
    height: 28px;
    line-height: 28px;
    border-bottom: 1px solid #ccc;

}


.Main_content ul li {
    margin-left: 22px;
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
    margin-right: 50px;
}
</style>