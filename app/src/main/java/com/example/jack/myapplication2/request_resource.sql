CREATE TABLE `h5perf`.`request_resource` (
  `id` INT NOT NULL,
  `tid` VARCHAR(45) NULL,
  `test_url` VARCHAR(200) NULL,
  `index` INT NULL,
  `start_timestamp` BIGINT(11) NULL,
  `timestamp` BIGINT(11) NULL,
  `time_cost` INT NULL,
  `request_url` VARCHAR(500) NULL,
  `method` VARCHAR(10) NULL,
  `headers` LONGTEXT NULL,
  PRIMARY KEY (`id`));


INSERT INTO `h5perf`.`request_resource` (`tid`, `test_url`, `index`, `start_timestamp`, `timestamp`, `time_cost`, `request_url`, `method`, `headers`) VALUES (null, https://www.baidu.com, 55, 1493110027583, 1493110268754, 241171, https://hm.baidu.com/hm.gif?cc=0&ck=1&cl=32-bit&ds=360x640&ep={"netAll":6918,"netDns":17,"netTcp":143,"srv":385,"dom":50749,"loadEvent":236285,"qid":"","bdDom":0,"bdRun":0,"bdDef":0}&et=87&ja=1&ln=zh-cn&lo=0&lt=1493103153&nv=0&rnd=1335849590&si=12423ecbc0e2ca965d84259063d35238&st=4&v=1.2.13&lv=2, GET, {"User-Agent":"Mozilla/5.0 (Linux; Android 5.0; SM-N9006 Build/LRX21V) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile Safari/537.36","Accept":"image/webp,*/*;q=0.8"});

INSERT INTO `h5perf`.`request_resource` (`tid`, `test_url`, `index`, `start_timestamp`, `timestamp`, `time_cost`, `request_url`, `method`, `headers`) VALUES ('null', 'https://www.baidu.com', '52', '1493110523975', '1493110528105', '4130', 'https://m.baidu.com/tc?tcreq4log=1&ssid=0&from=844b&pu=sz@320_1001,ta@iphone_2_5.0_3_537&qid=2956884724&ct=10&cst=4&ref=index_iphone&lid=2956884724&w=0_0_&sid=102572_114551_100185_114824_113884_115654_108373_115161_100098_110231_115339_115245_115703_115701_115287_115495_114798_115533_115624_115446_114329_115350_115031_115523_114276_110085&rids=1404535268183549074&pos=1&clk_from=mid_news&stype=0&picNum=9&extra={"attention_fea":"","catid":17,"cb_rank_score":0,"channel_id":1,"city":"\u676d\u5dde","city_code":"","click":1,"cover":[0,2,1],"coverquality":1,"cs":"2486633014 2005842983","ctr_fea":"","district":"","flag":1,"gif_site":"","img_num":9,"localname":"","mark":4,"mark_rec":4,"province":"\u6d59\u6c5f","rec_att":"","rec_src":[15],"sample_name":"wise_pic_feed","show":5,"source_id":81150,"steplength":2,"sub_cate":"\u7efc\u5408","type":0,"ua":"1080_1920_android_5.0_0","ut":"sm-n9006_5.0_0_samsung","vertical_type":1,"msrcid":81150}&logExtra={"st":"news","isBaijiahao":0,"flow":4,"extra":"{\"attention_fea\":\"\",\"catid\":17,\"cb_rank_score\":0,\"channel_id\":1,\"city\":\"\\u676d\\u5dde\",\"city_code\":\"\",\"click\":1,\"cover\":[0,2,1],\"coverquality\":1,\"cs\":\"2486633014 2005842983\",\"ctr_fea\":\"\",\"district\":\"\",\"flag\":1,\"gif_site\":\"\",\"img_num\":9,\"localname\":\"\",\"mark\":4,\"mark_rec\":4,\"province\":\"\\u6d59\\u6c5f\",\"rec_att\":\"\",\"rec_src\":[15],\"sample_name\":\"wise_pic_feed\",\"show\":5,\"source_id\":81150,\"steplength\":2,\"sub_cate\":\"\\u7efc\\u5408\",\"type\":0,\"ua\":\"1080_1920_android_5.0_0\",\"ut\":\"sm-n9006_5.0_0_samsung\",\"vertical_type\":1,\"msrcid\":81150}","stype":0,"rid":"1404535268183549074","pos":1,"picNum":3,"title":"\u8fd9\u4e9b\u56fe\u7247\u6015\u4ee5\u540e\u89c1\u4e0d\u5230\u4e86 \u5f88\u73cd\u8d35"}&r=1493110527967', 'GET', '{"User-Agent":"Mozilla/5.0 (Linux; Android 5.0; SM-N9006 Build/LRX21V) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile Safari/537.36","Accept":"image/webp,*/*;q=0.8"}');
