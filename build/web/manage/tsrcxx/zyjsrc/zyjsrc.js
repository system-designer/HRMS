var seletedUser="";
$(function() {
    sfzjrc();
    loadCombobox();
    var edior = loadEditor("hjcq");
    showOne();
    showList();
});

function loadCombobox() {
    $.post(path + "manage/tsrcxx/zyjsrc/ZyjsrcAction.jsp?mode=SHOWCOMBOXLIST", '', function(data) {
        if (data !== null) {
            loadComboboxData(data, new Array("zclb", "Zclb", "zclbbm", "zclbmc", false, false), new Array("jszc", "Zc", "zcbm", "zcmc", false, false));
            $("#zclb").combobox("setValue", seletedUser.zclbbm);
            $("#jszc").combobox("setValue", seletedUser.zcbm);    
        }
    }, "json");
}
function sfzjrc() {
    $.ajax({
        url: path + "manage/tsrcxx/zyjsrc/ZyjsrcAction.jsp?mode=SFZJRC&&ryid=" + ryid,
        cache: false,
        async: false,
        success: function(data) {
            if (!isNaN(data)) {
                var dataInt = parseInt(data);
                if (dataInt === 0) {
                    sfzjrc = '0';
                    $("#update").hide();
                    $("#dell").hide();
                    $("#add").show();

                } else {
                    sfzjrc = '1';
                    $("#update").show();
                    $("#dell").show();
                    $("#add").hide();
                }
            }
        }
    });
}

function del() {
    $.post(path + "manage/tsrcxx/zyjsrc/ZyjsrcAction.jsp?mode=DEL", {
        zyjsrcid: zyjsrcid,
        ryid: ryid
    }, function(data) {
        $.messager.alert("提示", data.notice, "info");
        if (data.sfzyrc == "0") {
            sfzjrc = '0';
            $("#update").hide();
            $("#dell").hide();
            $("#add").show();
            sfzjrc = data.sfzyrc;
            $("#cszy").val("");
            $("#gzgw").val("");
            CKEDITOR.instances.hjcq.setData("");
            $("#zclb").combobox("clear");
            $("#jszc").combobox("clear");
        }
    }, "json");
    showList();
}

function add() {
    var cszy = $("#cszy").val();
    var gzgw = $("#gzgw").val();
    var hjcq = CKEDITOR.instances.hjcq.getData();
    var zclbbm = $("#zclb").combobox("getValue");
    var zcbm = $("#jszc").combobox("getValue");
    $.post(path + "manage/tsrcxx/zyjsrc/ZyjsrcAction.jsp?mode=ADD", {
        ryid: ryid,
        zyjsrcid: zyjsrcid, //专业职称id
        cszy: cszy, //从事专业
        gzgw: gzgw, //工作岗位
        jszcbm: zcbm, //职称编码
        hjcq: hjcq, //获奖情况
        zclbbm: zclbbm //职称类别编码
    }, function(data) {
        $.messager.alert("提示", data.notice, "info");
        if (data.sfzjrc == "1") {
            $("#update").show();
            $("#dell").show();
            $("#add").hide();
            sfzjrc = data.sfzjrc;
        }
        showList();
    }, "json");
}
function showList() {
    $.post(path + "manage/tsrcxx/zyjsrc/ZyjsrcAction.jsp?mode=SHOWLIST&&ryid=" + ryid, '', function(data) {
        seletedUser=data[0];
        $("#cszy").val(data[0].cszy);
        $("#gzgw").val(data[0].gzgw);
        $("#hjcq").val(data[0].hjcq);
        $("#zclb").combobox("setValue", data[0].zclbbm);
        $("#jszc").combobox("setValue", data[0].zcbm);
        zyjsrcid = data[0].zyjsrcid;
        if (sfzjrc === "1") {
            $("#update").show();
            $("#dell").show();
            $("#add").hide();
        }
        if (sfzjrc === "0") {
            $("#update").hide();
            $("#dell").hide();
            $("#add").show();
        }
    }, "json");
}
function showOne() {
    $.post(path + "manage/tsrcxx/zyjsrc/ZyjsrcAction.jsp?mode=SHOWONE&&ryid=" + ryid, '', function(data) {
        $("#dwmc").val(data[0].dwmc);
        $("#xm").val(data[0].xm);
        $("#xb").val(data[0].xb);
        $("#zjhm").val(data[0].zjhm);
    }, "json");
}