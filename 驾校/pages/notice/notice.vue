<template>
	<view>
		您已做完最后一道题
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
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

</style>
