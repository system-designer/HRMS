$(function() {
    console.info("ryid:"+ryid);
    validate();
});
function loadDitgride() {
    $('#jtcy').datagrid({
        url: path + 'manage/tsrcxx/dzrc/DzrccxAction.jsp?mode=SHOWDITGRIDE&ryid=' + ryid,
        queryParams: {
        },
        loadMsg: '数据加载中，请稍等...',
        columns: [[
        {
            field: 'cw',
            title: '称谓',
            align: 'center',
            width: 100
        },
        {
            field: 'xm',
            title: '姓 名',
            align: 'center',
            width: 120
        }, {
            field: 'jtcyid',
            hidden: true
        }, {
            field: 'csny',
            title: '出生年月',
            align: 'center',
            width: 130
        }, {
            field: 'zzmmmc',
            title: '政治面貌',
            align: 'center',
            width: 120
        }, {
            field: 'zzmmbm',
            hidden: true
        }, {
            field: 'gzdw',
            title: '工作单位',
            align: 'center',
            width: 120
        }, {
            field: 'zw',
            title: '职务',
            align: 'center',
            width: 80
        }
        ]]
    });
}

function validate() {
    $.ajax({
        url: path + 'manage/tsrcxx/dzrc/DzrccxAction.jsp?mode=SHOWDITGRIDE&ryid=' + ryid,
        type: 'post',
        success: function(data) {
            if (data !== null && data.total !== 0) {
                loadDitgride();
            } else {
                $(".jtcyyshgx").css('display', 'none');//如果属性设为可见即block
            }
        }
    });
}