<template>
	<view>
		<view class="content">
			<view :style="{'transform':'translate3d('+xMove+'px,'+yMove+'px,0)'}" class="touch"
				@touchstart="handleStart" @touchmove="handleMove" @touchend="handleEnd" @click="toclickquan()"><image src="/static/朋友圈.png" alt="" style="width: 90rpx;height:90rpx;" class="image_saoyisao">
					</image></view>
		</view>
		<view class="box">
			<view class="float-left">欢迎您：{{userInfo.name}}</view>
			<view class="float-right">
				<button open-type="share" class="fenxiang-button">
					<img src="/static/分享.png" alt="" style="width: 45rpx;height: 45rpx;vertical-align: middle;">分享
				</button>
				<button @click="go()" class="fenxiang-button" style="border: none;">
					<img src="/static/客服.png" alt="" style="width: 45rpx;height: 45rpx;vertical-align: middle;">客服
				</button>
			</view>
		</view>
		<view class="ninth-view">
			<ul class="ninth">
				<li @click="toclick" style="border: 1px solid #3c17f4;" class="view_saoyisao">
					<image src="/static/答题卡.png" alt="" style="width: 55rpx;height:55rpx;" class="image_saoyisao">
					</image>
					<text style="font-size: 28rpx;margin-top: 20rpx;">科一单选题</text>
				<li @click='toclickfour' style="border: 1px solid #f40a35;" class="view_saoyisao">
					<image src="/static/答题卡 (1).png" alt="" style="width: 55rpx;height:55rpx;" class="image_saoyisao">
					</image>
					<text style="font-size: 28rpx;margin-top: 20rpx;">科四单选题</text>
				</li>
				<li @click='toclicktwo' style="border: 1px solid #e9f40e;" class="view_saoyisao">
					<image src="/static/答题卡 (2).png" alt="" style="width: 55rpx;height:55rpx;" class="image_saoyisao">
					</image>
					<text style="font-size: 28rpx;margin-top: 20rpx;">科一多选题</text>
				</li>
				<li @click='toclickfive' style="border: 1px solid #32f406;" class="view_saoyisao">
					<image src="/static/答题卡 (3).png" alt="" style="width: 55rpx;height:55rpx;" class="image_saoyisao">
					</image>
					<text style="font-size: 28rpx;margin-top: 20rpx;">科四多选题</text>
				</li>
				<li @click='toclickthree' style="border: 1px solid #ff349d;" class="view_saoyisao">
					<image src="/static/答题卡 (4).png" alt="" style="width: 55rpx;height:55rpx;" class="image_saoyisao">
					</image>
					<text style="font-size: 28rpx;margin-top: 20rpx;">科一判断题</text>
				</li>
				<li @click='toclicksix' style="border: 1px solid #f48200;" class="view_saoyisao">
					<image src="/static/答题卡 (5).png" alt="" style="width: 55rpx;height:55rpx;" class="image_saoyisao">
					</image>
					<text style="font-size: 28rpx;margin-top: 20rpx;">科四判断题</text>
				</li>
				<li @click='toclickseven' style="border: 1px solid #9d00eb;" class="view_saoyisao">
					<image src="/static/地方病科.png" alt="" style="width: 55rpx;height:55rpx;" class="image_saoyisao">
					</image>
					<text style="font-size: 28rpx;margin-top: 20rpx;">地方题库</text>
				</li>
			</ul>
		</view>
	</view>
</template>

<script>
	var curPoint = {
		x: 0,
		y: 0
	}; // 记录原点
	var startPoint = {};
	// 标志位（只触发点击事件按，并没有移动-就不必触发end事件）
	var isTouchMove = false;
	export default {
		data() {
			return {
				userInfo: {},
				xMove: 0,
				yMove: 0
			}
		},
		onShareAppMessage(res) {
			return {
				title: '驾校一点通，想过你就来',
				path: '/pages/Login/Login',
				imageUrl: "/images/share.png", //自定义图片的地址
			}
		},
		onLoad() {
			var that = this;
			// var userInfo = getStorageSync("globalUser");
			// this.userInfo = userInfo
			uni.getStorage({
				key: 'globalUser',
				success: function(res) {
					console.log(res.data);
					var userInfo = res.data;
					if (userInfo != null && userInfo != "" && userInfo != undefined) {
						that.userInfo = userInfo;
					} else {
						that.userInfo = {};
					}
				}
			});
		},
		methods: {
			toclick() {
				uni.navigateTo({
					url: '/pages/Projectone/Projectone'
				})
			},
			toclicktwo() {
				uni.navigateTo({
					url: '/pages/Projecttwo/Projecttwo'
				})
			},
			toclickfour() {
				uni.navigateTo({
					url: '/pages/Projectfour/Projectfour'
				})
			},
			toclickthree() {
				uni.navigateTo({
					url: '/pages/Projectthree/Projectthree'
				})
			},
			toclickfive() {
				uni.navigateTo({
					url: '/pages/Projectfive/Projectfive'
				})
			},
			toclicksix() {
				uni.navigateTo({
					url: '/pages/Projectsix/Projectsix'
				})
			},
			toclickseven() {
				uni.navigateTo({
					url: '/pages/Groundword/Groundword'
				})
			},
			toclickquan() {
				uni.navigateTo({
					url: '/pages/quanzi/quanzi'
				})
			},
			go() {
				uni.makePhoneCall({

					// 手机号
					phoneNumber: '18547516632',
					// 成功回调
					success: (res) => {
						console.log('调用成功!')
					},
					// 失败回调
					fail: (res) => {
						console.log('调用失败!')
					}

				});
			},
			handleStart(ev) {
				// console.log('start',ev);
				// 记录一开始手指按下的坐标
				var touch = ev.changedTouches[0];
				startPoint.x = touch.pageX;
				startPoint.y = touch.pageY;
			},
			handleMove(ev) {
				// console.log('move',ev);
				// 防止页面高度很大，出现滚动条，不能移动-默认拖动滚动条事件
				ev.preventDefault();

				isTouchMove = true;

				var touch = ev.changedTouches[0];
				var diffPonit = {}; // 存放差值
				var movePonit = {
					// 记录移动的距离
					x: 0,
					y: 0
				};
				diffPonit.x = touch.pageX - startPoint.x;
				diffPonit.y = touch.pageY - startPoint.y;
				// 移动的距离 = 差值 + 当前坐标点
				movePonit.x = diffPonit.x + curPoint.x;
				movePonit.y = diffPonit.y + curPoint.y;
				this.move(movePonit.x, movePonit.y);
			},
			handleEnd(ev) {
				// console.log('end', ev);
				if (!isTouchMove) return;

				//  更新坐标原点
				var touch = ev.changedTouches[0];

				curPoint.x += touch.pageX - startPoint.x;
				curPoint.y += touch.pageY - startPoint.y;

				// 重置
				isTouchMove = false;
			},
			move(x, y) {
				x = x || 0; // 没有传就是0
				y = y || 0;
				this.xMove = x;
				this.yMove = y;
				// translate3d  (tx,ty,tz)  在X轴偏移tx，在Y轴偏移ty，在Z轴偏移tz，单位px
			}
		}
	}
</script>

<style>
	.ninth {
		/* padding: 30rpx 30rpx 0 30rpx; */
		display: flex;
		align-items: center;
		/* justify-content: space-between; */
		flex-wrap: wrap;
		background: white;
		border: 500px;
	}

	.ninth li {
		display: flex;
		align-items: center;
		justify-content: center;
		width: 33%;
		height: 250rpx;
		/* margin-bottom: 30rpx; */
		background: white;
		list-style: none;

		/*边框合并*/
		margin: 0 0 -1px -1px;
		position: relative;
		z-index: 0;
	}

	.ninth li:hover {
		border-color: orange;
		z-index: 1;
	}

	.fenxiang-button {
		border: none;
		outline: 0;
		border-color: transparent;
		width: 80%;
		height: 60rpx;
		font-size: 30rpx;
		line-height: 60rpx;
		text-align: right;
		vertical-align: middle;
		background-color: white;
	}

	.float-right {
		float: right;
		width: 50%;
	}

	.float-left {
		float: left;
		margin-top: 18px;
		margin-left: 10%;
	}

	.box {
		width: 100%;
		height: 100px;
		margin-top: 30px;
	}

	.fenxiang-button::after {
		border: none;
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

	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.touch {
		position: fixed;
		right: 20px;
		bottom: 20px;
		width: 45px;
		height: 45px;
		/* 知识点
	        line-height是行高，针对的对象是文字，height针对的是容器，
	        也就是高度，当height和line-height值相同时会居中，
	        当line-height值小于height时文字向上移动，反之向下移动。
	         */
		line-height: 45px;
		/* 文字垂直居中 */
		text-align: center;
		/* 水平居中 */
		background-color: rgba(0, 0, 0, 0.6);
		border-radius: 50%;
		color: #fff;
		font-size: 30px;
		/* 去除标签点击事件高亮效果 */
		-webkit-tap-highlight-color: transparent;
		/* 使用transform: translate3d 处理性能高 GUP */
		z-index: 99;
	}
</style>
