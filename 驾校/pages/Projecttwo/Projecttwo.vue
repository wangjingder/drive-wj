<template>
	<view>
		<view class="title">当前第{{current}}题/共{{lists.length}}题</view>
		<view class="uni-padding-wrap">
			<view class="uni-title uni-common-mt">
				题目:{{object.test}}
			</view>
		</view>
		<view class="uni-list">
			<checkbox-group @change="checkboxChange">
				<view>
					<checkbox value="A" v-if="isRefresh" />
					<text>A:{{object.choose_a}}</text>
				</view>
				<view>
					<checkbox value="B" v-if="isRefresh" />
					<text>B:{{object.choose_b}}</text>
				</view>
				<view>
					<checkbox value="C" v-if="isRefresh" />
					<text>C:{{object.choose_c}}</text>
				</view>
				<view>
					<checkbox value="D" v-if="isRefresh" />
					<text>D:{{object.choose_d}}</text>
				</view>
			</checkbox-group>
			<button type="button" @click="Checkboxone">提交</button>
			<button type="button" @click="Checkbox">下一题</button>
			<view class="title" v-if="text===true">题目解析：{{object.text}}</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				right: [],
				select: '',
				lists: [],
				current: 1,
				object: [],
				isRefresh: true,
				text: false
			}
		},
		onLoad() {
			var that = this;
			uni.request({
				url: 'http://localhost:8082/user/testtwo',
				data: {},
				success: (res) => {
					if (res.data.code != 200) {
						console.log("请求失败")
					} else {
						that.lists = res.data.data
						var object = that.lists[0]
						that.object = object
						var right = res.data.data[0].answer
						that.right = right
						console.log(res.data.data[0].answer)
					}
				}
			})
		},
		methods: {
			checkboxChange(res) {
				var that = this;
				var select = res.detail.value
				that.select = select
				// console.log('用户选择了',res.detail.value)
			},
			Checkbox() {
				var that = this;
				this.reload()
				if (that.select != "") {
					if (that.current < that.lists.length) {
						var that = this;
						var num = that.current + 1
						that.current = num
						// console.log(that.current-1)
						that.right = that.lists[that.current - 1].answer
						that.object = that.lists[that.current - 1]
					} else {
						uni.navigateTo({
							url: "../notice/notice"
						})
					}
				} else {
					uni.showToast({
						title: '请选择答案',
						duration: 2000,
						icon: 'none'
					})
				}
				that.text = false
				var select = ""
				that.select = select

			},
			Checkboxone() {
				var that = this;
				var count = 0
				// console.log(that.select[0])
				// console.log(that.right[0])
				console.log(that.select)
				// console.log(that.right.length)
				if (that.select != "") {
					if (that.select.length == that.right.length) {
						for (var i = 0; i < that.select.length; i++) {
							if (that.select[i] !== that.right[i]) {
								uni.showToast({
									title: '正确答案是:' + that.right,
									duration: 2000,
								})
								that.text = true
								break;
							} else {
								count++;
							}
						}
						if (count == that.right.length) {
							uni.showToast({
								title: '正确！',
								duration: 2000,
							})
						}
					} else {
						// console.log(that.select[0])
						// console.log(that.right[0])
						uni.showToast({
							title: '正确答案是:' + that.right,
							duration: 2000,
						})
						that.text = true
					}
					// console.log("abbbbbbbbbbbbbbb")
				} else {
					uni.showToast({
						title: '请选择答案',
						duration: 2000,
						icon: 'none'
					})
				}
			},
			reload() {
				this.isRefresh = false
				this.$nextTick(() => {
					this.isRefresh = true
				})
			}
		}

	}
</script>

<style>
	.title {
		text-align: center;
		color: red;
		margin: 15rpx;
		font-size: 45rpx;
	}
</style>


<style>

</style>
