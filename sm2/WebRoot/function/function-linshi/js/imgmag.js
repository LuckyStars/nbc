
; (function ($) {
    $.fn.imgmag = function (opstions) {
        var seting = {
            show: 0,
            number:0
        };
        var tab_width = 114;
        $.extend(seting, opstions);
        var div = this.next();
        var lis = this.next().find('li').length;
        var uls=this.next().find('li');
        var index = 0;
        var self = this;
        $(this).click(function () {
            if (div.scrollLeft() < (lis - seting.show - 1) * tab_width) {
                $(self).next().removeClass(opstions.show);
                index++;
               
                div.animate({
                    scrollLeft: seting.number * tab_width * index
                }, 300);
            }
            if (div.scrollLeft() == (lis - seting.show - 2) * tab_width) {
                $(self).addClass(opstions.show);
            }

        });
        $(this).prev().click(function () {
            if (index >= 1) {
                $(self).removeClass(opstions.show);
                index--;
                div.animate({
                    scrollLeft: seting.number * tab_width * index
                }, 300);
            }
            if (index == 0) {
                $(this).addClass(opstions.show);
            }
        });

        
    };
}(jQuery));
