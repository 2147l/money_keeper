<template>
    <div class="banner">
        <img src="src/assets/image/左箭头.png" alt="" class="cancel" @click="goBack">
        <!-- 切换支出/收入页面 -->
        <select id="optType" @change="goto">
            <option selected>支出</option>
            <option>收入</option>
        </select>
        <span style="padding-left: 43px"></span>
        <!-- 周月年选择标签 -->
        <ul class="date">
            <li @click="changeState1" :class="{ chosen: flag1, unchosen: !flag1 }">周</li>
            <li @click="changeState2" :class="{ chosen: flag2, unchosen: !flag2 }">月</li>
            <li @click="changeState3" :class="{ chosen: flag3, unchosen: !flag3 }">年</li>
        </ul>
    </div>
    <!-- 周月年各自小标签 -->
    <!-- 这是周的：本周、上周、上上周 -->
    <div>
        <ul class="optLabel" v-show="flag1">
            <li @click="changeState11" :class="{ chosenOptLabel: flag11, unchosenOptLabel: !flag11 }">上上周</li>
            <li @click="changeState12" :class="{ chosenOptLabel: flag12, unchosenOptLabel: !flag12 }">上周</li>
            <li @click="changeState13" :class="{ chosenOptLabel: flag13, unchosenOptLabel: !flag13 }">本周</li>
        </ul>
    </div>

    <!-- 周月年各自小标签 -->
    <!-- 这是月的：本月、上月、上上月 -->
    <div>
        <ul class="optLabel" v-show="flag2">
            <li @click="changeState21" :class="{ chosenOptLabel: flag21, unchosenOptLabel: !flag21 }">上上月</li>
            <li @click="changeState22" :class="{ chosenOptLabel: flag22, unchosenOptLabel: !flag22 }">上月</li>
            <li @click="changeState23" :class="{ chosenOptLabel: flag23, unchosenOptLabel: !flag23 }">本月</li>
        </ul>
    </div>

    <!-- 周月年各自小标签 -->
    <!-- 这是年的：今年、去年、前年 -->
    <div>
        <ul class="optLabel" v-show="flag3">
            <li @click="changeState31" :class="{ chosenOptLabel: flag31, unchosenOptLabel: !flag31 }">前年</li>
            <li @click="changeState32" :class="{ chosenOptLabel: flag32, unchosenOptLabel: !flag32 }">去年</li>
            <li @click="changeState33" :class="{ chosenOptLabel: flag33, unchosenOptLabel: !flag33 }">今年</li>
        </ul>
    </div>
    <br>
    <br>

    <!-- 注意：下面content11、content12、content13属于周的内容 -->
    <!-- 下面这个是支出上上周的内容，标为content11 -->
    <div class="content11" v-show="flag1 && flag11">
        <!-- 这是总览数据，总支出和平均值 -->
        <div class="overview">
            <label for="">总支出：40.00</label>
            <br>
            <label for="">平均值：5.71</label>
        </div>
        <!-- 由于这里图表不知道怎么表示，就先放张图片，后续再改 -->
        <div class="chart">
            <img src="../assets/image/上上周支出.png" alt="">
        </div>
        <!-- 支出排行榜 -->
        <div class="title">
            <label for="">支出排行榜</label>
        </div>
        <!-- 下面是一些具体消费信息，由于不知道怎么调数据，这里也先固定写一些 -->
        <ul class="expense">
            <li>
                <img src="../assets/icon/fruit.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如餐饮 69.2%    67 -->
                    <div>
                        <label for="" class="label1">水果 100.0%</label>
                        <label for="" class="label2">40</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 280px;"></div>
                    <div>
                    </div>
                </div>
            </li>
        </ul>
    </div>


    <!-- 下面这个是支出上周的内容，标为content12 -->
    <div class="content12" v-show="flag1 && flag12">
        <!-- 这是总览数据，总支出和平均值 -->
        <div class="overview">
            <label for="">总支出：107.00</label>
            <br>
            <label for="">平均值：15.29</label>
        </div>
        <!-- 由于这里图表不知道怎么表示，就先放张图片，后续再改 -->
        <div class="chart">
            <img src="../assets/image/上周支出.png" alt="">
        </div>
        <!-- 支出排行榜 -->
        <div class="title">
            <label for="">支出排行榜</label>
        </div>
        <!-- 下面是一些具体消费信息，由于不知道怎么调数据，这里也先固定写一些 -->
        <ul class="expense">
            <li>
                <img src="../assets/icon/shopping.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如餐饮 69.2%    67 -->
                    <div>
                        <label for="" class="label1">购物 64.4%</label>
                        <label for="" class="label2">69</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 280px;"></div>
                    <div>
                    </div>
                </div>
            </li>
            <li>
                <img src="../assets/icon/food.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如购物 30.8%    12 -->
                    <div>
                        <label for="" class="label1">餐饮 21.5%</label>
                        <label for="" class="label2">23</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 88px;"></div>
                    <div>
                    </div>
                </div>
            </li>
            <li>
                <img src="../assets/icon/communication.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如购物 30.8%    12 -->
                    <div>
                        <label for="" class="label1">通讯 14.0%</label>
                        <label for="" class="label2">15</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 47px;"></div>
                    <div>
                    </div>
                </div>
            </li>
        </ul>
    </div>


    <!-- 下面这个是支出本周的内容，标为content13 -->
    <div class="content13" v-show="flag1 && flag13">
        <!-- 这是总览数据，总支出和平均值 -->
        <div class="overview">
            <label for="">总支出：27.00</label>
            <br>
            <label for="">平均值：3.86</label>
        </div>
        <!-- 由于这里图表不知道怎么表示，就先放张图片，后续再改 -->
        <div class="chart">
            <img src="../assets/image/本周支出.png" alt="">
        </div>
        <!-- 支出排行榜 -->
        <div class="title">
            <label for="">支出排行榜</label>
        </div>
        <!-- 下面是一些具体消费信息，由于不知道怎么调数据，这里也先固定写一些 -->
        <ul class="expense">
            <li>
                <img src="../assets/icon/food.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如餐饮 69.2%    67 -->
                    <div>
                        <label for="" class="label1">餐饮 69.2%</label>
                        <label for="" class="label2">67</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 280px;"></div>
                    <div>
                    </div>
                </div>
            </li>
            <li>
                <img src="../assets/icon/shopping.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如购物 30.8%    12 -->
                    <div>
                        <label for="" class="label1">购物 30.8%</label>
                        <label for="" class="label2">12</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 110px;"></div>
                    <div>
                    </div>
                </div>

            </li>
        </ul>
    </div>



    <!-- 注意：下面content21、content22、content23属于月的内容 -->
    <!-- 下面这个是支出上上月的内容，标为content21 -->
    <div class="content21" v-show="flag2 && flag21">
        <!-- 这是总览数据，总支出和平均值 -->
        <div class="overview">
            <label for="">总支出：59.00</label>
            <br>
            <label for="">平均值：1.90</label>
        </div>
        <!-- 由于这里图表不知道怎么表示，就先放张图片，后续再改 -->
        <div class="chart">
            <img src="../assets/image/上上月支出.png" alt="">
        </div>
        <!-- 支出排行榜 -->
        <div class="title">
            <label for="">支出排行榜</label>
        </div>
        <!-- 下面是一些具体消费信息，由于不知道怎么调数据，这里也先固定写一些 -->
        <ul class="expense">
            <li>
                <img src="../assets/icon/snack.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如餐饮 69.2%    67 -->
                    <div>
                        <label for="" class="label1">零食 100.0%</label>
                        <label for="" class="label2">59</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 280px;"></div>
                    <div>
                    </div>
                </div>
            </li>
        </ul>
    </div>


    <!-- 下面这个是支出上月的内容，标为content22 -->
    <div class="content22" v-show="flag2 && flag22">
        <!-- 这是总览数据，总支出和平均值 -->
        <div class="overview">
            <label for="">总支出：270.00</label>
            <br>
            <label for="">平均值：9.00</label>
        </div>
        <!-- 由于这里图表不知道怎么表示，就先放张图片，后续再改 -->
        <div class="chart">
            <img src="../assets/image/上月支出.png" alt="">
        </div>
        <!-- 支出排行榜 -->
        <div class="title">
            <label for="">支出排行榜</label>
        </div>
        <!-- 下面是一些具体消费信息，由于不知道怎么调数据，这里也先固定写一些 -->
        <ul class="expense">
            <li>
                <img src="../assets/icon/beautify.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如餐饮 69.2%    67 -->
                    <div>
                        <label for="" class="label1">美容 44.4%</label>
                        <label for="" class="label2">120</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 280px;"></div>
                    <div>
                    </div>
                </div>
            </li>
            <li>
                <img src="../assets/icon/communication.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如购物 30.8%    12 -->
                    <div>
                        <label for="" class="label1">通讯 33.3%</label>
                        <label for="" class="label2">90</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 214px;"></div>
                    <div>
                    </div>
                </div>
            </li>
            <li>
                <img src="../assets/icon/fruit.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如购物 30.8%    12 -->
                    <div>
                        <label for="" class="label1">水果 14.8%</label>
                        <label for="" class="label2">40</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 92px;"></div>
                    <div>
                    </div>
                </div>
            </li>
            <li>
                <img src="../assets/icon/food.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如购物 30.8%    12 -->
                    <div>
                        <label for="" class="label1">餐饮 7.4%</label>
                        <label for="" class="label2">20</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 46px;"></div>
                    <div>
                    </div>
                </div>
            </li>
        </ul>
    </div>


    <!-- 下面这个是支出本月的内容，标为content23 -->
    <div class="content23" v-show="flag2 && flag23">
        <!-- 这是总览数据，总支出和平均值 -->
        <div class="overview">
            <label for="">总支出：146.00</label>
            <br>
            <label for="">平均值：29.20</label>
        </div>
        <!-- 由于这里图表不知道怎么表示，就先放张图片，后续再改 -->
        <div class="chart">
            <img src="../assets/image/本月支出.png" alt="">
        </div>
        <!-- 支出排行榜 -->
        <div class="title">
            <label for="">支出排行榜</label>
        </div>
        <!-- 下面是一些具体消费信息，由于不知道怎么调数据，这里也先固定写一些 -->
        <ul class="expense">
            <li>
                <img src="../assets/icon/shopping.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如餐饮 69.2%    67 -->
                    <div>
                        <label for="" class="label1">购物 55.5%</label>
                        <label for="" class="label2">81</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 280px;"></div>
                    <div>
                    </div>
                </div>
            </li>
            <li>
                <img src="../assets/icon/food.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如购物 30.8%    12 -->
                    <div>
                        <label for="" class="label1">餐饮 34.2%</label>
                        <label for="" class="label2">50</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 169px;"></div>
                    <div>
                    </div>
                </div>
            </li>
            <li>
                <img src="../assets/icon/communication.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如购物 30.8%    12 -->
                    <div>
                        <label for="" class="label1">通讯 10.3%</label>
                        <label for="" class="label2">15</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 47px;"></div>
                    <div>
                    </div>
                </div>
            </li>
        </ul>
    </div>

    <!-- 注意：下面content31、content32、content33属于年的内容 -->
    <!-- 下面这个是支出前年的内容，标为content31 -->
    <div class="content31" v-show="flag3 && flag31">
        <!-- 这是总览数据，总支出和平均值 -->
        <div class="overview">
            <label for="">总支出：199.00</label>
            <br>
            <label for="">平均值：16.58</label>
        </div>
        <!-- 由于这里图表不知道怎么表示，就先放张图片，后续再改 -->
        <div class="chart">
            <img src="../assets/image/前年支出.png" alt="">
        </div>
        <!-- 支出排行榜 -->
        <div class="title">
            <label for="">支出排行榜</label>
        </div>
        <!-- 下面是一些具体消费信息，由于不知道怎么调数据，这里也先固定写一些 -->
        <ul class="expense">
            <li>
                <img src="../assets/icon/beautify.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如餐饮 69.2%    67 -->
                    <div>
                        <label for="" class="label1">美容 44.4%</label>
                        <label for="" class="label2">99</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 280px;"></div>
                    <div>
                    </div>
                </div>
            </li>
            <li>
                <img src="../assets/icon/food.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如餐饮 69.2%    67 -->
                    <div>
                        <label for="" class="label1">餐饮 33.2%</label>
                        <label for="" class="label2">74</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 209px;"></div>
                    <div>
                    </div>
                </div>
            </li>
            <li>
                <img src="../assets/icon/communication.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如餐饮 69.2%    67 -->
                    <div>
                        <label for="" class="label1">通讯 15.7%</label>
                        <label for="" class="label2">35</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 88px;"></div>
                    <div>
                    </div>
                </div>
            </li>
            <li>
                <img src="../assets/icon/snack.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如餐饮 69.2%    67 -->
                    <div>
                        <label for="" class="label1">零食 6.7%</label>
                        <label for="" class="label2">15</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 41px;"></div>
                    <div>
                    </div>
                </div>
            </li>
        </ul>
    </div>


    <!-- 下面这个是支出去年的内容，标为content32 -->
    <div class="content32" v-show="flag3 && flag32">
        <!-- 这是总览数据，总支出和平均值 -->
        <div class="overview">
            <label for="">总支出：199.00</label>
            <br>
            <label for="">平均值：16.58</label>
        </div>
        <!-- 由于这里图表不知道怎么表示，就先放张图片，后续再改 -->
        <div class="chart">
            <img src="../assets/image/去年支出.png" alt="">
        </div>
        <!-- 支出排行榜 -->
        <div class="title">
            <label for="">支出排行榜</label>
        </div>
        <!-- 下面是一些具体消费信息，由于不知道怎么调数据，这里也先固定写一些 -->
        <ul class="expense">
            <li>
                <img src="../assets/icon/shopping.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如餐饮 69.2%    67 -->
                    <div>
                        <label for="" class="label1">购物 41.2%</label>
                        <label for="" class="label2">82</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 280px;"></div>
                    <div>
                    </div>
                </div>
            </li>
            <li>
                <img src="../assets/icon/food.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如购物 30.8%    12 -->
                    <div>
                        <label for="" class="label1">餐饮 37.7%</label>
                        <label for="" class="label2">75</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 258px;"></div>
                    <div>
                    </div>
                </div>
            </li>
            <li>
                <img src="../assets/icon/communication.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如购物 30.8%    12 -->
                    <div>
                        <label for="" class="label1">通讯 13.1%</label>
                        <label for="" class="label2">26</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 88px;"></div>
                    <div>
                    </div>
                </div>
            </li>
            <li>
                <img src="../assets/icon/snack.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如购物 30.8%    12 -->
                    <div>
                        <label for="" class="label1">零食 8.0%</label>
                        <label for="" class="label2">16</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 59px;"></div>
                    <div>
                    </div>
                </div>
            </li>
        </ul>
    </div>


    <!-- 下面这个是支出今年的内容，标为content33 -->
    <div class="content33" v-show="flag3 && flag33">
        <!-- 这是总览数据，总支出和平均值 -->
        <div class="overview">
            <label for="">总支出：551.00</label>
            <br>
            <label for="">平均值：45.92</label>
        </div>
        <!-- 由于这里图表不知道怎么表示，就先放张图片，后续再改 -->
        <div class="chart">
            <img src="../assets/image/今年支出.png" alt="">
        </div>
        <!-- 支出排行榜 -->
        <div class="title">
            <label for="">支出排行榜</label>
        </div>
        <!-- 下面是一些具体消费信息，由于不知道怎么调数据，这里也先固定写一些 -->
        <ul class="expense">
            <li>
                <img src="../assets/icon/communication.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如餐饮 69.2%    67 -->
                    <div>
                        <label for="" class="label1">通讯 29.9%</label>
                        <label for="" class="label2">165</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 280px;"></div>
                    <div>
                    </div>
                </div>
            </li>
            <li>
                <img src="../assets/icon/beautify.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如购物 30.8%    12 -->
                    <div>
                        <label for="" class="label1">美容 21.8%</label>
                        <label for="" class="label2">120</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 217px;"></div>
                    <div>
                    </div>
                </div>
            </li>
            <li>
                <img src="../assets/icon/food.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如购物 30.8%    12 -->
                    <div>
                        <label for="" class="label1">餐饮 15.6%</label>
                        <label for="" class="label2">86</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 153px;"></div>
                    <div>
                    </div>
                </div>
            </li>
            <li>
                <img src="../assets/icon/shopping.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如购物 30.8%    12 -->
                    <div>
                        <label for="" class="label1">购物 14.7%</label>
                        <label for="" class="label2">81</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 136px;"></div>
                    <div>
                    </div>
                </div>
            </li>
            <li>
                <img src="../assets/icon/snack.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如购物 30.8%    12 -->
                    <div>
                        <label for="" class="label1">零食 10.7%</label>
                        <label for="" class="label2">59</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 88px;"></div>
                    <div>
                    </div>
                </div>
            </li>
            <li>
                <img src="../assets/icon/fruit.png" width="45" height="45" alt="">
                <div class="data">
                    <!-- 下面放的是一些数据信息，如购物 30.8%    12 -->
                    <div>
                        <label for="" class="label1">水果 7.3%</label>
                        <label for="" class="label2">40</label>
                    </div>
                    <br>
                    <!-- 下面放的是百分比例条，由于这里不知道怎么调用，这里先固定写，后续再改 -->
                    <!-- 以盒子压缩表示比例条 -->
                    <div class="percentBar" style="width: 61px;"></div>
                    <div>
                    </div>
                </div>
            </li>
        </ul>
    </div>
    <tabBar :id="this.id"></tabBar>
</template>
<script>
export default {
    data() {
        return {
            id: this.$route.query.id,
            optType: "/lineChartOutput",
            // week的相关标志,比如flag1是周标签,flag11、flag12、flag13则分别是上上周、上周、本周标签
            flag1: true,
            flag11: false,
            flag12: false,
            flag13: true,

            //month的相关标志,比如flag2是月标签,flag21、flag22、flag23则分别是上上月、上月、本月标签
            flag2: false,
            flag21: false,
            flag22: false,
            flag23: true,

            // year的相关标志,比如flag3是年标签,flag31、flag32、flag33则分别是前年、去年、今年标签
            flag3: false,
            flag31: false,
            flag32: false,
            flag33: true,
        }
    },
    methods: {
        goto() {
            this.$router.replace({ path: "/lineChartInput", query: { id: this.id } })
        },
        goBack() {
            this.$router.go(-1)
        },
        changeState1() {
            this.flag1 = true;
            this.flag2 = false;
            this.flag3 = false;
        },
        changeState2() {
            this.flag1 = false;
            this.flag2 = true;
            this.flag3 = false;
        },
        changeState3() {
            this.flag1 = false;
            this.flag2 = false;
            this.flag3 = true;
        },

        // 上上周、上周、本周
        changeState11() {
            this.flag11 = true;
            this.flag12 = false;
            this.flag13 = false;
        },
        changeState12() {
            this.flag11 = false;
            this.flag12 = true;
            this.flag13 = false;
        },
        changeState13() {
            this.flag11 = false;
            this.flag12 = false;
            this.flag13 = true;
        },

        // 上上月、上月、本月
        changeState21() {
            this.flag21 = true;
            this.flag22 = false;
            this.flag23 = false;
        },
        changeState22() {
            this.flag21 = false;
            this.flag22 = true;
            this.flag23 = false;
        },
        changeState23() {
            this.flag21 = false;
            this.flag22 = false;
            this.flag23 = true;
        },

        // 前年、去年、今年
        changeState31() {
            this.flag31 = true;
            this.flag32 = false;
            this.flag33 = false;
        },
        changeState32() {
            this.flag31 = false;
            this.flag32 = true;
            this.flag33 = false;
        },
        changeState33() {
            this.flag31 = false;
            this.flag32 = false;
            this.flag33 = true;
        },


    }
}
</script>
<style scoped>
.banner {
    width: 390px;
    height: 90px;
    background-color: #FFD946;
    text-align: center;
}

.cancel {
    float: left;
    padding: 18px 0 0 15px;
}

#optType {
    width: 70px;
    height: 33px;
    margin-top: 12px;
    background-color: #FFD946;
    border: 0;
    font-size: 18px;
}

.date {
    margin-left: 20px;
    margin-top: 8px;
}

.date li {
    float: left;
    width: 117px;
    height: 24px;
    font-size: 16px;
    border: 1px solid #343233;
}

/* 当周月年分别被选中时的样式 */
.chosen {
    background-color: #343233;
    color: #FFD946;
}

.unchosen {
    background-color: #FFD946;
    color: #343233;
}

.optLabel {
    margin-left: 42px;
    margin-top: 8px;
    text-align: center;
    line-height: 29px;
}

.optLabel li {
    float: left;
    width: 102px;
    height: 29px;
}

/* 当本周、上周、上上周分别被选中时的样式 */
.chosenOptLabel {
    border-bottom: 1px solid #343233;
    color: #343233;
}

.unchosenOptLabel {
    border-bottom: 0;
    color: #C1C1C1;

}

.overview {
    margin-top: 15px;
    margin-left: 33px;
    font-size: 14px;
    color: #8E8E8E;
}

.chart {
    width: 390px;
    height: 140px;
    border-bottom: 1px solid #dddddd;
    padding-left: 20px;
    margin-top: 15px;

}

.title {
    margin-top: 15px;
    padding-left: 20px;
    font-size: 16px;
    color: #101010;
}

.expense {
    margin-top: 15px;
    margin-left: 20px;

}

.expense li {
    width: 330px;
    height: 40px;
    margin-bottom: 15px;
}

.data {
    float: right;
    width: 280px;
    height: 50px;
    color: #101010;
}

.label1 {
    float: left;
}

.label2 {
    float: right;
}

.percentBar {
    float: left;
    margin-top: 10px;
    height: 4px;
    background-color: #FFD946;
}
</style>