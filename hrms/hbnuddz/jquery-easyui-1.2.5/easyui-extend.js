(function($){
    /***
     * accodion效果
     */
    $.fn.accodin = function(parm){
        var alls = $(this).find(">div>span");
        alls.addClass("ui-accodin_div_span");
        $(this).find(">div>div").addClass("ui-accodin_div_div");
        alls.click(function(){
            if($($(this).siblings()[0]).css("display") == "block"){
                $($(this).siblings()[0]).slideUp();
                $(this).css({
                    "font-weight":"100"
                });
            }
            else{
                $(this).parent().siblings().find(">div").slideUp();
                $(this).parent().siblings().find(">span").css({
                    "font-weight":"100"
                });
                $($(this).siblings()[0]).slideDown();
                $(this).css({
                    "font-weight":"600"
                });
            }
        });
    }
})(jQuery);