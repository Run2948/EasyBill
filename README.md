# 简易记账客户端 EasyBill

在我们生活中经常不知道钱花在什么地方，但是我们自己又不是很想写在记账本上进行管理,因为关于记账，我们每个人都有自己的需求，虽然市面上有许多优秀的记账app,所以对于自己来说，开发一个属于自己的软件来记账是个非常有趣的事情，但一直因为时间安排的原因未能实现。 
所以趁着本学期程序实践的机会，顺便做一个项目---采用MVP架构的Android记账本APP，后台采用ssm框架，本人小白，如果有不足的地方，希望看到的大神给予指点建议，不胜感激！

### 下载资源
应用下载地址：[简易记账 v1.2.apk](https://github.com/Run2948/EasyBill/blob/master/screenshots/简易记账v1.2.apk)

### 版本日志

v6.04.1:2019.06.04
- 基于nodejs + Express + MongoDB 重新服务接口
- 完善了检查更新更能
- 添加了云同步功能
- 修复了本地删除的Bug 
- 后台地址(采用express框架)：[https://github.com/Run2948/nodeBillBook](https://github.com/Run2948/nodeBillBook)

v1.2.0: 2018.10.03
- 后台地址(采用ssm框架)：[https://github.com/Run2948/ssmBillBook](https://github.com/Run2948/ssmBillBook)

v0.4.1: 2018.03.31
- 添加本地账单；

v0.3.1: 2017.12.28
- 修复用户无法注册；
- 修复修改账单无效；
- 添加上传头像功能；
- 添加修改主题功能；
- 添加账单分类管理；
- 添加侧滑删除编辑；

### 程序效果如下
<p>
<img width="32%" src="https://github.com/Run2948/EasyBill/blob/master/screenshots/Screenshot_1541742087.png" />
<img width="32%" src="https://github.com/Run2948/EasyBill/blob/master/screenshots/Screenshot_1541742161.png" />
<img width="32%" src="https://github.com/Run2948/EasyBill/blob/master/screenshots/Screenshot_1541742167.png" />
<img width="32%" src="https://github.com/Run2948/EasyBill/blob/master/screenshots/Screenshot_1541742184.png" />
<img width="32%" src="https://github.com/Run2948/EasyBill/blob/master/screenshots/Screenshot_1541744966.png" />
<img width="32%" src="https://github.com/Run2948/EasyBill/blob/master/screenshots/Screenshot_1541745005.png" />
<img width="32%" src="https://github.com/Run2948/EasyBill/blob/master/screenshots/Screenshot_1541745031.png" />
<img width="32%" src="https://github.com/Run2948/EasyBill/blob/master/screenshots/Screenshot_1541745071.png" />
<img width="32%" src="https://github.com/Run2948/EasyBill/blob/master/screenshots/Screenshot_1541745045.png" />
</p>

##### 注：本程序素材来源网络，如有影响你的权益，请及时联系本人

### TODO
- [x] 帐薄总支出、收入显示。
- [x] 账目数据增加编辑功能。
- [x] 账目数据增加归类功能。
- [x] 账目数据增加同步功能。
- [x] 自定义分类、支付方式。
- [x] 统计功能。
- [x] 换肤功能。
- [x] 检测更新。
- [x] 应用瘦身。
- [ ] 密码锁功能。

### Thanks to
 1. butterknife: https://github.com/JakeWharton/butterknife
 2. glide: https://github.com/bumptech/glide
 3. okhttp: https://github.com/square/okhttp
 4. MPAndroidChart: https://github.com/PhilJay/MPAndroidChart
 5. Android-PickerView: https://github.com/Bigkoo/Android-PickerView
 6. AwesomeSplash: https://github.com/ViksaaSkool/AwesomeSplash

### 更新.gitignore
 1. git rm -r --cached .                //清空缓存
 2. git add .                           //重新提交
 3. git commit -m "update .gitignore"   //暂存本地
 4. git push                            //推送远端

### [ERROR: x86 emulation currently requires hardware acceleration!的解决方法](https://blog.csdn.net/chjqxxxx/article/details/52541714)
##### 前提： CPU 支持 VT （Virtualization Technology）， 而且仅限于 Intel CPU
* 首先要打开SDK Manager 下载intel haxm，下载位置：android-sdk\extras\intel\Hardware_Accelerated_Execution_Manager\IntelHaxm.exe
* 下载完毕后运行IntelHaxm.exe(intelhaxm-android.exe)安装，完毕后命令行执行 sc query intelhaxm ，如果 STATE RUNNING 表示安装成功 。(如果BIOS里面没有开启Virtualization Technology，安装的时候会有相关错误提示，进BIOS开启就好)
* 打开 SDK Manager 下载 intel x86镜像创建AVD，CPU选择 intel atom x86

### [如何解决android studio不能在线安装插件的问题](https://blog.csdn.net/yyongchao/article/details/81016597)
* 勾掉use secure connction，问题即可完美解决。

### [修改Android Studio 的默认配置后，插件无法安装和使用问题](https://blog.csdn.net/wohyd/article/details/80090503)
* 当修改完idea.system.path后，默认的插件路径也在这个路径下的plugins目录下了。
* 所以我们要重新指定一下插件的目录：`idea.plugins.path=${idea.system.path}/plugins`。

### [Android Studio安装插件 重启后保存不上的问题](https://blog.csdn.net/qq_27818541/article/details/51558955)
* 在idea.properties文件中最后一行添加如下一句话：`idea.plugins.path=${idea.system.path}/plugins`