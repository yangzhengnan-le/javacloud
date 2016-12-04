var resp;
var transError;
function doTranslate() {
    var src=$('#source_text').val();//当前jquery的html()和text()貌似不起作用
    if(!src) return;
    $.ajax({
        type: "POST",
        url: "/api/translate",
        data: {"query":src},
        success: function (data) {
            console.log(data.translateResult);
            resp=data;
            transError=null;
            loading(false);
            $('#translation_text').html(resp.translateResult.google);
        },
        error: function (response) {
            transError=(response.responseText);
            resp=null;
            $('#translation_text').html('');
            loading(false);
            $('#error-msg').html(response.responseText).show();
        }
    });
    //加载中
    loading(true);
}
/**
 *
 * @param start false:加载结束
 */
function loading(start) {
    $('#error-msg').hide();
    if(start){
        $('#translation_wrapper').removeClass('non-loading').addClass('loading');
    }else {
        $('#translation_wrapper').removeClass('loading').addClass('non-loading');
    }
}
function switchSrc(button) {
    $('#recent_translation_tools').children().removeClass('blue');
    $(button).addClass('blue');
    console.log($(button).text());
    if(!transError&&resp){
        $('#translation_text').html(resp.translateResult[$(button).text()]);
    }
}

function responsive() {
        //页脚的屏幕适配问题

        if($(window).height()==$(document).height()){
            $("#footer").addClass("navbar-fixed-bottom");
        }
        else{
            $("#footer").removeClass("navbar-fixed-bottom");
        }
}

/*$(window).ready(responsive).resize(responsive)*/
