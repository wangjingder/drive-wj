<template>
	<view class="personal">
		<personal-info></personal-info>
	</view>
</template>

<script>
	import personalInfo from '../../components/personalInfo.vue'
	export default {
		components:{
			personalInfo
		},
		data() {
			return {
				
			}
		},
		onLoad() {

		},
		methods: {

		}
	}
</script>

<style>
.personal{
	width: 100%;
	height: 100%;
}
</style>
