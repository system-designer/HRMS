var ncsyrcid='';//农村实用人才id
$(function() {
    showOne();
});


/**
 *加载combobox :奖励等级、农村实用人才类别、上年纯收入
 *
 */
function loadCombobox() {
    $.post(path + "manage/tsrcxxcx/ncsyrc/NcsyrcAction.jsp?mode=SHOWCOMBOXLIST", '', function(data) {
        if (data !== null) {
            loadComboboxData(data, new Array("jldj", "Jldj","jldjbm","jldjmc", false, false), new Array("ncrclb", "Ncrclb", "ncrclbbm", "ncrclbmc", false, false),new Array("csr", "Csr", "csrbm", "csrmc", false, false));
        }
    },"json")
}

/**
 *修改ryid的农村实用人才信息
 */
function update() {
    var jldjbm = $("#jldj").combobox("getValue");
    var ncrclbbm = $("#ncrclb").combobox("getValue");
    var sncsrbm = $("#csr").combobox("getValue");
    var sfwcdgbn=$("input[name='sfwcdgbn']:checked").val()
    $.post(path + "manage/tsrcxxcx/ncsyrc/NcsyrcAction.jsp?mode=UPDATE", {
        ncsyrcid: ncsyrcid, //农村实用人才id
        jldjbm: jldjbm , //奖励等级编码
        ncrclbbm: ncrclbbm, //农村人才类别编码
        sncsrbm: sncsrbm,//上年纯收入编码
        sfwcdgbn:sfwcdgbn
    }, function(data) {
        alert(data.notice);
        showNcsyrcxx();
    }, "json")
}

/**
**删除ryid的农村实用人才信息
*/
function del() {
    $.post(path + "manage/tsrcxxcx/ncsyrc/NcsyrcAction.jsp?mode=DEL", {
        ncsyrcid: ncsyrcid, //农村实用人才id
        ryid:ryid
    }, function(data) {
        alert(data.notice);
        if(data.notice=="删除成功"){
            $("#ncsyrc").form('clear');
            sfncsyrc=0;
            return ;
        }
        showNcsyrcxx();
    }, "json")
}

/**
* 添加ryid的农村实用人才信息
 */
function add() {
    var jldjbm = $("#jldj").combobox("getValue");
    var ncrclbbm = $("#ncrclb").combobox("getValue");
    var sncsrbm = $("#csr").combobox("getValue");
    var sfwcdgbn=$("input[name='sfwcdgbn']:checked").val()
    $.post(path + "manage/tsrcxxcx/ncsyrc/NcsyrcAction.jsp?mode=ADD", {
        ryid: ryid,
        sfwcdgbn:sfwcdgbn,//是否外出打工半年
        jldjbm: jldjbm , //奖励等级编码
        ncrclbbm: ncrclbbm, //农村人才类别编码
        sncsrbm: sncsrbm //上年纯收入编码
    }, function(data) {
        alert(data.notice);
        if(data.notice=="添加成功")
            sfncsyrc=1;
        showOne();
    }, "json")
}
/**
    * 显示人员id的基本信息  
     */
function showOne() {
    $.post(path + "manage/tsrcxxcx/ncsyrc/NcsyrcAction.jsp?mode=SHOWONE&&ryid=" + ryid, '', function(data) {
        $("#dwmc").val(data[0].dwmc);
        $("#xm").val(data[0].xm);
        $("#xb").val(data[0].xb);
        $("#zjhm").val(data[0].zjhm);
    }, "json");
    loadCombobox();
    //根据sfncsyrc（是否农村实用人才）判断显示对应的农村实用人才信息
    if(sfncsyrc==1){
        setTimeout('showNcsyrcxx()',300);
    }
}
/**
 *显示ryid对应的农村实用人才信息
 */
function showNcsyrcxx(){
    $.post(path + "manage/tsrcxxcx/ncsyrc/NcsyrcAction.jsp?mode=SHOWNCSYRCXX&&ryid=" + ryid, '', function(data) {
        $("#jldj").combobox('setValue', data[0].jldjbm);
        $("#ncrclb").combobox('setValue', data[0].rclbbm);
        $("#csr").combobox('setValue', data[0].sncsrbm);
        $("input[name='sfwcdgbn'][value="+data[0].sfwcdg+"]").attr("checked",'checked');
        ncsyrcid =data[0].ncsyrcid;
    }, "json");
}

/**
 * 根据sfncsyrc 决定是添加农村实用人才信息还是修改   
 * sfncsyrc值为1表示修改
 * sfncsyrc值为0表示添加
 */
function perSubmit(){
    if(sfncsyrc==0) {
        add();
    }
       
    if(sfncsyrc==1) {
        update();
    }
}

