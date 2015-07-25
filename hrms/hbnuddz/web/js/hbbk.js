/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//我与环保
$(document).ready(function(){
    var lilist=$(".wyhb li");
    //   alert($(lilist.eq(0).height())) ;
    $(".nr").eq(0).show().siblings().hide();
    if($(".nr").eq(0).height()<=534){
        $(".nr").eq(0).height("534");
    }else{
        $(".nr").eq(0).height($(".nr").eq(0).height()) ;
    }
    $(lilist).eq(0).addClass("one");
    lilist.click(function(){
        $(this).addClass("one").siblings().removeClass("one");
        index=lilist.index(this);
        $(".nr").eq(index).show().siblings().hide();
        if($(".nr").eq(index).height()<=534){
            $(".nr").eq(index).height("534");
        }else{
            $(".nr").eq(index).height($(".nr").eq(index).height()) ;
        }
    })
})
