<template>
    <div class="settings">
        <div class="title">
            <div class="exit"><img src="../assets/image/左箭头.png" alt=""></div>
            账户设置
        </div>
        <div class="avator">
            <label for="">头像</label>
            <img src="../assets/image/右箭头.png" alt="" class="rightArrow">
            <img src="../assets/image/头像.png" alt="" class="avatorImg">
        </div>
        <div class="ID">
            <label for="">ID</label>
            <input type="text" value="52356533" readonly>
        </div>
        <div class="nickname">
            <label for="">昵称</label>
            <img src="../assets/image/右箭头.png" alt="" class="rightArrow">
            <input type="text" value="测试者">
        </div>
        <div class="sex">
            <label for="">性别</label>
            <!-- 点击箭头实现性别选择，而不是让用户手动输入 -->
            <img src="../assets/image/右箭头.png" alt="" class="rightArrow">
            <input type="text" readonly>
        </div>
        <!-- 以下数据绑定后颜色为银灰色，未绑定为橘红色 -->
        <div class="phone">
            <label for="">手机号</label>
            <img src="../assets/image/右箭头.png" alt="" class="rightArrow">
            <input type="text" v-model="inputValue1"
                :class="{ textColor11: inputValue1 === '未绑定', textColor12: inputValue1 !== '未绑定' }">
        </div>
        <div class="weixin">
            <label for="">微信</label>
            <img src="../assets/image/右箭头.png" alt="" class="rightArrow">
            <input type="text" v-model="inputValue2"
                :class="{ textColor21: inputValue2 === '未绑定', textColor22: inputValue2 !== '未绑定' }">
        </div>
        <div class="emergencyPhone">
            <label for="">应急联系方式</label>
            <img src="../assets/image/右箭头.png" alt="" class="rightArrow">
            <input type="text" v-model="inputValue3"
                :class="{ textColor31: inputValue3 === '未绑定', textColor32: inputValue3 !== '未绑定' }">
        </div>
        <button class="logOut">退出登录</button>

    </div>
</template>
<script>
export default {
    data() {
        return {
            inputValue1: "未绑定",
            inputValue2: "未绑定",
            inputValue3: "未绑定",
        }
    }
}
</script>
<style scoped>
.settings {
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

.avator {
    margin-top: 14px;
    width: 390px;
    height: 85px;
    background-color: #fff;
    text-align: center;
    line-height: 75px;
}

.avatorImg {
    float: right;
    margin-top: 8px;
    margin-right: 6px;
    width: 68px;
    height: 68px;
    border-radius: 34px;
}

label {
    float: left;
    color: #101010;
    font-size: 18px;
    margin-left: 15px;

}

.rightArrow {
    float: right;
    margin-top: 25px;
    margin-right: 10px;
    width: 30px;
    height: 30px;

}

.ID {
    margin-top: 5px;
    width: 390px;
    height: 60px;
    background-color: #fff;
    text-align: center;
    line-height: 60px;
}

.ID input {
    float: right;
    margin-top: 18px;
    margin-right: 30px;
    width: 90px;
    height: 20px;
    font-size: 18px;
    color: #ADB0B2;
}

.nickname {
    margin-top: 5px;
    width: 390px;
    height: 60px;
    background-color: #fff;
    text-align: center;
    line-height: 60px;
    color: #ADB0B2;
}

.nickname input {
    margin-top: 18px;
    float: right;
    height: 20px;
    text-align: right;
    font-size: 18px;
    color: #ADB0B2;
}

.nickname .rightArrow {
    margin-top: 16px;
}

.sex {
    margin-top: 5px;
    width: 390px;
    height: 60px;
    background-color: #fff;
    text-align: center;
    line-height: 60px;
}

.sex input {
    margin-top: 18px;
    float: right;
    height: 20px;
    text-align: right;
    font-size: 18px;

}

.sex .rightArrow {
    margin-top: 16px;
}

.phone {
    margin-top: 5px;
    width: 390px;
    height: 60px;
    background-color: #fff;
    text-align: center;
    line-height: 60px;
}

.phone input {
    margin-top: 18px;
    float: right;
    height: 20px;
    text-align: right;
    font-size: 18px;
}

.phone .rightArrow {
    margin-top: 16px;
}

.weixin {
    margin-top: 5px;
    width: 390px;
    height: 60px;
    background-color: #fff;
    text-align: center;
    line-height: 60px;
}

.weixin input {
    margin-top: 18px;
    float: right;
    height: 20px;
    text-align: right;
    font-size: 18px;
}

.weixin .rightArrow {
    margin-top: 16px;
}

.emergencyPhone {
    margin-top: 5px;
    width: 390px;
    height: 60px;
    background-color: #fff;
    text-align: center;
    line-height: 60px;
}

.emergencyPhone input {
    margin-top: 18px;
    float: right;
    height: 20px;
    text-align: right;
    font-size: 18px;
}

.emergencyPhone .rightArrow {
    margin-top: 16px;
}

.textColor11 {
    color: #EC8D49;

}

.textColor12 {
    color: #ADB0B2;

}

.textColor21 {
    color: #EC8D49;

}

.textColor22 {
    color: #ADB0B2;

}

.textColor31 {
    color: #EC8D49;

}

.textColor32 {
    color: #ADB0B2;

}

.logOut {
    margin-top: 20px;
    margin-left: 14px;
    width: 365px;
    height: 55px;
}
</style>