# 工程简介

所有类、函数都要有注释，要让别人能看懂代码功能

## 返回类

com/fzu/result/ServiceResult.java

基本应用在controller层

里面有大量函数，先看一遍

主要看createBySuccess、createByError

所有错误要返回错误信息，自己先测试自己写的接口



## 实体类entity @ApiModel

与数据库对应，可根据需求增加参数

## VO类 @ApiModel

与请求体对应，一个请求对应一个VO类

## mapper层

继承BaseMapper<>

## service层

### 接口：

继承IService<>

### impl实现类：

继承ServiceImpl<>，继承对应接口

## controller层 @Api @ApiOperation @ApiImplicitParams @ApiImplicitParam @ApiParam

接受前台数据和返回页面请求信息，不能直接操作数据库

具体逻辑应在service实现，能封装尽量封装

controller基本都是调用方法，不写逻辑

## utils工具类

### MsgCodeUtils

封装各种信息

### Page

分页查询使用

### PictureUtil

sshSftp方法：图片保存到服务器的/home/images目录下

图片命名：微信用户openpid-图片种类-序号.jpg

