<template>
    <div class="settings" v-if="isShow">
        <!-- 标题 -->
        <div class="title">
            <div class="cancel"><img src="../assets/image/左箭头.png" alt="" @click="goBack"></div>
            账户设置
        </div>
        <!-- 头像设置 -->
        <div class="avator">
            <label for="">头像</label>
            <img src="../assets/image/右箭头.png" alt="" class="rightArrow">
            <img :src=result.avatar alt="" class="avatorImg">
        </div>
        <!-- ID展示 -->
        <div class="ID">
            <label for="">ID</label>
            <input type="text" :value=result.id readonly>
        </div>
        <!-- 昵称设置 -->
        <div class="nickname">
            <label for="">昵称</label>
            <img src="../assets/image/右箭头.png" alt="" class="rightArrow">
            <input type="text" :value=result.username>
        </div>
        <!-- 性别设置 -->
        <div class="sex">
            <label for="">性别</label>
            <!-- 点击箭头实现性别选择，而不是让用户手动输入 -->
            <img src="../assets/image/右箭头.png" alt="" class="rightArrow">
            <input type="text" :value=result.sex readonly>
        </div>
        <!-- 以下数据绑定后颜色为银灰色，未绑定为橘红色 -->
        <!-- 手机号绑定 -->
        <div class="phone">
            <label for="">手机号</label>
            <img src="../assets/image/右箭头.png" alt="" class="rightArrow">
            <input type="text" v-model="result.phone"
                :class="{ textColor11: result.phone === '未绑定', textColor12: result.phone !== '未绑定' }">
        </div>
        <!-- 微信绑定 -->
        <div class="weixin">
            <label for="">微信</label>
            <img src="../assets/image/右箭头.png" alt="" class="rightArrow">
            <input type="text" v-model="result.wechat"
                :class="{ textColor21: result.wechat === '未绑定', textColor22: result.wechat !== '未绑定' }">
        </div>
        <!-- 应急联系方式绑定 -->
        <!-- <div class="emergencyPhone">
            <label for="">应急联系方式</label>
            <img src="../assets/image/右箭头.png" alt="" class="rightArrow">
            <input type="text" v-model="inputValue3"
                :class="{ textColor31: inputValue3 === '未绑定', textColor32: inputValue3 !== '未绑定' }">
        </div> -->
        <!-- 退出登录按钮 -->
        <button class="logOut" @click="exit">退出登录</button>

    </div>
</template>
<script>
export default {
    data() {
        return {
            isShow: false,
            id: this.$route.query.id,
            result: {
                id: null,
                phone: null,
                password: null,
                username: null,
                avatar: null,
                sex: null,
                wechat: null,
                email: null
            },
            inputValue1: "未绑定",
            inputValue2: "未绑定",
            inputValue3: "未绑定",
        }
    },
    methods: {
        goBack() {
            this.$router.go(-1)
        },
        // 向后台获取用户数据
        getInfor() {
            this.$axios.get("http://localhost:8080/user/getById", { params: { userId: this.id } })
                .then(res => {
                    this.result = res.data
                    if (null == this.result.username)
                        this.result.username = "未知"
                    if (null == this.result.avatar)
                        this.result.avatar = "src/assets/image/我的.png"
                    if (null == this.result.sex)
                        this.result.sex = "未知"
                    if (null == this.result.wechat)
                        this.result.wechat = "未绑定"
                    if (null == this.result.email)
                        this.result.email = "未绑定"
                    this.isShow = true
                    console.log(res.data)
                })
                .catch(err => {
                    console.log(err)
                })
        },
        exit() {
            this.$router.push('/login')
        }
    },
    created() {
        this.getInfor()
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


.cancel {
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
    height: 24px;
    font-size: 18px;
    color: #ADB0B2;
    text-align: right;
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
    height: 24px;
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
    height: 24px;
    text-align: right;
    font-size: 18px;
    color: #ADB0B2;
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
    height: 24px;
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
    height: 24px;
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