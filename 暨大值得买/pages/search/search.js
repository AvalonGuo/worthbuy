
Page({
    data: {

    },
    onLoad(options) {

    },
    onReady() {

    },
    onShow() {

    },
    onHide() {

    },
    onUnload() {

    },
    onPullDownRefresh() {

    },
    onReachBottom() {

    },
    onShareAppMessage() {

    },
      // 获取焦点 唤起软键盘
  bindfocus(e){
    console.log(e, '键盘弹起')
    console.log(e)
    this.setData({
      bottomHeight:20 //将键盘的高度设置为comment容器与page容器下边界之间的距离。
    })
   
    },
    // 输入内容
    bindinput(e){
      this.setData({
        content:e.detail.value
      })
    },
    
    // 失去焦点 
    bindblur(e){
      console.log(e, '收起键盘')
      this.setData({
        bottomHeight:0
      })
    },
    OnTapDelete(e){
        console.log(e)
    }
})