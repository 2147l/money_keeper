<template>
  <div class="topic">
    <div class="cancel" @click="gotoMain">取消</div>
    <div class="title" @click="gotoOutput">
      <div id="Output">支出</div>
      <div id="Input">收入</div>
    </div>
  </div>
  <div class="category" :style="{ height: containerHeight + 'px' }">
    <div class="line">
      <div class="block"></div>
      <div class="group" id="salary" @click="categoryId = 12; clickShow('salary')">
        <div class="icon" :style="{ backgroundColor: backgroundColor === 'salary' ? '#ffd946' : 'white' }">
          <img src="../assets/icon/工资.png" height="30" width="30" alt=""/>
        </div>
        <div class="text">工资</div>
      </div>
      <div class="group" id="parttime" @click="categoryId = 13; clickShow('parttime')">
        <div class="icon" :style="{ backgroundColor: backgroundColor === 'parttime' ? '#ffd946' : 'white' }">
          <img src="../assets/icon/兼职.png" height="30" width="30" alt=""/>
        </div>
        <div class="text">兼职</div>
      </div>
      <div class="group" id="licai" @click="categoryId = 14; clickShow('licai')">
        <div class="icon" :style="{ backgroundColor: backgroundColor === 'licai' ? '#ffd946' : 'white' }">
          <img src="../assets/icon/理财.png" height="30" width="30" alt=""/></div>
        <div class="text">理财</div>
      </div>
      <div class="group" id="giftcash" @click="categoryId = 15; clickShow('giftcash')">
        <div class="icon" :style="{ backgroundColor: backgroundColor === 'giftcash' ? '#ffd946' : 'white' }">
          <img src="../assets/icon/礼金.png" height="30" width="30" alt="">
        </div>
        <div class="text">礼金</div>
      </div>
    </div>
    <div class="line">
      <div class="block"></div>
      <div class="group" id="others" @click="categoryId = 16; clickShow('others')">
        <div class="icon" :style="{ backgroundColor: backgroundColor === 'others' ? '#ffd946' : 'white' }">
          <img src="../assets/icon/其他.png" height="30" width="30"/></div>
        <div class="text">其他</div>
      </div>
      <!-- <div class="group" id="setting" @click="categoryId = null; clickShow('setting')">
        <div class="icon" :style="{ backgroundColor: backgroundColor === 'setting' ? '#ffd946' : 'white' }">
          <img src="../assets/icon/设置.png" height="30" width="30" alt=""/></div>
        <div class="text">设置</div>
      </div> -->
    </div>
  </div>

  <div class="calculator" v-show="isVisible">
    <div class="input-container">
      <!--      <input type="text" id="input" v-model="input" readonly="readonly"/>-->
      <input type="text" id="output" v-model="output" readonly="readonly"/>
      <div class="remark">
        <div class="remark" id="text">备注：</div>
        <input class="remark" type="text"/>
      </div>
    </div>

    <div class="line">
      <div class="key" id="7" @click="clickNumber(7)">7</div>
      <div class="key" id="8" @click="clickNumber(8)">8</div>
      <div class="key" id="9" @click="clickNumber(9)">9</div>
      <div class="key" id="date" @click="clickDate()">日期</div>
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
      <!-- <div class="key" id="=" @click="clickConfirm" style="background: #ffd946">=</div> -->
      <div class="key" id="=" @click="submit" style="background: #ffd946">提交</div>
    </div>
  </div>

</template>

<script>
// 实现计算器的功能
export default {
  data() {
    return {
      id: this.$route.query.id,
      categoryId: 0,
      input: '',
      output: '0.00',
      operator: '',
      date: '',
      time: '',
      category: '',
      amount: '',
      note: '',
      count: 0,
      isVisible: false,
      containerHeight: 680,
      backgroundColor: 'ffffff',
    };
  },
  methods: {
    // 回到主界面
    gotoMain() {
      this.$router.push({path: '/main', query:{id: this.id}})
    },
    // 跳转到支出页面
    gotoOutput() {
      this.$router.push({path: '/output', query:{id: this.id}})
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
    // 点击后显示
    clickShow(category) {
      this.isVisible = true;
      this.containerHeight = 360;
      this.backgroundColor = category;

    },
    // 点击后改变高度
    changeHeight() {
      this.containerHeight = this.containerHeight - 320;
    },
    // 提交表单
    submit() {
      this.$axios.post('http://localhost:8080/bill/create', {
        userId: this.id,
        categoryId: this.categoryId,
        type: true,
        amount: this.output,
        date: "",
        note: this.note
      })
        .then(res => res.data).then(res => {
          // console.log(res) // 控制台打印返回结果观察
          // 提示创建成功还是失败
          this.$router.push({ path: '/main', query: { id: this.id } })
        })
    }
  },
};
</script>

<style scoped lang="scss">
div {
  padding: 0;
  margin: 0;
  border: 0;
}

body {
  padding: 0;
  margin: 0;
  border: 0;
  overflow: hidden;
}

.cancel {
  font-size: large;
  text-align: right;
  border-right-width: 10px;
  padding-right: 20px;
  padding-top: 40px;
}

.topic {
  width: 100%;
  height: 110px;
  background: #ffd946;
  text-align: center;
  position: relative;
}

.topic::after {
  content: "";
  position: absolute;
  left: 220px;
  bottom: 0;
  width: 32px;
  height: 2px;
  background-color: #000;
}

.category {
  overflow-y: scroll;
}

.icon {
  width: 50px;
  height: 50px;
  margin: 0 auto;

  border-radius: 50%;
  background: #ffffff;
}

.icon img {
  position: relative;
  top: 20%;
}

#Output {
  width: 2em;
  text-align: left;
  display: inline-block;
  position: absolute;
  left: 143px;
  top: 78px;
  font-size: large;
}

#Input {
  width: 2em;
  text-align: right;
  display: inline-block;
  position: absolute;
  left: 215px;
  top: 71px;
  font-size: x-large;
  font-weight: bold;
}

.group {
  width: 25%;
  text-align: center;
  display: inline-block;
}

.group :focus {
  background-color: #ffd946;
  border: 0;
  color: #ffd946;
}

.input-container {
  width: 370px;
  height: 80px;
  text-align: center;

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

.remark {
  height: 30px;
  display: flex;
  align-content: center;
  background: #efefef;
}

#text.remark {
  width: 50px;
  line-height: 30px;
  height: 30px;
  //text-index: 1em;
  font-size: 15px;
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

.block {
  height: 20px;
}

input {
  text-align: right;
  width: 90%;
  height: 30px;
  border: 0;
}

#output {
  font-size: 26px;
}
</style>