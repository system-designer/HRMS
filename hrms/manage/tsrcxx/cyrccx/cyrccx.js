/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    bindEvents();
    data();
    loadData();
})

function loadData() {
    $.post(path + 'manage/tsrcxx/cyrc/CyrccxAction.jsp?mode=gettables', "",
            function(data, statues) {
                if (data != null) {
                    loadComboboxData(data,
                            new Array("dwxz", "Dwxz", "dwxzbm", "dwxzmc", false, false),
                            new Array("ssxt", "Ssxt", "ssxtbm", "ssxtmc", false, false),
                            new Array("sshy", "Sshy", "sshybm", "sshymc", false, false),
                            new Array("xl", "Xl", "xlbm", "xlmc", false, false),
                            new Array("xw", "Xw", "xwbm", "xwmc", false, false),
                            new Array("dwlb", "Dwlb", "dwlbbm", "dwlbmc", false, false))
                }
            },
            'json');
}
function submitForm() {
    data();
}

function data() {
    var xwys = document.getElementById("xwys");
    var xlys = document.getElementById("xlys");
    $('#jbxx').datagrid({
        url: path + 'manage/tsrcxx/cyrc/CyrccxAction.jsp?mode=showlist',
        queryParams: {
            dwxz: $('#dwxz').combobox('getValue'),
            ssxt: $('#ssxt').combobox('getValue'),
            dwlb: $('#dwlb').combobox('getValue'),
            sshy: $('#sshy').combobox('getValue'),
            nl1: $('#nl1').val(),
            nl2: $('#nl2').val(),
            xl: $('#xl').combobox('getValue'),
            xw: $('#xw').combobox('getValue'),
            gzdw: $('#gzdw').val(),
            xwys: xwys.checked ? "true" : "false",
            xlys: xlys.checked ? "true" : "false"
        },
        rownumbers: true,
        pagination: true,
        rownumber: true,
        fitColumns: true,
        striped: true,
        collapsible: true,
        remoteSort: false,
        nowrap: false,
        checkOnSelect: false,
        singleSelect: true,
        loadMsg: '数据加载中，请稍等...',
        columns: [[{
                    field: 'dwmc',
                    width: 150,
                    title: '工作单位',
                    align: 'center'
                },
                {
                    field: 'xm',
                    width: 100,
                    title: '姓名',
                    align: 'center'
                }, {
                    field: 'xb',
                    width: 80,
                    title: '性别',
                    align: 'center'
                }, {
                    field: 'csrq',
                    width: 100,
                    title: '出生日期',
                    align: 'center'
                }, {
                    field: 'xlmc',
                    width: 100,
                    title: '学历',
                    align: 'center'
                },
                {
                    field: 'zjhm',
                    width: 150,
                    title: '证件号码',
                    align: 'center'
                },
                {
                    field: 'action',
                    title: '操作',
                    align: 'center',
                    width: 80,
                    formatter: function(value, rowData, rowIndex) {
                        return "<a href='javascript:void(0)' onclick='window.parent.addTab(\"manage/tsrcxx/cyrc/CyrccxAction.jsp?mode=SHOWONE&cyrcid=" + rowData.ryid + "\",\"" + rowData.xm + "的详细信息\")' style=\"text-decoration: none;\">详情</a>";
                    }
                }
            ]]
    })
}
//添加绑定事件
function bindEvents() {
    $("#hidden_xlys").val("false");
    $("#hidden_xwys").val("false");
    $("#xlys").bind("click",
            function(event) {
                if ($("#xlys").attr('checked')) {
                    $("#hidden_xlys").val("true");
                } else {
                    $("#hidden_xlys").val("false");
                }
            });
    $("#xwys").bind("click",
            function(event) {
                if ($("#xwys").attr('checked')) {
                    $("#hidden_xwys").val("true");
                } else {
                    $("#hidden_xwys").val("false");
                }
            });
}
function tjbb() {
    $("#cxtj").submit();
}