var ryidxx = '';//保存人员id的基本信息
var sfcyrc = '';

$(function() {
    decide();
    getRyxx();
});
/**
 **
 *通过人员id显示单个人员信息
 */
function getRyxx() {
    $.post(path + "manage/tsrcxx/cyrc/CyrcAction.jsp?mode=SHOWONE&ryid=" + ryid + "&sfcyrc=" + sfcyrc + '',
            function(data, status) {
                var ryxx = data;
                ryidxx = ryxx[0];
                var dwmc = ryxx[0].dwmc;
                var xm = ryxx[0].xm;
                var xb = ryxx[0].xb;
                var zjhm = ryxx[0].zjhm;
                var yrzjg = ryxx[0].yrzjg;
                var yzw = ryxx[0].yzw;
                var zhgzsj = ryxx[0].zhgzsj;
                var email = ryxx[0].email;
                var yjzdz = ryxx[0].yjzdz;
                var zyry = ryxx[0].zyry;
                $("input[name='dwmc']").val(dwmc);
                $("input[name='xm']").val(xm);
                $("input[name='xb']").val(xb);
                $("input[name='zjhm']").val(zjhm);
                $("input[name='yrzjg']").val(yrzjg);
                $("input[name='yzw']").val(yzw);
                $("input[name='zhgzsj']").val(zhgzsj);
                $("input[name='email']").val(email);
                $("input[name='yjzdz']").val(yjzdz);
                $("#zyry").val(zyry);
                if (sfcyrc === '1') {
                    $("#dialog_update").show();
                    $("#dialog_delete").show();
                } else if (sfcyrc === '0') {
                    $("#dialog_add").show();
                }
            }, 'json');
}
/*
 * 更新创业人才信息的提交函数
 */
function update() {
    $("#cyrctj").form('submit', {
        url: path + "manage/tsrcxx/cyrc/CyrcAction.jsp?mode=UPDATE&ryid=" + ryid,
        success: function(data) {
            if (!isNaN(data)) {
                var dataInt = parseInt(data);
                if (dataInt === 0) {
                    alert("操作失败");
                    getRyxx();
                    return;
                } else {
                    alert("操作成功");
                    getRyxx();
//                    $("#cyrc").dialog("close");
                }
            }
        }
    });
}
/*
 * 添加创业人才信息的提交函数
 */
function add() {
    $("#cyrctj").form('submit', {
        url: path + "manage/tsrcxx/cyrc/CyrcAction.jsp?mode=ADD&ryid=" + ryid,
        success: function(data) {
            if (!isNaN(data)) {
                var dataInt = parseInt(data);
                if (dataInt === 0) {
                    alert("操作失败");
                    getRyxx();
                    return;
                } else {
                    alert("操作成功");
                    getRyxx();
//                    $("#cyrc").dialog("close");
                }
            }
        }
    });
}
/*
 * 删除创业人才信息的提交函数
 */
function del() {
    $("#cyrctj").form('submit', {
        url: path + "manage/tsrcxx/cyrc/CyrcAction.jsp?mode=DELETE&ryid=" + ryid,
        success: function(data) {
            if (!isNaN(data)) {
                var dataInt = parseInt(data);
                if (dataInt === 0) {
                    alert("操作失败");
                    getRyxx();
                    return;
                } else {
                    alert("操作成功");
                    getRyxx();
//                    $("#cyrc").dialog("close");
                }
            }
        }
    });
}

function decide() {
    $.ajax({
        url: path + "manage/tsrcxx/cyrc/CyrcAction.jsp?mode=DECIDE&ryid=" + ryid,
        cache: false,
        async: false,
        success: function(data) {
            if (!isNaN(data)) {
                var dataInt = parseInt(data);
                if (dataInt === 0) {
                    sfcyrc = '0';
                } else {
                    sfcyrc = '1';
                }
            }
        }
    });
}