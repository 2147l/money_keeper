<template>
    <div class="myInfo">
        <div class="banner">
            <div class="info">
                <img src="../assets/image/头像.png" alt="" class="avatorImg">
                <label for="" class="infoText">测试者</label>
            </div>
            <button>{{ message }}</button>
            <div>
                <ul>
                    <li class="first">
                        <label for="" class="text1">{{ clockInNum }}</label>
                        <br>
                        <label for="" class="text2">已连续打卡</label>
                    </li>
                    <li>
                        <label for="" class="text1">{{ accountDayNum }}</label>
                        <br>
                        <label for="" class="text2">记账总天数</label>
                    </li>
                    <li>
                        <label for="" class="text1">{{ accountNum }}</label>
                        <br>
                        <label for="" class="text2">记账总笔数</label>
                    </li>

                </ul>
            </div>
        </div>
        <div class="content">
            <div class="item">
                <img src="../assets/image/安全.png" alt="">
                <label for="">&nbsp;&nbsp;账户安全中心</label>
                <img src="../assets/image/右箭头.png" alt="" class="rightArrow">


            </div>
            <div class="item">
                <img src="../assets/image/反馈.png" alt="">
                <label for="">&nbsp;&nbsp;意见反馈</label>
                <img src="../assets/image/右箭头.png" alt="" class="rightArrow">
            </div>
            <div class="item">
                <img src="../assets/image/版本.png" alt="">
                <label for="">&nbsp;&nbsp;关于记账本</label>
                <img src="../assets/image/右箭头.png" alt="" class="rightArrow">
            </div>

        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            message: "已打卡",
            clockInNum: 1,
            accountDayNum: 1,
            accountNum: 1
        }
    }
}
</script>
<style scoped>
.myInfo {
    width: 390px;
    height: 844px;
    background-color: #F6F6F6;
}

.banner {
    position: relative;
    width: 390px;
    height: 203px;
    background-color: #FFD946;
    border-radius: 0 0 15px 15px;
}

.info {
    position: absolute;
    margin-top: 50px;
    width: 390px;
    height: 70px;
    line-height: 70px;
}

.avatorImg {
    margin-left: 20px;
    width: 68px;
    height: 68px;
    border-radius: 34px;
}

.infoText {
    margin-left: 10px;
    font-size: 18px;
    font-weight: 700;
    color: #101010;
}

button {
    float: right;
    margin-top: 70px;
    margin-right: 20px;
    width: 66px;
    height: 30px;
    line-height: 26px;
    background-color: #fff;
    border-radius: 5px;
}

ul {
    position: absolute;
    margin-top: 140px;
    width: 390px;
    text-align: center;
}

ul li {
    float: left;
    text-align: center;
    margin-right: 30px;
}

ul .first {
    margin-left: 40px;
}

.text1 {
    font-size: 20px;
    font-weight: 700;
    color: #101010;
}

.text2 {
    font-size: 16px;
    color: #7E7E7E;
    font-weight: 600;
}

.item {
    margin-left: 15px;
    padding-left: 12px;
    margin-top: 8px;
    width: 360px;
    height: 55px;
    background-color: #fff;
    line-height: 55px;
    border-radius: 5px;
}

.rightArrow {
    float: right;
    margin-top: 15px;
    margin-right: 10px;
    width: 25px;
    height: 25px;

}
</style>