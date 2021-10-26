<template>
	<view class="body">
		<!-- <view v-if="userIsLogin">
			<!-- <view>
				<view>账号：{{userInfo.userName}}</view>
				<view>手机号：{{userInfo.phone}}</view>
				<view>真实姓名：{{userInfo.name}}</view>
			</view> -->
		<!-- </view> -->
		<view class="list" v-if="userIsLogin">
			<view class="list-box">
				<view class="left">	<image src="/static/账号.png" alt="" style="width: 55rpx;height:55rpx;vertical-align: middle;margin-right: 15px;" class="image_saoyisao"></image>账号：{{userInfo.userName}}</view>
			</view>
			<view class="list-box">
				<view class="left"><image src="/static/手机号.png" alt="" style="width: 55rpx;height:55rpx;vertical-align: middle;margin-right: 15px;" class="image_saoyisao"></image>手机号：{{userInfo.phone}}</view>
			</view>
			<view class="list-box">
				<view class="left"><image src="/static/姓名.png" alt="" style="width: 55rpx;height:55rpx;vertical-align: middle;margin-right: 15px;" class="image_saoyisao"></image>真实姓名：{{userInfo.name}}</view>
			</view>
		</view>	
		<view v-else>请登录后再来！</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userIsLogin:false,
				userInfo:{}
			}
		},
		onShow() {
			var that = this;
			// var userInfo = getStorageSync("globalUser");
			uni.getStorage({
			    key: 'globalUser',
			    success: function (res) {
			        console.log(res.data);
					var userInfo = res.data;
			if(userInfo != null && userInfo != "" && userInfo != undefined){
				that.userIsLogin = true;
				that.userInfo = userInfo;
			}else{
				that.userIsLogin = false;
				that.userInfo = userInfo;
			}
			}
			});
		},
		methods: {
			
		}
	}
</script>

<style>
.body{
	width: 100%;
}
.box{
	width: 90%;
	height: 80px;
	border: 1px solid black;
	border-radius: 15px;
	margin:auto;
	margin-bottom: 8px;
	background: url(../../static/img/1.jpg) 0 0 no-repeat;
	background-size: 100% 100%;
}
.h{
	margin-left:20rpx;
	margin-top: 10rpx;
}
.p{
	margin-left: 20rpx;
	margin-top: 60rpx;
}
.t{
	float: right;
	margin-right: 20rpx;
	margin-top: 10rpx;
	color: red;
}
.list{
		margin-top: 40px;
		padding: 5rpx 2px;
	}
	.list-box{
		width: 100%;
		height: 45px;
		line-height: 45px;
		border: 1px solid #dedede;
		margin:0 0 -1px -1px;
		position: relative; 
		z-index: 0;
	}
	.left{
		float: left;
		font-size: 15px;
		margin-left: 10px;
	}
	.right{
		float: right;
		font-size: 15px;
		width: 10px;
	}
</style>
