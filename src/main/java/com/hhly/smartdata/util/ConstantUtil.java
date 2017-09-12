package com.hhly.smartdata.util;

import java.io.File;

/**
 * 常量类
 */
public class ConstantUtil {

    /**
     * 开关状态
     */
    public final static String SWITCH_STATUS_CLOSE = "0";//关
    public final static String SWITCH_STATUS_OPEN = "1";//开

    /**
     * 充值返利订单状态
     */
    public final static String RECHARGE_RETURN_ORDER_STATUS_01 = "1";//处理中
    public final static String RECHARGE_RETURN_ORDER_STATUS_02 = "2";//信息错误
    public final static String RECHARGE_RETURN_ORDER_STATUS_03 = "3";//充值异常
    public final static String RECHARGE_RETURN_ORDER_STATUS_04 = "4";//充值完成
    public final static String RECHARGE_RETURN_ORDER_STATUS_05 = "5";//信息已更新
    public final static String RECHARGE_RETURN_ORDER_STATUS_06 = "6";//充值异常(待审核)
    public final static String RECHARGE_RETURN_ORDER_STATUS_07 = "7";//充值异常(审核未通过)


    /**
     * 黑名单类型
     */
    public final static int BLACK_USER_TYPE_EXERCISE = 1;//活动
    public final static int BLACK_USER_TYPE_ALBUM = 2;//相册
    public final static int BLACK_USER_TYPE_STAR = 3;//新秀

    /**
     * 投票类型
     */
    public final static String VOTE_TYPE_FREE = "0";//免费
    public final static String VOTE_TYPE_CHARGE = "1";//收费
    public final static String VOTE_TYPE_ADD = "2";//手工加票

    /**
     * 操作系统类型
     */
    public final static String OS_TYPE_ANDROID = "1";//安卓
    public final static String OS_TYPE_IOS = "2";//IOS
    public final static String OS_TYPE_PC = "3";//PC

    /**
     * 状态
     */
    public final static String STATUS_REFUSE = "2";//未通过审核
    public final static String STATUS_VALID = "1";//已通过审核or有效
    public final static String STATUS_INVALID = "0";//新建or无效


    /**
     * 时间单位
     */
    public final static String TIME_UNIT_DAY = "D";//天
    public final static String TIME_UNIT_HOUR = "H";//小时
    public final static String TIME_UNIT_FOREVER = "F";//永久


    /**
     * 活动类型
     */
    public final static String EXERCISE_TYPE_CROWDFUNDING = "0";//众筹
    public final static String EXERCISE_TYPE_VOTE = "1";//投票
    public final static String EXERCISE_TYPE_STARSHOW = "2";//明星专场
    public final static String EXERCISE_TYPE_COMMON_EXERCISE = "3";//普通活动

    /**
     * 活动状态
     */
    public final static String EXERCISE_STATUS_NOT_BEGIN = "0";//未开始
    public final static String EXERCISE_STATUS_EXECUTING = "1";//进行中
    public final static String EXERCISE_STATUS_FINISH = "2";//已结束

    /**
     * 状态
     */
    public final static String STATUS_ONLINE = "1";//已上线
    public final static String STATUS_OFFLINE = "0";//未上线

    /**
     * 消息推送模块消息类型
     */
    public final static String MESSAGE_PUSH_OFFLINE = "1";//离线消息(上线推送)
    public final static String MESSAGE_PUSH_ONLINE = "0";//在线消息

    /**
     * 呀呀小秘书yunvaId
     */
    public final static Long YAYA_YUNVA_ID = 10000L;

    /**
     * 消息推送模块消息接收类型
     */
    public final static String MESSAGE_PUSH_TYPE_00 = "0";//所有人
    public final static String MESSAGE_PUSH_TYPE_01 = "1";//会长
    public final static String MESSAGE_PUSH_TYPE_02 = "2";//无公会成员
    public final static String MESSAGE_PUSH_TYPE_03 = "3";//有公会成员
    public final static String MESSAGE_PUSH_TYPE_04 = "4";//性别
    public final static String MESSAGE_PUSH_TYPE_05 = "5";//等级区间

    /**
     * 性别类型
     */
    public final static int MESSAGE_PUSH_SEX_TYPE_FEMAIL = 1;//女
    public final static int MESSAGE_PUSH_SEX_TYPE_MAIL = 2;//男

    /**
     * 权限类型
     */
    public static final String TYPE_LIST_ADMIN_AUTH_TYPE = "ADMIN_AUTH_TYPE";
    public static final String TYPE_LIST_ADMIN_AUTH_TYPE_01 = "01";//公会权限
    public static final String TYPE_LIST_ITEM_AUTH_TYPE_02 = "02";//礼物应用权限

    /**
     * 公会会长专有角色
     */
    public static final String ROLE_GUILD_LEADER = "guildLeader";

    /**
     * 用户角色  2代表会长
     */
    public static final Integer USER_GUILD_ROLE_ID = 2;

    /**
     * 是否为超级账号
     */
    public final static String ISSUPER = "1";//是
    public final static String NOSUPER = "0";//否

    /**
     * SkipInfo类型(pageSkipType)
     */
    public final static String SKIP_INFO_PAGE_SKIP_TYPE_01 = "1";//图片
    public final static String SKIP_INFO_PAGE_SKIP_TYPE_02 = "2";//帖子
    public final static String SKIP_INFO_PAGE_SKIP_TYPE_03 = "3";//底部名称

    /**
     * 公会礼包类型
     */
    public final static String GIFT_COLLECT_TYPE_GUILD = "1";//公会礼包
    public final static String GIFT_COLLECT_TYPE_PUBLIC = "0";//开放礼包

    /**
     * 礼包状态
     */
    public final static String GIFT_STATE_ONLINE = "1";//已上线
    public final static String GIFT_STATE_OFFLINE = "0";//未上线

    /**
     * 导入样式
     */
    public static final String IMP_TYPE_TILE = "tile";
    public static final String IMP_TYPE_SUPER_POSITION = "superPosition";

    // 订单接口状态
    public static final String TYPE_LIST_I_OUT_ORDER_STATUS = "I_OUT_ORDER_STATUS";
    public static final String TYPE_LIST_I_OUT_ORDER_STATUS_01 = "01"; // 未生成出库订单
    public static final String TYPE_LIST_I_OUT_ORDER_STATUS_02 = "02"; // 已生成出库订单
    public static final String TYPE_LIST_I_OUT_ORDER_STATUS_03 = "03"; // 已关闭
    public static final String TYPE_LIST_I_OUT_ORDER_STATUS_04 = "04"; // 已完成
    public static final String TYPE_LIST_I_OUT_ORDER_STATUS_05 = "05"; // 已发货
    public static final String TYPE_LIST_I_OUT_ORDER_STATUS_06 = "06"; // 已包装

    /**
     * 进销存出库单状态
     */
    public final static String TYPE_LIST_OS_STOCK_OUT_ORDER_STATUS = "OS_STOCK_OUT_ORDER_STATUS";// 进销存出库单状态
    public final static String TYPE_LIST_OS_STOCK_OUT_ORDER_STATUS_DELETE = "00";// 删除
    public final static String TYPE_LIST_OS_STOCK_OUT_ORDER_STATUS_CREATE = "01";// 等待拣货
    public final static String TYPE_LIST_OS_STOCK_OUT_ORDER_STATUS_WAIT = "02";// 等待出库
    public final static String TYPE_LIST_OS_STOCK_OUT_ORDER_STATUS_PRINT = "03";// 已打印
    public final static String TYPE_LIST_OS_STOCK_OUT_ORDER_STATUS_OUT = "04";// 已发货
    public final static String TYPE_LIST_OS_STOCK_OUT_ORDER_STATUS_CUT = "05";// 已截单

    /**
     * 进销存入库单状态
     */
    public final static String TYPE_LIST_STOCK_IN_ORDER_STATUS = "OS_STOCK_IN_ORDER_STATUS";// 进销存入库单状态
    public final static String TYPE_LIST_STOCK_IN_ORDER_STATUS_WAITING_RECEIVE = "01";// 等待入库
    public final static String TYPE_LIST_STOCK_IN_ORDER_STATUS_RECEIVED = "02";// 入库完成

    /**
     * 是否为叶子节点
     */
    public final static String TYPE_LIST_YES_NO_YES = "1";//是
    public final static String TYPE_LIST_YES_NO_NO = "0";//否

    /**
     * 是否为叶子节点
     */
    public final static String ISLEAF = "1";//是
    public final static String NOTLEAF = "0";//否


    /**
     * 是否启用
     */
    public final static String ENABLED = "1";//启用
    public final static String DISABLED = "0";//停用

    /**
     * 是否为默认
     */
    public final static String TL_DEFAULT_TYPE = "DEFAULT_TYPE";
    public final static String TL_DEFAULT_TYPE_DEFAULT = "1";//默认
    public final static String TL_DEFAULT_TYPE_NOTDEFAULT = "0";//不为默认
    
    /**
     * Session中相关key
     */
    public final static String LOGIN_USER = "loginUser";//登录账号
    public final static String LOGIN_MENU = "loginMenu";//登录账号菜单

    /**
     * 上传文件返回MAP的key
     */
    public final static String UPLOAD_FILE_ERROR="error";//上传文件返回的错误
    public final static String UPLOAD_FILE_PATH="path";//上传文件的路径，包含文件名在内
    /**
     *
     * 默认日期时间格式
     */
    public final static String DEFAULT_DATE_PATTERN= "yyyy-MM-dd";//默认日期格式
    public final static String DEFAULT_TIME_PATTERN= "hh:mm:ss";//默认时间格式
    public final static String DEFAULT_DATETIME_PATTERN= "yyyy-MM-dd HH:mm:ss";//默认日期时间格式
    public final static String DEFAULT_DATE_STREAM= "HHmmss";//默认日期格式

    public final static String OTHER_DATETIME_PATTERN_0= "yyyy/MM/dd HH:mm:ss";//日期时间格式1
    
    /**
     * 是否
     */
    public final static String TL_YES_NO= "YES_NO";
    public final static Integer TL_YES_NO_NO= 0;//否
    public final static Integer TL_YES_NO_YES= 1;//是

    /**
     * 未生效，已生效，已过期
     */
    public final static String TL_EFFECT= "EFFECT";
    public final static Integer TL_EFFECT_NO= 0;//未生效
    public final static Integer TL_EFFECT_YES= 1;//已生效
    public final static Integer TL_EFFECT_OVERDUE= 2;//已生效

    /**
     * 运单库中运单是否领用
     */
    public final static String TL_TRACKING_NO= "TRACKING_NO";
    public final static Integer TL_TRACKING_NO_NO = 0;//否
    public final static Integer TL_TRACKING_NO_YES = 1;//是

    /**
     * 材积除
     */
    public final static String TL_VOL_DIV="VOL_DIVID";
//    public final static Integer TL_VOL_DIV_5000 = 0;//除5000
//    public final static Integer TL_VOL_DIV_5500 = 1;//除5500
//    public final static Integer TL_VOL_DIV_6000 = 2;//除6000

    /**
     * 短信平台
     */
    public final static String DBTL_SMS_PLATFORM="SMS_PLATFORM";

    /**
     * 规则匹配
     */
    public final static String DBTL_SMS_AUTO_TYPE="SMS_AUTO_TYPE";

    /**
     * 结算重量计算方法
     */
    public final static String TL_BA_WEIGHT_RULE="BA_WEIGHT_RULE";
    public final static Integer TL_BA_WEIGHT_RULE_MAX = 0;//取实重和材积的最大值
    public final static Integer TL_BA_WEIGHT_RULE_AVERAGE = 1;//取实重和材积的平均值
    public final static Integer TL_BA_WEIGHT_RULE_WEIGHT = 2;//取实重

    /**
     * 多件重量算法
     */
     public final static String TL_MULTI_WEIGHT_RULE="MULTI_WEIGHT_RULE";
     public final static Integer TL_MULTI_WEIGHT_RULE_EARLIER_ADD = 0;//先累加再比较
     public final static Integer TL_MULTI_WEIGHT_RULE_AFTER_ADD = 1;//先比较再累加

    /**
     * 入出货渠道类型
     */
    public final static String TL_IO_TYPES="IO_TYPES";
    public final static Integer TL_IO_TYPES_IN = 2;//入货渠道
    public final static Integer TL_IO_TYPES_OUT = 1;//出货渠道
    public final static Integer  TL_IO_TYPES_INOUT = 0;//出入货渠道

    /**
     * 货物类型
     */
    public final static String TL_PRODUCT_TYPE ="PRODUCT_TYPE";
    public final static Integer TL_PRODUCT_TYPE_WPX = 1;//包裹
    public final static Integer TL_PRODUCT_TYPE_PAK = 2;//PAK袋
    public final static Integer TL_PRODUCT_TYPE_DOC = 4;//文件

    /**
     * 物品类别
     */
    public final static String TL_GOODS_CATEGORY ="GOODS_CATEGORY";

    /**
     * 允许包含特殊货品
     */
    public final static String TL_SPECIAL_ITEMS ="SPECIAL_ITEMS";
    public final static Integer TL_SPECIAL_ITEMS_LIQUID = 1;//液体
    public final static Integer TL_SPECIAL_ITEMS_POWDER = 2;//粉未
    public final static Integer TL_SPECIAL_ITEMS_BATTERY = 4;//电池
    public final static Integer TL_SPECIAL_ITEMS_COHERER=8;//金属
    public final static Integer TL_SPECIAL_ITEMS_OTHER=16;//其它1
    public final static Integer TL_SPECIAL_ITEMS_OTHERTWO=32;//其它2

    /**
     * 留言发起者
     */
    public final static String TL_MSG_SOURCE ="MSG_SOURCE";
    public final static Integer TL_MSG_SOURCE_CONSIGNOR = 0;//客户
    public final static Integer TL_MSG_SOURCE_COMPANY = 1;//公司

    /**
     * 留言类型
     */
    public final static String TL_MSG_TYPE ="MSG_TYPE";
    public final static Integer TL_MSG_TYPE_ZX = 0;//咨询
    public final static Integer TL_MSG_TYPE_TS = 1;//投诉
    public final static Integer TL_MSG_TYPE_JY = 2;//建议

    /**
    * 留言状态
    */
    public final static String TL_MSG_STATUS ="MSG_STATUS";
    public final static Integer TL_MSG_STATUS_NO = 0;//未答复
    public final static Integer TL_MSG_STATUS_YES = 1;//已答复

    /**
     * 客户信息状态
     */
    public final static String TL_C_MSG_STATUS ="C_MSG_STATUS";
    public final static Integer TL_C_MSG_STATUS_NO = 0;//未发布
    public final static Integer TL_C_MSG_STATUS_YES = 1;//已发布

    /**
     * 账户类型
     */
    public final static String TL_ACCOUNT_TYPE = "ACCOUNT_TYPE";
    public final static Integer TL_ACCOUNT_TYPE_INCOME = 0;//收入
    public final static Integer TL_ACCOUNT_TYPE_ADJUST = 1;//调账
    public final static Integer TL_ACCOUNT_TYPE_LOSS = 2;//亏损

    /**
     * 客户种类
     */
    public final static String TL_CONSIGNOR_CATEGORY = "CONSIGNOR_CATEGORY";
    public final static Integer TL_CONSIGNOR_CATEGORY_ZK = 0;//直客
    public final static Integer TL_CONSIGNOR_CATEGORY_TH = 1;//同行

    /**
     * 报价类型
     */
    public final static String TL_PS_PRICE_TYPE ="PS_PRICE_TYPE";
    public final static Integer TL_PS_PRICE_TYPE_KH = 0;//客户特殊报价
    public final static Integer TL_PS_PRICE_TYPE_LX = 1;//客户类型报价
    public final static Integer TL_PS_PRICE_TYPE_DL = 2;//代理报价

    /**
     * 计算方法
     */
    public final static String TL_CALCULATE_METHOD= "CALCULATE_METHOD";
    public final static Integer TL_CALCULATE_METHOD_SC = 0;//速查
    public final static Integer TL_CALCULATE_METHOD_ZL = 1;//增量
    public final static Integer TL_CALCULATE_METHOD_ZC = 2;//总乘
    public final static Integer TL_CALCULATE_METHOD_ZHC = 3;//直乘

    /**
     * 客户结算期限
     */
    public final static String TL_CONSIGNOR_BALANCE_WAY = "CONSIGNOR_BALANCE_WAY";
    public final static Integer TL_CONSIGNOR_BALANCE_WAY_XF = 0;//现付
    public final static Integer TL_CONSIGNOR_BALANCE_WAY_YJ = 1;//月结

    /**
     * 岗位
     */
    public final static String TL_JOB = "JOB";
    public final static Integer TL_JOB_SALESMAN = 0;//业务
    public final static Integer TL_JOB_WAITER = 1;//客服
    public final static Integer TL_JOB_OPERATOR = 2;//操作
    public final static Integer TL_JOB_FIANCE = 3;//财务
    public final static Integer TL_JOB_POSTMAN = 4;//取件员
    public final static Integer TL_JOB_OTHER = 99;//其他

    /**
     * 使用状态
     */
    public final static String TL_USE = "USE";
    public final static Integer TL_USE_NO = 0;//未启用
    public final static Integer TL_USE_YES = 1;//启用
    public final static Integer TL_USE_STOP = 2;//停用


    /**
     * 短信发送状态
     */
    public final static String TL_SMS_SEND_STATUS = "SMS_SEND_STATUS";
    public final static Integer TL_SMS_SEND_STATUS_CREATE   = 0;//未发送
    public final static Integer TL_SMS_SEND_STATUS_TRANSMITTED  = 1;//已发送
    public final static Integer TL_SMS_SEND_STATUS_FAILURE  = 2;//发送失败

    /**
     * 状态
     */
    public final static String TL_ARP = "ARP";
    public final static Integer TL_ARP_NO = 0;  //制单
    public final static Integer TL_ARP_YES = 1; //审核

    /**
     * 扣放货状态
     */
    public final static String TL_BUCKLE = "BUCKLE";
    public final static Integer TL_BUCKLE_NO = 0;  //放货
    public final static Integer TL_BUCKLE_YES = 1; //扣货
    
    /**
     * 结算状态
     */
    public final static String TL_BA_BIZ_ORDER = "FSTATUS";
    public final static Integer TL_BA_BIZ_ORDER_ZD = 0;  //制单
    public final static Integer TL_BA_BIZ_ORDER_SHZ= 1; //核销中
    public final static Integer TL_BA_BIZ_ORDER_HX = 2; //已核销
    /**
     * 核销状态
     */
    public final static String TL_BA_BIZ_ORDER_HXS = "HXSTATUS";
    public final static Integer TL_BA_BIZ_ORDER_HXS_ZD = 0;  //制单
    public final static Integer TL_BA_BIZ_ORDER_HXS_SH= 1; //审核
    public final static Integer TL_BA_BIZ_ORDER_HXS_YHX = 2; //已核销

    /**
     * 业务类型
     */
    public final static String TL_BA_BIZ_TYPE = "BIZ_TYPE";
    public final static Integer TL_BA_BIZ_TYPE_RC = 0;//入仓
    public final static Integer TL_BA_BIZ_TYPE_TRC = 1;//退货入仓
    public final static Integer TL_BA_BIZ_TYPE_TKH = 2;//退给客户
    public final static Integer TL_BA_BIZ_TYPE_QTYS = 3;//其他应收
    public final static Integer TL_BA_BIZ_TYPE_RM = 4;//收款
    public final static Integer TL_BA_BIZ_TYPE_RECEIVE = 9;//实收
    public final static Integer TL_BA_BIZ_TYPE_CC = 10;//出仓
    public final static Integer TL_BA_BIZ_TYPE_TCC = 11;//退货出仓
    public final static Integer TL_BA_BIZ_TYPE_DLT = 12;//代理退货
    public final static Integer TL_BA_BIZ_TYPE_QTYF = 13;//其他应付
    public final static Integer TL_BA_BIZ_TYPE_CM = 14;//付款
    public final static Integer TL_BA_BIZ_TYPE_PAY = 19;//实付
    public final static Integer TL_BA_BIZ_TYPE_COST = 20;//成本
    public final static Integer TL_BA_BIZ_TYPE_TELLER = 21;//出纳



    /**
     * 收付方向
     */
    public final static String TL_BA_DIRECT = "DIRECT";
    public final static Integer TL_BA_DIRECT_RECEIVE = 0;//收
    public final static Integer TL_BA_DIRECT_PAY = 1;//付
    public final static Integer TL_BA_DIRECT_COST = 2;//成本

    /**
     * 基本费用类型
     */
    public final static String TL_FEE_TYPE = "FEE_TYPE";
    public final static Integer TL_FEE_TYPE_TRANS = 1;//运费（含燃油）
    public final static Integer TL_FEE_TYPE_FUEL = 2;//燃油费
    public final static Integer TL_FEE_TYPE_TRANS_FARAWAY = 3;//偏远费

    /**
     * 费用类别
     */
    public final static String TL_FEE_CATEGORY="FEE_CATEGORY";
    public final static Integer TL_FEE_CATEGORY_RP =0;//应收应付
    public final static Integer TL_FEE_CATEGORY_COST=1;//成本
    public final static Integer TL_FEE_CATEGORY_REST=2;//其他
    public final static Integer TL_FEE_CATEGORY_ALL=3;//所有的

    /**
     *报价的类型 0 客户报价,1 客户类型报价，2 代理报价
     */
    public final static Integer PS_TYPE_CO=0,PS_TYPE_CO_TYPE=1,PS_TYPE_SP=2;
    /**
     * 分摊方式
     */
    public final static String TL_DIVIDE="DIVIDE";
    public final static Integer TL_DIVIDE_WEIGHT=0;//按重量分摊
    public final static Integer TL_DIVIDE_POLL=1;//按票数分摊
    public final static Integer TL_DIVIDE_SINGLE=2;//录入单票费用

    /**
     * 重量类型
     */
    public final static String TL_WEIGHT_TYPE="WEIGHT_TYPE";
    public final static Integer TL_WEIGHT_TYPE_IN=0;//入仓重量
    public final static Integer TL_WEIGHT_TYPE_OUT=1;//出仓重量

    /**
     * 渠道类型
     */
    public final static String TL_CHANNEL_TYPE="CHANNEL_TYPE";
    public final static Integer TL_CHANNEL_TYPE_XGDHL=0;//香港DHL代理价
    public final static Integer TL_CHANNEL_TYPE_UPS=1;//UPS
    public final static Integer TL_CHANNEL_TYPE_XGXBGH=2;//香港小包挂号
    public final static Integer TL_CHANNEL_TYPE_DHL=3;//DHL

    /**
     * 小包两种类型
     */
    public final static String TL_REGISTER = "REGISTER";
    public final static Integer TL_REGISTER_NO = 0;    //平邮
    public final static Integer TL_REGISTER_YES = 1;   //挂号

    /**
     * 物流模式
     */
    public final static String TL_LOGISTICS="LOGISTICS";
    public final static Integer TL_LOGISTICS_INTERNATIONAL_EXPRESS=0;//国际快递
    public final static Integer TL_LOGISTICS_SPECIAL_LINE=1;//国际专线
    public final static Integer TL_LOGISTICS_PARCEL_POST=2;//邮政小包
    public final static Integer TL_LOGISTICS_DOMESTIC_EXPRESS=3;//国内快递


    /**
     * 地区类型
     */
    public final static String TL_REGION_TYPE="REGION_TYPE";
    public final static String TL_REGION_TYPE_COUNTRY="1"; //国家
    public final static String TL_REGION_TYPE_CITY="2"; //城市
    public final static String TL_REGION_TYPE_TOWN="3"; //乡镇

    /**
     * 结算对象
     */
    public final static String TL_ACCOUNT_OBJECT="ACCOUNT_OBJECT";
    public final static Integer TL_ACCOUNT_OBJECT_CONSIGNOR=0;//客户
    public final static Integer TL_ACCOUNT_OBJECT_SP=1;//代理

    /**
     * 入仓类型
     */
    public final static String TL_IN_TYPE = "IN_TYPE";
    public final static Integer TL_IN_TYPE_NO = 0;  //正常入仓
    public final static Integer TL_IN_TYPE_YES = 1; //代理退回

    /**
     * 客户年限
     */
    public final static String TL_CUSTOMER_YEAR = "CUSTOMER_YEAR";
    public final static Integer TL_CUSTOMER_YEAR_1 = 0;  //1年以内
    public final static Integer TL_CUSTOMER_YEAR_2 = 1; //2年以内
    public final static Integer TL_CUSTOMER_YEAR_3 = 2;  //3年以内
    public final static Integer TL_CUSTOMER_YEAR_4 = 3; //4年以内
    public final static Integer TL_CUSTOMER_YEAR_5 = 4;  //5年以内
    public final static Integer TL_CUSTOMER_YEAR_6 = 5; //6年以内
    public final static Integer TL_CUSTOMER_YEAR_7 = 6;  //7年以内
    public final static Integer TL_CUSTOMER_YEAR_8 = 7; //8年以内
    public final static Integer TL_CUSTOMER_YEAR_9 = 8;  //9年以内
    public final static Integer TL_CUSTOMER_YEAR_10 = 9; //10年以内
    public final static Integer TL_CUSTOMER_YEAR_11 = 10;  //11年以内
    public final static Integer TL_CUSTOMER_YEAR_12 = 11; //12年以内

    /**
     * 客户欠款时间
     */
    public final static String TL_SIGE = "SIGE";
    public final static Integer TL_SIGE_0 = 0; //未欠款
    public final static Integer TL_SIGE_1 = 1; //欠款
    public final static Integer TL_SIGE_2 = 2; //一个月
    public final static Integer TL_SIGE_3 = 3; //二个月以上

    /**
     * 分区显示位置
     */
    public final static String TL_AREA_LOCATION="AREA_LOCATION";
    public final static Integer TL_AREA_LOCATION_X=0; //显示在列上
    public final static Integer TL_AREA_LOCATION_Y=1; //显示在行上

    /**
     * o
     */
    public final static Integer CONSTANT_ZERO = 0;

    /**
     *  999999
     */
    public final static Integer MAX_WEIGHT_BP = 999999000; //后台最大重量
    public final static Integer MAX_WEIGHT_UI = 999999; //前台最大重量

    /**
     * 折扣取值范围
     */
    public final static Integer MAX_DISCOUNT_I = 1000; //整型最大折扣
    public final static Integer MIN_DISCOUNT_I = 100; //整型最小折扣

    public final static Double MAX_DISCOUNT_D = 1.0; //实型最大折扣
    public final static Double MIN_DISCOUNT_D = 0.1; //实型最小折扣

    /**
     * 保险类型
     */
    public final static String TL_INSURED_TYPE="INSURED_TYPE";
    public final static Integer TL_INSURED_TYPE_1=0;//五元每票
    public final static Integer TL_INSURED_TYPE_2=1;//0.6%保费

    /**
     * 放大倍数
     * 长宽高(cm) ：按mm存储，放大10倍；
     * 重量（kg)：按g存储，放大1000倍；
     * 金额（元）：按分存储，放到100倍；
     * 汇率（保留4位小数）：放大10000倍；
     * 燃油率（保留3位小数）：放大1000倍；
     */
    public final static Integer MAGNIFICATION_LENGTH = 10;
    public final static Integer MAGNIFICATION_WEIGHT = 1000;
    public final static Integer MAGNIFICATION_SUM = 100;
    public final static Integer MAGNIFICATION_EXCHANGE_RATE = 10000;
    public final static Integer MAGNIFICATION_FUEL_RATE = 1000;
    public final static Integer MAGNIFICATION_DISCOUNT = 1000;

    /**
     *  JAVA中整型的最大值
     */
    public final static Double JAVA_MAX_INTEGER_UI=2147483.647;
    public final static Integer JAVA_MAX_INTEGER=2147483647;

    /**
     *  JAVA中长整型的最大值
     */
    public final static Double JAVA_MAX_LONG_UI=9223372036854775.807;
    public final static Long JAVA_MAX_LONG = 9223372036854775807L;
    /**
     * MYSQL 无符号整型的最大值
     */
    public final static Integer MYSQL_TINYINT_MAX=255;
    public final static Integer MYSQL_SMALLINT_MAX=65535;
    public final static Integer MYSQL_MEDIUMINT_MAX=16777215;
    public final static Long  MYSQL_INT_MAX=4294967295L;
    public final static Long MYSQL_BIGINT_MAX=9223372036854775807L;

    /**
     * 报价计算公式.对于速查，公式就是金额
     * beginPrce:起价
     * Math.ceil:向上去整
     * weight:实重
     * beginWeight:起始重量
     * unitWeight:单位重量
     * price:单价(金额)
     * discount:折扣
     *
     * FORMULA_ZL:增量计算公式
     * FORMULA_ZC:总乘计算公式
     * FORMULA_ZHC:直乘计算公式
     */

    public final static String FORMULA_PARAM_BEGIN_PRICE="beginPrice";
    public final static String FORMULA_PARAM_BALANCE_WEIGHT="balanceWeight";
    public final static String FORMULA_PARAM_BEGIN_WEIGHT="beginWeight";
    public final static String FORMULA_PARAM_UNIT_WEIGHT="unitWeight";
    public final static String FORMULA_PARAM_PRICE="price";
    public final static String FORMULA_PARAM_DISCOUNT="discount";
    public final static String FORMULA_PARAM_FUEL_RATE="fuelRate";
    public final static String FORMULA_SC="price*"+FORMULA_PARAM_DISCOUNT;
    public final static String FORMULA_ZL="(beginPrice+((balanceWeight-beginWeight)/unitWeight)*price)*"+FORMULA_PARAM_DISCOUNT;
    public final static String FORMULA_ZC="balanceWeight/unitWeight*price*"+FORMULA_PARAM_DISCOUNT;
    public final static String FORMULA_ZHC="balanceWeight*price*"+FORMULA_PARAM_DISCOUNT;

    /**以下是Database中的基础数据的标识=======================================**/
    /**
     * 客户类型
     */
    public final static String DBTL_CONSIGNOR_TYPE="CONSIGNOR_TYPE";
    /**
     * 站点
     */
    public final static String DBTL_STATION="STATION";


    /**
     * 收/发
     */
    public final static String TL_SMS_DIRECT = "SMS_DIRECT";
    public final static Integer TL_SMS_DIRECT_SEND = 0;//发
    public final static Integer TL_SMS_DIRECT_RECEIVE = 1;//收

    /**
     * 短信类型
     */
    public final static String  TL_SMS_TYPE = "SMS_TYPE";
    public final static Integer TL_SMS_TYPE_WTJTZ  = 0;//问题件通知
    public final static Integer TL_SMS_TYPE_CJTZ   = 1;//查件通知
    public final static Integer TL_SMS_TYPE_KJTZ   = 2;//扣件通知
    public final static Integer TL_SMS_TYPE_ZDTZ   = 3;//帐单通知
    public final static Integer TL_SMS_TYPE_QFDX   = 4;//群发短信
    public final static Integer TL_SMS_TYPE_KHGJ   = 5;//客户跟进
    public final static Integer TL_SMS_TYPE_YDRC   = 6;//运单入仓
    public final static Integer TL_SMS_TYPE_CJSW   = 7;//查件上网
    public final static Integer TL_SMS_TYPE_CJQS   = 8;//查件签收
    public final static Integer TL_SMS_TYPE_CJYC   = 9;//查件异常
    public final static Integer TL_SMS_TYPE_CZDH   = 10;//查转单号
    public final static Integer TL_SMS_TYPE_XZKHYH = 11;//新增客户用户
    public final static Integer TL_SMS_TYPE_XBCJ   = 12;//小包查件

    /**
     * 状态，通用
     */
    public final static String TL_STATUS = "STATUS";
    public final static Integer TL_STATUS_NEW = 0;//制单
    public final static Integer TL_STATUS_AUDITED = 1;//审核


    /**
     * 委托运单来源
     */
    public final static String ORDER_SOURCE ="ORDER_SOURCE";
    public final static Integer ORDER_SOURCE_MANUAL=0;//手工制单
    public final static Integer ORDER_SOURCE_ONLINE=1;//网上拉单

    /**
     * 委托单单据状态
     */
    public final static String ORDER_STATUS = "ORDER_STATUS";
    public final static Integer ORDER_STATUS_DEL = 0;//已删除
    public final static Integer ORDER_STATUS_DRAFT = 1;//草稿
    public final static Integer ORDER_STATUS_VOUCHER = 2;//制单
    public final static Integer ORDER_STATUS_AUDIT = 3;//已预报
    public final static Integer ORDER_STATUS_INING = 104;//入仓中
    public final static Integer ORDER_STATUS_IN_WAREHOUSE = 4;//已入仓
    public final static Integer ORDER_STATUS_OUTING = 105;//出仓中
    public final static Integer ORDER_STATUS_OUT_WAREHOUSE = 5;//已出仓
    public final static Integer ORDER_STATUS_RECEIVED = 6;//已签收
    public final static Integer ORDER_STATUS_IN_BUCKLE = 7;//已扣件
    public final static Integer ORDER_STATUS_RETURNING = 108;//退货中
    public final static Integer ORDER_STATUS_RETURNED = 8;//已退货
    public final static Integer ORDER_STATUS_STOCKOUT = 255;//缺货
    public final static Integer ORDER_STATUS_TO_BE_CONFIRMED = 10;//待确认


    /**
     * 入仓运单的状态
     */
    public final static String TL_REC_WAYBILL_STATUS = "REC_WAYBILL_STATUS";
    public final static Integer TL_REC_WAYBILL_STATUS_INING = 104;//制单(入仓中)
    public final static Integer TL_REC_WAYBILL_STATUS_INED = 4;//已入仓
    public final static Integer TL_REC_WAYBILL_STATUS_OUTING = 105;//出仓中
    public final static Integer TL_REC_WAYBILL_STATUS_LOCKED = 6;//已扣件
    public final static Integer TL_REC_WAYBILL_STATUS_OUTED = 5;//已出仓
//    public final static Integer TL_REC_WAYBILL_STATUS_OFFLINE = 207;//未上网
//    public final static Integer TL_REC_WAYBILL_STATUS_ONLINE = 200;//上网
    public final static Integer TL_REC_WAYBILL_STATUS_DISCARD = 9;//已丢弃
//    public final static Integer TL_REC_WAYBILL_STATUS_DEADLINE = 201;//期限件
//    public final static Integer TL_REC_WAYBILL_STATUS_lOCAL_RETURN= 202;//当地退回
//    public final static Integer TL_REC_WAYBILL_STATUS_CLEARANCE_DELAY= 203;//已清关延误
//    public final static Integer TL_REC_WAYBILL_STATUS_NOT_FIND= 204;//查不到
//    public final static Integer TL_REC_WAYBILL_STATUS_PACKAGE= 205;//包裹信息
    public final static Integer TL_REC_WAYBILL_STATUS_END= 203;//完结
    public final static Integer TL_REC_WAYBILL_STATUS_RETURNING = 108;//退货中
    public final static Integer TL_REC_WAYBILL_STATUS_RETURNED = 8;//已退货

    /**
     * 出仓运单的状态
     */
    public final static String TL_SP_WAYBILL_STATUS = "REC_WAYBILL_STATUS";
    public final static Integer TL_SP_WAYBILL_STATUS_NEW = 105;//出仓中(制单)
    public final static Integer TL_SP_WAYBILL_STATUS_OUTED = 5;//已出仓
    public final static Integer TL_SP_WAYBILL_STATUS_RECEIPT = 6;//已签收
    public final static Integer TL_SP_WAYBILL_STATUS_RETURNING = 109;//退货中
    public final static Integer TL_SP_WAYBILL_STATUS_RETURNED = 9;//已退货

    /**
     * 运单财务状态
     */
    public final static String TL_WAYBILL_FINANCE_STATUS = "WAYBILL_FINANCE_STATUS";
    public final static Integer TL_WAYBILL_FINANCE_STATUS_NEW = 0;  //制单
    public final static Integer TL_WAYBILL_FINANCE_STATUS_AUDITED = 1;  //审核
    public final static Integer TL_WAYBILL_FINANCE_STATUS_SETTLING = 2;  //核销中
    public final static Integer TL_WAYBILL_FINANCE_STATUS_SETTLED = 3;  //已核销

    /**
     * 委托单申报物品单位
     */
    public final static String CO_ITEM_UNIT = "CO_ITEM_UNIT";
    public final static Integer CO_ITEM_UNIT_PIECE =0;//件
    public final static Integer CO_ITEM_UNIT_SET = 1;//套
    public final static Integer CO_ITEM_UNIT_DOZEN = 2;//打
    public final static Integer CO_ITEM_UNIT_KILO = 3;//公斤
    public final static Integer CO_ITEM_UNIT_METER = 4;//米
    public final static Integer CO_ITEM_UNIT_PAIR = 5;//双
    public final static Integer CO_ITEM_UNIT_SINGLE = 6;//单个

    /**
     * 单据业务类型
     */
    public final static String  BTYPE_WAYBILL = "BTYPE_WAYBILL";
    public final static Integer BTYPE_WAYBILL_REC =0;//入仓单
    public final static Integer BTYPE_WAYBILL_SP = 1;//出仓单
    public final static Integer BTYPE_WAYBILL_REC_CHANGE = 2;//公司换单
    public final static Integer BTYPE_WAYBILL_SP_CHANGE = 3;//代理换单
    public final static Integer BTYPE_WAYBILL_COST = 4;//成本费用单
    public final static Integer BTYPE_WAYBILL_CRETURN = 5;//客户退货单
    public final static Integer BTYPE_WAYBILL_SRETURN = 6;//代理退货单
    public final static Integer BTYPE_WAYBILL_WITHHOLD = 7;//扣件单
    public final static Integer BTYPE_WAYBILL_OPEN = 8;//放货单

	
	/**
     * 单据类型
     */
    public final static String BTYPE_BORDER = "BTYPE_BORDER";
    public final static Integer BTYPE_BORDER_INING = 0; //入仓;
    public final static Integer BTYPE_BORDER_INPACKETING = 1; //邮政小包入仓;
    public final static Integer BTYPE_BORDER_INRETURNING = 2; //退货单;
    public final static Integer BTYPE_BORDER_GATHERING = 3; //收款单;
    public final static Integer BTYPE_BORDER_RECEIVABLE = 4; //预收单;
    public final static Integer BTYPE_BORDER_ELSERECEIVABLE = 5; //其它应收;
    public final static Integer BTYPE_BORDER_OUTING = 10; //出仓;
    public final static Integer BTYPE_BORDER_OUTPACKETING = 11; //邮政小包出仓;
    public final static Integer BTYPE_BORDER_OUTRETURNING = 12; //代理退货单;
    public final static Integer BTYPE_BORDER_PAYMENT = 13; //付款单;
    public final static Integer BTYPE_BORDER_PREPAY = 14; //预付单;
    public final static Integer BTYPE_BORDER_ELSEPREPAY = 15; //其它应付;
    public final static Integer BTYPE_BORDER_COST = 20; //成本费用;
    public final static Integer BTYPE_BORDER_CASHIER = 21; //出纳费;
	
    /**
     * 问题提醒
     */
    public final static String  QUES_DESC = "QUES_DESC";
    public final static Integer QUES_DESC_NO_POST =0;//不通邮
    public final static Integer QUES_DESC_NOT_COUNTRY = 1;//中英文国家不符
    public final static Integer QUES_DESC_NO_INVOICE = 2;//没发票
    public final static Integer QUES_DESC_CUST_WITHHOLD = 3;//客户要求扣件

    /**
     *客户问题类型
     */
    public final static String CO_ORDER_CUSTOMER_ISSUE = "CUSTOMER_ISSUE";
    public final static Integer CO_ORDER_CUSTOMER_ISSUE_NORMAL = 0;//正常
    public final static Integer CO_ORDER_CUSTOMER_ISSUE_FINANCIAL = 1;//财务问题
    public final static Integer CO_ORDER_CUSTOMER_ISSUE_WAYBILL = 2;//当天问题
    public final static Integer CO_ORDER_CUSTOMER_ISSUE_BUCKLE = 3;//客户扣件
    public final static Integer CO_ORDER_CUSTOMER_ISSUE_PY = 4;//偏远
    public final static Integer CO_ORDER_CUSTOMER_ISSUE_BYY = 5;//不通邮
    public final static Integer CO_ORDER_CUSTOMER_ISSUE_DC = 6;//电池
    public final static Integer CO_ORDER_CUSTOMER_ISSUE_WJP = 7;//违禁品
    public final static Integer CO_ORDER_CUSTOMER_ISSUE_ZLBF = 8;//重量不符
    public final static Integer CO_ORDER_CUSTOMER_ISSUE_GJBF = 9;//国家不符
    public final static Integer CO_ORDER_CUSTOMER_ISSUE_MYB = 10;//没预报
    public final static Integer CO_ORDER_CUSTOMER_ISSUE_MFP = 11;//没发票
    public final static Integer CO_ORDER_CUSTOMER_ISSUE_FP = 12;//仿牌
    public final static Integer CO_ORDER_CUSTOMER_ISSUE_ZZ = 13;//中转问题
    public final static Integer CO_ORDER_CUSTOMER_ISSUE_NB = 14;//内部问题
    public final static Integer CO_ORDER_CUSTOMER_ISSUE_OTHER = 255;//其他

    /**
     *代理问题类型
     */
    public final static String CO_ORDER_SP_ISSUE = "SP_ISSUE";
    public final static Integer CO_ORDER_SP_ISSUE_NORMAL = 0;//正常
    public final static Integer CO_ORDER_SP_ISSUE_WAYBILL = 2;//当天问题

    /**
     * 运单状态
     */
    public final static String TL_SUB_ORDER="SUB_ORDER";
    public final static Integer TL_SUB_ORDER_HOST = 0;//主运单
    public final static Integer TL_SUB_ORDER_SON = 1; //子运单


    /**
     * 问题件业务类型
     */
    public final static String TL_WS_ISSUE_BTYPE = "WS_ISSUE_BTYPE";
    public final static Integer TL_WS_ISSUE_BTYPE_RC = 0;//入仓
    public final static Integer TL_WS_ISSUE_BTYPE_CC = 1;//出仓
    public final static Integer TL_WS_ISSUE_BTYPE_CCT = 2;//出仓退货

    /**
     * 问题件状态
     */
    public final static String TL_WS_ISSUE_WAYBILL_STATUS = "WS_ISSUE_WAYBILL_STATUS";
    public final static Integer TL_WS_ISSUE_WAYBILL_NEW =0;//制单
    public final static Integer TL_WS_ISSUE_WAYBILL_NOT_RESPOND =1;//待回复
    public final static Integer TL_WS_ISSUE_WAYBILL_RESPOND =2;//已回复
    public final static Integer TL_WS_ISSUE_WAYBILL_PROCESSED =3;//已处理
    /**
     * 联系方式
     */
    public final static String TL_CONTACT_TYPE = "CONTACT_TYPE";
    public final static Integer TL_CONTACT_TYPE_SHIP = 0;//发货
    public final static Integer TL_CONTACT_TYPE_RECEIVE = 1;//收货

    /**
     * 问题件回复类型
     */
    public final static String TL_WS_ISSUE_RESP_TYPE = "RESP_TYPE";
    public final static Integer TL_WS_ISSUE_RESP_TYPE_ENTRUST =0;//委托处理
    public final static Integer TL_WS_ISSUE_RESP_TYPE_RETURN =1;//退回
    public final static Integer TL_WS_ISSUE_RESP_TYPE_DISCARD =2;//丢弃

    /**
     * 客户报价类型
     */
    public final static String TL_CO_PRICE_TYPE ="CO_PRICE_TYPE";
    public final static Integer TL_CO_PRICE_TYPE_CT = 0;//客户类型报价
    public final static Integer TL_CO_PRICE_TYPE_C = 1;//客户报价

    /**
     * 业务类型
     */
    public final static String TL_BA_RP_TYPE ="B_TYPE";        //0预收款/1收款   2预付款/3付款
    public final static Integer TL_BA_RP_TYPE_PRESETTLE_IN =0;
    public final static Integer TL_BA_RP_TYPE_SETTLE_IN=1;
    public final static Integer TL_BA_RP_TYPE_PRESETTLE_OUT =2;
    public final static Integer TL_BA_RP_TYPE_SETTLE_OUT=3;

    /**
     * 标签打印格式
     */
    public final static String TL_LABEL_STYLE = "LABEL_STYLE";
    public final static Integer TL_LABEL_STYLE_STANDARD = 0;//标准样式
    public final static Integer TL_LABEL_STYLE_HKPOST = 1;//香港小包样式
    public final static Integer TL_LABEL_STYLE_CNPOST = 2;//中国邮政
    public final static Integer TL_LABEL_STYLE_AIRLINE = 3;//专线样式
    public final static Integer TL_LABEL_STYLE_SGPOST = 4;//瑞士样式
    public final static Integer TL_LABEL_STYLE_AU = 5;//澳洲样式

    /**
     * 报关单格式
     */
    public final static String TL_AC_STYLE = "AC_STYLE";
    public final static Integer TL_AC_STYLE_STANDARD = 0;//标准样式

    /**
     * 字段映射中模块名
     */
    public final static String FIELD_MATCHING_REC_IMP_PARCEL_BILL = "REC_IMP_PARCEL_BILL";//小包入仓导入
    public final static String FIELD_MATCHING_REC_IMP_EXPRESS_BILL = "REC_IMP_EXPRESS_BILL";//快递入仓导入
    public final static String FIELD_MATCHING_SP_RETURN_IMP = "SP_RETURN_IMP";//代理退货导入

    /**
     * 渠道类型的运输方式
     */
    public final static Integer CHANNEL_TYPE_DHL = 1;//DHL

    /**
     * Service接口实现类的名称
     * 用于SpringUtil.getBean(serviceName)中的serviceName参数
     */
    public final static String SERVICE_IMPL_ADMIN = "adminServiceImpl";//账号service实现类
    public final static String SERVICE_IMPL_BD_TYPE = "bdTypeServiceImpl";//基础数据service实现类
    public final static String SERVICE_IMPL_BD_CHANNEL_TYPE = "bdChannelTypeServiceImpl";//渠道类型service实现类
    public final static String SERVICE_IMPL_BD_CURRENCY = "bdCurrencyServiceImpl";//币种service实现类
    public final static String SERVICE_IMPL_SYS_SP_SENDER = "sysSpSenderServiceImpl";//代理寄件人service实现类
    public final static String SERVICE_IMPL_BD_CHANNEL="bdChannelServiceImpl"; //渠道service实现类
    public final static String SERVICE_IMPL_SYS_REGION="sysRegionServiceImpl";//地区Service实现类
    public final static String SERVICE_IMPL_BD_CHANNEL_DEST="bdChannelDestServiceImpl";//渠道目地的service实现类
    public final static String SERVICE_IMPL_BD_FEE_TYPE="bdFeeTypeServiceImpl";//费用项Service实现类
    public final static String SERVICE_IMPL_BD_BANK="bdBankServiceImpl";//银行账号Service实现类
    public final static String SERVICE_IMPL_SYS_CONSIGNOR="sysConsignorServiceImpl";// 客户Service实现类
    public final static String SERVICE_IMPL_SYS_SP = "sysSpServiceImpl";//代理service实现类
    public final static String SERVICE_IMPL_BA_ARP = "baArpServiceImpl";//其它应收付service实现类
    public final static String SERVICE_IMPL_BA_ACCOUNT = "baAccountServiceImpl";//代理service实现类
    public final static String SERVICE_IMPL_CO_ITEM ="coItemServiceImpl";
    public final static String SERVICE_IMPL_PS_CHANNEL_FUEL = "psChannelFuelServiceImpl";//渠道燃油service实现类
    public final static String SERVICE_IMPL_PS_CO_TYPE = "psCoTypeServiceImpl";//客户类型报价主表service实现类
    public final static String SERVICE_IMPL_PS_CO_TYPE_PRICE = "psCoTypePriceServiceImpl";//客户类型报价service实现类
    public final static String SERVICE_IMPL_PS_CO_TYPE_DISCOUNT = "psCoTypeDiscountServiceImpl";//客户类型报价折扣service实现类
    public final static String SERVICE_IMPL_PS_CO = "psCoServiceImpl";//客户报价主表service实现类
    public final static String SERVICE_IMPL_PS_CO_PRICE = "psCoPriceServiceImpl";//客户报价service实现类
    public final static String SERVICE_IMPL_PS_CO_DISCOUNT = "psCoDiscountServiceImpl";//客户报价折扣service实现类
    public final static String SERVICE_IMPL_PS_SP = "psSpServiceImpl";//代理报价主表service实现类
    public final static String SERVICE_IMPL_PS_SP_PRICE = "psSpPriceServiceImpl";//代理报价service实现类
    public final static String SERVICE_IMPL_PS_SP_DISCOUNT = "psSpDiscountServiceImpl";//代理报价折扣service实现类
    public final static String SERVICE_IMPL_PS_CO_PP = "psCoPpServiceImpl";//小包客户报价service实现类
    public final static String SERVICE_IMPL_PS_CO_TYPE_PP = "psCoTypePpServiceImpl";//小包客户类型报价service实现类
    public final static String SERVICE_IMPL_PS_SP_PP = "psSpPpServiceImpl";//小包代理报价service实现类
    public final static String SERVICE_IMPL_PS_UTIL = "psUtilServiceImpl";//小报价工具service实现类
    public final static String SERVICE_IMPL_BA_BIZ_ORDER = "baBizOrderServiceImpl";//结算单Service实现类
    public final static String SERVICE_IMPL_BD_CONSIGNOR_ADDR = "bdConsignorAddrServiceImpl";//结算单Service实现类
    public final static String SERVICE_IMPL_WS_REC_WAYBILL = "wsRecWaybillServiceImpl";//入仓运单表
    public final static String SERVICE_IMPL_WS_REC_WAYBILL_ITEM = "wsRecWaybillItemServiceImpl";//入仓运单表
    public final static String SERVICE_IMPL_WS_REC_RETURN_WAYBILL = "wsRecReturnWaybillServiceImpl";//退货运单表
    public final static String SERVICE_IMPL_WS_SP_RETURN_WAYBILL = "wsSpReturnWaybillServiceImpl";//代理退货运单表
    public final static String SERVICE_IMPL_WS_SP_WAYBILL = "wsSpWaybillServiceImpl";//出仓运单表
    public final static String SERVICE_IMPL_BD_TRACKING_NO = "bdTrackingNoServiceImpl";//运单号库Service实现类
    public final static String SERVICE_IMPL_WS_REC_WAYBILL_CONTACT = "wsRecWaybillContactServiceImpl";//运单收发货人Service实现类
    public final static String SERVICE_IMPL_BD_FARAWAY= "bdFarawayServiceImpl";//运单收发货人Service实现类
    public final static String SERVICE_IMPL_V_WS_WAYBILL = "vWsWaybillServiceImpl"; //入出仓运单Service实现类
    public final static String SERVICE_IMPL_BD_COST_ITEM = "baCostItemServiceImpl"; //成本明细表Service实现类
    public final static String SERVICE_IMPL_BA_FEE = "baFeeServiceImpl"; //费用明细Serivce实现类
    public final static String SERVICE_IMPL_WS_REC_ORDER = "wsRecOrderServiceImpl"; //Serivce实现类
    public final static String SERVICE_IMPL_BA_BANK = "baBankServiceImpl"; //银行帐号Serivce实现类
    public final static String SERVICE_IMPL_WS_TRACE_SYS= "wsWaybillTraceSystemServiceImpl";
    public final static String SERVICE_CO_ORDER= "coOrderServiceImpl";
    public final static String SERVICE_WS_REC_RETURN="wsRecReturnServiceImpl";
    public final static String SERVICE_WS_ISSUE_WAYBILL="wsIssueWaybillServiceImpl";
    public final static String SERVICE_WS_SP_ORDER= "wsSpOrderServiceImpl";
    public final static String SERVICE_WS_REC_ORDER= "wsRecOrderServiceImpl";
    public final static String SERVICE_BD_FIEL_MATCHING= "bdFieldMatchingServiceImpl";
    public final static String SERVICE_IMPL_CRM_CONSIGNOR_MSG= "crmConsignorMsgServiceImpl";
    public final static String SERVICE_IMPL_CRM_CONSIGNOR_MSG_CO= "crmConsignorMsgCoServiceImpl";
    public final static String SERVICE_IMPL_CRM_LEAVE_MSG= "crmLeaveMsgServiceImpl"; //客户留言实现类
    public final static String SERVICE_IMPL_BA_BIZ_ORDER_ADJUST = "baBizOrderAdjustServiceImpl";  //调整Serivce实现类
    public final static String SERVICE_IMPL_SYS_SMS_SETUP = "sysSmsSetupServiceImpl"; //短信设置实现类
    public final static String SERVICE_IMPL_SYS_SMS_SEND = "sysSmsSendServiceImpl";  //短信发送实现类
    public final static String SERVICE_IMPL_SYS_SMS_SEND_RULE = "sysSmsSendRuleServiceImpl";  //短信发送规则实现类
    public final static String SERVICE_IMPL_WS_WAYBILL_PATH = "wsWaybillPathServiceImpl";
    public final static String SERVICE_IMPL_BD_DEFAULT_CHANNEL = "bdDefaultChannelServiceImpl";
    public final static String SERVICE_IMPL_BD_DEFAULT_ITEM = "bdDefaultItemServiceImpl";
    public final static String SERVICE_IMPL_FREIGHT_TEMPLATE = "freightTemplateServiceImpl";
    public final static String SERVICE_IMPL_PRODUCT_GROUP = "productGroupServiceImpl";
    public final static String SERVICE_IMPL_PRODUCT_CATEGORY = "productCategoryServiceImpl";
    public final static String SERVICE_IMPL_BD_PRODUCT = "bdProductServiceImpl";
    public final static String SERVICE_IMPL_SYS_SHOP = "sysShopServiceImpl";
    public final static String SERVICE_IMPL_BASE_OS_OUT_ORDER = "baseOsOutOrderServiceImpl";
    public final static String SERVICE_IMPL_OS_OUT_ORDER = "osOutOrderServiceImpl";
    public final static String SERVICE_IMPL_WS_INVENTORY = "wsInventoryServiceImpl";
    public final static String SERVICE_ADMIN_AUTH = "adminAuthServiceImpl";
    public final static String SERVICE_MSG_PUSH = "msgPushServiceImpl";

    //特许码
    public final static String PPI_CODE = "PPI_CODE";

    //跟踪单号类型
    public static enum TrackingNoType{
        tno,//最终跟踪号
        wno,//运单号
        mno,//改单号
        rno//转单号
    }

    //增删改查
    public static enum CRUD{
       create, read, update, delete
    }

    public final static String ITEM_INFO_EXCEL_TEMPLATE_PATH="template"+ File.separator+"item_info_template.xlsx";
    public final static String CONSUME_EXCEL_TEMPLATE_PATH="template"+ File.separator+"consume_template.xlsx";
    public final static String LOGIN_FREQUENCY_EXCEL_TEMPLATE_PATH="template"+ File.separator+"login_frequency.xlsx";
    public final static String ROOM_TIME_EXCEL_TEMPLATE_PATH="template"+ File.separator+"room_time.xlsx";

    public final static String[] QUESTION_TITLE={"编号","应用编号","云娃编号","问题","答案","时间",};
}
