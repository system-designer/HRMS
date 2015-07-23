jQuery.xxyfocusblur=function(id){
    var inputTag=$(id);
    var defaultText=inputTag.val();
    inputTag.focus(function(){
        var thisvar=$(this).val();
        if(thisvar==defaultText){
            $(this).val("");
        }
    });
    inputTag.blur(function(){
        var thisvar=$.trim($(this).val());
        if(thisvar==""){
            $(this).val(defaultText);
        }
    });
}

jQuery.xxybaidu=function(divid,url,inputid){
    jQuery.xxyfocusblur(inputid);
    $(inputid).keyup(function(){
        $.post(url, {
            keyword:$(inputid).val()
        }, function(data){
            var children=$(divid).children();
            for(var i=0;i<children.length;i++){
                children[i].remove();
            }
            for(var i=0;i<data.length;i++){
                var id="bg"+i;
                $(divid).append("<div id=\""+id+"\" class=\"bg\">"+data[i].keyword+"</div>");
                            
                $("#"+id+"").mouseover(function(){
                    $(this).click(function(){
                        $(inputid).val($(this).text());
                        $(divid).css("visibility","hidden");
                    });
                    $(this).css("background-color", "aquamarine");
                    $(this).css("cursor", "pointer");
                });
                $("#"+id+"").mouseout(function(){
                    $(this).css("background-color", "white");
                });
            }
        }, 'json');
        $(divid).css("visibility","visible");
    });
    $(inputid).focus(function(){
        if($(divid).children().length!=0){
            $(divid).css("visibility","visible");
        }
    });
}




