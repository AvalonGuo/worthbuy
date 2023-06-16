// pages/report/report.js
const app = getApp()
Page({
  data: { 
    items: [
      {name: 'USA', value: '虚假信息'},
      {name: 'CHN', value: '恶意欺骗', checked: 'true'},
      {name: 'BRA', value: '言语辱骂'},
      {name: 'JPN', value: '违法'},
    ],
    reason:"",
    uid:"",
    GoodID:""
  },
  OnTapReturn(){
    wx.navigateTo({
      url: '../comment/comment',
    })
  },
  checkboxChange: function(e) {
      var reason = e.detail.value
      this.setData({
          reason:reason,
          uid:wx.getStorageSync('uid'),
          GoodID:app.good[0].id
      })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },
  OnTapSubmmit(){
    wx.request({
        url: 'http://localhost:8080/MyInfoModule/Report',
        data:{
            GoodID:this.data.GoodID,
            uid:this.data.uid,
            reason:this.data.reason,
            gdname:app.good[0].gdname
        }
    }),
     wx.showToast({
            title: '举报成功',
     })
      setTimeout( ()=>{
        wx.navigateTo({
            url: '../comment/comment',
          })
     },2000);

  }
})