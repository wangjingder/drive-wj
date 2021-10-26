<template>
	<view class="ninth-view">
		<swiperTabHead :tabBars="tabBars" :tabIndex="tabIndex" @tabtap="tabtap"></swiperTabHead>
  <!-- <ul class="ninth">
    <li @click="toclick">
      科一模拟考
	<li @click='toclickfour'>
	  科四模拟考
	</li>
  </ul> -->
  <view class="uni-tab-bar">
              <swiper class="swiper-box" :style="{height:swiperheight+'px'}" :current="tabIndex" @change="tabChange">
                  <swiper-item v-for="(items,index) in newslist" :key="index">
                      <scroll-view scroll-y class="list">
                          <template v-if="items.list.length > 0">
                              <!-- 图文列表 -->
                              <block v-for="(item,index1) in items.list" :key="index1">
                                  <view>{{item}}</view>
								  <ul class="ninth" v-if="current===0">
								    <li @click="toclick" style="border: 1px solid #3c17f4;border-radius: 200px;" class="view_saoyisao">
										<image src="/static/答题卡.png" alt="" style="width: 55rpx;height:55rpx;" class="image_saoyisao"></image>
								        <text style="font-size: 28rpx;margin-top: 20rpx;">科一模拟考</text>
									  </li>
								  </ul>
								 <ul class="ninth" v-else>
								   <li @click="toclickfour" style="border: 1px solid #f40a35;border-radius: 200px;" class="view_saoyisao">
								 	<image src="/static/答题卡 (2).png" alt="" style="width: 55rpx;height:55rpx;" class="image_saoyisao"></image>
								    <text style="font-size: 28rpx;margin-top: 20rpx;">科四模拟考</text>
								 	</li>
								 </ul>
                              </block>
                          </template>
                      </scroll-view>
                  </swiper-item>
              </swiper>
          </view>
</view>
</template>

<script>
	import swiperTabHead from "../../components/tab.vue";
	export default {
		components:{
		     swiperTabHead,
		   },
		data() {
			return {
				current:0,
				tabIndex:0,// 选中的
				tabBars:[
				      { name:"科目一",id:"kemuyi" },
				      { name:"科目四",id:"kemusi" },
				       ],
				swiperheight:500,//高度
				newslist:[
				   {
				     list:[
						""
				       ]
				   },
				   { list:[
						""
				       ],
					   },
				]
			}
		},
		onLoad() {
	
		},
		methods: {
			tabtap(index){
			                this.tabIndex = index;
			},
			toclick(){
				uni.navigateTo({
					url:'/pages/Kaoshi/Kaoshi'
				})
			},
			tabChange(e){
			   this.tabIndex = e.detail.current;
			   var that = this
			   var current = e.detail.current
			   that.current = current
			   console.log(e.detail.current)
			   },
			}
		}
</script>

<style>
.ninth{
 /* padding: 30rpx 30rpx 0 30rpx; */
 display: flex;
 align-items: center;
 /* justify-content: space-between; */
 flex-wrap: wrap;
 background: white;
 height: 350px;
}
.ninth li{
		display: flex;
		align-items: center;
		justify-content: center;
		width: 33%;
		height: 250rpx;
		/* margin-bottom: 30rpx; */
		background: white;
		list-style: none;
		
		    /*边框合并*/
		margin:0 0 -1px -1px;
		position: relative; 
		z-index: 0;
		margin-left: 33%;
	}
.ninth li:hover{
	    border-color: orange;
	    z-index: 1;
	}
	.view_saoyisao {
	  display: flex;
	  flex-direction: column;
	  width: 60px;
	  align-items: center;
	}
	
	.image_saoyisao {
	  display: flex;
	  width: 40px;
	  height: 40px;
	}
</style>