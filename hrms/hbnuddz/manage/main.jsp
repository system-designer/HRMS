<%--
    Document   : main
    Created on : 2013-2-7, 10:49:41
    Author     : 刘雷
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="<z:path/>web/image/favicon.ico" type="image/x-icon" />
        <link rel="Shortcut Icon" type="image/x-icon" href="<z:path/>web/image/favicon.ico" />
        <link rel="stylesheet" type="text/css" href="<z:path/>jquery-easyui-1.2.5/themes/default/easyui.css"/>
        <link rel="stylesheet" type="text/css" href="<z:path/>jquery-easyui-1.2.5/themes/icon.css"/>
        <link rel="stylesheet" type="text/css" href="<z:path/>jquery-easyui-1.2.5/themes/mycss/i-style.css">
        <!--<link rel="stylesheet" type="text/css" href="<z:path/>manage/css/main.css"/>-->
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="<z:path/>jquery-easyui-1.2.5/locale/easyui-lang-zh_CN.js"></script>
        <!--<script type="text/javascript" src="<z:path/>manage/js/main.js"></script>-->
        <title>湖师担当者志愿社后台管理系统</title>
        <script type="text/javascript">
            var tabWidth;
            var tabHeight;
            var path='<z:path/>'
            $(function() {
                var index_tabsMenu = $('#index_tabsMenu');
                var index_tabs = $('#tt');
                tabWidth = $("#indexpage").width();
                tabHeight = $("#indexpage").height()-3;//得到右边工作区的高度和宽度
                index_tabs.tabs({
                    fit : true,
                    border : false,
                    onContextMenu : function(e, title) {
                        e.preventDefault();
                        index_tabsMenu.menu('show', {
                            left : e.pageX,
                            top : e.pageY
                        }).data('tabTitle', title);
                    }
                });
                index_tabsMenu.menu({
                    onClick : function(item) {
                        var curTabTitle = $(this).data('tabTitle');
                        var type = $(item.target).attr('m-type');
                        if (type === 'refresh') {
                            var href = index_tabs.tabs('getSelected').panel('options').href;
                            if (href) {/*说明tab是以href方式引入的目标页面*/
                                var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
                                index_tabs.tabs('getTab', index).panel('refresh');
                            } else {/*说明tab是以content方式引入的目标页面*/
                                var panel = index_tabs.tabs('getSelected').panel('panel');
                                var frame = panel.find('iframe');
                                try {
                                    if (frame.length > 0) {
                                        for ( var i = 0; i < frame.length; i++) {
                                            frame[i].contentWindow.document.write('');
                                            frame[i].contentWindow.close();
                                            frame[i].src = frame[i].src;
                                        }
                                        if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
                                            try {
                                                CollectGarbage();
                                            } catch (e) {
                                            }
                                        }
                                    }
                                } catch (e) {
                                }
                            }
                            return;
                        }
                        if (type === 'close') {
                            var t = index_tabs.tabs('getTab', curTabTitle);
                            if (t.panel('options').closable) {
                                index_tabs.tabs('close', curTabTitle);
                            }
                            return;
                        }
                        var allTabs = index_tabs.tabs('tabs');
                        var closeTabsTitle = [];
                        $.each(allTabs, function() {
                            var opt = $(this).panel('options');
                            if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
                                closeTabsTitle.push(opt.title);
                            } else if (opt.closable && type === 'closeAll') {
                                closeTabsTitle.push(opt.title);
                            }
                        });
                        for ( var i = 0; i < closeTabsTitle.length; i++) {
                            index_tabs.tabs('close', closeTabsTitle[i]);
                        }
                    }
                });
                var nava = $(".i-nav-a");
                var n = -1;
                nava.each(function(i) {
                    nava.eq(i).click(function() {
                        showTab($(this).attr("i-title"), $(this).attr("i-url"));
                        if (n != i) {
                            nava.eq(i).addClass("i-nav-selected");
                            if (n >= 0) {
                                nava.eq(n).removeClass("i-nav-selected");
                            }
                        }
                        n = i;
                    });
                });
                var timer = setInterval(function() {
                    showMenuTime($("#imenutime"));
                }, 90);
                $.messager.show({
                    title: '提示',
                    showSpeed: 1000,
                    width: 360,
                    height: 140,
                    msg: '</br><center>您好 <b>${sessionScope.user.xm}</b>，欢迎登录湖师担当者志愿社后台管理系统！</br></br>今天是：' + getDateTimeString() + '<center>',
                    timeout: 10000,
                    showType: 'slide'
                });
            });
            //更新菜单时间
            function showMenuTime($obj) {
                $obj.text(getDateTimeString());
            }
            //时间格式化
            function getDateTimeString() {
                var d = getDateTime();
                d['h'] = ((d['h'] + '').length == 1) ? ('0' + d['h']) : d['h'];
                d['m'] = ((d['m'] + '').length == 1) ? ('0' + d['m']) : d['m'];
                d['s'] = ((d['s'] + '').length == 1) ? ('0' + d['s']) : d['s'];
                return d['Y'] + '年' + d['M'] + '月' + d['D'] + '日 ' + d['h'] + ':' + d['m'] + ':' + d['s'] + ' ' + d['W'];
            }
            //得到当前时间
            function getDateTime(date) {
                if (!date)
                    date = new Date();
                var dateArr = [];
                var week = ["日", "一", "二", "三", "四", "五", "六"];
                dateArr['Y'] = date.getFullYear();
                dateArr['M'] = date.getMonth() + 1;
                dateArr['D'] = date.getDate();
                dateArr['h'] = date.getHours();
                dateArr['m'] = date.getMinutes();
                dateArr['s'] = date.getSeconds();
                dateArr['w'] = date.getDay();
                dateArr['W'] = '星期' + week[date.getDay()];
                return dateArr;
            }
            //显示选项卡（已打开则选中,未打开则加载url）
            function showTab(_title, _url) {
                if (_url && _title) {
                    //先判断是否存在标题为title的选项卡
                    var tab = $('#tt').tabs('exists', _title);
                    if (tab) {
                        //若存在，则直接打开
                        $('#tt').tabs('select', _title);
                    } else {
                        //否则创建
                        $('#tt').tabs('add', {
                            title: _title,
                            content: "<iframe  width='" + tabWidth + "px' height='" + tabHeight + "px' frameborder='0' scrolling='auto'  src='" + _url + "'></iframe>",
                            closable: true
                        });
                    }
                }
            }
            //添加一个tab到右侧工作区
            function addTab(href, title) {
                if ($("#tt").tabs("exists", title)) {
                    $("#tt").tabs('select', title);
                } else {
                    $("#tt").tabs("add", {
                        title: title,
                        content: "<iframe src='" + path + href + "' style='overflow:auto;width:" + tabWidth + "px;height:" + tabHeight + "px;'  noresize='noresize' frameborder='0'></iframe>",
                        closable: true
                    });
                }
            }
        </script>
        <style type="text/css">
            #indexpage{background:url('<z:path/>manage/image/manage_index_bg.jpg') no-repeat}
            #indexpage p{font-size:30px;margin:200px auto;position:relative;text-align:center}
            .menu-sep{margin:3px 0 3px 25px;font-size:1px;border-top:1px solid #ccc;border-bottom:1px solid #fff}
        </style>
    </head>
    <body class="easyui-layout">
        <div class="i-north" region="north">   
            <div class="i-logo">湖师担当者志愿社后台管理系统</div>
            <div class="i-menu">
                <a  class="i-menu-a i-menu-back" href="<z:path/>IndexAction.jsp?mode=SHOWLIST">回到首页</a>
                <a id="imenuout" class="i-menu-a i-menu-out" href="<z:path/>IndexAction.jsp?mode=LOGINOUT">退出</a>
                <a id="imenutime" class="i-menu-a i-menu-time" href="javascript:;"></a>
                <a id="imenuuser" class="i-menu-a i-menu-user" href="javascript:;">${sessionScope.yhb.yhm}<span style="font-weight:500">（${sessionScope.yhqxmc}）</span></a>
            </div>
        </div> 
        <div class="i-west" region="west" iconCls="icon-nav0" split="false"  title="管理菜单" id="menu">
            <div class="easyui-accordion" border="false" animate="true" id="accordionMenu">
                <div title="协会管理" class="i-nav" iconCls="icon-nav1">
                    <a class="i-nav-a" href="javascript:void(0);" onclick="addTab('/manage/gywm/xhcy.jsp','协会成员')">协会成员</a>
                    <a class="i-nav-a" href="javascript:void(0);" onclick="addTab('/manage/gywm/xhbm.jsp','协会部门')">协会部门</a>
                </div>
                <div title="公告管理" class="i-nav" iconCls="icon-nav1">
                    <a class="i-nav-a" href="javascript:void(0);" onclick="addTab('/manage/stgg/stgg.jsp','公告管理')">公告管理</a>
                    <a class="i-nav-a" href="javascript:void(0);" onclick="addTab('/manage/stgg/gglb.jsp','公告类别')">公告类别</a>
                </div>
                <div title="活动管理" class="i-nav" iconCls="icon-nav1">
                    <a class="i-nav-a" href="javascript:void(0);" onclick="addTab('/manage/sthd/sthd.jsp','活动管理')">活动管理</a>
                    <a class="i-nav-a" href="javascript:void(0);" onclick="addTab('/manage/sthd/hdlb.jsp','活动类别')">活动类别</a>
                    <a class="i-nav-a" href="javascript:void(0);" onclick="addTab('/manage/hdjy/hdjy.jsp','活动剪影')">活动剪影</a>
                </div>
                <div title="百科管理" class="i-nav" iconCls="icon-nav1">
                    <a class="i-nav-a" href="javascript:void(0);" onclick="addTab('/manage/hbbk/hbbk.jsp','百科管理')">百科管理</a>
                    <a class="i-nav-a" href="javascript:void(0);" onclick="addTab('/manage/hbbk/bklb.jsp','百科类别')">百科类别</a>
                </div>
                <div title="论坛管理" class="i-nav" iconCls="icon-nav1">
                    <a class="i-nav-a" href="javascript:void(0);" onclick="addTab('/manage/jltd/bkgl.jsp','板块管理')">板块管理</a>
                    <a class="i-nav-a" href="javascript:void(0);" onclick="addTab('/manage/jltd/ztgl.jsp','主帖管理')">主帖管理</a>
                    <a class="i-nav-a" href="javascript:void(0);" onclick="addTab('/manage/jltd/htgl.jsp','回帖管理')">回帖管理</a>
                </div>
                <div title="留言管理" class="i-nav" iconCls="icon-nav1">
                    <a class="i-nav-a" href="javascript:void(0);" onclick="addTab('/manage/lywm/lywm.jsp','留言管理')">留言管理</a>
                </div>
                <div title="系统管理" class="i-nav" iconCls="icon-nav1">
                    <a class="i-nav-a" href="javascript:void(0);" onclick="addTab('/manage/sthd/tpgl.jsp','图片管理')">图片管理</a>
                    <a class="i-nav-a" href="javascript:void(0);" onclick="addTab('/manage/jltd/yhgl.jsp','用户管理')">用户管理</a>
                    <a class="i-nav-a" href="javascript:void(0);" onclick="addTab('/manage/webindex/webindex.jsp','网站首页管理')">网站首页管理</a>
                    <a class="i-nav-a" href="javascript:void(0);" onclick="addTab('/manage/system/serverManager.jsp','服务器管理')">服务器管理</a>
                </div>
            </div>
        </div>
        <div region="center" class="i-center">
            <div class="easyui-tabs" fit="true" id="tt" style="padding:0px;">
                <div id="indexpage" title="我的主页" style="">
                    <p>欢迎访问湖师担当者志愿社后台管理系统！</p>
                </div>
            </div>
            <div id="index_tabsMenu" style="width: 120px; display: none;">
                <div m-type="refresh" iconCls="icon-reload">刷新</div>
                <div class="menu-sep"></div>
                <div m-type="close" iconCls="icon-delete">关闭当前</div>
                <div m-type="closeOther" iconCls="icon-delete">关闭其他</div>
                <div m-type="closeAll" iconCls="icon-delete">关闭所有</div>
            </div>
        </div>
        <div  class="i-south" region="south" border="false" style="font-size:12px;">
            <div class="easyui-panel" title="Copyright© 2011-2012 湖北师范学院计算机科学与技术系Jplus实验室&nbsp;&nbsp;最佳分辨率1400x900"
                 headerCls="titleCenter" collapsible="false" border="false"></div>
        </div>
    </body>
</html>
