# MVP-News
一款基于MVP开发的新闻类app项目；
项目主要包括新闻、段子、微信精选阅读三部分，其中数据来源于聚合数据的免费api接口；
项目整体框架由Retrofit2+okhttp+RxJava基于MPV模式搭建；
新闻页由于api接口不提供分页，所以只对段子模块和微信精选模块做了分页加载处理；
图片加载框架使用Glide加载；
图片缩放控件使用PhotoView；
