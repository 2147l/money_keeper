<template>
    <div class="budget">
        <!-- 标题 -->
        <div class="title">
            <div class="exit"><img src="../assets/image/左箭头.png" alt="" @click="goBack"></div>
            设置每月预算
        </div>
        <!-- 内容 -->
        <div class="box">
            <span>本月预算：</span>
            <span>{{ isBudget ? (plan > 0 ? plan : "未设置") : "加载中" }}</span>
        </div>

        <div class="addBudget" @click="isVisible = true; input = plan == 0 ? '' : String(plan); output = String(plan);">设置预算</div>

    </div>


    <div class="calculator" v-show="isVisible">
        <div class="input-container">
            <input type="text" id="output" v-model="output" readonly="readonly" />
        </div>

        <div class="line">
            <div class="key" id="7" @click="clickNumber(7)">7</div>
            <div class="key" id="8" @click="clickNumber(8)">8</div>
            <div class="key" id="9" @click="clickNumber(9)">9</div>
            <!-- <div class="key" id="date" @click="clickDate() ">本月</div> -->
            <div class="key" id="date">本月</div>
        </div>
        <div class="line">
            <div class="key" id="4" @click="clickNumber(4)">4</div>
            <div class="key" id="5" @click="clickNumber(5)">5</div>
            <div class="key" id="6" @click="clickNumber(6)">6</div>
            <div class="key" id="+" @click="clickOperator('+')">+</div>
        </div>
        <div class="line">
            <div class="key" id="1" @click="clickNumber(1)">1</div>
            <div class="key" id="2" @click="clickNumber(2)">2</div>
            <div class="key" id="3" @click="clickNumber(3)">3</div>
            <div class="key" id="-" @click="clickOperator('-')">-</div>
        </div>
        <div class="line">
            <div class="key" id="." @click="clickNumber('.')">.</div>
            <div class="key" id="0" @click="clickNumber(0)">0</div>
            <div class="key" id="del" @click="clickDelete">退格</div>
            <div class="key" id="=" @click="submit" style="background: #ffd946">提交</div>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            id: this.$route.query.id,
            isBudget: false,
            date: null,
            plan: 0,
            isVisible: false,
            input: '',
            output: '0',
        }
    },
    beforeMount() {
        this.getBudget()
    },
    methods: {
        // 返回上一页
        goBack() {
            this.$router.go(-1)
        },
        // 查询预算情况
        getBudget() {
            this.$axios.get('http://localhost:8080/budget/get', { params: { userId: this.id } })
            .then(res => {
                this.plan = res.data.plan
                this.isBudget = true
            })
            .catch(err => {
                console.log(err)
            })
        },
        // 点击数字键
        clickNumber(num) {
            this.input += num;
            this.output = this.input;
        },
        // 点击运算符
        clickOperator(op) {
            this.count++;
            if (this.count > 1) {
                this.clickConfirm();
            }
            this.operator = op;
            this.input += op;
            this.output = this.input;
        },
        // 点击日期键
        clickDate() {
            const date = new Date();
            const year = date.getFullYear();
            const month = date.getMonth() + 1;
            const day = date.getDate();
            this.date = `${year}-${month}-${day}`;
            this.input += this.date;
            this.output = this.input;
        },
        // 点击删除键
        clickDelete() {
            this.input = this.input.slice(0, -1);
            this.output = this.input;
        },
        // 点击确认键
        clickConfirm() {
            // 将输入的字符串分割成数组
            const arr = this.input.split(this.operator);
            // 将数组中的字符串转换成数字
            const num1 = Number(arr[0]);
            const num2 = Number(arr[1]);
            // 根据运算符计算结果
            let result = 0;
            switch (this.operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                default:
                    break;
            }
            // 将结果显示在输出框中
            this.output = result;
            // 将输入的字符串清空
            this.input = this.output;
            this.count = 1;
        },
        // 提交表单
        submit() {
            this.$axios.post('http://localhost:8080/budget/create', {
                    userId: this.id,
                    plan: this.output
            })
                .then(res => {
                    this.isVisible = false
                    this.getBudget()
                })
                .catch(err => {
                    console.log(err)
                })
        },
    }
}
</script>
<style scoped>
.budget {
    width: 390px;
    height: 844px;
    background-color: #F6F6F6;
}

.title {
    position: relative;
    width: 390px;
    height: 60px;
    background-color: #FFD946;
    text-align: center;
    font-size: 20px;
    color: #101010;
    line-height: 60px;
}


.exit {
    position: absolute;
    margin-left: 15px;
    float: left;
}

.box {
    font-size: 18px;
    line-height: 68px;
    margin: 20px auto;
    height: 68px;
    background-color: white;
}

.box span:nth-child(1) {
    float: left;
    margin-left: 20px;
}

.box span:nth-child(2) {
    float: right;
    margin-right: 20px;
}

.addBudget {
    margin: 10px auto;
    text-align: center;
    font-size: 18px;
    line-height: 40px;
    background-color: #FFD946;
    width: 150px;
    height: 40px;
    border-radius: 9px;
}

.input-container {
    width: 370px;
    height: 50px;
    text-align: center;
    margin: auto;
}

.key {
    width: 25%;
    height: 60px;
    line-height: 60px;
    text-align: center;
    background: #ffffff;
    display: inline-block;
    border: 1px solid black;
    font-size: 18px;
}

.line {
    text-align: left;
}

.calculator {
    z-index: 1;
    position: fixed;
    bottom: 0;
    width: 390px;
}

#output {
    font-size: 26px;
    text-align: right;
    width: 100%;
}
</style>