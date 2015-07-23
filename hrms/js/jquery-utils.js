/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
(function(jQuery) {
    $.loadComboboxData = function loadComboboxData(options) {
        var paramLen = arguments.length;//获得实参的个数
        var data = new Array();
        data = arguments[0];//获得JSON数据
        var opts = new Array();
        for (var i = 1; i < paramLen; i++) {//从第一个开始遍历所有的combobox
            opts = arguments[i];
            if (data[opts[1]] != null) {//避免因为数据不存在，而产生错误
                var edit = false;
                var select = true;
                if (opts[5] != null) {//是否可编辑 如果不写参数，则默认不可编辑
                    edit = opts[5];
                }
                if (opts[4] != null && !opts[4]) {
                    select = false
                }
                $("#" + opts[0]).combobox({//为每个combobox加载数据
                    data: data[opts[1]],
                    valueField: opts[2],
                    textField: opts[3],
                    editable: edit
                });
                $("#" + opts[0]).css("width", "100px");
                if (select) {//是否选中第一个，如果不写参数，默认选中第一个
                    if (data[opts[1]] != null && data[opts[1]].length > 0) {
                        $("#" + opts[0]).combobox("setValue", data[opts[1]][0][opts[2]]);
                    }
                }
            }
        }
    }
})(jQuery);

