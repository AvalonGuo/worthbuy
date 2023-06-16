// pages/tip-off/tip-off.js
Page({
  data: {
    index:0,
    month:3,
    iarray:[2,3,4,5,6],
    array:["2","3","4","5","6"],
    count:3,
    imgs:[],
    gdname:"",
    currentPrice:0,
    htryPrice:{
        "2":"0",
        "3":"0",
        "4":"0",
        "5":"0",
        "6":"0"
    },
    input:""
  },
  // 点击返回按钮
  onTapReturn(){
    wx.navigateTo({
      url: '/pages/home/home'
    })
  },
  GetGdname(e){
    this.setData({
        gdname:e.detail.value
    })
  },
  GetInput(e){
    this.setData({
        input:e.detail.value
        
    })
  },
  GetCurrentPrice(e){
    this.setData({
        currentPrice:e.detail.value
    })
  },
  GetHtryPrice(e){
    var htryPrice=this.data.htryPrice
    var key = this.data.array[this.data.index]
    htryPrice[key] = Number(e.detail.value)
    this.setData({
        htryPrice:htryPrice
    })
  },
  OnTapTipOff(){
    var gdname = this.data.gdname
    var tempFilePath = this.data.imgs
    wx.request({
      url: 'http://localhost:8080/goodsModule/upLoadNewGood',
      data:{
          uid:wx.getStorageSync('uid'),
          input:this.data.input,
          gdname:this.data.gdname,
          currentPrice:this.data.currentPrice,
          htryPrice:this.data.htryPrice,
          imgs:this.data.imgs
      },
      success(res){
          for(var i=0;i<tempFilePath.length;i++){
              wx.uploadFile({
                filePath: tempFilePath[i],
                name: "file",
                url: 'http://localhost:8080/goodsModule/upLoadPic',
                //额外参数
                formData:{
                    gdname:gdname,
                    i:i
                }
              })
          }
      }
    })
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
  bindPickerChange(e){
    this.setData({
        index:e.detail.value
    })
  },
  OnTapUpload(){
    switch (this.data.imgs.length) {
        case 0:
          this.data.count = 3
          break
        case 1:
          this.data.count = 2
          break
        case 2:
          this.data.count = 1
          break
      }
    var that = this
    wx.chooseImage({
        count:that.data.count,
        success(res){
            var tempFilePaths = res.tempFilePaths
            that.setData({
                imgs:tempFilePaths
            })
        }
    })
  },

})