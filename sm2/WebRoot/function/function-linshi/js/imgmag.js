
; (function ($) {
    $.fn.imgmag = function (opstions) {
        var seting = {
            show: 0,
            number:0
        };
        $.extend(seting, opstions);
        var div = this.next();
        var lis = this.next().find('li').length;
        var index = 0;
        var self = this;
        $(this).click(function () {
            if (div.scrollLeft() < (lis - seting.show - 1) * 113) {
                $(self).next().removeClass(opstions.show);
                index++;
                console.log(div);
                div.animate({
                	scrollLeft: seting.number*113 * index
                });
            }
            if (div.scrollLeft() == (lis - seting.show - 2) *113) {
                $(self).addClass(opstions.show);
            }

        });
        $(this).prev().click(function () {
            if (index >= 1) {
                $(self).removeClass(opstions.show);
                index--;
                console.log(div);
                div.animate({
                	scrollLeft: seting.number*113 * index
                });
            }
            if (index == 0) {
                $(this).addClass(opstions.show);
            }
        });

    };
}(jQuery));
