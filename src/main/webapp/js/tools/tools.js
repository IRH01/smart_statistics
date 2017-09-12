/**
 * Created by jason on 2015/10/26.
 */
(function($){
    $.mediaTools = {
        initForm:function(){

        },changeUploadInputType:function(status,el){
            var that = this;
            var file = $("#"+$(el).data('file'));
            var url = $("#"+$(el).data('url'));
            if(status==0){
                url.show();
                file.hide();
                $(el).attr({value:"使用文件",onclick:"$.mediaTools.changeUploadInputType(1,this)"});
            }else{
                url.hide();
                file.show();
                $(el).attr({value:"使用url",onclick:"$.mediaTools.changeUploadInputType(0,this)"});
            }
        }
    }
})(jQuery);
