<template>
    <div class="header">
        <!-- 年份选择器 -->
        <!-- 如果是月账单，显示年份选择器 -->
        <select v-if="showMonthly" class="year" v-model="year" @change="showMonthlyBills">
            <option value="2021">2021年</option>
            <option value="2022">2022年</option>
            <!-- 下拉框默认值用selected -->
            <option value="2023">2023年</option>
            <option value="2024">2024年</option>
        </select>

        <!-- 月账单和年账单的按钮 -->
        <!-- 根据显示年账单与否，应用不同的按钮样式 -->
        <ul :class="showYearly ? 'billTypeWithoutYear' : 'billTypeWithYear'">
            <li @click="showMonthlyBills" :class="{ chosen: showMonthly, unchosen: !showMonthly }">
                月账单
            </li>
            <li @click="showYearlyBills" :class="{ chosen: showYearly, unchosen: !showYearly }">
                年账单
            </li>
        </ul>
        <div class="cancel" @click="goBack">返回</div>
    </div>

    <!-- 总体统计信息 -->
    <div class="summary">
        <!-- 根据显示月账单或年账单的不同，展示不同的信息 -->
        <div v-if="showMonthly">
            <!-- 月账单汇总信息 -->
            <p>
                年结余:<br /><span class="balance">{{ monthlySummary.yearBalance.toFixed(2) }}</span>
            </p>
            <!-- 列出月账单的收入和支出信息 -->
            <ul>
                <li>
                    年收入:
                    <span class="incomeAndExpense">{{ monthlySummary.yearIncome.toFixed(2) }}</span>
                </li>
                <li>
                    年支出:
                    <span class="incomeAndExpense">{{ monthlySummary.yearExpense.toFixed(2) }}</span>
                </li>
            </ul>
        </div>
        <div v-if="showYearly">
            <!-- 年账单汇总信息 -->
            <p>
                总结余: <br /><span class="balance">{{ yearlySummary.totalBalance.toFixed(2) }}</span>
            </p>
            <!-- 列出年账单的总收入和总支出信息 -->
            <ul>
                <li>
                    总收入:
                    <span class="incomeAndExpense">{{ yearlySummary.totalIncome.toFixed(2) }}</span>
                </li>
                <li>
                    总支出:
                    <span class="incomeAndExpense">{{ yearlySummary.totalExpense.toFixed(2) }}</span>
                </li>
            </ul>
        </div>
    </div>

    <!-- 月/年账单明细 -->
    <div class="details">
        <!-- 根据显示月账单或年账单的不同，展示不同的账单明细 -->
        <ul class="directory" v-if="showMonthly">
            <!-- 显示月账单的列名 -->
            <li>月份</li>
            <li>月收入</li>
            <li>月支出</li>
            <li>月结余</li>
        </ul>
        <ul class="directory" v-if="showYearly">
            <!-- 显示年账单的列名 -->
            <li>年份</li>
            <li>年收入</li>
            <li>年支出</li>
            <li>年结余</li>
        </ul>
        <!-- 根据显示月账单或年账单的不同，循环展示对应的账单数据 -->
        <div v-for="(bill, index) in showMonthly ? monthlyBills : yearlyBills" :key="index" @click="goto(index + 1)">
            <ul class="data">
                <!-- 显示具体的账单数据 -->
                <li>{{ bill.id }}</li>
                <li>{{ bill.income.toFixed(2) }}</li>
                <li>{{ bill.expense.toFixed(2) }}</li>
                <li :style="{color : (bill.income + bill.expense) > 0 ? 'red' : 'green'}">{{ (bill.income + bill.expense).toFixed(2) }}</li>
            </ul>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            id: this.$route.query.id,
            year: 2023,
            showMonthly: true,
            showYearly: false,
            monthlySummary: {
                yearBalance: 0,
                yearIncome: 0,
                yearExpense: 0,
            }, // 月账单汇总数据
            monthlyBills: [], // 月账单明细数据
            yearlySummary: {
                totalBalance: 0,
                totalIncome: 0,
                totalExpense: 0
            }, // 年账单汇总数据
            yearlyBills: [], // 年账单明细数据
        };
    },
    created() {
        // 在组件创建后立即获取初始数据
        this.showMonthlyBills(); // 或者 this.showYearlyBills();
    },
    methods: {
        // 跳转到月账单界面
        goto(month) {
            this.$router.push({path: '/monthlyView', query: {id: this.id, year: this.year, month: month}})
        },
        // 显示月账单
        showMonthlyBills() {
            this.showMonthly = true;
            this.showYearly = false;

            // todo
            // 在此处获取月账单数据并更新 monthlySummary 和 monthlyBills
            this.$axios.get('http://localhost:8080/bill/getYearSum',
                { params: { userId: this.id, year: this.year } })
                .then(res => {
                    // console.log(res.data)
                    this.monthlySummary.yearIncome = res.data.income
                    this.monthlySummary.yearExpense = res.data.expense
                    this.monthlySummary.yearBalance = this.monthlySummary.yearIncome + this.monthlySummary.yearExpense
                })
                .catch(err => {
                    console.log(err)
                })
            // 模拟获取月账单数据
            // const mockMonthlySummary = {
            //     yearBalance: 770.0,
            //     yearIncome: 3450.0,
            //     yearExpense: 2680.0,
            // };
            this.$axios.get('http://localhost:8080/bill/getYearMonthSum',
                { params: { userId: this.id, year: this.year } })
                .then(res => {
                    // console.log(res.data)
                    this.monthlyBills = res.data
                    for (let i = 0; i < 12; i++) {
                        this.monthlyBills[i].id = i + 1 + "月"
                    }
                })
                .catch(err => {
                    console.log(err)
                })
            // const mockMonthlyBills = [
            //     {
            //         id: "12" + "月",
            //         income: 200,
            //         expense: 30,
            //         balance: 170,
            //     },
            //     {
            //         id: "11" + "月",
            //         income: 400,
            //         expense: 500,
            //         balance: -100,
            //     },
            //     {
            //         id: "10" + "月",
            //         income: 500,
            //         expense: 300,
            //         balance: 200,
            //     },
            //     {
            //         id: "9" + "月",
            //         income: 400,
            //         expense: 250,
            //         balance: 150,
            //     },
            //     {
            //         id: "8" + "月",
            //         income: 550,
            //         expense: 500,
            //         balance: 50,
            //     },
            //     {
            //         id: "7" + "月",
            //         income: 400,
            //         expense: 450,
            //         balance: -50,
            //     },
            //     {
            //         id: "6" + "月",
            //         income: 600,
            //         expense: 400,
            //         balance: 200,
            //     },
            //     {
            //         id: "5" + "月",
            //         income: 400,
            //         expense: 250,
            //         balance: 150,
            //     }
            //     // ... 更多月份数据
            // ];

            // 更新数据
            // this.monthlySummary = mockMonthlySummary;
            // this.monthlyBills = mockMonthlyBills;
        },
        // 显示年账单
        showYearlyBills() {
            this.showMonthly = false;
            this.showYearly = true;


            // todo
            // 在此处获取年账单数据并更新 yearlySummary 和 yearlyBills
            // let i = 2021
            // for (i = 2021; i <= 2023; i++) {
            //     this.$axios.get('http://localhost:8080/bill/getYearSum',
            //         { params: { userId: this.id, year: i } })
            //         .then(res => {
            //             console.log(res.data)
            //             let tmp = {
            //                 id: i + "年",
            //                 income: res.data.income,
            //                 expense: res.data.expense,
            //                 balance: res.data.income + res.data.expense,
            //             }
            //             this.yearlyBills.push(tmp)
            //             console.log(i)
            //             this.yearlySummary.totalBalance += tmp.balance
            //             this.yearlySummary.totalIncome += tmp.income
            //             this.yearlySummary.totalExpense += tmp.expense

            //         })
            //         .catch(err => {
            //             console.log(err)
            //         })
            // }

            // 模拟获取年账单数据
            const mockYearlySummary = {
                totalBalance: 1620.0,
                totalIncome: 11350.0,
                totalExpense: 9730.0,
            };

            const mockYearlyBills = [
                {
                    id: "2023" + "年",
                    income: 3450,
                    expense: 2680,
                    balance: 770,
                },
                {
                    id: "2022" + "年",
                    income: 1700,
                    expense: 1800,
                    balance: -100,
                }
                ,
                {
                    id: "2021" + "年",
                    income: 3250,
                    expense: 2650,
                    balance: 600,
                }
                ,
                {
                    id: "2020" + "年",
                    income: 2950,
                    expense: 2600,
                    balance: 350,
                }
                // ... 更多月份数据
            ];

            // 更新数据
            this.yearlySummary = mockYearlySummary;
            this.yearlyBills = mockYearlyBills;
        },
        goBack() {
            this.$router.go(-1)
        }
    },

};
</script>



  
<style scoped>
/* 返回按钮 */
.cancel {
    font-size: 18px;
    width: 79px;
    text-align: center;
}

/* 页面头部的样式 */
.header {
    width: 390px;
    height: 40px;
    display: flex;
    justify-content: space-between;
    /* 子元素沿着主轴两端对齐 */
    align-items: center;
    /* 子元素在交叉轴上居中对齐 */
    margin: 13px 0;
}

/* 按钮样式：有年份选择器时和没有年份选择器时的不同样式 */
.billTypeWithYear {
    display: flex;
    list-style-type: none;
    padding: 0;
    margin: 0;
}

.billTypeWithoutYear {
    display: flex;
    list-style-type: none;
    padding: 0;
    margin-left: 126px;
}

.billTypeWithYear li,
.billTypeWithoutYear li {
    float: left;
    width: 80px;
    height: 30px;
    font-size: 16px;
    border: 1px solid #343233;
    text-align: center;
    /* 文字居中 */
    padding: 3px;
}

/* 当月账单或年账单被选中时的样式 */
.chosen {
    background-color: #343233;
    color: white;
}

/* 当月账单或年账单未被选中时的样式 */
.unchosen {
    background-color: white;
    color: #343233;
}

/* 年份选择器样式 */
.year {
    border: 1px solid grey;
    /* 去掉边框 */
    border-radius: 5px;
    margin: 10px;
    padding: 3px;
    font-size: 16px;
}



/* CSS 样式 */
.summary {
    width: 370px;
    height: 100px;
    border-radius: 10px;
    padding: 15px;
    margin: 10px;
    background-color: #ffd946;
    /* 设置背景色 */
}

.details {
    width: 390px;
    /* 设定宽度 */
}

.balance {
    font-size: 22px;
    /* 设置字体大小 */
    color: black;
    /* 设置文字颜色 */
}

/* 账单汇总信息列表的样式 */
.summary li {
    display: inline-block;
    /* 横向排列列表项 */
    margin: 4px 0px;
    /* 调整列表项的间距 */
    width: 45%;
    margin-left: 0px;
    /* 调整左侧间距 */
}

.incomeAndExpense {
    font-size: 15px;
    /* 设置字体大小 */
    color: black;
    /* 设置文字颜色 */
}

/* 列名的样式 */
.directory li {
    display: inline-block;
    /* 横向排列列表项 */
    width: 25%;
    /* 设置列宽 */
}

/* 账单明细数据的样式 */
.data li {
    display: inline-block;
    /* 横向排列列表项 */
    width: 25%;
    /* 设置列宽 */
    font-size: 14px;
    /* 设置字体大小 */
    color: black;
    /* 设置文字颜色 */
}

.details ul {
    margin: 0 15px;
    /* 调整上下间距 */
    border-bottom: 1px solid lightgrey;
    /* 设置底部边框 */
    padding: 7px;
    /* 设置内边距 */
}
</style>
  