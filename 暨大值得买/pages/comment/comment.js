// pages/searchresult/searchresult.js

const app = getApp()
Page({
    data: {
        good:[],
        star:1,
        zelist:["../../images/ze-success.png","../../images/ze-plus.png"],
        pic:[],
        checkprice:0,
        deserve:[0,1,3],
        comments:["1"],
        input:""
    },
    OnTapReturn(){
        wx.switchTab({
          url: '../home/home',
        })
    },
    onLoad() {
        var that = this
        wx.request({
          url: 'http://localhost:8080/goodsModule/getMyDeserve',
          data:{
            uid:wx.getStorageSync('uid')
          },
          success(res){
              app.deserve_list=res.data             
          }
        })
        wx.request({
          url: 'http://localhost:8080/goodsModule/getNotDeserve',
          data:{
            uid:wx.getStorageSync('uid')
          },
          success(res){
              app.not_deserve_list = res.data
          }
        })
        wx.request({
          url: 'http://localhost:8080/MyInfoModule/GetCommentByGID',
          data:{
              GoodID:app.good[0].id,
          },
          success(res){
              that.setData({
                  comments:res.data
              })
              
          }
        })
        
        var deserve = this.data.deserve
        var goodID = app.good[0].id
        deserve[0] = app.good[0].deserve
        deserve[1] = app.good[0].not_deserve
        if(app.deserve_list.indexOf(goodID)!=-1){
            deserve[2] = 1
        }
        else if(app.not_deserve_list.indexOf(goodID)!=-1){
            deserve[2] = 0
        }
        this.setData({
            deserve:deserve,
            good:app.good,
            pic:app.good[0].pic,
        })
    },
    getStarInfo(){
        var star = this.data.star==1?0:1
        this.setData({
            star:star
        })
    },
    onClickHtryprice:function(e){
        let that = this
        var checkprice = this.data.checkprice
        if(checkprice==0){
            wx.request({
              url: 'http://localhost:8080/goodsModule/htryPriceGet',
              data:{
                Id:e.currentTarget.dataset.goodid,
              },
              success(){
                  checkprice=1
                  that.setData({
                      checkprice:checkprice,
                  })
                  
              }
            })
        }else{
            checkprice=0
            that.setData({
                checkprice:checkprice,
            })
        }
    },
    OnTapDeserve(e){
        var index = e.currentTarget.dataset.index
        var deserve=this.data.deserve
        deserve[2] = index
        if(index==1){
            app.good[0].deserve +=1
            wx.request({
              url: 'http://localhost:8080/goodsModule/UpdateDeserve',
              data:{
                  uid:wx.getStorageSync('uid'),
                  GoodID:this.data.good[0].id,
                  deserve:app.good[0].deserve
              },
            })
            this.onLoad()
        }
        else{
            app.good[0].not_deserve +=1
            wx.request({
              url: 'http://localhost:8080/goodsModule/UpdateNotDeserve',
              data:{
                uid:wx.getStorageSync('uid'),
                GoodID:this.data.good[0].id,
                not_deserve:app.good[0].not_deserve,
              }
            })
            this.onLoad()
        }

    },
    OnTapReport(){
        wx.navigateTo({
          url: '../report/report',
        })
    },
    OnTapInput(e){
        this.setData({
            input:e.detail.value
        })
    },
    OnTapFaBu(){
        var that = this
        wx.request({
          url: 'http://localhost:8080/MyInfoModule/InsertComment',
          data:{
              GoodID:app.good[0].id,
              uid:wx.getStorageSync('uid'),
              gdname:app.good[0].gdname,
              input:this.data.input
          },
          success:(res)=>{
              this.setData({
                  input:""
              })
              that.onLoad()
          }
        })
    },
    onReady() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow() {
    },

    onHide() {
    },
    onUnload() {

    },
    onPullDownRefresh() {

    },
    
})