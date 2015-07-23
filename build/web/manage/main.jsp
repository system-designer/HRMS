<%@page import="com.hrms.manage.permission.UserContants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://opdps.hbnu.edu.cn/jplus" prefix="z"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%
    pageContext.setAttribute("ucontants", new UserContants(), PageContext.PAGE_SCOPE);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>黄石人才信息管理系统后台管理</title>
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/icon.css">
        <link rel="stylesheet" type="text/css" href="<z:path/>js/jquery-easyui-1.3.2/themes/mycss/i-style.css">
        <script type="text/javascript" src="<z:path/>js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>  
        <script type="text/javascript" src="<z:path/>js/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
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
                    msg: '</br><center>您好 <b>${sessionScope.user.xm}</b>，欢迎登录黄石市人才信息管理系统后台管理！</br></br>今天是：' + getDateTimeString() + '<center>',
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
            #indexpage{background:url('<z:path/>manage/img/welcome_banner.jpg') right bottom no-repeat}
            #indexpage p{font-size:30px;margin:200px auto;position:relative;text-align:center}
            .menu-sep{margin:3px 0 3px 25px;font-size:1px;border-top:1px solid #ccc;border-bottom:1px solid #fff}
        </style>
    </head>
    <body class="easyui-layout">
        <div region="north" border="false" class="i-north">
            <div class="i-logo">黄石市人才信息管理系统</div>
            <div class="i-menu">
                <a id="imenuout" class="i-menu-a i-menu-out" href="<z:path/>LoginAction.jsp?mode=loginout">退出</a>
                <a id="imenutime" class="i-menu-a i-menu-time" href="javascript:;"></a>
                <a id="imenuuser" class="i-menu-a i-menu-user" href="javascript:;">${sessionScope.user.xm}
                    <span style="font-weight:500">
                        （${sessionScope.user.jb eq pageScope.ucontants.SHI?pageScope.ucontants.userLevelMap[pageScope.ucontants.SHI]:sessionScope.user.jb eq pageScope.ucontants.QU?pageScope.ucontants.userLevelMap[pageScope.ucontants.QU]:sessionScope.user.jb eq pageScope.ucontants.QIYE?pageScope.ucontants.userLevelMap[pageScope.ucontants.QIYE]:""}${sessionScope.user.lb eq pageScope.ucontants.ADMIN?pageScope.ucontants.userTypeMap[pageScope.ucontants.ADMIN]:sessionScope.user.lb eq pageScope.ucontants.PUTONG?pageScope.ucontants.userTypeMap[pageScope.ucontants.PUTONG]:""}）
                        </span>
                </a>
            </div>
        </div>
        <div class="i-west" region="west" iconCls="icon-nav0" split="false" title="管理菜单" id="menu">
            <div class="easyui-accordion" border="false" animate="true" id="accordionMenu">
                <%--一级菜单--%>
                <c:if test="${sessionScope.user.jb ne pageScope.ucontants.QIYE}">
                    <%--维护系统：非企业用户--%>
                    <div class="i-nav" title="系统维护" iconCls="icon-nav1">
                        <%--二级菜单--%>
                        <a class="i-nav-a" href="javascript:;" i-title="工作单位维护" i-url="<z:path/>manage/xtwh/gzdwwh.jsp">工作单位维护</a>
                        <c:if test="${sessionScope.user.lb eq pageScope.ucontants.ADMIN}">
                            <%--维护用户：市/区管理员--%>
                            <a class="i-nav-a" href="javascript:;" i-title="用户维护" i-url="<z:path/>manage/xtwh/yhwh.jsp">用户维护</a>
                        </c:if>
                    </div>
                </c:if>
                <div class="i-nav" title="人才信息维护" iconCls="icon-nav1">
                    <a class="i-nav-a" href="javascript:;" i-title="基本人才信息" i-url="<z:path/>manage/jbrcxx/jbrcxx.jsp">基本人才信息</a>
                    <a class="i-nav-a" href="javascript:;" i-title="照片维护" i-url="<z:path/>manage/xtwh/rcxxwhtpgl.jsp">照片维护</a>
                </div>
                <div class="i-nav" title="人才信息查询" iconCls="icon-nav1">
                    <a class="i-nav-a" href="javascript:;" i-title="创业人才" i-url="<z:path/>manage/tsrcxx/cyrccx/cyrccx.jsp">创业人才</a>
                    <a class="i-nav-a" href="javascript:;" i-title="专技人才" i-url="<z:path/>manage/tsrcxx/zyjsrccx/zyjsrccx.jsp">专技人才</a>
                    <a class="i-nav-a" href="javascript:;" i-title="党政人才" i-url="<z:path/>manage/tsrcxx/dzrc/dzrccx.jsp">党政人才</a>
                    <a class="i-nav-a" href="javascript:;" i-title="农村实用人才" i-url="<z:path/>manage/tsrcxx/ncsyrccx/ncsyrccx.jsp">农村实用人才</a>
                    <a class="i-nav-a" href="javascript:;" i-title="高技能人才" i-url="<z:path/>manage/tsrcxx/jnrccx/jnrccx.jsp">高技能人才</a>
                    <a class="i-nav-a" href="javascript:;" i-title="企业管理人才" i-url="<z:path/>manage/tsrcxx/qyglrccx/qyglrccx.jsp">企业管理人才</a>
                    <a class="i-nav-a" href="javascript:;" i-title="社会工作人才" i-url="<z:path/>manage/tsrcxx/shgzrccx/shgzrccx.jsp">社会工作人才</a>

                </div>
                <!--                <div class="i-nav" title="资源管理" iconCls="icon-nav1">
                                   
                                </div>-->
                <div class="i-nav" title="需求岗位" iconCls="icon-nav1">
                    <a class="i-nav-a" href="javascript:;" i-title="需求岗位" i-url="<z:path/>gwxq/gwxq.jsp">需求岗位</a>
                    <a class="i-nav-a" href="javascript:;" i-title="需求岗位查询" i-url="<z:path/>gwxq/gwxqcx.jsp">需求岗位查询</a>
                </div>
            </div>
        </div>
        <div region="center" class="i-center">
            <div class="easyui-tabs" fit="true" id="tt" style="padding:0px;">
                <div id="indexpage" title="我的主页" style="">
                    <p>欢迎访问黄石市人才信息管理系统！</p>
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
        <div class="i-south" region="south" border="false" style="font-size:12px;">
            &copy; 2011-2013 湖北省黄石市人才管理.&nbsp;&nbsp;技术支持: 湖北师范学院计算机科学与技术系Jplus实验室&nbsp;&nbsp;（最佳分辨率1400x900）
        </div>
    </body>
</html>

