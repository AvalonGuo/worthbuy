// pages/myself/myself.js
Page({
    data: {
        Reports:[1],
        Comment:[1],
        show:false,
        show1:false,
        rowlist:["../../images/ze-arrow-down@1x.png","../../images/ze-arrow-up@1x.png"],
    },
    onLoad() {
    },
    OnTapShow(){
        wx.request({
            url: 'http://localhost:8080/MyInfoModule/GetReportByUid',
            data:{
              uid:wx.getStorageSync('uid')
            },
            success:(res)=>{
                this.setData({
                    Reports:res.data
                })
            }
          })
        var show = this.data.show
        this.setData({
            show:!show
        })
    },
    OnTapShow1(){
        wx.request({
          url: 'http://localhost:8080/MyInfoModule/GetCommentByUID',
          data:{
              uid:wx.getStorageSync('uid')
          },
          success:(res)=>{
                this.setData({
                    Comment:res.data
                })
          }

        })
        var show1 = this.data.show1
        this.setData({
            show1:!show1
        })
    },
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

    /**
     * 用户点击右上角分享
     */
    OnTapGetGoods(e){
        let newarr = [];
        console.log(e.currentTarget.id)

    }
})