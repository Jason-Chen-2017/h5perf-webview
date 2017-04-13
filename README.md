H5性能测试平台
===

系统拓扑图
===

![_E8_9E_A2_E5_B9_95_E5_BF_AB_E7_85_A7_2017-04-13_15.43.24](http://git.cn-hangzhou.oss-cdn.aliyun-inc.com/uploads/info_platfrom_auto/h5perf/56ef60d86d6c7ca8bf25a61746b92ea8/%E8%9E%A2%E5%B9%95%E5%BF%AB%E7%85%A7_2017-04-13_15.43.24.png)


系统时序交互图
===


![iper_E7_B3_BB_E7_BB_9F_E6_97_B6_E5_BA_8F_E4_BA_A4_E4_BA_92](http://git.cn-hangzhou.oss-cdn.aliyun-inc.com/uploads/info_platfrom_auto/h5perf/117ce731963692cd067bbe761a937faf/iper%E7%B3%BB%E7%BB%9F%E6%97%B6%E5%BA%8F%E4%BA%A4%E4%BA%92.png)


其中，测试机器与Web服务的交互主要依赖adb，任务的触发以及url参数的传递，采用

```
adb -s [deviceId] shell am start --es [key] [value]

```

Tcpdump抓包
====

需要root权限。



Harviewer
====
当我们拿到har文件格式的数据，直接可以输出如下的报告：


![_E8_9E_A2_E5_B9_95_E5_BF_AB_E7_85_A7_2017-04-12_22.55.44](http://git.cn-hangzhou.oss-cdn.aliyun-inc.com/uploads/info_platfrom_auto/h5perf/dfc94226a025fd86184a915f9ecd2158/%E8%9E%A2%E5%B9%95%E5%BF%AB%E7%85%A7_2017-04-12_22.55.44.png)





测试手机端app
====
android源码即当前工程。