var ryidxx = '';//保存人员id的基本信息
var sfdzrc = '';
$(function() {
    panDuan();
    getRyxx();
});

function panDuan() {
    $.ajax({
        url: path + "/manage/tsrcxx/dzrc/DzrcAction.jsp?mode=PANDUAN&ryid=" + ryid,
        cache: false,
        async: false,
        success: function(data) {
            if (!isNaN(data)) {
                var dataInt = parseInt(data);
                if (dataInt === 0) {
                    sfdzrc = '0';
                } else {
                    sfdzrc = '1';
                }
            }
        }
    });
}
/**
 **
 *通过人员id显示单个人员信息
 */
function getRyxx() {
//    panDuan();    
    $.post(path + "/manage/tsrcxx/dzrc/DzrcAction.jsp?mode=SHOWONE&ryid=" + ryid + "&sfdzrc=" + sfdzrc, '',
            function(data, status) {
                var ryxx = data;
                ryidxx = ryxx[0];
                var dwmc = ryxx[0].dwmc;
                var xm = ryxx[0].xm;
                var xb = ryxx[0].xb;
                var zjhm = ryxx[0].zjhm;
                var zjmc = ryxx[0].zjmc;
                var zw = ryxx[0].zw;
                var rxzsj = ryxx[0].rxzsj;
                var khqk1 = ryxx[0].khqk1;
                var khqk2 = ryxx[0].khqk2;
                var khqk3 = ryxx[0].khqk3;
                $("input[name='dwmc']").val(dwmc);
                $("input[name='xm']").val(xm);
                $("input[name='xb']").val(xb);
                $("input[name='zjhm']").val(zjhm);
                $("input[name='zjmc']").val(zjmc);
                $("input[name='zw']").val(zw);
                $("input[name='rxzsj']").val(rxzsj);
                $("input[name='khqk1']").val(khqk1);
                $("input[name='khqk2']").val(khqk2);
                $("input[name='khqk3']").val(khqk3);
                if (sfdzrc === '1') {
                    $("#dialog_button_update").show();
                    $("#dialog_button_delete").show();
                } else if (sfdzrc === '0') {
                    $("#dialog_button_add").show();
                }
            }, 'json');
}
/*
 * 更新党政人才信息的提交函数
 */
function updatedzrc() {
    $("#dzrcxx").form('submit', {
        url: path + "/manage/tsrcxx/dzrc/DzrcAction.jsp?mode=UPDATE&ryid=" + ryid,
        success: function(data) {
            if (!isNaN(data)) {
                var dataInt = parseInt(data);
                if (dataInt === 0) {
                    alert("操作失败");
                    getRyxx();
                    return;
                } else {
                    alert("操作成功");
                    $("#dialog_button_update").show();
                    $("#dialog_button_delete").show();
                }
            }
        }
    });
}
/*
 * 添加党政人才信息的提交函数
 */
function adddzrc() {
    $("#dzrcxx").form('submit', {
        url: path + "/manage/tsrcxx/dzrc/DzrcAction.jsp?mode=ADD&ryid=" + ryid,
        success: function(data) {
            if (!isNaN(data)) {
                var dataInt = parseInt(data);
                if (dataInt === 0) {
                    alert("操作失败");
                    getRyxx();
                    return;
                } else {
                    alert("操作成功"); 
                    $("#dialog_button_add").hide();
                    $("#dialog_button_update").show();
                    $("#dialog_button_delete").show();
                }
            }
        }
    });
}
/*
 * 删除党政人才信息的提交函数
 */
function deletedzrc() {
    $("#dzrcxx").form('submit', {
        url: path + "/manage/tsrcxx/dzrc/DzrcAction.jsp?mode=DELETE&ryid=" + ryid,
        success: function(data) {
            if (!isNaN(data)) {
                var dataInt = parseInt(data);
                if (dataInt === 0) {
                    alert("操作失败");
                    getRyxx();
                    return;
                } else {                   
                    alert("操作成功");
                    $("#dialog_button_update").hide();
                    $("#dialog_button_delete").hide();
                    $("#dialog_button_add").show();
                    $("#rxzsj").val("");
                    $("#khqk1").val("");
                    $("#khqk2").val("");
                    $("#khqk3").val("");
                }
            }
        }
    });
}
