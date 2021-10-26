<template>
	<view class="box">
		<view>
		您共答对：
		</view>
		<view>
		<text style="font-size: 20px;color: #ff0000;">{{myDate}}</text><text>道题</text>
		</view>
		<view>
			<text>考了:</text>
			<text style="font-size: 25px;color: #ff0000;">{{fenshu}}</text><text>分</text>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				myDate:{},
				fenshu:{}
			}
		},
		onLoad(option) {
			var that = this
			const myData = option.myDate
			var Hc = option.myDate
			// console.log(Hc);
			that.myDate = Hc;
			// that.myDate = myDate
			var fenshu = that.myDate*2
			that.fenshu = fenshu
		},
		methods: {
			onUnload: function () {
			    wx.reLaunch({
			      url: '../index/index'
			    })
			  }
		}
	}
</script>

<style>
.box{
	width:50%;
	height: 370rpx;
	margin: 0 auto;
	border: 1px solid #FF0000;
	border-radius: 200px;
	margin-top: 150px;
	text-align: center;
	line-height: 100rpx;
}
</style>
