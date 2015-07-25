$(function(){
    //取得div层
    var $search = $('#baidu_search');
    //取得输入框JQuery对象
   
    var $searchInput = $search.find('#'+input_id);
    //关闭浏览器提供给输入框的自动完成
    var InputHeight=parseInt($searchInput.outerHeight());
//  alert(InputHeight);
    var InputTop=parseInt($searchInput.css("top"));
    var  InputLeft=$searchInput.css("left");
//     alert($searchInput.css("left"))
    $searchInput.attr('autocomplete','off');
    //创建自动完成的下拉列表，用于显示服务器返回的数据,插入在搜索按钮的后面，等显示的时候再调整位置
    var $autocomplete = $('<div class="autocomplete"></div>')
    .hide()
    .insertAfter('#'+submit_id);
    $autocomplete.css({"top":(InputTop+InputHeight)+"px","left":InputLeft});
    //清空下拉列表的内容并且隐藏下拉列表区
    var clear = function(){
        $autocomplete.empty().hide();
    };
    //注册事件，当输入框失去焦点的时候清空下拉列表并隐藏
    $searchInput.blur(function(){
        setTimeout(clear,500);
    });
    //下拉列表中高亮的项目的索引，当显示下拉列表项的时候，移动鼠标或者键盘的上下键就会移动高亮的项目，想百度搜索那样
    var selectedItem = null;
    //timeout的ID
    var timeoutid = null;
    //设置下拉项的高亮背景
    var setSelectedItem = function(item){
        //更新索引变量
        selectedItem = item ;
        //按上下键是循环显示的，小于0就置成最大的值，大于最大值就置成0
        if(selectedItem < 0){
            selectedItem = $autocomplete.find('li').length - 1;
        }
        else if(selectedItem > $autocomplete.find('li').length-1 ) {
            selectedItem = 0;
        }
        //首先移除其他列表项的高亮背景，然后再高亮当前索引的背景
        $autocomplete.find('li').removeClass('highlight')
        .eq(selectedItem).addClass('highlight');
    };
    var ajax_request = function(){
        //ajax服务端通信
        $.ajax({
            'url':path+urlPattern, //服务器的地址
            'data':{
                'input_id':$searchInput.val()
            }, //参数
            'dataType':'json', //返回数据类型
            'type':'POST', //请求类型
            'success':function(data){
                if(data.length) {
                    //遍历data，添加到自动完成区
                    $.each(data, function(index,term) {
                        //创建li标签,添加到下拉列表中
                        $('<li></li>').text(term).appendTo($autocomplete)
                        .addClass('clickable')
                        .hover(function(){
                            //下拉列表每一项的事件，鼠标移进去的操作
                            $(this).siblings().removeClass('highlight');
                            $(this).addClass('highlight');
                            selectedItem = index;
                        },function(){
                            //下拉列表每一项的事件，鼠标离开的操作
                            $(this).removeClass('highlight');
                            //当鼠标离开时索引置-1，当作标记
                            selectedItem = -1;
                        })
                        .click(function(){
                            //鼠标单击下拉列表的这一项的话，就将这一项的值添加到输入框中
                            $searchInput.val(term);
                            //清空并隐藏下拉列表
                            $autocomplete.empty().hide();
                        });
                    });//事件注册完毕
                    //设置下拉列表的位置，然后显示下拉列表
                    $autocomplete.css('width',$searchInput.css('width'));
                    setSelectedItem(0);
                    //显示下拉列表
                    $autocomplete.show();
                }
            }
        });
    };
    //对输入框进行事件注册
    $searchInput
    .keyup(function(event) {
        //字母数字，退格，空格
        if(event.keyCode > 40 || event.keyCode == 8 || event.keyCode ==32) {
            //首先删除下拉列表中的信息
            $autocomplete.empty().hide();
            clearTimeout(timeoutid);
            if(!empty($searchInput.val())){
                timeoutid = setTimeout(ajax_request,100);
            }
        }
        else if(event.keyCode == 38){
            //上
            //selectedItem = -1 代表鼠标离开
            if(selectedItem == -1){
                setSelectedItem($autocomplete.find('li').length-1);
            }
            else {
                //索引减1
                setSelectedItem(selectedItem - 1);
            }
            event.preventDefault();
        }
        else if(event.keyCode == 40) {
            //下
            //selectedItem = -1 代表鼠标离开
            if(selectedItem == -1){
                setSelectedItem(0);
            }
            else {
                //索引加1
                setSelectedItem(selectedItem + 1);
            }
            event.preventDefault();
        }
    })
    .keypress(function(event){
        //enter键
        if(event.keyCode == 13) {
            //列表为空或者鼠标离开导致当前没有索引值
            if($autocomplete.find('li').length == 0 || selectedItem == -1) {
                return;
            }
            $searchInput.val($autocomplete.find('li').eq(selectedItem).text());
            $autocomplete.empty().hide();
            event.preventDefault();
        }
    })
    .keydown(function(event){
        //esc键
        if(event.keyCode == 27 ) {
            $autocomplete.empty().hide();
            event.preventDefault();
        }
    });
});
//判空函数
function empty(v){
    switch (typeof v){
        case 'undefined' :
            return true;
        case 'string' :
            if(v.replace(/^\s+|\s+$/g, '').length === 0) return true;
            break;
        case 'number' :
            if(0 === v) return true;
            break;
        case 'object' :
            if(null === v) return true;
            if(undefined !== v.length && v.length===0) return true;
            for(var k in v){
                return false;
            }
            return true;
            break;
    }
    return false; 
}