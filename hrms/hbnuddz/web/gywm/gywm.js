/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(function(){
    $.ajax({
        type:'post',
        dataType:'json',
        url:path+'GywmIndexAction.jsp',
        data:{
            'mode':'SHOWLIST'
        },
        success:function(msg){
            var size=msg.length;
            var data="";
            for(var i=0;i<size;i++){
                data+="<tr><td >"+msg[i].xm+"</td><td>"+msg[i].zwmc+"</td><td>"+msg[i].xj+"</td></tr>";
            }
            $("#xhcy").append(data);
        }
    })
    var lilist=$("#tab li");
    lilist.eq(0).css({
        'background':'#999',
        'color':'red'
    });
    lilist.each(function(key,value){
        $(value).click(function(){
            $("#right .nr").eq(key).css('display','block').siblings(".nr").css('display','none');
            $(this).css({
                'background':'#999',
                'color':'red'
            }).siblings().css({
                "background":"yellowgreen",
                "color":"black"
            });
        })
    })
})

