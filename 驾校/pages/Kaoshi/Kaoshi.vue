<template>
	<view>
		<view class="jishiqi">{{minute}}分{{second}}秒</view>
		<view class="title">当前第{{current}}题/共{{result1.length+result2.length+result3.length}}题</view>
		<progress :percent="percent" border-radius='20' stroke-width="15" show-info="true" font-size="18"
			style="margin: 15rpx;" />
		<view v-if="flag===1">
			{{object1.type}}:<view class="title">第{{current1}}题/共{{result1.length}}题</view>
			<view>题目：{{object1.test}}</view>
			<radio-group @change="radioChange">
				<view>
					<radio value="A" v-if="isRefresh" />
					<text>A:{{object1.choose_a}}</text>
				</view>
				<view>
					<radio value="B" v-if="isRefresh" />
					<text>B:{{object1.choose_b}}</text>
				</view>
				<view>
					<radio value="C" v-if="isRefresh" />
					<text>C:{{object1.choose_c}}</text>
				</view>
				<view>
					<radio value="D" v-if="isRefresh" />
					<text>D:{{object1.choose_d}}</text>
				</view>
				<button type="button" @click="Checkboxone">下一题</button>
			</radio-group>
		</view>


		<view v-else-if="flag===2">
			{{object2.type}}:<view class="title">第{{current1}}题/共{{result2.length}}题</view>
			<!-- <view class="title">当前第{{current}}题/共{{result2.length}}题</view> -->
			<view class="uni-padding-wrap">
				<view class="uni-title uni-common-mt">
					题目:{{object2.test}}
				</view>
			</view>
			<view class="uni-list">
				<checkbox-group @change="checkboxChange">
					<view>
						<checkbox value="A" v-if="isRefresh" />
						<text>A:{{object2.choose_a}}</text>
					</view>
					<view>
						<checkbox value="B" v-if="isRefresh" />
						<text>B:{{object2.choose_b}}</text>
					</view>
					<view>
						<checkbox value="C" v-if="isRefresh" />
						<text>C:{{object2.choose_c}}</text>
					</view>
					<view>
						<checkbox value="D" v-if="isRefresh" />
						<text>D:{{object2.choose_d}}</text>
					</view>
				</checkbox-group>
				<button type="button" @click="Checkbox">提交</button>
				<button type="button" @click="Checkboxone">下一题</button>
			</view>
		</view>


		<view v-else="flag===3">
			{{object1.type}}:<view class="title">第{{current1}}题/共{{result3.length}}题</view>
			<!-- <view class="title">当前第{{current}}题/共{{result3.length}}题</view> -->
			<view>题目：{{object3.test}}</view>
			<radio-group @change="radioChangeone">
				<view>
					<radio value="T" v-if="isRefresh" />
					<text>T</text>
				</view>
				<view>
					<radio value="F" v-if="isRefresh" />
					<text>F</text>
				</view>
				<button type="button" @click="Checkboxone">下一题</button>
			</radio-group>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				percent: 0,
				flag: 1,
				right1: [],
				select: '',
				current: 1,
				current1: 1,
				object1: [],
				right2: [],
				object2: [],
				right3: [],
				object3: [],
				result1: [],
				result2: [],
				result3: [],
				isRefresh: true,
				subjectNow: [],
				fenshu: 0,
				minute: {},
				second: {}
			}
		},
		onShow() {
			var that = this
			let time = 2700
			setInterval(function() {
				time = time - 1;
				var minute = parseInt(time / 60);
				var second = parseInt(time % 60);
				that.minute = minute
				that.second = second
				if (time == 0) {
					uni.navigateTo({
						url: "../fenshu/fenshu?myDate=" + that.fenshu
					})
				}
			}, 1000);
		},
		onLoad() {
			var that = this;
			uni.request({
					url: 'http://localhost:8082/user/testone',
					data: {},
					success: (res) => {
						if (res.data.code != 200) {
							console.log("请求失败")
						} else {
							var result1 = [];
							for (var i = 0; i < 5; i++) {
								result1[i] = res.data.data[Math.floor(Math.random() * res.data.data.length)]
								that.result1 = result1
							}
							var object1 = that.result1[0]
							that.object1 = object1
							var right1 = that.result1[0].answer
							that.right1 = right1
							console.log(right1)
							console.log(result1);
							uni.showModal({
								title: '考试规则',
								content: '小车-C1/C2/C3科目一，考试时间45分钟，总分100分，没做错一题扣一分，及格成绩为90分',
								showCancel: false
							})
						}
					}
				}),
				uni.request({
					url: 'http://localhost:8082/user/testtwo',
					data: {},
					success: (res) => {
						if (res.data.code != 200) {
							console.log("请求失败")
						} else {
							var result2 = [];
							for (var i = 0; i < 5; i++) {
								result2[i] = res.data.data[Math.floor(Math.random() * res.data.data.length)]
								that.result2 = result2
							}
							var object2 = that.result2[0]
							that.object2 = object2
							var right2 = that.result2[0].answer
							that.right2 = right2
							console.log(right2)
							console.log(result2);
						}
					}
				}),
				uni.request({
					url: 'http://localhost:8082/user/testthree',
					data: {},
					success: (res) => {
						if (res.data.code != 200) {
							console.log("请求失败")
						} else {
							var result3 = [];
							for (var i = 0; i < 5; i++) {
								result3[i] = res.data.data[Math.floor(Math.random() * res.data.data.length)]
								that.result3 = result3
							}
							var object3 = that.result3[0]
							that.object3 = object3
							var right3 = that.result3[0].answer
							that.right3 = right3
							console.log(right3)
							console.log(result3);
						}
					}
				})

		},
		methods: {
			radioChange(res) {
				var that = this;
				var select = res.detail.value
				that.select = select
				console.log('用户选择了', select)
				if (that.select == this.right1) {
					uni.showToast({
						title: '正确！',
						duration: 2000,
					})
					var that = this;
					var num = that.fenshu + 1
					that.fenshu = num
				} else {
					uni.showToast({
						title: '错误。应选:' + that.right1,
						duration: 2000,
					})
					let subject = that.object1
					this.subject = subject
					var subjectNow = that.subjectNow
					subjectNow.push(subject)
					console.log(subjectNow)
				}
			},
			radioChangeone(res) {
				var that = this;
				var select = res.detail.value
				that.select = select
				console.log('用户选择了', select)
				if (that.select == this.right3) {
					uni.showToast({
						title: '正确！',
						duration: 2000,
					})
					var that = this;
					var num = that.fenshu + 1
					that.fenshu = num
				} else {
					uni.showToast({
						title: '错误。应选:' + that.right3,
						duration: 2000,
					})
					let subject = that.object2
					this.subject = subject
					var subjectNow = that.subjectNow
					subjectNow.push(subject)
					console.log(subjectNow)
				}
			},
			checkboxChange(res) {
				var that = this;
				var select = res.detail.value
				that.select = select
				console.log('用户选择了', res.detail.value)
			},
			Checkbox() {
				var that = this;
				var count = 0
				// console.log(that.select[0])
				// console.log(that.right[0])
				// console.log(that.select.length)
				// console.log(that.right.length)
				if (that.select.length == that.right2.length) {
					for (var i = 0; i < that.select.length; i++) {
						if (that.select[i] !== that.right2[i]) {
							uni.showToast({
								title: '错误。应选:' + that.right2,
								duration: 2000,
							})
							let subject = that.object2
							this.subject = subject
							var subjectNow = that.subjectNow
							subjectNow.push(subject)
							console.log(subjectNow)
							break;
						} else {
							count++;
						}
					}
					if (count == that.right2.length) {
						uni.showToast({
							title: '正确！',
							duration: 2000,
						})
						var that = this;
						var num = that.fenshu + 1
						that.fenshu = num
					}
				} else {
					console.log(that.select[0])
					console.log(that.right2[0])
					uni.showToast({
						title: '错误。应选:' + that.right2,
						duration: 2000,
					})
					let subject = that.object2
					this.subject = subject
					var subjectNow = that.subjectNow
					subjectNow.push(subject)
					console.log(subjectNow)
				}
			},
			Checkboxone() {
				var that = this;
				this.reload()
				// var select = ""
				// that.select = select
				if (that.select != "") {
					if (that.current < that.result1.length + that.result2.length + that.result3.length) {
						var that = this;
						var num = that.current + 1
						that.current = num
						var percent = (num / (that.result1.length + that.result2.length + that.result3.length) * 100)
							.toFixed(2)
						that.percent = percent
						if (num > 5 && num < 10) {
							that.flag = 2
						} else if (num > 10) {
							that.flag = 3
						}
						console.log(that.select)
						console.log(that.flag)
						var num1 = that.current1 + 1
						// var percent=((num - 1)/(result1.length+result2.length+result3.length) * 100).toFixed(2)
						// that.percent = percent
						if (num1 < 6) {
							that.current1 = num1
						} else {
							that.current1 = 1
						}
						that.right1 = that.result1[that.current1 - 1].answer
						that.right2 = that.result2[that.current1 - 1].answer
						that.right3 = that.result3[that.current1 - 1].answer
						that.object1 = that.result1[that.current1 - 1]
						that.object2 = that.result2[that.current1 - 1]
						that.object3 = that.result3[that.current1 - 1]
					} else {
						uni.navigateTo({
							url: "../fenshu/fenshu?myDate=" + that.fenshu
						})
						console.log("你答对了" + that.fenshu)
						// console.log("that.subjectNow",that.subjectNow)
						// uni.setStorageSync("Msg",that.subjectNow)
					}
				} else {
					uni.showToast({
						title: '请选择答案',
						duration: 2000,
						icon: 'none'
					})
				}
				var select = ""
				that.select = select
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
	.jishiqi {
		text-align: center;
		border: 1px solid #dedede;
		width: 60%;
		margin: 0 auto;
		height: 35px;
		line-height: 35px;
		border-radius: 20px;
		margin-top: 12px;
		margin-bottom: 12px;
		background-color: #aaffff;
	}
</style>
